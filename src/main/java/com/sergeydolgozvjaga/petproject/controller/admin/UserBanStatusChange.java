package com.sergeydolgozvjaga.petproject.controller.admin;

import com.sergeydolgozvjaga.petproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Changes the ban status of a user
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "UserBanStatusChange",
        urlPatterns = "/admin/ban"
)
public class UserBanStatusChange extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UserBanStatusChange.class);
    UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        try {
            userService.changeBannedStatusUserByID(userID);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            response.sendRedirect("/error-pages/notFound.jsp");
        }
        response.sendRedirect("/admin/adminHome.jsp");
    }
}
