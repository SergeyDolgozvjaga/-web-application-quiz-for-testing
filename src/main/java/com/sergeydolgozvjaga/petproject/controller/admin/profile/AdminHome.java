package com.sergeydolgozvjaga.petproject.controller.admin.profile;

import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Shows the home page for users with an administrator role,
 * loads all users who are users and shows them on the page
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "AdminHome",
        urlPatterns = "/admin"
)
public class AdminHome extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> allUsers = userService.getAllUsersOnly();
        request.setAttribute("users", allUsers);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/adminHome.jsp");
        dispatcher.forward(request, response);
    }
}

