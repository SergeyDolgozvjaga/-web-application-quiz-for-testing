package com.sergeydolgozvjaga.petproject.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sort tests
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "AllTestsSortServlet",
        urlPatterns = "/user/tests/sorting"
)
public class AllTestsSort extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/user/userHome.jsp";

        String typeOfSort = request.getParameter("sort");
        request.getSession().setAttribute("typeOfSort", typeOfSort);
        response.sendRedirect(url);
    }
}
