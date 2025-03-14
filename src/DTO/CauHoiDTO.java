/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Tuong Vy Ha
 */
public class CauHoiDTO {
    private int qID, qTopicID, qStatus;
    private String qContent, qPictures, qLevel;

    public CauHoiDTO() {
    }
    
    
    
    public CauHoiDTO(int qID, String qContent, String qPictures, int qTopicID, String qLevel, int qStatus){
        this.qID = qID;
        this.qTopicID = qTopicID;
        this.qStatus = qStatus;
        this.qContent = qContent;
        this.qPictures = qPictures;
        this.qLevel = qLevel;
    }

    public int getqID() {
        return qID;
    }

    public void setqID(int qID) {
        this.qID = qID;
    }

    public int getqTopicID() {
        return qTopicID;
    }

    public void setqTopicID(int qTopicID) {
        this.qTopicID = qTopicID;
    }

    public int getqStatus() {
        return qStatus;
    }

    public void setqStatus(int qStatus) {
        this.qStatus = qStatus;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public String getqPictures() {
        return qPictures;
    }

    public void setqPictures(String qPictures) {
        this.qPictures = qPictures;
    }

    public String getqLevel() {
        return qLevel;
    }

    public void setqLevel(String qLevel) {
        this.qLevel = qLevel;
    }
}
