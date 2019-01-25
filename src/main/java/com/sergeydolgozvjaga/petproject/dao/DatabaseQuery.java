package com.sergeydolgozvjaga.petproject.dao;

/**
 * All queries to database
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public enum DatabaseQuery {

    USER_BANNED("update users set is_banned = ? where userID = ?"),
    USER_CREATE("insert into users (login, password, user_name, age, email, role) values (?, ?, ?, ?, ?, ?)"),
    USER_GET_ALL("select * from users"),
    USER_GET_ENTITY_BY_ID("select * from users where userID = ?"),
    USER_GET_PASSWORD_BY_ID("select password from users where userID = ?"),
    USER_GET_ENTITY_BY_LOGIN("select * from users where login = ?"),
    USER_UPDATE("update users set login = ? , password = ?, user_name = ?, age = ?, email = ?, role = ? where userID = ?"),
    USER_UPDATE_PASSWORD_BY_ID("update users set password = ? where userID = ?"),
    USER_DELETE_BY_ID("delete from users where userID = ?"),

    TESTSECTOR_GET_ALL("select * from tests"),
    TESTSECTOR_GET_BY_ID("select * from tests where testID = ?"),
    TESTSECTOR_UPDATE("update tests set subject = ?, name = ?, difficult_level = ?, solve_time = ? where testID = ?"),
    TESTSECTOR_CREATE("insert into tests (subject, name, difficult_level, solve_time) values (?, ?, ?, ?)"),
    TESTSECTOR_DELETE_BY_ID("delete from tests where testID = ?"),

    TESTRESULT_GET_ALL("select * from results"),
    TESTRESULT_GET_ENTITY_BY_ID("select * from results where resultID = ?"),
    TESTRESULT_UPDATE("update results set userID = ?, testID = ?, is_test_done = ?, result_points = ? where resultID = ?"),
    TESTRESULT_CREATE("insert into results (userID, testID, is_test_done, result_points) values (?, ?, ?, ?)"),
    TESTRESULT_DELETE_BY_ID("delete from results where resultID = ?"),
    TESTRESULT_RESULTS_BY_ID("select * from results where userID = ?"),

    TESTQUESTION_GET_ALL("select * from questions"),
    TESTQUESTION_GET_BY_ID("select * from questions where questionID = ?"),
    TESTQUESTION_GET_BY_TESTID("select * from questions where testID = ?"),
    TESTQUESTION_UPDATE("update questions set testID = ?, question = ? where questionID = ?"),
    TESTQUESTION_CREATE("insert into questions (testID, question) values (?, ?)"),
    TESTQUESTION_DELETE_BY_ID("delete from questions where questionID = ?"),

    TESTANSWER_GET_ALL("select * from answers"),
    TESTANSWER_GET_ENTITY_BY_ID("select * from answers where answerID = ?"),
    TESTANSWER_GET_ANSWERS_BY_QUESTIONID("select * from answers where questionID = ?"),
    TESTANSWER_UPDATE("update answers set questionID = ?, answer_the_question = ?, is_answer_correct = ? where answerID = ?"),
    TESTANSWER_CREATE("insert into answers (questionID, answer_the_question, is_answer_correct) values (?, ?, ?)"),
    TESTANSWER_DELETE_BY_ID("delete from answers where answerID = ?");


    private String query;

    DatabaseQuery(String query) {
        this.query = query;
    }

    public String query() {
        return query;
    }
}
