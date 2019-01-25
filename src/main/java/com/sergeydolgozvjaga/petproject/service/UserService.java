package com.sergeydolgozvjaga.petproject.service;

import com.sergeydolgozvjaga.petproject.dao.UserDao;
import com.sergeydolgozvjaga.petproject.encoder.PasswordDecoder;
import com.sergeydolgozvjaga.petproject.encoder.PasswordEncoder;
import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for user
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class UserService {

    static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private User user;
    private UserDao userDao;


    public List<User> getAllUsersOnly() {
        userDao = new UserDao();
        try {
            return userDao
                    .getAll()
                    .stream()
                    .filter(user -> user.getRole().equals(Role.USER))
                    .collect(Collectors.toList());
        } finally { userDao.closeConnection(); }
    }

    public void updateUser(String login, String userName, int age, String email, User currentUser) {
        if (login != null) currentUser.setLogin(login);
        if (userName != null) currentUser.setUserName(userName);
        if (age != 0) currentUser.setAge(age);
        if (email != null) currentUser.setEmail(email);
        userDao = new UserDao();
        try {
            userDao.update(currentUser);
        } finally { userDao.closeConnection(); }
    }

    public boolean deleteUser(User user) {
        userDao = new UserDao();
        try {
            return userDao.delete(user.getUserID());
        } finally { userDao.closeConnection(); }
    }

    public boolean setRoleAdmin(int userID, String login) {
        userDao = new UserDao();
        try {
            if (userID <= 0 || login == null || login.isEmpty()) return false;
            user = userDao.getEntityById(userID);

            if (!(user == null) && user.getLogin().equals(login)) {
                user.setRole(Role.ADMIN);
                userDao.update(user);
                return true;
            } else {
                return false;
            }
        } finally { userDao.closeConnection(); }
    }

    public boolean setRoleUser(int userID, String login) {
        userDao = new UserDao();
        try {
            if (userID <= 0 || login == null || login.isEmpty()) return false;
            user = userDao.getEntityById(userID);

            if (!(user == null) && user.getLogin().equals(login)) {
                user.setRole(Role.USER);
                userDao.update(user);
                return true;
            } else {
                return false;
            }
        } finally { userDao.closeConnection(); }
    }

    public boolean checkUniqueUserLogin(String login) {
        userDao = new UserDao();
        List<User> allUsers = userDao.getAll();

        try{

            for (User user : allUsers) {
                if (user.getLogin().equals(login) == true) {
                    return true;
                } else {
                    return false;
                }
            }
            return Boolean.parseBoolean(null);
        } finally { userDao.closeConnection(); }
    }

    public void changeBannedStatusUserByID(int id) {
        userDao = new UserDao();
        try {
            user = userDao.getEntityById(id);
            if (user.getIsBanned()) {
                userDao.banUserOperation(id, false);
            } else userDao.banUserOperation(id, true);
        } finally { userDao.closeConnection(); }
    }

    public boolean checkUserBanStatus(User user) {
        return user.getIsBanned();
    }

    public void updateUserPassword(String oldPassword,String newPassword, User user) throws IllegalArgumentException {
        userDao = new UserDao();
        try {
            String getPassword = String.valueOf(user.getPassword());
            String passwordDecode = String.valueOf(PasswordDecoder.passwordDecoder(getPassword));
            if (oldPassword.equals(passwordDecode)) {
                String encodedPass =  PasswordEncoder.passwordEncoder(newPassword);
                user.setPassword(encodedPass.toCharArray());
            } else throw new IllegalArgumentException("Incorrect password");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                IllegalBlockSizeException | IllegalArgumentException | BadPaddingException e) {
            LOG.error(e.getMessage());
        } finally { userDao.closeConnection(); }
    }
}

