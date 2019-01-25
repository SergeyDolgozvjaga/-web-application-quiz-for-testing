package com.sergeydolgozvjaga.petproject.service;

import com.sergeydolgozvjaga.petproject.encoder.PasswordDecoder;
import com.sergeydolgozvjaga.petproject.encoder.PasswordEncoder;

import com.sergeydolgozvjaga.petproject.dao.UserDao;
import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Service for user account
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class AccountUserService {

    static final Logger LOG = LoggerFactory.getLogger(AccountUserService.class);
    private UserDao userDao;

    /**
     * First checks the user's data, if all the data is valid, creates a new user
     *
     * @param login    User login
     * @param password User password
     * @param username User name
     * @param age      User age
     * @param email    User email
     * @return Created user
     * @throws IllegalArgumentException Incorrect input data
     * @author Sergey Dolgozvjaga
     * @version 1.0
     */
    public User createUserAccount(String login, String password, String username, String age, String email, Role role)  {

        userDao = new UserDao();
        User user = new User();
        user.setLogin(login);
        String encodedPass = null;
        try {
            encodedPass = PasswordEncoder.passwordEncoder(password);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException |
                IllegalBlockSizeException | InvalidKeyException e) {
            LOG.error(e.getMessage());
        }
        user.setPassword(encodedPass.toCharArray());
        user.setUserName(username);
        user.setAge(Integer.parseInt(age));
        user.setEmail(email);
        user.setRole(role);

        try {
            userDao.create(user);
            return user;
        } finally {
            userDao.closeConnection();
        }
    }

    /**
     * Checks the user's login and pass valid, get user by login and check it's password by hash, if they are same, returns user
     *
     * @param login Logged user login
     * @param password  Logged user password
     * @return User, found in the database
     * @throws IllegalAccessException   Incorrect password
     * @throws IllegalArgumentException Incorrect input data
     */
    public User getUserAccount(String login, String password) throws IllegalAccessException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        userDao = new UserDao();
        try {
            User user = userDao.getEntityByLogin(login);
            String getPassword = String.valueOf(user.getPassword());
            String passwordDecode = String.valueOf(PasswordDecoder.passwordDecoder(getPassword));

            if (password.equals(passwordDecode)) {
                return user;
            } else throw new IllegalAccessException("ValidationPassword is incorrect");
        } catch (Exception e){
            LOG.error(e.getMessage());
            return null;
        }finally {
            userDao.closeConnection();
        }
    }
}
