package com.sergeydolgozvjaga.petproject.controller.admin.testquestion;

import com.sergeydolgozvjaga.petproject.service.TestQuestionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Question delete servlet
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "TestQuestionDelete",
        urlPatterns = "/admin/question/delete"
)
public class TestQuestionDelete extends HttpServlet {

    private TestQuestionService testQuestionService;

    @Override
    public void init() throws ServletException {
        testQuestionService = new TestQuestionService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int questionID = Integer.parseInt(request.getParameter("questionID"));
        int testID = Integer.parseInt(request.getParameter("testID"));

        testQuestionService.deleteTestQuestion(questionID);
        response.sendRedirect("/admin/questions?testID=" + testID);
    }
}

