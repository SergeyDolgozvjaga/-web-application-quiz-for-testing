package com.sergeydolgozvjaga.petprojet.dao;

import com.sergeydolgozvjaga.petproject.dao.UserDao;
import com.sergeydolgozvjaga.petproject.model.User;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.H2Connector;


public class UserDaoTest {

    private User user;
    private UserDao userDao;
    private H2Connector h2Connector = new H2Connector();

    @Before
    public void init() throws ClassNotFoundException, LiquibaseException {
        h2Connector.buildDatabase();
        user = new User();
        userDao = new UserDao();

        user.setLogin("Sergio");
        char[] pass = {'1', '2', '3', '4', '5'};
        user.setPassword(pass);
        user.setUserName("Armani");
        user.setAge(22);
        user.setEmail("userdaotest@epam.com");
    }

    @After
    public void destroy() throws DatabaseException, LockException {
        h2Connector.dropDatabase();
    }

    @Test
    public void getAllTest(){
        //GIVEN
        int expectedSize = 1;
        //WHEN
        user.setLogin("Pablo");
        user.setUserName("Pab");
        user.setAge(37);
        user.setEmail("userdaotest333@epam.com");
        userDao.create(user);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedSize, userDao.getAll().size());
    }

    @Test
    public void getEntityByIdTest() {
        //GIVEN
        String expectedLogin = "Servelat";
        String expectedName = "Servel";
        int expectedAge = 51;
        String expectedEmail = "userdaotest444@epam.com";
        //WHEN
        User user = new User();
        user.setLogin("Servelat");
        user.setUserName("Servel");
        user.setAge(51);
        user.setEmail("userdaotest444@epam.com");
        userDao.create(user);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(expectedLogin, userDao.getEntityById(1).getLogin());
        Assert.assertEquals(expectedName, userDao.getEntityById(1).getUserName());
        Assert.assertEquals(expectedAge, userDao.getEntityById(1).getAge());
        Assert.assertEquals(expectedEmail, userDao.getEntityById(1).getEmail());
    }

    @Test
    public void getEntityByLoginTest() {
        //GIVEN
        String expectedLogin = "Sebastian";
        //WHEN
        user .setLogin("Sebastian");
        userDao.create(user);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedLogin, userDao.getEntityByLogin("Sebastian").getLogin());
    }

    @Test
    public void createTest() {
        //GIVEN
        String expectedLogin = "Anna";
        String expectedName = "Anna777";
        int expectedAge = 19;
        String expectedEmail = "userdaotest@epam.com";
        //WHEN
        user = new User();
        user.setLogin("Anna");
        user.setUserName("Anna777");
        user.setAge(19);
        user.setEmail("userdaotest@epam.com");
        userDao.create(user);
        //THEN
        Assert.assertNotNull(user);
        Assert.assertEquals(expectedLogin, userDao.getEntityById(1).getLogin());
        Assert.assertEquals(expectedName, userDao.getEntityById(1).getUserName());
        Assert.assertEquals(expectedAge, userDao.getEntityById(1).getAge());
        Assert.assertEquals(expectedEmail, userDao.getEntityById(1).getEmail());

    }

    @Test
    public void updateTest(){
        //GIVEN
        String expectedLogin = "Gunter";
        String expectedName = "WhiteMan";
        int expectedAge = 18;
        String expectedEmail = "updatetest@epam.com";
        //WHEN
        userDao.create(user);
        user.setUserID(1);
        user.setLogin("Gunter");
        user.setUserName("WhiteMan");
        user.setAge(18);
        user.setEmail("updatetest@epam.com");
        userDao.update(user);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedLogin, userDao.getEntityById(1).getLogin());
        Assert.assertEquals(expectedName, userDao.getEntityById(1).getUserName());
        Assert.assertEquals(expectedAge, userDao.getEntityById(1).getAge());
        Assert.assertEquals(expectedEmail, userDao.getEntityById(1).getEmail());
    }

    @Test
    public void deleteTest(){
        //GIVEN
        int expectedSize = 1;
        //WHEN
        user.setUserID(1);
        user.setLogin("Gunter");
        user.setUserName("WhiteMan");
        user.setAge(18);
        user.setEmail("deletetest@epam.com");
        userDao.create(user);
        User user2 = new User();
        user2.setUserID(2);
        user2.setLogin("Ganz");
        user2.setUserName("BlackMan");
        user2.setAge(19);
        user2.setEmail("deletetest2@epam.com");
        userDao.create(user2);
        userDao.delete(2);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedSize, userDao.getAll().size());
    }

    @Test
    public void banUserOperationTest(){
        //GIVEN
        boolean expectedBanned = true;
        //WHEN
        userDao.create(user);
        userDao.banUserOperation(1, true);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedBanned, userDao.getEntityById(1).getIsBanned());
    }

    @Test
    public void russianEncodingTest() {
        //GIVEN
        String expectedLogin = "Артем";
        //WHEN
        user.setLogin("Артем");
        userDao.create(user);
        //THEN
        Assert.assertNotNull(userDao);
        Assert.assertEquals(expectedLogin, userDao.getEntityById(1).getLogin());
    }
}
