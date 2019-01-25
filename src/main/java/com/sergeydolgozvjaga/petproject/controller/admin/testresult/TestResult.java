package com.sergeydolgozvjaga.petproject.controller.admin.testresult;

import com.sergeydolgozvjaga.petproject.service.TestResultService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(
        name = "TestResult",
        urlPatterns = "/admin/result"
)
public class TestResult extends HttpServlet {

    private List<com.sergeydolgozvjaga.petproject.model.TestResult> results;
    private TestResultService testResultService;

    @Override
    public void init() throws ServletException {
        testResultService = new TestResultService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        results = testResultService.getAllTestResults();
        req.setAttribute("results", results);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testResult.jsp");
        dispatcher.forward(req, resp);
    }
}