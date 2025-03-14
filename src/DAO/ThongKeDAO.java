/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ThongKeDAO {

    public int getBaiThi() {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM result");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public int getBaiThiTheoNam(int nam) {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM result WHERE YEAR(rs_date) =" + nam);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public int getBaiThiTheoNgay(Date startDate, Date endDate) {
        try {
            String query = "SELECT COUNT(*) FROM result WHERE 1=1"; // Điều kiện mặc định

            if (startDate != null) {
                query += " AND DATE(rs_date) >= ?";
            }
            if (endDate != null) {
                query += " AND DATE(rs_date) <= ?";
            }

            java.sql.PreparedStatement stmt = MyConnect.conn.prepareStatement(query);
            int index = 1;

            if (startDate != null) {
                stmt.setDate(index++, new java.sql.Date(startDate.getTime()));
            }
            if (endDate != null) {
                stmt.setDate(index++, new java.sql.Date(endDate.getTime()));
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Trả về số bài thi
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi
    }

    public int getSLDuThi() {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(DiSTINCT userID) FROM result");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public int getSLDuThiTheoNam(int nam) {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(DiSTINCT userID) FROM result  WHERE YEAR(rs_date)=" + nam);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public int getSLDuThiTheoNgay(Date startDate, Date endDate) {
        try {
            String query = "SELECT COUNT(DiSTINCT userID) FROM result WHERE 1=1"; // Điều kiện mặc định

            if (startDate != null) {
                query += " AND DATE(rs_date) >= ?";
            }
            if (endDate != null) {
                query += " AND DATE(rs_date) <= ?";
            }

            java.sql.PreparedStatement stmt = MyConnect.conn.prepareStatement(query);
            int index = 1;

            if (startDate != null) {
                stmt.setDate(index++, new java.sql.Date(startDate.getTime()));
            }
            if (endDate != null) {
                stmt.setDate(index++, new java.sql.Date(endDate.getTime()));
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Trả về số bài thi
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi
    }

    public int getSLDat() {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(CASE WHEN rs_mark >= 5 THEN 1 ELSE 0 END) FROM result ");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public int getSLDatTheoNam(int nam) {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(CASE WHEN rs_mark >= 5 THEN 1 ELSE 0 END) FROM result  WHERE YEAR(rs_date)=" + nam);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public int getSLDatTheoNgay(Date startDate, Date endDate) {
        try {
            String query = "SELECT SUM(CASE WHEN rs_mark >= 5 THEN 1 ELSE 0 END) FROM result WHERE 1=1"; // Điều kiện mặc định

            if (startDate != null) {
                query += " AND DATE(rs_date) >= ?";
            }
            if (endDate != null) {
                query += " AND DATE(rs_date) <= ?";
            }

             java.sql.PreparedStatement stmt = MyConnect.conn.prepareStatement(query);
            int index = 1;

            if (startDate != null) {
                stmt.setDate(index++, new java.sql.Date(startDate.getTime()));
            }
            if (endDate != null) {
                stmt.setDate(index++, new java.sql.Date(endDate.getTime()));
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Trả về số bài thi
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0; // Trả về 0 nếu có lỗi
    }

    public int getSLRot() {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(CASE WHEN rs_mark < 5 THEN 1 ELSE 0 END) FROM result ");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

    public int getSLRotTheoNam(int nam) {
        try {
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(CASE WHEN rs_mark < 5 THEN 1 ELSE 0 END) FROM result WHERE YEAR(rs_date)=" + nam);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            return -1;
        }
        return 0;
    }

public int getSLRotTheoNgay(Date startDate, Date endDate) {
    try {
        // Tạo câu truy vấn SQL
        String query = "SELECT SUM(CASE WHEN rs_mark < 5 THEN 1 ELSE 0 END) FROM result WHERE 1=1";

        if (startDate != null) {
            query += " AND DATE(rs_date) >= ?";
        }
        if (endDate != null) {
            query += " AND DATE(rs_date) <= ?";
        }

        // Chuẩn bị câu truy vấn
        java.sql.PreparedStatement stmt = MyConnect.conn.prepareStatement(query); // Không cần ép kiểu
        int index = 1;

        if (startDate != null) {
            stmt.setDate(index++, new java.sql.Date(startDate.getTime()));
        }
        if (endDate != null) {
            stmt.setDate(index++, new java.sql.Date(endDate.getTime()));
        }

        // Thực hiện truy vấn
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1); // Trả về số bài thi không đạt
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return 0; // Trả về 0 nếu có lỗi
}


    public ArrayList<Integer> getDanhSachNam() {
        ArrayList<Integer> danhSachNam = new ArrayList<>();
        try {
            // Thực hiện truy vấn SQL
            String query = "SELECT DISTINCT YEAR(`rs_date`) AS year FROM result ORDER BY year DESC;";
            Statement stmt = MyConnect.conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Lấy kết quả và thêm vào danh sách
            while (rs.next()) {
                danhSachNam.add(rs.getInt("year"));
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách năm: " + e.getMessage());
        }

        return danhSachNam;
    }

    public HashMap<Integer, Integer> layDuLieuBaiThi(int nam) {
        HashMap<Integer, Integer> duLieuThang = new HashMap<>();
        String sql = "SELECT MONTH(rs_date) AS thang, COUNT(*) AS soLuong "
                + "FROM result WHERE YEAR(rs_date) = " + nam + " GROUP BY MONTH(rs_date)";

        try (Statement stmt = MyConnect.conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int thang = rs.getInt("thang");
                int soLuong = rs.getInt("soLuong");
                duLieuThang.put(thang, soLuong); // Thêm dữ liệu vào HashMap
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn: " + e.getMessage());
        }
        return duLieuThang;
    }

    public HashMap<Integer, Integer> layDuLieuDuThi(int nam) {
        HashMap<Integer, Integer> duLieuThang = new HashMap<>();
        String sql = "SELECT MONTH(rs_date) AS thang, COUNT(DISTINCT userID) AS soLuong "
                + "FROM result WHERE YEAR(rs_date) = " + nam + " GROUP BY MONTH(rs_date)";

        try (Statement stmt = MyConnect.conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int thang = rs.getInt("thang");
                int soLuong = rs.getInt("soLuong");
                duLieuThang.put(thang, soLuong); // Thêm dữ liệu vào HashMap
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn: " + e.getMessage());
        }
        return duLieuThang;
    }

    public HashMap<Integer, Integer> layDuLieuDat(int nam) {
        HashMap<Integer, Integer> duLieuThang = new HashMap<>();
        String sql = "SELECT MONTH(rs_date) AS thang, SUM(CASE WHEN rs_mark >= 5 THEN 1 ELSE 0 END) AS soLuong "
                + "FROM result WHERE YEAR(rs_date) = " + nam + " GROUP BY MONTH(rs_date)";

        try (Statement stmt = MyConnect.conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int thang = rs.getInt("thang");
                int soLuong = rs.getInt("soLuong");
                duLieuThang.put(thang, soLuong); // Thêm dữ liệu vào HashMap
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn: " + e.getMessage());
        }
        return duLieuThang;
    }

    public HashMap<Integer, Integer> layDuLieuRot(int nam) {
        HashMap<Integer, Integer> duLieuThang = new HashMap<>();
        String sql = "SELECT MONTH(rs_date) AS thang, SUM(CASE WHEN rs_mark < 5 THEN 1 ELSE 0 END) AS soLuong "
                + "FROM result WHERE YEAR(rs_date) = " + nam + " GROUP BY MONTH(rs_date)";

        try (Statement stmt = MyConnect.conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int thang = rs.getInt("thang");
                int soLuong = rs.getInt("soLuong");
                duLieuThang.put(thang, soLuong); // Thêm dữ liệu vào HashMap
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy vấn: " + e.getMessage());
        }
        return duLieuThang;
    }

}
