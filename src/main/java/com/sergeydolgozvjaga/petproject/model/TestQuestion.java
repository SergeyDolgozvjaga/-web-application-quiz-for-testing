package com.sergeydolgozvjaga.petproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * TestQuestion
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestQuestion implements Serializable {

    private static final long serialVersionUID = 8263620259119540272L;

    private int questionID;
    private int testID;
    private String question;
    private List<TestAnswer> testAnswers;


    public TestQuestion() {}




    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public List<TestAnswer> getTestAnswers() { return testAnswers; }

    public void setTestAnswers(List<TestAnswer> testAnswers) {this.testAnswers = testAnswers;}

    @Override
    public int hashCode() {
        int result = questionID;
        result = 42 * result + (question != null ? question.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestion testQuestion = (TestQuestion) o;
        if (question != null ? !question.equals(testQuestion.question) : testQuestion.question != null) return false;
        return testID >= 0 ? testID == (testQuestion.testID) : false;
    }

    @Override
    public String toString() {
        return "TestQuestion{" +
                "question id=" + questionID +
                ", test id="   + testID     +
                ", question='" + question   + '\'' +
                '}';
    }
}
