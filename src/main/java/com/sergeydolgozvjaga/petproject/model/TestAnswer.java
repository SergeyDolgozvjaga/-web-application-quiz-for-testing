package com.sergeydolgozvjaga.petproject.model;

import java.io.Serializable;

/**
 * TestAnswer
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestAnswer implements Serializable {

    private static final long serialVersionUID = -7462894482697640005L;

    private int answerID;
    private int questionID;
    private String answerTheQuestion;
    private boolean isAnswerCorrect;


    public TestAnswer() {}


    public int getAnswerID() { return answerID; }

    public void setAnswerID(int answerID) { this.answerID = answerID; }

    public int getQuestionID() { return questionID; }

    public void setQuestionID(int questionID) { this.questionID = questionID; }

    public String getAnswerTheQuestion() { return answerTheQuestion; }

    public void setAnswerTheQuestion(String answerTheQuestion) { this.answerTheQuestion = answerTheQuestion; }

    public boolean getIsAnswerCorrect() { return isAnswerCorrect; }

    public void setAnswerCorrect(boolean answerCorrect) { isAnswerCorrect = answerCorrect; }

    @Override
    public int hashCode() {
        int result = answerID;
        result = 42 * result + (answerTheQuestion != null ? answerTheQuestion.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAnswer testAnswers = (TestAnswer) o;
        if (answerTheQuestion != null ? !answerTheQuestion.equals(testAnswers.answerTheQuestion) : testAnswers.answerTheQuestion != null) return false;
        if (!isAnswerCorrect == testAnswers.isAnswerCorrect) return false;
        return questionID >= 0 ? questionID == (testAnswers.questionID) : false;
    }

    @Override
    public String toString() {
        return "TestAnswer{"            +
                "answerID="             + answerID          +
                ", questionID="         + questionID        +
                ", answerTheQuestion='" + answerTheQuestion + '\'' +
                ", isAnswerCorrect="    + isAnswerCorrect   +
                '}';
    }
}
