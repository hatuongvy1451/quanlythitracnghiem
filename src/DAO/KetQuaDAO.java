/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CauHoiDTO;
import DTO.KetQuaDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author HP
 */
public class KetQuaDAO {
    
    public ArrayList<KetQuaDTO> getListKetQua(){
        ArrayList<KetQuaDTO> listKQ = new ArrayList<>();
        try {
            
            String sql = "select * from result";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KetQuaDTO kq = new KetQuaDTO();
                kq.setRs_num(rs.getInt(1));
                kq.setUserID(rs.getInt(2));
                kq.setExCode(rs.getString(3));
                kq.setRs_anwsers(rs.getString(4));
                kq.setRs_mask(rs.getFloat(5));
                kq.setDate(rs.getString(6));
                listKQ.add(kq);
            }
          
        } catch (Exception e) {
        }
        
        return listKQ;
        
    }
    
    public boolean addKetQua(KetQuaDTO kq){
        boolean result = false;
        try {
            String sql ="insert into result values (?,?,?,?,?,?)";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, kq.getRs_num());
            ps.setInt(2, kq.getUserID());
            ps.setString(3, kq.getExCode());
            ps.setString(4, kq.getRs_anwsers());
            ps.setFloat(5, kq.getRs_mask());
            ps.setString(6, kq.getDate());
            result = ps.executeUpdate()>0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public float getDiemTheoUserIDvaExams(int userID, String exCode){
        int diem = 0;
        try {
            String sql = "Select rs_mark from result where userID =? and exCode =? ORDER BY rs_num DESC LIMIT 1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, exCode);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                diem = rs.getInt("rs_mark");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diem;
    }
    
    public String getThoiGianTheoUserIDvaExams(int userID, String exCode){
        String time =null;
        try {
            String sql = "select rs_date from result where userID =? and exCode =? ORDER BY rs_num DESC LIMIT 1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
          ps.setInt(1, userID);
            ps.setString(2, exCode);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                time = rs.getString("rs_date");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
    
    public List<String> getDapAnTheoUserIDvaExams(int userID, String exCode){
        List<String> answers  =null;
        try {
//            String sql = "select rs_anwsers from result where userID =? and exCode =? ORDER BY rs_date DESC LIMIT 1";
String sql = "SELECT rs_anwsers FROM result WHERE userID = ? AND exCode = ? ORDER BY rs_num DESC LIMIT 1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, exCode);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String jsonString = rs.getString("rs_anwsers");
                JSONArray jsonArray = new JSONArray(jsonString);
                for(int i=0; i< jsonArray.length(); i++){
                    answers.add(jsonArray.getString(i));
                }  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }
    
    
    
    
    public ArrayList<KetQuaDTO> getListKetQuaTheoID(int userID){
        
        try {
            ArrayList<KetQuaDTO> listKQ = new ArrayList<>();
            String sql = "select * from result where userID = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 KetQuaDTO kq = new KetQuaDTO();
                kq.setRs_num(rs.getInt(1));
                kq.setUserID(rs.getInt(2));
                kq.setExCode(rs.getString(3));
                kq.setRs_anwsers(rs.getString(4));
                kq.setRs_mask(rs.getFloat(5));
                kq.setDate(rs.getString(6));
                listKQ.add(kq);
            }
          return listKQ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;      
    }
    
    public String getUserAnswers(int userID, String exCode) {
        String userAnswersJSON = null;
//        String sql = "SELECT rs_anwsers FROM result WHERE userID = ? AND exCode = ? ORDER BY rs_date DESC LIMIT 1";
String sql = "SELECT rs_anwsers FROM result WHERE userID = ? AND exCode = ? ORDER BY rs_num DESC LIMIT 1";
        try (PreparedStatement ps = MyConnect.conn.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ps.setString(2, exCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userAnswersJSON = rs.getString("rs_anwsers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAnswersJSON;
    }
    
    public List<CauHoiDTO> getQuestionsByTopicID(int topicID) {
        List<CauHoiDTO> questions = new ArrayList<>();
        String sql = "SELECT qID, qContent FROM questions WHERE qTopicID = ?";
        try (PreparedStatement ps = MyConnect.conn.prepareStatement(sql)) {
            ps.setInt(1, topicID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CauHoiDTO question = new CauHoiDTO();
                question.setqID(rs.getInt("qID"));
                question.setqContent(rs.getString("qContent"));
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
    
    public String getexCodeTheoUserIDvaExams(int userID){
        String exCode =null;
        try {
//            String sql = "select exCode from result where userID =? ORDER BY rs_date DESC LIMIT 1";
String sql = "SELECT exCode FROM result WHERE userID = ? ORDER BY rs_num DESC LIMIT 1";
      
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
          ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                exCode = rs.getString("exCode");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exCode;
    }
    
}
