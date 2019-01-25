package com.sergeydolgozvjaga.petproject.controller.admin.testanswer;

import com.sergeydolgozvjaga.petproject.service.TestAnswerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Answer create servlet
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "TestAnswerCreate",
        urlPatterns = "/admin/tests/answer/create"
)
public class TestAnswerCreate extends HttpServlet {
    TestAnswerService testAnswerService;

    @Override
    public void init() throws ServletException {
        testAnswerService = new TestAnswerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testID = request.getParameter("testID");
        request.setAttribute("testID", testID);

        String questionID = request.getParameter("questionID");

        if ((testID == null) || (questionID == null)) {
            response.sendRedirect("/admin/tests");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testAnswerCreate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int questionID = Integer.parseInt(request.getParameter("IDQuestion"));
        String answerTheQuestion = request.getParameter("answerQuestion");
        boolean isAnswerCorrect = Boolean.parseBoolean(request.getParameter("isReallyAnswerCorrect"));
        String testID = request.getParameter("IDTest");

        try {
            testAnswerService.createTestAnswer(questionID, answerTheQuestion, isAnswerCorrect);
        } finally {
            if (testAnswerService.createTestAnswer(questionID, answerTheQuestion, isAnswerCorrect) != null) {
                response.sendRedirect("/admin/questions?testID=" + testID);
            } else {response.sendRedirect("/error-pages/databaseError.jsp");}
        }
    }
}


/*

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int questionID = Integer.parseInt(req.getParameter("IDOfQuestion"));
        boolean right = Boolean.parseBoolean(req.getParameter("isRight"));

        String answer = req.getParameter("answer");
        String testID = req.getParameter("IDOfTest");

        myAnswer = service.createAnswer(answer, right, questionID);


    }
* */