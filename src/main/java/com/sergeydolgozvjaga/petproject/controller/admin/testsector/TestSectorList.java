package com.sergeydolgozvjaga.petproject.controller.admin.testsector;


import com.sergeydolgozvjaga.petproject.model.TestSector;
import com.sergeydolgozvjaga.petproject.service.TestSectorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for showing and deleting tests
 *
 * @author Sergey Dolgozvjaga
 * @version 1.1
 */
@WebServlet(
        name = "TestSectorListTests",
        urlPatterns = "/admin/tests"
)
public class TestSectorList extends HttpServlet {

    private TestSectorService testSectorService;

    @Override
    public void init() throws ServletException {
        testSectorService = new TestSectorService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<TestSector> testList = testSectorService.getAllTestSectors();
        request.setAttribute("tests", testList);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/testSectorListTests.jsp");
        dispatcher.forward(request, response);
    }

}
