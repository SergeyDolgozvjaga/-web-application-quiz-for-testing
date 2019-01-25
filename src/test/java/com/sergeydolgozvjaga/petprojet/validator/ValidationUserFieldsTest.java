package com.sergeydolgozvjaga.petprojet.validator;

import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.validator.ValidationUserFields;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;


public class ValidationUserFieldsTest {

    private H2Connector h2Connector = new H2Connector();
    private ValidationUserFields validator = new ValidationUserFields();
    private User user;

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
    public void isLoginValidTest() {
        //GIVEN
        boolean isLoginValidate = true;
        //WHEN
        user = new User();
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
        Assert.assertEquals(isLoginValidate, validator.isLoginValid(user.getLogin()));

    }

    @Test
    public void isLoginInvalidTest() {
        //GIVEN
        boolean isLoginValidate = false;
        //WHEN
        user = new User();
        user.setLogin("1");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(22);
        user.setEmail("usertest@epam.com");
        user.setIsBanned(false);
        user.setRole(Role.USER);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(isLoginValidate, validator.isLoginValid(user.getLogin()));

    }


    @Test
    public void isUserNameValidTest() {
        //GIVEN
        boolean isUserNameValidate = true;
        //WHEN
        user = new User();
        user.setLogin("1");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(22);
        user.setEmail("usertest@epam.com");
        user.setIsBanned(false);
        user.setRole(Role.USER);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(isUserNameValidate, validator.isNameValid(user.getUserName()));
    }

    @Test
    public void isAgeValidTest() {
        //GIVEN
        boolean isAgeValidate = true;
        //WHEN
        user = new User();
        user.setLogin("1");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(22);
        user.setEmail("usertest@epam.com");
        user.setIsBanned(false);
        user.setRole(Role.USER);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(isAgeValidate, validator.isAgeValid(String.valueOf(user.getAge())));
    }

    @Test
    public void isEmailValidTest() {
        //GIVEN
        boolean isEmailValidate = true;
        //WHEN
        user = new User();
        user.setLogin("1");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(22);
        user.setEmail("usertest@epam.com");
        user.setIsBanned(false);
        user.setRole(Role.USER);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(isEmailValidate, validator.isEmailValid(user.getEmail()));
    }

    @Test
    public void isEmailInvalidTest() {
        //GIVEN
        boolean isEmailValidate = false;
        //WHEN
        user = new User();
        user.setLogin("1");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(22);
        user.setEmail("user@test@epam.com");
        user.setIsBanned(false);
        user.setRole(Role.USER);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(isEmailValidate, validator.isEmailValid(user.getEmail()));
    }

    @Test
    public void isAgeInvalidTest() {
        //GIVEN
        boolean isAgeValidate = false;
        //WHEN
        user = new User();
        user.setLogin("1");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(0000000);
        user.setEmail("usertest@epam.com");
        user.setIsBanned(false);
        user.setRole(Role.USER);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(isAgeValidate, validator.isAgeValid(String.valueOf(user.getAge())));
    }

    @Test
    public void isUserNameInvalidTest() {
        //GIVEN
        boolean isUserNameValidate = false;
        //WHEN
        user = new User();
        user.setLogin("1");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("A@r@m@a@n@i");
        user.setAge(22);
        user.setEmail("usertest@epam.com");
        user.setIsBanned(false);
        user.setRole(Role.USER);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(isUserNameValidate, validator.isNameValid(user.getUserName()));
    }
}
