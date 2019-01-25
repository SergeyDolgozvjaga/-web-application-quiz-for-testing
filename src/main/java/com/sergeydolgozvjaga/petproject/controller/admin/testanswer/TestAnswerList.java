package com.sergeydolgozvjaga.petproject.controller.admin.testanswer;


import com.sergeydolgozvjaga.petproject.model.TestAnswer;
import com.sergeydolgozvjaga.petproject.service.TestAnswerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "TestAnswerList",
        urlPatterns = "/admin/answer/list"
)
public class TestAnswerList extends HttpServlet {

    private TestAnswerService testAnswerService;
    private List<TestAnswer> testAnswers;

    @Override
    public void init(ServletConfig config) throws ServletException {
        testAnswerService = new TestAnswerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testAnswerList.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer testID = Integer.valueOf(req.getParameter("testID"));
        Integer questionID = Integer.valueOf(req.getParameter("questionID"));

        testAnswers = testAnswerService.getTestAnswersByQuestionID(questionID);
        req.setAttribute("testID", testID);
        req.setAttribute("answerList", testAnswers);
        doGet(req, resp);
    }
}