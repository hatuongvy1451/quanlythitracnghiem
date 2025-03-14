/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author HP
 */
public class KetQuaDTO {
    private int rs_num;
    private int userID;
    private String exCode;
    private String rs_anwsers;
    private float rs_mask;
    private String date;

    public KetQuaDTO() {
    }

    public KetQuaDTO(int rs_num, int userID, String exCode, String rs_anwsers, float rs_mask, String date) {
        this.rs_num = rs_num;
        this.userID = userID;
        this.exCode = exCode;
        this.rs_anwsers = rs_anwsers;
        this.rs_mask = rs_mask;
        this.date = date;
    }

    public int getRs_num() {
        return rs_num;
    }

    public void setRs_num(int rs_num) {
        this.rs_num = rs_num;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getRs_anwsers() {
        return rs_anwsers;
    }

    public void setRs_anwsers(String rs_anwsers) {
        this.rs_anwsers = rs_anwsers;
    }

    public float getRs_mask() {
        return rs_mask;
    }

    public void setRs_mask(float rs_mask) {
        this.rs_mask = rs_mask;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "KetQuaDTO{" + "rs_num=" + rs_num + ", userID=" + userID + ", exCode=" + exCode + ", rs_anwsers=" + rs_anwsers + ", rs_mask=" + rs_mask + ", date=" + date + '}';
    }

    
      
}
