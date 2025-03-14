/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.CauHoiBUS;
import BUS.DeThiBUS;
import BUS.KetQuaBUS;
import DAO.MyConnect;
import DTO.CauHoiDTO;
import DTO.DapAnDTO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author HP
 */
public class KetQuaGUI extends javax.swing.JFrame {

    CauHoiBUS chBUS = new CauHoiBUS();
    KetQuaBUS kqBUS = new KetQuaBUS();
    DeThiBUS dtBUS = new DeThiBUS();
    private ArrayList<CauHoiDTO> cauHoi;
    private ArrayList<DapAnDTO> dapAn;
    private JButton[] btnQues;
    private int currentQuestionIndex = 0;
    private String exCode = "EX001";
    private int userID = 3;
    private Map<Integer, String> userAnswers = new HashMap<>();

    /**
     * Creates new form KetQuaGUI
     */
    public KetQuaGUI() {
        initComponents();
        loadDSCauHoi();
        PrevandNext();
        hienThiResult();
        txtDiem.setEnabled(false);
        txtSoCauDung.setEnabled(false);
        txtSoCauSai.setEnabled(false);
        txtThoiGian.setEnabled(false);

        loadUserAnswers(userID, exCode); // Tải câu trả lời của người dùng từ cơ sở dữ liệu

        if (cauHoi != null && !cauHoi.isEmpty()) {
            int firstQuestionID = cauHoi.get(currentQuestionIndex).getqID(); // Lấy ID câu hỏi đầu tiên
            showQuestionResult(firstQuestionID); // Gọi hàm hiển thị câu hỏi đầu tiên
        }
    }

    private void hienThiResult() {
        txtDiem.setText(String.valueOf(kqBUS.getDiemTheoUserIDvaExams(userID, exCode)));
        int tongSoCau = (int) dtBUS.getTongSoCauHoiTheoExCode(exCode);
        int soCauDung = (int) kqBUS.getDiemTheoUserIDvaExams(userID, exCode);
        int soCauSai = tongSoCau - soCauDung;
        txtSoCauDung.setText(String.valueOf(soCauDung));
        txtSoCauSai.setText(String.valueOf(soCauSai));
        String time = kqBUS.getThoiGianTheoUserIDvaExams(userID, exCode);
        System.out.println(time);
        txtThoiGian.setText(time);

    }

    private void loadUserAnswers(int userID, String exCode) {
        String userAnswersJSON = kqBUS.getUserAnswers(userID, exCode); // Gọi phương thức từ KetQuaBUS

        if (userAnswersJSON == null || userAnswersJSON.isEmpty()) {
            System.out.println("Warning: userAnswersJSON is null or empty.");
            return;
        }

        try {
            // Giả sử JSON của bạn là mảng các câu trả lời của người dùng
            JSONArray jsonArray = new JSONArray(userAnswersJSON); // Parse JSON array
            for (int i = 0; i < jsonArray.length(); i++) {
                // Lưu câu trả lời vào bản đồ với ID câu hỏi là i + 1, mảng JSON bắt đầu từ 0
                userAnswers.put(i + 1, jsonArray.getString(i));
            }
            // In ra kết quả để kiểm tra
            System.out.println("Parsed userAnswers: " + userAnswers);
        } catch (JSONException e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
    }

    private void setColorForButton(JButton button, String answerContent, String userAnswer, String correctAnswer) {
        if (answerContent.equals(correctAnswer)) {
            button.setBackground(Color.GREEN); // Đúng
        } else if (answerContent.equals(userAnswer)) {
            button.setBackground(Color.RED); // Sai 
        } else {
            button.setBackground(Color.LIGHT_GRAY); // Không chọn
        }
    }

    public void showQuestionResult(int questionID) {
        CauHoiDTO ch = cauHoi.stream().filter(c -> c.getqID() == questionID).findFirst().orElse(null);
        if (ch != null) {
            number.setText(String.valueOf(currentQuestionIndex + 1));
            txtCauHoi.setText(ch.getqContent());

            List<DapAnDTO> dapAnList = kqBUS.getQuestionsAndAnswersByTopicID(ch.getqTopicID()).get(questionID);

            if (dapAnList != null && dapAnList.size() >= 4) {
                btnA.setText(dapAnList.get(0).getAwContent());
                btnB.setText(dapAnList.get(1).getAwContent());
                btnC.setText(dapAnList.get(2).getAwContent());
                btnD.setText(dapAnList.get(3).getAwContent());

                // Đặt màu nền của các nút về màu mặc định (không chọn)
                btnA.setBackground(Color.LIGHT_GRAY);
                btnB.setBackground(Color.LIGHT_GRAY);
                btnC.setBackground(Color.LIGHT_GRAY);
                btnD.setBackground(Color.LIGHT_GRAY);

                // Lấy câu trả lời của người dùng cho câu hỏi hiện tại
                String userAnswer = userAnswers.getOrDefault(questionID, "");
                // Lấy đáp án đúng
                String correctAnswer = dapAnList.stream()
                        .filter(da -> da.getIsRight() == 1)
                        .findFirst()
                        .map(DapAnDTO::getAwContent)
                        .orElse("");

                // Trích xuất ký tự đầu tiên của đáp án đúng để so sánh
                String correctAnswerChar = correctAnswer.length() > 0 ? correctAnswer.substring(0, 1) : "";

                // In ra thông tin để kiểm tra
                System.out.println("Question ID: " + questionID);
                System.out.println("User Answer: " + userAnswer);
                System.out.println("Correct Answer: " + correctAnswer);

                // Đặt màu sắc cho từng nút dựa trên đáp án của người dùng và đáp án đúng
                setColorForButton(btnA, "A", userAnswer, correctAnswerChar);
                setColorForButton(btnB, "B", userAnswer, correctAnswerChar);
                setColorForButton(btnC, "C", userAnswer, correctAnswerChar);
                setColorForButton(btnD, "D", userAnswer, correctAnswerChar);

                // Nếu người dùng chọn đáp án sai, hiển thị màu đỏ cho đáp án sai đó
                if (!userAnswer.equals(correctAnswerChar) && !userAnswer.isEmpty()) {
                    System.out.println("User selected wrong answer: " + userAnswer);
                    if (btnA.getText().equals(userAnswer)) {
                        btnA.setBackground(Color.RED);
                    } else if (btnB.getText().equals(userAnswer)) {
                        btnB.setBackground(Color.RED);
                    } else if (btnC.getText().equals(userAnswer)) {
                        btnC.setBackground(Color.RED);
                    } else if (btnD.getText().equals(userAnswer)) {
                        btnD.setBackground(Color.RED);
                    }
                }
            }
        }
    }

    private void loadDSCauHoi() {
        cauHoi = chBUS.getCauHoiChoDeThi(exCode);
        if (cauHoi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có câu hỏi nào.");
        } else {

            btnQues = new javax.swing.JButton[cauHoi.size()];
            pnlCauHoi.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));

            for (int i = 0; i < cauHoi.size(); i++) {
                btnQues[i] = new javax.swing.JButton(String.valueOf(i + 1));
                btnQues[i].setPreferredSize(new java.awt.Dimension(50, 35));
                btnQues[i].setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btnQues[i].setBackground(java.awt.Color.WHITE);
                final int questionIndex = i;
                btnQues[i].addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        currentQuestionIndex = questionIndex;
                        PrevandNext();
                    }
                });
                pnlCauHoi.add(btnQues[i]);
            }
            pnlCauHoi.revalidate();
            pnlCauHoi.repaint();

        }
    }

    private void loadDapAn(int questionID) {
        dapAn = chBUS.getDapAnChoCauHoi(questionID);
    }

    private void PrevandNext() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < cauHoi.size()) {
            CauHoiDTO ch = cauHoi.get(currentQuestionIndex);
            number.setText(String.valueOf(currentQuestionIndex + 1));
            txtCauHoi.setText(ch.getqContent());
            loadDapAn(ch.getqID());
            if (dapAn.size() >= 4) {
                btnA.setText(dapAn.get(0).getAwContent());
                btnB.setText(dapAn.get(1).getAwContent());
                btnC.setText(dapAn.get(2).getAwContent());
                btnD.setText(dapAn.get(3).getAwContent());

                btnA.setActionCommand(String.valueOf(dapAn.get(0).getIsRight()));
                btnB.setActionCommand(String.valueOf(dapAn.get(1).getIsRight()));
                btnC.setActionCommand(String.valueOf(dapAn.get(2).getIsRight()));
                btnD.setActionCommand(String.valueOf(dapAn.get(3).getIsRight()));
            }

            // Hiển thị kết quả câu hỏi hiện tại
            showQuestionResult(ch.getqID());
        }
    }

    private void answerButtonClicked() {
        if (currentQuestionIndex < cauHoi.size() - 1) {
            currentQuestionIndex++;
            PrevandNext();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        txtSoCauDung = new javax.swing.JTextField();
        txtSoCauSai = new javax.swing.JTextField();
        txtThoiGian = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCauHoi = new javax.swing.JTextArea();
        lblSoCau = new javax.swing.JLabel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        pnlCauHoi = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        number = new javax.swing.JLabel();
        btnA = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        btnB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Điểm:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Số câu đúng: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Số câu sai: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Thời gian: ");

        txtDiem.setEditable(false);
        txtDiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiemActionPerformed(evt);
            }
        });

        txtSoCauDung.setEditable(false);

        txtSoCauSai.setEditable(false);

        txtThoiGian.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiem, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(txtSoCauDung, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(txtSoCauSai)
                    .addComponent(txtThoiGian))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSoCauDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoCauSai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCauHoi.setColumns(20);
        txtCauHoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCauHoi.setRows(5);
        jScrollPane1.setViewportView(txtCauHoi);

        lblSoCau.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSoCau.setText("Câu ");

        btnPrev.setBackground(new java.awt.Color(0, 153, 153));
        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrev.setForeground(java.awt.Color.white);
        btnPrev.setText("Prev");
        btnPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(0, 153, 153));
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNext.setForeground(java.awt.Color.white);
        btnNext.setText("Next");
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        pnlCauHoi.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlCauHoiLayout = new javax.swing.GroupLayout(pnlCauHoi);
        pnlCauHoi.setLayout(pnlCauHoiLayout);
        pnlCauHoiLayout.setHorizontalGroup(
            pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlCauHoiLayout.setVerticalGroup(
            pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setForeground(java.awt.Color.white);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Kết quả");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(321, 321, 321))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        number.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        number.setText("1");

        btnA.setBackground(new java.awt.Color(0, 153, 153));
        btnA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnA.setForeground(java.awt.Color.white);
        btnA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
            }
        });

        btnC.setBackground(new java.awt.Color(0, 153, 153));
        btnC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnC.setForeground(java.awt.Color.white);
        btnC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btnD.setBackground(new java.awt.Color(0, 153, 153));
        btnD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnD.setForeground(java.awt.Color.white);
        btnD.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDActionPerformed(evt);
            }
        });

        btnB.setBackground(new java.awt.Color(0, 153, 153));
        btnB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnB.setForeground(java.awt.Color.white);
        btnB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSoCau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(622, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                    .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(pnlCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnPrev)
                                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNext)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoCau)
                    .addComponent(number))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            int questionID = cauHoi.get(currentQuestionIndex).getqID();
            showQuestionResult(questionID);
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (currentQuestionIndex < cauHoi.size() - 1) {
            currentQuestionIndex++;
            int questionID = cauHoi.get(currentQuestionIndex).getqID();
            showQuestionResult(questionID);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed

    }//GEN-LAST:event_btnAActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed

    }//GEN-LAST:event_btnCActionPerformed

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDActionPerformed

    }//GEN-LAST:event_btnDActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed

    }//GEN-LAST:event_btnBActionPerformed

    private void txtDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KetQuaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KetQuaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KetQuaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KetQuaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        new MyConnect();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KetQuaGUI kqGUI = new KetQuaGUI();
                kqGUI.setLocationRelativeTo(null);
                kqGUI.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSoCau;
    private javax.swing.JLabel number;
    private javax.swing.JPanel pnlCauHoi;
    private javax.swing.JTextArea txtCauHoi;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtSoCauDung;
    private javax.swing.JTextField txtSoCauSai;
    private javax.swing.JTextField txtThoiGian;
    // End of variables declaration//GEN-END:variables
}
