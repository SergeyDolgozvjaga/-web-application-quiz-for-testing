package com.sergeydolgozvjaga.petproject.controller.profile;


import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.AccountUserService;
import com.sergeydolgozvjaga.petproject.service.UserService;
import com.sergeydolgozvjaga.petproject.validator.ValidationUserFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Servlet, which deals with users login
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "LoginServlet",
        urlPatterns = "/login"
)
public class Login extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(Login.class);
    private User user;
    private UserService userService;
    private AccountUserService accountUserService;
    private ValidationUserFields validationUserFields;

    @Override
    public void init() throws ServletException {
        user = new User();
        userService = new UserService();
        accountUserService = new AccountUserService();
        validationUserFields = new ValidationUserFields();
    }

    /**
     * Redirects the user to the login page
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Checks the entered user data and creates a user session,
     * sets max interact session time for 10 hours
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        checkValidLoginAndPass(request, response, login, password);

        checkLoginExist(request, response, login);

        checkBannedStatus(request, response, user);

        try {
            user = accountUserService.getUserAccount(request.getParameter("login"), request.getParameter("password"));

            session.setAttribute("loggedUser", user);
            session.setMaxInactiveInterval(10 * 60 * 60);

            response.sendRedirect("/user/userHome.jsp");

        } catch (IllegalArgumentException | IllegalAccessException e) {
            request.setAttribute("error", "incorrect_login_info");
            doGet(request, response);
        } catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOG.error(e.getMessage());
            doGet(request, response);
        }
    }

    private void checkLoginExist(HttpServletRequest request, HttpServletResponse response, String login) throws IOException, ServletException {
        if (userService.checkUniqueUserLogin(login) == false) {
            request.setAttribute("error", "user_by_login_not_found");
            doGet(request, response);
        }
    }

    private void checkValidLoginAndPass(HttpServletRequest request, HttpServletResponse response, String login, String password) throws IOException, ServletException {
        if (!validationUserFields.isLoginValid(login) || !validationUserFields.isPasswordValid(password)) {
            LOG.warn("Invalid characters");
            request.setAttribute("error", "incorrect_login_info");
            doGet(request, response);
        }
    }

    private void checkBannedStatus(HttpServletRequest request, HttpServletResponse response, User user) throws IOException, ServletException {
        if (userService.checkUserBanStatus(user)) {
            request.setAttribute("error", "user_banned");
            doGet(request, response);
        }
    }
}



