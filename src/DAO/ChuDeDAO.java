/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChuDeDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ChuDeDAO {
    
    
    public ArrayList<ChuDeDTO> getListChuDe() {
        try {
            ArrayList<ChuDeDTO> ds = new ArrayList<>();
            String sql = "SELECT * FROM topics";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChuDeDTO cd = new ChuDeDTO();
                cd.setTpID(rs.getInt(1));
                cd.setTpTitle(rs.getString(2));
                cd.setTpParentID(rs.getInt(3));
                cd.setTpStatus(rs.getInt(4));
               
                ds.add(cd);
            }
            return ds;
        } catch (Exception e) {
        }
        return null;
    }
    
    public String getTenChuDeTheoID(int tpID){
        String name = null;
        try {
            String sql = "select tpTitle from topics where tpID =? ";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, tpID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("tpTitle");
            }
        } catch (Exception e) {
        }
        
        return name;
    }
    
    public ArrayList<ChuDeDTO> getChuDe(){
          ArrayList<ChuDeDTO> arr = new ArrayList<>();
        try {
            Statement st = MyConnect.conn.createStatement();
            String sql = "SELECT * FROM topics";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ChuDeDTO tp = new ChuDeDTO();
               tp.setTpID(rs.getInt(1));
                tp.setTpTitle(rs.getString(2));
                tp.setTpParentID(rs.getInt(3));
                tp.setTpStatus(rs.getInt(4));
                arr.add(tp);
            }
            
        } catch (Exception e) {
        } 
        return arr;
    }
    
    
    public ChuDeDTO getIdTp(int id){
         
         ChuDeDTO tp = new ChuDeDTO();
        String sql = "SELECT * FROM topics WHERE tpID=?";

    try (
         PreparedStatement ps = MyConnect.conn.prepareStatement(sql)) {

        ps.setInt(1, id); // Gán giá trị ID vào câu lệnh SQL
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            
            tp.setTpID(rs.getInt(1));
            tp.setTpTitle(rs.getString(2));
            tp.setTpParentID(rs.getInt(3));
            tp.setTpStatus(rs.getInt(4));
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In lỗi nếu có
    }
        return tp;
    }
    
     public boolean addTopics(ChuDeDTO tp) throws SQLException{
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            String sql= "INSERT INTO topics(tpTitle,tpParent,tpStatus) VALUES(?,?,?)";
            st= MyConnect.conn.prepareCall(sql);
            st.setString(1, tp.getTpTitle());
            st.setInt(2, tp.getTpParentID());
            st.setInt(3, tp.getTpStatus());
            if(st.executeUpdate()>=1){
                kt= true;
            }
            
        } catch (Exception e) {
         } 
        return kt;
    }
      public boolean deletTopics(ChuDeDTO tp){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            String sql = "DELETE FROM topics WHERE id=?";
            st = MyConnect.conn.prepareStatement(sql);
            st.setInt(1, tp.getTpID());
            if(st.executeUpdate()>=1){
                kt=true;
            }
        } catch (Exception e) {
        } 
        return kt;
    } 
    public boolean updateTopics(ChuDeDTO tp){
        boolean kt = false;
        PreparedStatement st = null;
        try {
            String sql = "UPDATE topics SET tpTitle=?,tpStatus=? WHERE tpID=?";
            st = MyConnect.conn.prepareStatement(sql);
            st.setString(1, tp.getTpTitle());
            st.setInt(2, tp.getTpStatus());
            st.setInt(3, tp.getTpID());
            if(st.executeUpdate()>=1){
                kt=true;
            }  
        } catch (Exception e) {
        } 
        return kt;
    }
     public boolean DeleteStatus(ChuDeDTO tp){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            String sql = "UPDATE topics SET tpStatus=? WHERE tpID=?";
            st = MyConnect.conn.prepareStatement(sql);
            st.setInt(1, tp.getTpStatus());
            st.setInt(2, tp.getTpID());
            if(st.executeUpdate()>=1){
                kt=true;
            }  
        } catch (Exception e) {
        } 
        return kt;
    }
     
     public ArrayList<String> getDanhSachChuDe() {
        ArrayList<ChuDeDTO> danhsachchude = new ArrayList<>();
        try {
            String sql = "select * from topics";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChuDeDTO cd = new ChuDeDTO();
                cd.setTpID(rs.getInt(1));
                cd.setTpTitle(rs.getString(2));
                cd.setTpParentID(rs.getInt(3));
                cd.setTpStatus(rs.getInt(4));
               
                danhsachchude.add(cd);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
