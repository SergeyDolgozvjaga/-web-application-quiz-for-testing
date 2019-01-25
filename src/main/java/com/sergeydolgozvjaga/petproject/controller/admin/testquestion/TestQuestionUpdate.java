package com.sergeydolgozvjaga.petproject.controller.admin.testquestion;


import com.sergeydolgozvjaga.petproject.service.TestAnswerService;
import com.sergeydolgozvjaga.petproject.service.TestQuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Question update servlet
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "TestQuestionUpdate",
        urlPatterns = "/admin/question/update"
)
public class TestQuestionUpdate extends HttpServlet {
    private TestQuestionService testQuestionService;
    private TestAnswerService testAnswerService;

    @Override
    public void init() throws ServletException {
        testQuestionService = new TestQuestionService();
        testAnswerService = new TestAnswerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer questionID = Integer.valueOf(request.getParameter("questionID"));
        request.setAttribute("questionID", questionID);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testQuestionUpdate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int questionID = Integer.parseInt(request.getParameter("questionID"));
        int testID = Integer.parseInt(request.getParameter("testID"));
        String question = request.getParameter("question");

        try {
            testQuestionService.updateTestQuestion(questionID, testID, question);
        } finally {
            if (testQuestionService.updateTestQuestion(questionID, testID, question) != null) {
                response.sendRedirect("/admin/questions?testID=" + testID);
            } else { response.sendRedirect("/error-pages/databaseError.jsp"); }
        }
    }
}

