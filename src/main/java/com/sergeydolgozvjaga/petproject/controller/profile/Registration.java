package com.sergeydolgozvjaga.petproject.controller.profile;

import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.AccountUserService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet, which deals with users registration
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "Registration",
        urlPatterns = "/registration"
)
public class Registration extends HttpServlet {

    static final Logger LOG = LoggerFactory.getLogger(Registration.class);
    private User user;
    private UserService userService;
    private AccountUserService accountUserService;
    private ValidationUserFields validationUserFields;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        accountUserService = new AccountUserService();
        validationUserFields = new ValidationUserFields();
    }

    /**
     * Redirects the user to the registration page
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Checks the entered data and creates a new user,
     * sets max interact session time for 10 hours
     */

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String age = request.getParameter("age");
        String email = request.getParameter("email");

        validateUserFields(request, response, login, password, userName, age, email);

        checkUniqueLogin(request, response, login);

        user = accountUserService.createUserAccount(login, password, userName, age, email, Role.USER);

        session.setAttribute("loggedUser", user);
        session.setMaxInactiveInterval(10 * 60 * 60);
        response.sendRedirect("/user/userHome.jsp");


    }

    private void checkUniqueLogin(HttpServletRequest request, HttpServletResponse response, String login) throws IOException, ServletException {
        if (userService.checkUniqueUserLogin(login) == true){
            request.setAttribute("error", "user_with_this_login_exist");
            doGet(request, response);
        }
    }

    private void validateUserFields(HttpServletRequest request, HttpServletResponse response, String login, String password, String userName, String age, String email) throws IOException, ServletException {

        if (!validationUserFields.isLoginValid(login)
                || !validationUserFields.isPasswordValid(password)
                || !validationUserFields.isNameValid(userName)
                || !validationUserFields.isAgeValid(age)
                || !validationUserFields.isEmailValid(email)) {
            LOG.warn("Invalid registration information");
            request.setAttribute("error", "incorrect_insert_data");
            doGet(request, response);
        }
    }
}
