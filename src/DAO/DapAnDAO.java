/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DapAnDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tuong Vy Ha
 */
public class DapAnDAO {

    public ArrayList<DapAnDTO> getDanhSachDapAn() {
        ArrayList<DapAnDTO> danhsachdapan = new ArrayList<>();
        try {
            String sql = "select * from answer";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DapAnDTO dt = new DapAnDTO();
                dt.setAwID(rs.getInt(1));
                dt.setqID(rs.getInt(2));
                dt.setAwContent(rs.getString(3));
                dt.setAwPictures(rs.getString(4));
                dt.setIsRight(rs.getInt(5));
                dt.setAwStatus(rs.getInt(6));
                danhsachdapan.add(dt);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<DapAnDTO> getDapAnByCauHoi(int questionID) {
        ArrayList<DapAnDTO> ds = new ArrayList<>();
        try {
            String sql = "SELECT * FROM answers WHERE qID = ? AND awStatus = 1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, questionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int awID = rs.getInt("awID");
                String awContent = rs.getString("awContent");
                String awPictures = rs.getString("awPictures");
                int isRight = rs.getInt("isRight");

                DapAnDTO answer = new DapAnDTO(awID, questionID, awContent, awPictures, isRight);
                ds.add(answer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<DapAnDTO> getAnswers() {
        ArrayList<DapAnDTO> arr = new ArrayList<>();
        try {

            Statement st = MyConnect.conn.createStatement();
            String sql = "SELECT * FROM answers";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DapAnDTO answ = new DapAnDTO();
                answ.setAwID(rs.getInt(1));
                answ.setqID(rs.getInt(2));
                answ.setAwContent(rs.getString(3));
                answ.setAwPictures(rs.getString(4));
                answ.setIsRight(rs.getInt(5));
                answ.setAwStatus(rs.getInt(6));
                arr.add(answ);
            }
        } catch (Exception e) {
        }
        return arr;
    }

    public ArrayList<DapAnDTO> getID(int ma) {
        ArrayList<DapAnDTO> arr = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `answers` WHERE qID=?";
            PreparedStatement st = MyConnect.conn.prepareStatement(sql);
            st.setInt(1, ma);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                DapAnDTO answ = new DapAnDTO();
                answ.setAwID(rs.getInt(1));
                answ.setqID(rs.getInt(2));
                answ.setAwContent(rs.getString(3));
                answ.setAwPictures(rs.getString(4));
                answ.setIsRight(rs.getInt(5));
                answ.setAwStatus(rs.getInt(6));
                arr.add(answ);
            }
        } catch (Exception e) {
        }
        return arr;

    }

    public ArrayList<DapAnDTO> getIDAw(int ma) {
        ArrayList<DapAnDTO> arr = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `answers` WHERE awID=?";
            PreparedStatement st = MyConnect.conn.prepareStatement(sql);
            st.setInt(1, ma);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                DapAnDTO answ = new DapAnDTO();

                answ.setqID(rs.getInt(2));
                answ.setAwContent(rs.getString(3));
                answ.setAwPictures(rs.getString(4));
                answ.setIsRight(rs.getInt(5));
                answ.setAwStatus(rs.getInt(6));
                arr.add(answ);
            }
        } catch (Exception e) {
        }
        return arr;

    }

    public boolean addAnswers(DapAnDTO aw) throws SQLException {
        boolean kt = false;
        PreparedStatement st = null;
        try {
            String sql = "INSERT INTO answers(qID,awContent,awPictures,isRight,awStatus) VALUES(?,?,?,?,?)";
            st = MyConnect.conn.prepareCall(sql);
            st.setInt(1, aw.getqID());
            st.setString(2, aw.getAwContent());
            st.setString(3, aw.getAwPictures());
            st.setInt(4, aw.getIsRight());
            st.setInt(5, aw.getAwStatus());
            if (st.executeUpdate() >= 1) {
                kt = true;
            }

        } catch (Exception e) {

        }
        return kt;
    }

    public boolean DeleteStatus(int id, int status) {
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MyConnect.conn;
            String sql = "UPDATE answers SET awStatus=? WHERE awID=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, id);
            if (st.executeUpdate() >= 1) {
                kt = true;
            }
        } catch (Exception e) {
        } finally {
            // JDBCUtil.closeConnection(conn);
        }
        return kt;
    }

    public List<DapAnDTO> getAnswersByQuestionID(int questionID) {
        List<DapAnDTO> answers = new ArrayList<>();
        String sql = "SELECT awID, awContent, isRight FROM answers WHERE qID = ?";
        try (PreparedStatement ps = MyConnect.conn.prepareStatement(sql)) {
            ps.setInt(1, questionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DapAnDTO answer = new DapAnDTO();
                answer.setAwID(rs.getInt("awID"));
                answer.setAwContent(rs.getString("awContent"));
                answer.setIsRight(rs.getInt("isRight"));
                answers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }

    public boolean updateAnswers(DapAnDTO aw) {
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MyConnect.conn;
            String sql = "UPDATE answers SET awContent=? , awPictures=? , isRight=? , awStatus=? WHERE awID=?";
            st = conn.prepareStatement(sql);
            st.setString(1, aw.getAwContent());
            st.setString(2, aw.getAwPictures());
            st.setInt(3, aw.getIsRight());
            st.setInt(4, aw.getAwStatus());
            st.setInt(5, aw.getAwID());
            if (st.executeUpdate() >= 1) {
                kt = true;
            }
        } catch (Exception e) {
        } finally {
            //   conn.close();
        }
        return kt;
    }

    public void saveAnswer(int awID, int qID, String awContent, String awPictures, int isRight, int awStatus) {
        String sql = "INSERT INTO answers (awID, qID, awContent, awPictures, isRight, awStatus) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = MyConnect.conn.prepareStatement(sql)) {
            stmt.setInt(1, awID);
            stmt.setInt(2, qID);
            stmt.setString(3, awContent);
            stmt.setString(4, awPictures);
            stmt.setInt(5, isRight);
            stmt.setInt(6, awStatus);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
