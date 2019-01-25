package com.sergeydolgozvjaga.petproject.controller.operations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;


@WebServlet(
        name = "ChangeLocale",
        urlPatterns = "/change-locale"
)
public class ChangeLocale extends HttpServlet {

    private Locale locale;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("EN") != null) {
            locale = new Locale("en");
            request.getSession().setAttribute("LOCALE", locale);
        } else if (request.getParameter("RU") != null) {
            locale = new Locale("ru");
            request.getSession().setAttribute("LOCALE", locale);
        } else if (request.getParameter("UA") != null) {
            locale = new Locale("ua");
            request.getSession().setAttribute("LOCALE", locale);
        } else if (request.getParameter("DE") != null) {
            locale = new Locale("de");
            request.getSession().setAttribute("LOCALE", locale);
        } else {
            response.sendRedirect("/error-pages/badRequest.jsp");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}

