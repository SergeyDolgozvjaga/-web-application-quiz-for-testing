package com.sergeydolgozvjaga.petproject.controller.admin.testquestion;

import com.sergeydolgozvjaga.petproject.model.TestQuestion;
import com.sergeydolgozvjaga.petproject.service.TestQuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "QuestionList",
        urlPatterns = "/admin/questions"
)
public class TestQuestionListEditor extends HttpServlet {

    private TestQuestionService testQuestionService;

    @Override
    public void init() throws ServletException {
        testQuestionService = new TestQuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int testID = Integer.parseInt(req.getParameter("testID"));

        List<TestQuestion> questionList = testQuestionService.getQuestionsByTestID(testID);
        req.setAttribute("testID", testID);
        req.setAttribute("questions", questionList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testQuestionListEditor.css.jsp");
        dispatcher.forward(req, resp);
    }
}
