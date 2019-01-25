package com.sergeydolgozvjaga.petprojet.service;

import com.sergeydolgozvjaga.petproject.dao.UserDao;
import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import com.sergeydolgozvjaga.petproject.service.UserService;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;

import java.util.List;

public class UserServiceTest {

    private H2Connector h2Connector = new H2Connector();
    private UserService userService;
    private UserDao userDao;
    private User user;

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();

        user = new User();
        userDao = new UserDao();
        userService = new UserService();

        user.setUserID(1);
        user.setLogin("Sergio");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(30);
        user.setEmail("userdaoservicetest@epam.com");
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void getAllUsersOnlyTest(){
        //GIVEN
        int expectedSize = 1;
        //WHEN
        userDao.create(user);
        List<User> users = userService.getAllUsersOnly();
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedSize, users.size());
    }

    @Test
    public void updateUserTest(){
        //GIVEN
        String expectedLogin = "sergio";
        String expectedUserName = "sergio";
        int expectedAge = 30;
        String expectedEmail = "test@epam.com";
        //WHEN
        userDao.create(user);
        userService.updateUser("sergio", "sergio", 30, "test@epam.com", user);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedLogin, userDao.getEntityById(1).getLogin());
        Assert.assertEquals(expectedUserName, userDao.getEntityById(1).getUserName());
        Assert.assertEquals(expectedAge, userDao.getEntityById(1).getAge());
        Assert.assertEquals(expectedEmail, userDao.getEntityById(1).getEmail());
    }

    @Test
    public void deleteUserTest() {
        //GIVEN
        int expectedSize = 1;
        String expectedLogin = "Sergio";
        String expectedUserName = "Armani";
        int expectedAge = 30;
        String expectedEmail = "userdaoservicetest@epam.com";
        //WHEN
        userDao.create(user);
        User user1 = new User();
        user1.setUserID(2);
        user1.setLogin("Antonio");
        char[] pass = {'1', '4', '8', '8'};
        user1.setPassword(pass);
        user1.setUserName("Banderas");
        user1.setAge(31);
        user1.setEmail("userdaoservicetest2@epam.com");
        userDao.create(user1);
        userService.deleteUser(user1);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedSize, userDao.getAll().size());
        Assert.assertEquals(expectedLogin, userDao.getEntityById(1).getLogin());
        Assert.assertEquals(expectedUserName, userDao.getEntityById(1).getUserName());
        Assert.assertEquals(expectedAge, userDao.getEntityById(1).getAge());
        Assert.assertEquals(expectedEmail, userDao.getEntityById(1).getEmail());
    }

    @Test
    public void setRoleAdminTest(){
        //GIVEN
        Role expectedRole = Role.ADMIN;
        //WHEN
        userDao.create(user);
        userService.setRoleAdmin(1, "Sergio");
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedRole, userDao.getEntityById(1).getRole());
    }

    @Test
    public void setRoleUserTest(){
        //GIVEN
        Role expectedRole = Role.USER;
        //WHEN
        userDao.create(user);
        userService.setRoleUser(1, "Sergio");
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedRole, userDao.getEntityById(1).getRole());
    }

    @Test
    public void checkUniqueUserLoginTest(){
        //GIVEN
        boolean expectedLoginExist = true;
        //WHEN
        userDao.create(user);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedLoginExist, userService.checkUniqueUserLogin("Sergio"));
    }

    @Test
    public void changeUserBanStatusByIdTest() {
        //GIVEN
        boolean expectedBanned = true;
        //WHEN
        userDao.create(user);
        userService.changeBannedStatusUserByID(1);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedBanned, userDao.getEntityById(1).getIsBanned());
    }

    @Test
    public void checkUserBanStatusTest(){
        //GIVEN
        boolean expectedBannedUser = true;
        //WHEN
        user.setIsBanned(true);
        userDao.create(user);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedBannedUser, userService.checkUserBanStatus(user));
    }
}
