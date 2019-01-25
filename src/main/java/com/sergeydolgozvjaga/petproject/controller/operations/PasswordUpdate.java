package com.sergeydolgozvjaga.petproject.controller.operations;

import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.UserService;
import com.sergeydolgozvjaga.petproject.validator.ValidationUserFields;
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
        name = "PasswordUpdate",
        urlPatterns = "/account/password/update"
)
public class PasswordUpdate extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordUpdate.class);
    private ValidationUserFields validationUserFields;
    private UserService userService;
    private User user;


    @Override
    public void init() throws ServletException {
        user = new User();
        userService = new UserService();
        validationUserFields = new ValidationUserFields();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/passwordUpdate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        user = (User) request.getSession().getAttribute("loggedUser");

        updateUserPassword(request, response, oldPassword, newPassword, user);
        response.sendRedirect("/profile.jsp");
    }


    private void checkNewPassword(HttpServletRequest request, HttpServletResponse response, String newPassword) throws ServletException, IOException {
        if (!validationUserFields.isPasswordValid(newPassword)) {
            request.setAttribute("error", "database_error");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/passwordUpdate.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateUserPassword(HttpServletRequest request, HttpServletResponse response, String oldPassword, String newPassword, User user) throws ServletException, IOException {
        if (oldPassword != null && newPassword != null) {
            try {
                checkNewPassword(request, response, newPassword);
                userService.updateUserPassword(oldPassword, newPassword, user);
            }  catch (IllegalArgumentException e) {
                LOG.error(e.getMessage());
                request.setAttribute("error", "password_old_not_work");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/passwordUpdate.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
