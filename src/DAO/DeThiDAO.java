/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CauHoiDTO;
import DTO.DeThiDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class DeThiDAO {

    public ArrayList<String> getDanhSachBaiThi() {
        ArrayList<DeThiDTO> danhsachbaithi = new ArrayList<>();
        try {
            String sql = "select * from exams";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeThiDTO dt = new DeThiDTO();
                dt.setTestCode(rs.getString(1));
                dt.setExOrder(rs.getString(2));
                dt.setExCode(rs.getString(3));
                String quesIDsString = rs.getString(4);
                if (quesIDsString != null) {
                    dt.setEx_quesIDs(quesIDsString.split(","));
                } else {
                    dt.setEx_quesIDs(new String[0]);
                }
                danhsachbaithi.add(dt);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int getTongSoCauHoiTheoExCode(String exCode) {
        int soCau = 0;
        try {
            String sql = "Select (length(ex_quesIDs) - length(replace(ex_quesIDs, ',', '')) +1) AS tongsocau from exams where exCode = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, exCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                soCau = rs.getInt("tongsocau");
            }
        } catch (Exception e) {
        }

        return soCau;
    }

    public ArrayList<DeThiDTO> getDanhSachDeThi() {
        try {
            ArrayList<DeThiDTO> ds = new ArrayList<>();
            String sql = "SELECT testID, testCode, testTilte, tpID, testStatus FROM test";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DeThiDTO deThi = new DeThiDTO();
                deThi.setTestID(rs.getInt(1));
                deThi.setTestCode(rs.getString(2));
                deThi.setTestTitle(rs.getString(3));
                deThi.setTopicID(rs.getInt(4));
                deThi.setTestStatus(rs.getString(5));
                ds.add(deThi);
                System.out.println(deThi);
            }
            return ds;
        } catch (Exception e) {
        }
        return null;
    }

    public DeThiDTO getDeThiTheoID(int id) {
        DeThiDTO deThi = null;
        try {
            String sql = "Select * from test where testID = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                deThi = new DeThiDTO();
                deThi.setTestID(rs.getInt(1));
                deThi.setTestCode(rs.getString(2));
                deThi.setTestTitle(rs.getString(3));
                deThi.setTestTime(rs.getInt(4));
                deThi.setTopicID(rs.getInt(5));
                deThi.setNum_easy(rs.getInt(6));
                deThi.setNum_medium(rs.getInt(7));
                deThi.setNum_diff(rs.getInt(8));
                deThi.setTestLimit(rs.getInt(9));
                deThi.setTestDate(rs.getString(10));
                deThi.setTestStatus(rs.getString(11));
            }

        } catch (Exception e) {
            return null;
        }
        return deThi;
    }

    public List<String> getAllTestTitles() {
        List<String> testTitles = new ArrayList<>();
        try {
            String sql = "SELECT testTilte FROM test";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                testTitles.add(rs.getString("testTilte"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testTitles;
    }

    // Lấy exCode từ bảng exams theo testCode
    public ArrayList<String> getExCodesByTestCode(String testCode) {
        ArrayList<String> exCodes = new ArrayList<>();
        try {
            String sql = "SELECT exCode FROM exams WHERE testCode = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exCodes.add(rs.getString("exCode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exCodes;
    }

    // Lấy testLimit từ bảng test theo testCode
    public int getTestLimitByTestCode(String testCode) {
        int testLimit = -1; // Giá trị mặc định nếu không tìm thấy
        try {
            String sql = "SELECT testLimit FROM test WHERE testCode = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                testLimit = rs.getInt("testLimit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testLimit;
    }

    // Lấy số lần thi đã thực hiện từ bảng result theo exCode và userID
    public int getTotalAttemptsByTestCode(String testCode, int userID) {
        int totalAttempts = 0;
        try {
            String sql = "SELECT COUNT(*) AS attempts "
                    + "FROM result r "
                    + "JOIN exams e ON r.exCode = e.exCode "
                    + "WHERE e.testCode = ? AND r.userID = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ps.setInt(2, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalAttempts = rs.getInt("attempts");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalAttempts;
    }

    public String getExCodeCuoCungTrongBang() {
        String exCode = null;
        try {
            String sql = "select exCode from exams order by exCode DESC limit 1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exCode = rs.getString("exCode");
            }
        } catch (Exception e) {
        }
        return exCode;
    }

    public boolean addDeThi(DeThiDTO dt) {
        boolean result = false;
        try {
            String sql = "insert into exams(testCode, exOrder, exCode, ex_quesIDs) values (?,?,?,?)";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, dt.getTestCode());
            ps.setString(2, dt.getExOrder());
            ps.setString(3, dt.getExCode());
            String joinedQuesIDs = String.join(",", dt.getEx_quesIDs());
            ps.setString(4, joinedQuesIDs);
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getListexOrders(String testCode) {
        int exorder = 0;
        try {
            String sql = "select count(testCode) from exams where testCode =? ";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exorder = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return exorder;
    }

    public String getExCodeByTestCodeAndOrder(String testCode, String exOrder) {
        String exCode = null;

        try {
            String sql = "SELECT exCode FROM exams WHERE testCode = ? AND exOrder = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ps.setString(2, exOrder);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exCode = rs.getString("exCode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exCode;
    }

    public boolean updateExQuesIDs(String testCode, String exOrder, String[] ex_quesIDs) {

        try {
            String sql = "UPDATE exams SET ex_quesIDs = ? WHERE testCode = ? AND exOrder = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, String.join(",", ex_quesIDs));
            ps.setString(2, testCode);
            ps.setString(3, exOrder);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getExCodeTheoTestCodeTrongBang(String testCode) {
        String exCode = null;
        try {
            String sql = "select exCode from exams where testCode =? order by exCode asc limit 1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, testCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exCode = rs.getString("exCode");
            }
        } catch (Exception e) {
        }
        return exCode;
    }
}
