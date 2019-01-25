package com.sergeydolgozvjaga.petproject.controller.admin.testsector;

import com.sergeydolgozvjaga.petproject.service.TestSectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Creates test module
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(

        name = "TestSectorCreate",
        urlPatterns = "/admin/tests/create"
)
public class TestSectorCreate extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TestSectorCreate.class);
    private TestSectorService testSectorService;

    @Override
    public void init() throws ServletException {
        testSectorService = new TestSectorService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testSectorCreate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String testSubject = request.getParameter("subject");
        String testName = request.getParameter("name");
        int testDifficultLevel = Integer.parseInt(request.getParameter("difficultLevel"));
        int timeSolveTime = Integer.parseInt(request.getParameter("solveTime"));

        try {
            testSectorService.createTestSector(testSubject, testName, testDifficultLevel, timeSolveTime);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            response.sendRedirect("/error-pages/databaseError.jsp");
        }
        response.sendRedirect("/admin/testSectorListTests.jsp");
    }
}
