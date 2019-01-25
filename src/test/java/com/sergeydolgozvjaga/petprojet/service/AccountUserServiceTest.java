package com.sergeydolgozvjaga.petprojet.service;

import com.sergeydolgozvjaga.petproject.dao.UserDao;
import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.AccountUserService;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class AccountUserServiceTest {

    private H2Connector h2Connector = new H2Connector();
    private UserDao userDao;
    private AccountUserService accountUserService;
    private User user;

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();

        user = new User();
        userDao = new UserDao();
        accountUserService = new AccountUserService();

        user.setUserID(1);
        user.setLogin("Sergio");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(30);
        user.setEmail("accUserServiceTest@epam.com");
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }


    @Test
    public void createUserAccount(){
        //GIVEN
        String expectedLogin = "Sergio";
        String expectedUserName = "Armani";
        int expectedAge = 30;
        String expectedEmail = "accUserServiceTest@epam.com";
        Role expectedRole = Role.USER;

        //WHEN
        user = accountUserService.createUserAccount("Sergio", "12345",
                "Armani","30" , "accUserServiceTest@epam.com", Role.USER);
        //THEN
        Assert.assertNotNull(accountUserService);
        Assert.assertEquals(expectedLogin, user.getLogin());
        Assert.assertEquals(expectedUserName, user.getUserName());
        Assert.assertEquals(expectedAge, user.getAge());
        Assert.assertEquals(expectedEmail, user.getEmail());
        Assert.assertEquals(expectedRole, user.getRole());
    }


}
