package com.sergeydolgozvjaga.petproject.controller.admin.testquestion;

import com.sergeydolgozvjaga.petproject.service.TestQuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Question create servlet
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "TestQuestionCreate",
        urlPatterns = "/admin/tests/questions/create"
)
public class TestQuestionCreate extends HttpServlet {
    private TestQuestionService testQuestionService;

    @Override
    public void init() throws ServletException {
        testQuestionService = new TestQuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testQuestionCreate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String question = request.getParameter("question");
        int testID = Integer.parseInt(request.getParameter("testID"));

        try {
            testQuestionService.createTestQuestion(testID, question);
        } finally {
            if (testQuestionService.createTestQuestion(testID, question) != null) {
                response.sendRedirect("/admin/questions?testID=" + testID);
            } else {
                response.sendRedirect("/error-pages/databaseError.jsp");
            }
        }
    }
}
