package com.sergeydolgozvjaga.petproject.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TestSector
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestSector implements Serializable{

    private static final long serialVersionUID = -5101136013037562450L;

    private int testID;
    private String subject;
    private String name;
    private int difficultLevel;
    private int solveTime;
    private ArrayList<TestQuestion> testQuestion;


    public TestSector() {}


    public int getTestID() { return testID; }

    public void setTestID(int testID) { this.testID = testID; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getDifficultLevel() { return difficultLevel; }

    public void setDifficultLevel(int difficultLevel) { this.difficultLevel = difficultLevel; }

    public int getSolveTime() { return solveTime; }

    public void setSolveTime(int solveTime) { this.solveTime = solveTime; }

    public ArrayList<TestQuestion> getTestQuestion() { return testQuestion; }

    public void setTestQuestion(ArrayList<TestQuestion> testQuestion) { this.testQuestion = testQuestion; }

    @Override
    public int hashCode() {
        int result = testID;
        result = 42 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestSector testModule = (TestSector) o;
        if (testModule != null ? !name.equals(testModule.name) : testModule.name != null) return false;
        if (testModule != null ? !name.equals(testModule.subject) : testModule.subject != null) return false;
        if (difficultLevel != 0 ? difficultLevel != testModule.difficultLevel : testModule.difficultLevel != 0) return false;
        return solveTime >= 0 ? solveTime == (testModule.solveTime) : false;
    }

    @Override
    public String toString() {
        return "TestSector{"        +
                "testID="           + testID         +
                ", subject='"       + subject        + '\'' +
                ", name='"          + name           + '\'' +
                ", difficultLevel=" + difficultLevel +
                ", solveTime="      + solveTime      +
                '}';
    }
}
