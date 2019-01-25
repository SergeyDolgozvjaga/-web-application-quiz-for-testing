package com.sergeydolgozvjaga.petproject.controller.admin.testsector;

import com.sergeydolgozvjaga.petproject.service.TestSectorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Test update servlet
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "TestSectorUpdate",
        urlPatterns = "/admin/tests/update"
)
public class TestSectorUpdate extends HttpServlet {

    private TestSectorService testSectorService;

    @Override
    public void init() throws ServletException {
        testSectorService = new TestSectorService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testID = Integer.valueOf(request.getParameter("testID"));
        request.setAttribute("testID", testID);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testSectorUpdate.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testID = Integer.parseInt(request.getParameter("testID"));
        String testSubject = request.getParameter("subject");
        String testName = request.getParameter("name");
        int testDifficultLevel = Integer.parseInt(request.getParameter("difficultLevel"));
        int solveTime = Integer.parseInt(request.getParameter("solveTime"));
        try {
            testSectorService.updateTestSector(testID, testSubject, testName, testDifficultLevel,  solveTime);
        } finally {
            response.sendRedirect("/admin/testSectorListTests.jsp");
        }
    }
}
