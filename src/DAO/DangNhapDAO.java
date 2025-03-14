/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NguoiDungDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class DangNhapDAO {

    public NguoiDungDTO dangNhap(NguoiDungDTO nd) {
        try {
            String sql = "SELECT userName, userPassword FROM users WHERE userName=? AND userPassword=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, nd.getTenDN());
            pre.setString(2, nd.getMatKhau());
            ResultSet rs = pre.executeQuery();
            NguoiDungDTO ndLogin = null;
            if (rs.next()) {
                ndLogin = nd;
                ndLogin.setTenDN(rs.getString("userName"));
                ndLogin.setMatKhau(rs.getString("userPassword"));
            }
            return ndLogin;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nd;
    }
    
}
