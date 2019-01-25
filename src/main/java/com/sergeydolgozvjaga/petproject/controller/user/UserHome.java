package com.sergeydolgozvjaga.petproject.controller.user;

import com.sergeydolgozvjaga.petproject.model.TestResult;
import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.TestResultService;
import com.sergeydolgozvjaga.petproject.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * User's home page servlet
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "UserHome",
        urlPatterns = "/userhome"
)
public class UserHome extends HttpServlet {

    private UserService userService;
    private TestResultService testResultService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        testResultService = new TestResultService();
    }

    /**
     * Loads a set of user test results, by default, sort tests by name
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("loggedUser");
        String typeOfSort = (String) request.getAttribute("sort");

        ArrayList<TestResult> testResults = testResultService.takeSetOfResultsByUserId(currentUser.getUserID());
        if (testResults.isEmpty()) {
            response.sendRedirect("/user/tests");
        } else {
            checkEmptyTypeOfSort(testResults,typeOfSort);
            request.setAttribute("testsResultList", testResults);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/userHome.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void checkEmptyTypeOfSort(ArrayList<TestResult> testResults, String typeOfSort){
        if (typeOfSort == null || typeOfSort.isEmpty()) {
            testResultService.sortTestResults(testResults, "name");
        } else {
            testResultService.sortTestResults(testResults, typeOfSort);
        }
    }
}