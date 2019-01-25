package com.sergeydolgozvjaga.petproject.web.filter;

import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Filter that processes incoming administrator requests
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebFilter(
        urlPatterns = "/admin/*",
        filterName = "AdminFilter",
        description = "Filter all admin URLs"
)
public class AdminAccessFilter implements Filter {

    private List<String> adminAccessPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminAccessPath = Arrays.asList("admin", "tests");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        String pathWithParameters = StringUtils.substringAfterLast(uri, "/");
        String path = StringUtils.substringBefore(pathWithParameters, "?");

        if (!adminAccessPath.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("loggedUser");

        if (user != null && user.getRole().equals(Role.ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");
    }

    @Override
    public void destroy() {
        adminAccessPath = null;
    }
}
