/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.BaiThiDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Thu Huyền
 */
public class BaiThiDAO {

    public ArrayList<BaiThiDTO> getListBaiThi() {
        try {
            ArrayList<BaiThiDTO> ds = new ArrayList<>();
            String sql = "SELECT * FROM test";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BaiThiDTO bt = new BaiThiDTO();
                bt.setTestID(rs.getInt(1));
                bt.setTestCode(rs.getString(2));
                bt.setTestTitle(rs.getString(3));
                bt.setTestTime(rs.getInt(4));
                bt.setTpID(rs.getInt(5));
                bt.setNum_easy(rs.getInt(6));
                bt.setNum_medium(rs.getInt(7));
                bt.setNum_diff(rs.getInt(8));
                bt.setTestLimit(rs.getInt(9));
                bt.setTestDate(rs.getString(10));
                bt.setTestStatus(rs.getInt(11));

                ds.add(bt);
            }
            return ds;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<String> getListBaithi() {
        try {
            ArrayList<String> ds = new ArrayList<>();
            String sql = "SELECT testCode FROM test";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String testCode = rs.getString("testCode");
                ds.add(testCode);
            }
            return ds;
        } catch (Exception e) {
        }
        return null;
    }

    public String getTestTimeTheoTestCode(String testCode) {
        String time = "";
        try {
            String sql = "Select testTime from test where testCode =?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                time = rs.getString("testTime");
            }

        } catch (Exception e) {
        }

        return time;
    }

    public ArrayList<String> getDeThiTheoTestCode(String testCode) {
        try {
            ArrayList<String> dsdethi = new ArrayList<>();
            String sql = "select exCode from exams where testCode =?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String dethi = rs.getString("exCode");
                dsdethi.add(dethi);
            }
            return dsdethi;
        } catch (Exception e) {
        }

        return null;

    }

    public boolean addBaiThi(BaiThiDTO bt) {
        boolean result = false;
        try {
            String sql = "insert into test(testID, testCode, testTilte, testTime, tpID, num_easy, num_medium, num_diff, testLimit, testDate, testStatus) values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, bt.getTestID());
            ps.setString(2, bt.getTestCode());
            ps.setString(3, bt.getTestTitle());
            ps.setInt(4, bt.getTestTime());
            ps.setInt(5, bt.getTpID());
            ps.setInt(6, bt.getNum_easy());
            ps.setInt(7, bt.getNum_medium());
            ps.setInt(8, bt.getNum_diff());
            ps.setInt(9, bt.getTestLimit());
            ps.setString(10, bt.getTestDate());
            ps.setInt(11, bt.getTestStatus());
            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTestCodeCuoCungTrongBang() {
        String testCode = null;
        try {
            String sql = "SELECT testCode FROM test ORDER BY testCode DESC LIMIT 1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                testCode = rs.getString("testCode");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi để dễ debug
        }
        return testCode; // Trả về null nếu không có bản ghi hoặc lỗi
    }
    
    // Lấy dang sachs idTp từ bảng test theo testCode
    public ArrayList<String> getIdTpByTestCode(String testCode) {
        ArrayList<String> tpID = new ArrayList<>();
        try {
            String sql = "SELECT tpID FROM test WHERE testCode = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tpID.add(rs.getString("tpID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tpID;
    }
}
