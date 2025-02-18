/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NguoiDungDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class NguoiDungDAO {

    public ArrayList<NguoiDungDTO> getDanhSachNguoiDung() {
        try {
            ArrayList<NguoiDungDTO> ds = new ArrayList<>();
            String sql = "SELECT userID, userName, userEmail, userFullName, isAdmin FROM users";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDungDTO nd = new NguoiDungDTO();
                nd.setMaND(rs.getInt(1));
                nd.setTenDN(rs.getString(2));
                nd.setEmail(rs.getString(3));
                nd.setHoTen(rs.getString(4));
                nd.setVaiTro(rs.getString(5));
                ds.add(nd);
                System.out.println(nd);
            }
            return ds;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<NguoiDungDTO> getNguoiDung() {
        try {
            ArrayList<NguoiDungDTO> ds = new ArrayList<>();
            String sql = "SELECT * FROM users";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NguoiDungDTO nd = new NguoiDungDTO();
                nd.setMaND(rs.getInt(1));
                nd.setTenDN(rs.getString(2));
                nd.setEmail(rs.getString(3));
                nd.setMatKhau(rs.getString(4));
                nd.setHoTen(rs.getString(5));
                nd.setVaiTro(rs.getString(6));
                ds.add(nd);
            }
            return ds;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean xoaNguoiDung(int maND) {
        boolean result = false;
        try {
            String sql = "DELETE FROM users WHERE userID = ?";
            PreparedStatement prep = MyConnect.conn.prepareStatement(sql);
            prep.setInt(1, maND);
            result = prep.executeUpdate() > 0;
        } catch (SQLException ex) {
            return false;
        }
        return result;
    }
    
    public boolean add(NguoiDungDTO nd){
        boolean result = false;
        try {
            String sql = "insert into users values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, nd.getMaND());
            ps.setString(2, nd.getTenDN());
            ps.setString(3, nd.getEmail());
            ps.setString(4, nd.getMatKhau());
            ps.setString(5, nd.getHoTen());
            ps.setString(6, nd.getVaiTro());
            result = ps.executeUpdate() >0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    
    public ArrayList<String> getVaiTro(){
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql = "SELECT distinct isAdmin from users";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ds.add(rs.getString("isAdmin"));
            }
        } catch (Exception e) {
        }
        return ds;
    }
    
    public ArrayList<String> getListTenDangNhap(){
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql ="select userName from users ";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ds.add(rs.getString("userName"));
            }
            
        } catch (Exception e) {
        }
        
        return ds;
    }
    
    public NguoiDungDTO getNguoiDungTheoID(int id) {
        NguoiDungDTO nd = null;
        try {
            String sql = "Select * from users where userID =?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nd = new NguoiDungDTO();
                nd.setMaND(rs.getInt(1));
                nd.setTenDN(rs.getString(2));
                nd.setEmail(rs.getString(3));
                nd.setMatKhau(rs.getString(4));
                nd.setHoTen(rs.getString(5));
                nd.setVaiTro(rs.getString(6));

            }

        } catch (Exception e) {
            return null;
        }
        return nd;
    }
    
      public boolean update(NguoiDungDTO nd) {
        boolean result = false;
        try {
            String sql = "UPDATE users SET userEmail = ?, userPassword = ?, userFullName = ?, isAdmin = ? WHERE userID = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, nd.getEmail());
            ps.setString(2, nd.getMatKhau());
            ps.setString(3, nd.getHoTen());
            ps.setString(4, nd.getVaiTro());
            ps.setInt(5, nd.getMaND());

            result = ps.executeUpdate() >0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
