package com.sergeydolgozvjaga.petproject.model;

import java.io.Serializable;

/**
 * TestResult
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class TestResult implements Serializable{

    private static final long serialVersionUID = 7454341023908685827L;

    private int resultID;
    private int userID;
    private int testID;
    private boolean isTestDone;
    private int resultPoints;
    private TestSector testSector;


    public TestResult() {}


    public int getResultID() { return resultID; }

    public void setResultID(int resultID) { this.resultID = resultID; }

    public int getUserID() { return userID; }

    public void setUserID(int userID) { this.userID = userID; }

    public int getTestID() { return testID; }

    public void setTestID(int testID) { this.testID = testID; }

    public boolean getIsTestDone() { return isTestDone; }

    public void setIsTestDone(boolean done) { isTestDone = done; }

    public int getResultPoints() { return resultPoints; }

    public void setResultPoints(int resultPoints) { this.resultPoints = resultPoints; }

    public TestSector getTestSector() { return testSector; }

    public void setTestSector(TestSector testSector) { this.testSector = testSector; }

    @Override
    public int hashCode() {
        int result = resultID;
        result = 42 * result + (testSector != null ? testSector.hashCode() : 0);
        result += 42 * userID;
        result += 42 * testID;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResult testResult = (TestResult) o;
        if (userID != 0 ? userID != testResult.userID : testResult.userID != 0) return false;
        if (testID != 0 ? testID != testResult.testID : testResult.testID != 0) return false;
        return resultPoints >= 0 ? resultPoints == (testResult.resultPoints) : false;
    }

    @Override
    public String toString() {
        return "Test result {" +
                "result id='"  + resultID     + '\'' +
                ", user id='"  + userID       + '\'' +
                ", test id='"  + testID       + '\'' +
                ", is done='"  + isTestDone   + '\'' +
                ", points='"   + resultPoints + '\'' +
                '}';
    }
}
