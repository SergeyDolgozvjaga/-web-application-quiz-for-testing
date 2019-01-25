package com.sergeydolgozvjaga.petproject.model;

import java.io.Serializable;

/**
 * User
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2542279845534863621L;

    private int userID;
    private String login;
    private char[] password;
    private String userName;
    private int age;
    private String email;
    private boolean isBanned;
    private Role role;

    public User() {
        password = new char[60];
        age = 0;
        role = Role.USER;
    }

    public int getUserID() { return userID; }

    public void setUserID(int userID) { this.userID = userID; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public char[] getPassword() { return password; }

    public void setPassword(char[] password) { this.password = password; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public boolean getIsBanned() { return isBanned; }

    public void setIsBanned(boolean banned) { isBanned = banned; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = userID;
        result = 42 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{"        +
                ", login='"   + login    + '\'' +
                ", name='"    + userName + '\'' +
                ", age="      + age      +
                ", email='"   + email    + '\'' +
                ", isBanned=" + isBanned +
                ", role="     + role     +
                '}';
    }
}
