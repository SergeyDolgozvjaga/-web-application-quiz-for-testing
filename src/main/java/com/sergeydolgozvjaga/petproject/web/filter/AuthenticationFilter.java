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
 * Filter that processes all incoming requests,
 * if user is not authorized, redirects him to login page
 *
 * @author Sergey Dolgozvajga
 * @version 1.0
 */
@WebFilter(
        urlPatterns = "/*",
        filterName = "AuthenticationFilter"
)
public class AuthenticationFilter implements Filter {

    List<String> accessPath;
    List<String> blockedPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        accessPath = Arrays.asList("userhome", "profile", "sorting", "tests");
        blockedPath = Arrays.asList("login", "registration", "");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();

        String pathWithParameters = StringUtils.substringAfterLast(uri, "/");
        String path = StringUtils.substringBefore(pathWithParameters, "?");

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        User user = (User) session.getAttribute("loggedUser");

        if (blockedPath.contains(path) && user != null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/user/userHome.jsp");
            return;
        }

        if (user != null) {
            if (user.getRole().equals(Role.ADMIN)) {
                if (uri.matches("(.*)userhome(.*)")) {
                    ((HttpServletResponse) servletResponse).sendRedirect("/admin");
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!accessPath.contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        ((HttpServletResponse) servletResponse).sendRedirect("/login.jsp");
    }

    @Override
    public void destroy() {
        accessPath = null;
        blockedPath = null;
    }
}