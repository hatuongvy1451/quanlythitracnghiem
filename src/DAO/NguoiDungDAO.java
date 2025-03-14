/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.DangNhapBUS;
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

    public NguoiDungDTO getUserLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE userName = ? AND userPassword = ?";
        try (PreparedStatement stmt = MyConnect.conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NguoiDungDTO user = new NguoiDungDTO();
                    user.setMaND(rs.getInt("userID"));
                    user.setTenDN(rs.getString("userName"));
                    user.setEmail(rs.getString("userEmail"));
                    user.setMatKhau(rs.getString("userPassword"));
                    user.setHoTen(rs.getString("userFullName"));
                    user.setVaiTro(rs.getString("isAdmin"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public NguoiDungDTO getThongTinNguoiDung(String username, String password) {
        String query = "SELECT userName, userFullName, userEmail FROM users WHERE userName = ? AND userPassword = ?";
        try (PreparedStatement stmt = MyConnect.conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NguoiDungDTO user = new NguoiDungDTO();
                    user.setTenDN(rs.getString("userName"));
                    user.setEmail(rs.getString("userEmail"));
                    user.setHoTen(rs.getString("userFullName"));
                    return user;
                }
            }
        } catch (SQLException e) {
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

    public boolean add(NguoiDungDTO nd) {
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
            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<String> getVaiTro() {
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql = "SELECT distinct isAdmin from users";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(rs.getString("isAdmin"));
            }
        } catch (Exception e) {
        }
        return ds;
    }

    public ArrayList<String> getListTenDangNhap() {
        ArrayList<String> ds = new ArrayList<>();
        try {
            String sql = "select userName from users ";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean kiemTraTrungEmail(String email) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM users WHERE userEmail = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean kiemTraTrungTenDangNhap(String tenDN) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM users WHERE userName = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, tenDN);

            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean dangKyUser(NguoiDungDTO user) {
        boolean result = false;
        try {
            String sql = "INSERT INTO users (userName, userEmail, userPassword, userFullName, isAdmin) VALUES (?,?,?,?,?)";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, user.getTenDN());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMatKhau());
            ps.setString(4, user.getHoTen());
            ps.setString(5, "0");

            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateUser(NguoiDungDTO nd) {
        boolean result = false;
        try {
            String sql = "UPDATE users SET userEmail = ?, userFullName = ? WHERE userID = ?";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setString(1, nd.getEmail());
            ps.setString(2, nd.getHoTen());
            ps.setInt(3, nd.getMaND());

            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean nhapNguoiDungTuExcel(NguoiDungDTO user) {
        try {
            // Kiểm tra người dùng có tồn tại trong cơ sở dữ liệu hay không
            String checkSql = "SELECT COUNT(*) FROM users WHERE userName = ? OR userEmail = ?";
            PreparedStatement checkStmt = MyConnect.conn.prepareStatement(checkSql);
            checkStmt.setString(1, user.getTenDN());
            checkStmt.setString(2, user.getEmail());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // Người dùng đã tồn tại, không thêm mới
                return false;
            }

            // Thêm dữ liệu mới vào bảng 'users'
            String insertSql = "INSERT INTO users(userName, userEmail, userFullName) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = MyConnect.conn.prepareStatement(insertSql);
            insertStmt.setString(1, user.getTenDN());
            insertStmt.setString(2, user.getEmail());
            insertStmt.setString(3, user.getHoTen());

            insertStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean doiMatKhau(String matKhauCu, String matKhauMoi) {
        try {
            String sql = "UPDATE users SET userPassword=? WHERE userID=? AND userPassword=?";
            PreparedStatement pre = MyConnect.conn.prepareStatement(sql);
            pre.setString(1, matKhauMoi);
            pre.setInt(2, DangNhapBUS.nguoidungLogin.getMaND());
            pre.setString(3, matKhauCu);
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }
}
