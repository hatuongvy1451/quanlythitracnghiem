/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChuDeDAO;
import DTO.ChuDeDTO;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ChuDeBUS {
    private ChuDeDAO cdDAO= new ChuDeDAO();;
    private ArrayList<ChuDeDTO> listcd=null;
    

    public ChuDeBUS() {
        doclistchude();
    }
    
    public void doclistchude() {
        this.listcd = cdDAO.getListChuDe();
    }
    
    public ArrayList<ChuDeDTO> getListChuDe(){
        if(listcd == null){
            doclistchude();
        }
        return listcd;
    }
    
     public String getTenChuDeTheoID(int tpID){
         return cdDAO.getTenChuDeTheoID(tpID);
     }
     
     public ArrayList<ChuDeDTO> getChuDe(){
        return cdDAO.getChuDe();
     }
     
     public String addTp(ChuDeDTO tp) throws SQLException{
        if(cdDAO.addTopics(tp)){
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }
    public String getTenBH(int ID) {
        for (ChuDeDTO tp : cdDAO.getChuDe()) {
            if (tp.getTpID() == ID) {
                return  tp.getTpTitle();
            }
        }
        return "";
    }
    
    public ArrayList<String> getDanhSachChuDe(){
        return cdDAO.getDanhSachChuDe();
    }
    
}
