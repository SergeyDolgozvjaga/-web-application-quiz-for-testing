package com.sergeydolgozvjaga.petprojet.model;

import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

public class UserTest {

    private User user;
    private H2Connector h2Connector = new H2Connector();

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        user = new User();
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void userTest(){
        //GIVEN
        String expectedLogin = "Sergio";
        String expectedUserName = "Armani";
        int expectedAge = 22;
        String expectedEmail = "usertest@epam.com";
        boolean expectedBanned = false;
        Role expectedRole = Role.USER;
        //WHEN
        user.setLogin("Sergio");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(22);
        user.setEmail("usertest@epam.com");
        user.setIsBanned(false);
        user.setRole(Role.USER);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(expectedLogin, user.getLogin());
        Assert.assertEquals(expectedUserName, user.getUserName());
        Assert.assertEquals(expectedAge, user.getAge());
        Assert.assertEquals(expectedEmail, user.getEmail());
        Assert.assertEquals(expectedBanned, user.getIsBanned());
        Assert.assertEquals(expectedRole, user.getRole());
    }
}
