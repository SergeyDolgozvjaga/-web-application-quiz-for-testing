package com.sergeydolgozvjaga.petproject.controller.admin.profile;

import com.sergeydolgozvjaga.petproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "CreateNewAdmin",
        urlPatterns = "/admin/create"
)
public class CreateNewAdmin extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CreateNewAdmin.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/createNewAdmin.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userID = Integer.valueOf(request.getParameter("userID"));
        String login = request.getParameter("login");
        setAdminRole(request, response, userID, login);
    }

    private void setAdminRole(HttpServletRequest request, HttpServletResponse response, Integer userID, String login) throws ServletException, IOException {
        if (!userService.setRoleAdmin(userID, login)) {
            LOG.warn("User login and id are not same!");
            request.setAttribute("error", "id_and_login_are_different");
            doGet(request, response);
        } else response.sendRedirect("/admin/adminHome.jsp");
    }
}