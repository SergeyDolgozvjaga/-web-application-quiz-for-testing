package com.sergeydolgozvjaga.petproject.controller.profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet, that logout user
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "Logout",
        urlPatterns = "/logout"
)
public class Logout extends HttpServlet {

    /**
     * Deletes a user session and redirects to login page
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        response.sendRedirect("/login.jsp");
    }
}
