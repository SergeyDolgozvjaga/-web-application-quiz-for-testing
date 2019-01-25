package com.sergeydolgozvjaga.petproject.controller.admin.testsector;

import com.sergeydolgozvjaga.petproject.service.TestSectorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "TestSectorDelete",
        urlPatterns = "/admin/tests/delete"
)
public class TestSectorDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int testID = Integer.parseInt(req.getParameter("testID"));
        TestSectorService testSectorService = new TestSectorService();
        testSectorService.deleteTestSector(testID);
        resp.sendRedirect("/admin/testSectorListTests.jsp");
    }
}