package com.sergeydolgozvjaga.petproject.controller.user;

import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.ResultCountService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;


@WebServlet(
        name = "UserResults",
        urlPatterns = "/home/testing/result"
)
public class UserResult extends HttpServlet {

    private ResultCountService resultCountService;
    private int answerSize;
    private int correctValue;
    private double percentAmount;

    @Override
    public void init() throws ServletException {
        resultCountService = new ResultCountService();
        answerSize = 0;
        correctValue = 0;
        percentAmount = 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/userResult.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Integer> correctAnswers = (List<Integer>) request.getSession().getAttribute("correctAnswers");
        Enumeration answers = request.getParameterNames();

        while (answers.hasMoreElements()) {
            String param = (String) answers.nextElement();
            Integer var = Integer.valueOf(param);
            answerSize++;
            for (Integer correctAnswer : correctAnswers) {
                if (var.equals(correctAnswer)) {
                    correctValue++;
                }
            }
        }


        if (correctAnswers != null) {
            percentAmount = resultCountService.getPercentAmount(answerSize, correctAnswers.size(), correctValue);
        } else {
            response.sendRedirect("/error-pages/databaseError.jsp");
        }


        request.setAttribute("percentAmount", percentAmount);
        User user = (User) request.getSession().getAttribute("loggedUser");
        Integer testID = (Integer) (request.getSession().getAttribute("testID"));

        resultCountService.setResult(user.getUserID(), testID, percentAmount, correctValue);

        doGet(request, response);
    }
}


