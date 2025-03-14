/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.BaiThiDAO;
import DTO.BaiThiDTO;
import java.util.ArrayList;

/**
 *
 * @author Thu Huyền
 */
public class BaiThiBUS {
    BaiThiDAO btDAO = new BaiThiDAO();
    ArrayList<BaiThiDTO> listbt = null;

    public BaiThiBUS() {
        docdanhsach();
    }
    
    public void docdanhsach(){
        this.listbt = btDAO.getListBaiThi();
        
    }
    
    public ArrayList<BaiThiDTO> getListBaiThi(){
        if(listbt ==null){
            docdanhsach();
        }
        return this.listbt;
    }
    
    public ArrayList<String> getListBaithi(){
        return btDAO.getListBaithi();
    }
    
    public String getTestTimeTheoTestCode(String testCode){
        return btDAO.getTestTimeTheoTestCode(testCode);
    }
    
    public ArrayList<String> getDeThiTheoTestCode(String testCode){
        return btDAO.getDeThiTheoTestCode(testCode);
    }
    
    public boolean addBaiThi(BaiThiDTO bt){
        return btDAO.addBaiThi(bt);
    }
    
    public String getTestCodeCuoCungTrongBang(){
        return btDAO.getTestCodeCuoCungTrongBang();
    }
    
    public ArrayList<String> getIdTpByTestCode(String testCode){
        return btDAO.getIdTpByTestCode(testCode);
    }
    
}
