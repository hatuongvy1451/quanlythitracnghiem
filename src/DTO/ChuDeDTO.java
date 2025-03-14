/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author nhu
 */
public class ChuDeDTO {
    private int tpID;
    private String tpTitle;
    private int tpParentID;
    private int tpStatus;

    public ChuDeDTO() {
    }

    public ChuDeDTO(int tpID, String tpTitle, int tpParentID, int tpStatus) {
        this.tpID = tpID;
        this.tpTitle = tpTitle;
        this.tpParentID = tpParentID;
        this.tpStatus = tpStatus;
    }
    
    

    public int getTpID() {
        return tpID;
    }

    public int getTpParentID() {
        return tpParentID;
    }

    public void setTpParentID(int tpParentID) {
        this.tpParentID = tpParentID;
    }

    public void setTpID(int tpID) {
        this.tpID = tpID;
    }

    public String getTpTitle() {
        return tpTitle;
    }

    public void setTpTitle(String tpTitle) {
        this.tpTitle = tpTitle;
    }

    public int getTpStatus() {
        return tpStatus;
    }

    public void setTpStatus(int tpStatus) {
        this.tpStatus = tpStatus;
    }
    
}
