package com.sergeydolgozvjaga.petproject.controller.user;


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
 * Test list servlet
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
@WebServlet(
        name = "UserTests",
        urlPatterns = "/user/tests"
)
public class UserTests extends HttpServlet {

    private TestSectorService testSectorService;

    @Override
    public void init() throws ServletException {
        testSectorService = new TestSectorService();
    }

    /**
     * Loads a set of user test sectors
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String typeOfSort = request.getParameter("sort");

        checkEmptyTypeOfSort(typeOfSort);

        request.setAttribute("allTests", checkEmptyTypeOfSort(typeOfSort));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/userTests.jsp");
        dispatcher.forward(request, response);
    }


    private List<TestSector> checkEmptyTypeOfSort(String typeOfSort){
        List<TestSector> testSectorList = testSectorService.getAllTestSectors();

        if ((typeOfSort != null) && (!typeOfSort.isEmpty())) {
            testSectorService.sortTestResults(testSectorList, typeOfSort);
        }
        return testSectorList;
    }
}


