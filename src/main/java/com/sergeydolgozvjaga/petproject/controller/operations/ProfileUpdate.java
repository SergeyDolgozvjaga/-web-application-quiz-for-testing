package com.sergeydolgozvjaga.petproject.controller.operations;

import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ProfileUpdate",
        urlPatterns = "/profile/update"
)
public class ProfileUpdate extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/profileUpdate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String userName = request.getParameter("userName");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        User user = (User) request.getSession().getAttribute("loggedUser");

        userService.updateUser(login, userName, age, email, user);

        response.sendRedirect("/profile.jsp");
    }
}
