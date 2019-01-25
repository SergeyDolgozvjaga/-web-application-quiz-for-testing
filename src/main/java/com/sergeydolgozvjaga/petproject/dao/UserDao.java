package com.sergeydolgozvjaga.petproject.dao;

import com.sergeydolgozvjaga.petproject.model.Role;
import com.sergeydolgozvjaga.petproject.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.CharArrayReader;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Realization DAO pattern for User
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class UserDao extends AbstractDao<User, Integer> {

    static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
    private ResultSet resultSet;

    public UserDao() {}

    @Override
    public ArrayList<User> getAll() {

        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_GET_ALL.query())) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setUserName(resultSet.getString(4));
                user.setAge(resultSet.getInt(5));
                user.setEmail(resultSet.getString(6));
                user.setIsBanned(resultSet.getBoolean(7));
                user.setRole(Role.valueOf(resultSet.getString(8)));
                users.add(user);
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return users;
    }

    @Override
    public User getEntityById(Integer id) {

        User user = new User();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_GET_ENTITY_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUserID(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3).toCharArray());
                user.setUserName(resultSet.getString(4));
                user.setAge(resultSet.getInt(5));
                user.setEmail(resultSet.getString(6));
                user.setIsBanned(resultSet.getBoolean(7));
                user.setRole(Role.valueOf(resultSet.getString(8)));
            }
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return user;
    }

    public User getEntityByLogin(String login) {

        User user = new User();

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_GET_ENTITY_BY_LOGIN.query())) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setUserID(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3).toCharArray());
            user.setUserName(resultSet.getString(4));
            user.setAge(resultSet.getInt(5));
            user.setEmail(resultSet.getString(6));
            user.setIsBanned(resultSet.getBoolean(7));
            user.setRole(Role.valueOf(resultSet.getString(8)));
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return user;
    }

    @Override
    public boolean create(User entity) {

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_CREATE.query())) {

            Reader reader = new CharArrayReader(entity.getPassword());
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setCharacterStream(2, reader);
            preparedStatement.setString(3, entity.getUserName());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getRole().name());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    @Override
    public User update(User entity) {

        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_UPDATE.query())) {

            Reader reader = new CharArrayReader(entity.getPassword());

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setCharacterStream(2, reader);
            preparedStatement.setString(3, entity.getUserName());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getRole().name());
            preparedStatement.setInt(7, entity.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_DELETE_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    public boolean banUserOperation(int id, boolean ban) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_BANNED.query())) {
            preparedStatement.setBoolean(1, ban);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

    public char[] getPassword(int id) {
        char[] password = null;
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_GET_PASSWORD_BY_ID.query())) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            password = resultSet.getString(1).toCharArray();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return password;
    }

    public boolean updatePassword(int id, char[] pass) {
        try (PreparedStatement preparedStatement = openPrepareStatement(DatabaseQuery.USER_UPDATE_PASSWORD_BY_ID.query())) {
            preparedStatement.setCharacterStream(1, new CharArrayReader(pass));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlEx){LOG.error(sqlEx.getMessage());}
        return true;
    }

}
