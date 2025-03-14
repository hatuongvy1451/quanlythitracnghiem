/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CauHoiDTO;
import java.sql.*;
import java.util.ArrayList;

public class CauHoiDAO {

    public ArrayList<CauHoiDTO> getListCauHoi() {
        try {
            ArrayList<CauHoiDTO> ds = new ArrayList<>();
//            String sql = "SELECT qID, qContent, qPictures, qTopicID, qLevel FROM questions";
            String sql = "SELECT * FROM questions where qStatus =1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CauHoiDTO ch = new CauHoiDTO();
                ch.setqID(rs.getInt(1));
                ch.setqContent(rs.getString(2));
                ch.setqPictures(rs.getString(3));
                ch.setqTopicID(rs.getInt(4));
                ch.setqLevel(rs.getString(5));
                ch.setqStatus(rs.getInt(6));

                ds.add(ch);
            }
            return ds;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<CauHoiDTO> getCauHoiChoDeThi(String exCode) {
        ArrayList<CauHoiDTO> ds = new ArrayList<>();
        try {
            // Get the question IDs from the exams table
            String getIdsSql = "SELECT ex_quesIDs FROM exams WHERE exCode = ?";
            PreparedStatement getIdsPs = MyConnect.conn.prepareStatement(getIdsSql);
            getIdsPs.setString(1, exCode);
            ResultSet getIdsRs = getIdsPs.executeQuery();
            String[] questionIdsArray = new String[0];
            if (getIdsRs.next()) {
                String questionIds = getIdsRs.getString("ex_quesIDs");
                questionIdsArray = questionIds.split(","); // Split the IDs by commas
            }

            // Build the SQL query to get questions based on the retrieved IDs
            StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM questions WHERE qID IN (");
            for (int i = 0; i < questionIdsArray.length; i++) {
                sqlBuilder.append("?");
                if (i < questionIdsArray.length - 1) {
                    sqlBuilder.append(",");
                }
            }
            sqlBuilder.append(")");

            // Prepare the SQL statement
            PreparedStatement ps = MyConnect.conn.prepareStatement(sqlBuilder.toString());
            for (int i = 0; i < questionIdsArray.length; i++) {
                ps.setInt(i + 1, Integer.parseInt(questionIdsArray[i]));
            }

            // Execute the query and process the result set
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int qID = rs.getInt("qID");
                String qContent = rs.getString("qContent");
                String qPictures = rs.getString("qPictures");
                int qTopicID = rs.getInt("qTopicID");
                String qLevel = rs.getString("qLevel");
                int qStatus = rs.getInt("qStatus");

                CauHoiDTO cauHoi = new CauHoiDTO(qID, qContent, qPictures, qTopicID, qLevel, qStatus);
                ds.add(cauHoi);
            }

            // Ensure the questions are returned in the correct order
            ArrayList<CauHoiDTO> orderedQuestions = new ArrayList<>();
            for (String id : questionIdsArray) {
                for (CauHoiDTO ch : ds) {
                    if (String.valueOf(ch.getqID()).equals(id.trim())) {
                        orderedQuestions.add(ch);
                        break;
                    }
                }
            }

            return orderedQuestions;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    public ArrayList<CauHoiDTO> getQuestions() {
        ArrayList<CauHoiDTO> arr = new ArrayList<>();
        try {
            Statement st = MyConnect.conn.createStatement();
            String sql = "SELECT * FROM questions";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                CauHoiDTO question = new CauHoiDTO();
                question.setqID(rs.getInt(1));
                question.setqContent(rs.getString(2));
                question.setqPictures(rs.getString(3));
                question.setqTopicID(rs.getInt(4));
                question.setqLevel(rs.getString(5));
                question.setqStatus(rs.getInt(6));
                arr.add(question);
            }

        } catch (Exception e) {
        }
        return arr;
    }

    public CauHoiDTO getQuestionById(int id) {
        CauHoiDTO question = null;
        String sql = "SELECT qContent, qPictures FROM questions WHERE qID = ?";

        try (Connection conn = MyConnect.conn; PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); // Gán giá trị ID vào câu lệnh SQL
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                question = new CauHoiDTO();
                question.setqContent(rs.getString("qContent"));
                question.setqPictures(rs.getString("qPictures"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi nếu có
        }
        return question; // Trả về kết quả
    }

    public int addQuestions(CauHoiDTO question) throws SQLException {
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        int generatedId = -1;
        try {
            conn = MyConnect.conn;
            String sql = "INSERT INTO questions(qContent,qPictures,qTopicID,qLevel,qStatus) VALUES(?,?,?,?,?)";
            st = conn.prepareCall(sql);
            st.setString(1, question.getqContent());
            st.setString(2, question.getqPictures());
            st.setInt(3, question.getqTopicID());
            st.setString(4, question.getqLevel());
            st.setInt(5, question.getqStatus());
            int rowsInserted = st.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);  // Lấy ID vừa tạo
                }
            }

        } finally {
            conn.close();
        }
        return generatedId;
    }

    public boolean updateQuestions(CauHoiDTO ques) {
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = MyConnect.conn;
            String sql = "UPDATE questions SET qContent=? , qStatus=? , qLevel=? WHERE qID=?";
            st = conn.prepareStatement(sql);
            st.setString(1, ques.getqContent());
            st.setInt(2, ques.getqStatus());
            st.setString(3, ques.getqLevel());
            st.setInt(4, ques.getqID());
            if (st.executeUpdate() >= 1) {
                kt = true;
            }
        } catch (Exception e) {
        } finally {
            //   conn.close();
        }
        return kt;
    }

    public ArrayList<CauHoiDTO> getIDAw(int ma) {
        ArrayList<CauHoiDTO> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn = MyConnect.conn;
            String sql = "SELECT * FROM `questions` WHERE qID=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, ma);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CauHoiDTO question = new CauHoiDTO();
                question.setqID(rs.getInt(1));
                question.setqContent(rs.getString(2));
                question.setqPictures(rs.getString(3));
                question.setqTopicID(rs.getInt(4));
                question.setqLevel(rs.getString(5));
                question.setqStatus(rs.getInt(6));
                arr.add(question);
            }
        } catch (Exception e) {
        } finally {
            //  conn.close();
        }
        return arr;

    }

    public boolean deleteStatus(int id, int status) {
        String sql = "UPDATE questions SET qStatus=? WHERE qID=?";
        try (Connection conn = MyConnect.conn; PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, status);
            st.setInt(2, id);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveQuestion(int qID, String qContent, String qPictures, int qTopicID, String qLevel, int qStatus) {
        String sql = "INSERT INTO questions (qID, qContent, qPictures, qTopicID, qLevel, qStatus) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = MyConnect.conn.prepareStatement(sql)) {
            stmt.setInt(1, qID);
            stmt.setString(2, qContent);
            stmt.setString(3, qPictures);
            stmt.setInt(4, qTopicID);
            stmt.setString(5, qLevel);
            stmt.setInt(6, qStatus);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getCauHoiTheoqTpIDvaqLevel(int qTopicID, String qLevel) {
        ArrayList<Integer> listqIDch = new ArrayList<>();
        try {
            String sql = "select qID from questions where qTopicID =? and qLevel = ? and qStatus =1";
            PreparedStatement ps = MyConnect.conn.prepareStatement(sql);
            ps.setInt(1, qTopicID);
            ps.setString(2, qLevel);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listqIDch.add(rs.getInt("qID"));
            }

        } catch (Exception e) {
        }
        return listqIDch;
    }

//    public boolean nhapCauHoiTuExcel(CauHoiDTO cauHoi) {
//        String checkSql = "SELECT COUNT(*) FROM questions WHERE qID = ?";
//        String insertSql = "INSERT INTO questions(qID, qContent, qPictures, qTopicID, qLevel, qStatus) VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (Connection conn = MyConnect.conn; PreparedStatement checkStmt = conn.prepareStatement(checkSql); PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
//
//            // Kiểm tra câu hỏi có tồn tại trong cơ sở dữ liệu hay không
//            checkStmt.setInt(1, cauHoi.getqID());
//            ResultSet rs = checkStmt.executeQuery();
//
//            if (rs.next() && rs.getInt(1) > 0) {
//                // Câu hỏi đã tồn tại, không thêm mới
//                return false;
//            }
//
//            // Thêm dữ liệu mới vào bảng 'questions'
//            insertStmt.setInt(1, cauHoi.getqID());
//            insertStmt.setString(2, cauHoi.getqContent());
//            insertStmt.setString(3, cauHoi.getqPictures());
//            insertStmt.setInt(4, cauHoi.getqTopicID());
//            insertStmt.setString(5, cauHoi.getqLevel());
//            insertStmt.setInt(6, cauHoi.getqStatus());
//
//            insertStmt.executeUpdate();
//            return true;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
