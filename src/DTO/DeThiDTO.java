/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Tuong Vy Ha
 */
public class DeThiDTO {
    private int testID, topicID, testTime, testLimit, num_easy, num_medium, num_diff;
    private String testCode, testTitle, exOrder, exCode, testStatus, testDate;

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    public int getTestLimit() {
        return testLimit;
    }

    public void setTestLimit(int testLimit) {
        this.testLimit = testLimit;
    }

    public int getNum_easy() {
        return num_easy;
    }

    public void setNum_easy(int num_easy) {
        this.num_easy = num_easy;
    }

    public int getNum_medium() {
        return num_medium;
    }

    public void setNum_medium(int num_medium) {
        this.num_medium = num_medium;
    }

    public int getNum_diff() {
        return num_diff;
    }

    public void setNum_diff(int num_diff) {
        this.num_diff = num_diff;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }
    private String[] ex_quesIDs;

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int getTestID() {
        return testID;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getExOrder() {
        return exOrder;
    }

    public void setExOrder(String exOrder) {
        this.exOrder = exOrder;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String[] getEx_quesIDs() {
        return ex_quesIDs;
    }

    public void setEx_quesIDs(String[] ex_quesIDs) {
        this.ex_quesIDs = ex_quesIDs;
    }
}
