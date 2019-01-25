package com.sergeydolgozvjaga.petproject.controller.user;


import com.sergeydolgozvjaga.petproject.service.TestQuestionService;
import com.sergeydolgozvjaga.petproject.service.TestSectorService;
import com.sergeydolgozvjaga.petproject.model.TestAnswer;
import com.sergeydolgozvjaga.petproject.model.TestQuestion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User testing servlet. Shows testing page and assesses the results of the test
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "Testing",
        urlPatterns = "/testing"
)
public class Testing extends HttpServlet {

    private TestSectorService testSectorService;
    private TestQuestionService testQuestionService;


    @Override
    public void init() throws ServletException {
        testSectorService = new TestSectorService();
        testQuestionService = new TestQuestionService();
    }

    /**
     * Shows a testing page
     *
     * @param request  Parameter "testResultID" it's a id of testResult that been opened by user,
     *                 this indicates that the user has updated the page with the purpose of cheating
     * @param response Testing page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/userTesting.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Analyzes the test result
     *
     * @param request  Parameter "testResultID" it's a id of testResult that been opened by user,
     *                 array of parameters "answer", which contains id's of answers, which user chose
     * @param response Redirect to user home page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testID = Integer.parseInt(request.getParameter("testID"));
        listTestQuestionsEmptyCheck(testID, request, response);
        doGet(request, response);
    }

    private List<Integer> correctAnswersList(List<TestQuestion> testQuestions, HttpServletResponse response) throws ServletException, IOException{
        List<Integer> corAnswersList = new ArrayList<>();

        for (TestQuestion testQuestion : testQuestions) {
            if (testQuestion.getTestAnswers() != null) {
                for (TestAnswer testAnswer : testQuestion.getTestAnswers()) {
                    if (testAnswer != null && testAnswer.getIsAnswerCorrect()) {
                        corAnswersList.add(testAnswer.getAnswerID());
                    }
                }
            } else {
                response.sendRedirect("/error-pages/databaseError.jsp");
            }
        }
        return corAnswersList;
    }

    private List<TestQuestion> listTestQuestionsEmptyCheck(int testID, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TestQuestion> testQuestions = testQuestionService.getQuestionsByTestID(testID);

        if ((testQuestions != null) && (!testQuestions.isEmpty())) {
            testQuestions.forEach(question -> {
                testQuestionService.addAnswersToQuestion(question);
            });
            correctAnswersList(testQuestions, response);
            request.setAttribute("questions", testQuestions);
            request.setAttribute("test", testSectorService.getTestSector(testID));
            request.getSession().setAttribute("testID", testID);
            request.getSession().setAttribute("correctAnswers", correctAnswersList(testQuestions, response));
        }
        return testQuestions;
    }
}


