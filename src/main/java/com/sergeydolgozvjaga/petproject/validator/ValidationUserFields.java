package com.sergeydolgozvjaga.petproject.validator;

import java.util.regex.Pattern;

/**
 * Validates the fields of User
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class ValidationUserFields {


    // patterns for validate fields of User
    private static final String LOGIN_PATTERN = "^[\\w0-9]{3,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String NAME_PATTERN = "[\\w]{3,20}$";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public boolean isLoginValid(String login) {
        if (login == null || login.isEmpty()) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(LOGIN_PATTERN);
            return pattern.matcher(login).matches();
        }
    }

    public boolean isPasswordValid(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            return pattern.matcher(password).matches();
        }
    }

    public boolean isNameValid(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(NAME_PATTERN);
            return pattern.matcher(name).matches();
        }
    }

    public boolean isAgeValid(String age) {
        if (age == null || age.isEmpty()) {
            return false;
        } else {
            int intAge = Integer.parseInt(age);
            if (intAge <= 5 || intAge >= 95) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            return pattern.matcher(email).matches();
        }
    }
}
