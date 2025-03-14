/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CauHoiBUS;
import BUS.DangNhapBUS;
import BUS.DeThiBUS;
import BUS.KetQuaBUS;
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
public class PnlKetQua extends javax.swing.JPanel {

    CauHoiBUS chBUS = new CauHoiBUS();
    KetQuaBUS kqBUS = new KetQuaBUS();
    DeThiBUS dtBUS = new DeThiBUS();
    DangNhapBUS dnBUS = new DangNhapBUS();
    private ArrayList<CauHoiDTO> cauHoi;
    private ArrayList<DapAnDTO> dapAn;
    private JButton[] btnQues;
    private int currentQuestionIndex = 0;
//    private String exCode;
//    private int userID;
        private String exCode ;
    private int userID = dnBUS.nguoidungLogin.getMaND();
    private Map<Integer, String> userAnswers = new HashMap<>();
    

    /**
     * Creates new form PnlKetQua
     */
    public PnlKetQua() {
        initComponents();
        
        this.exCode = kqBUS.getexCodeTheoUserIDvaExams(userID);
        System.out.println("Kết quả của mã đè: "+ exCode+" của người dùng: "+ userID);
        
        loadDSCauHoi();
        PrevandNext();
        hienThiResult();
        txtDiem.setEnabled(false);
        txtSoCauDung.setEnabled(false);
        txtSoCauSai.setEnabled(false);
        txtThoiGian.setEnabled(false);
        txtexCode.setText(exCode);

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
    String userAnswersJSON = kqBUS.getUserAnswers(userID, exCode);

    if (userAnswersJSON == null || userAnswersJSON.isEmpty()) {
        System.out.println("Warning: userAnswersJSON is null or empty.");
        return;
    }

    try {
        JSONArray jsonArray = new JSONArray(userAnswersJSON);
        // Đảm bảo danh sách cauHoi đã được tải trước
        if (cauHoi == null || cauHoi.isEmpty()) {
            System.out.println("Error: cauHoi list is not initialized.");
            return;
        }

        // Ánh xạ câu trả lời với questionID từ cauHoi
        for (int i = 0; i < jsonArray.length() && i < cauHoi.size(); i++) {
            int questionID = cauHoi.get(i).getqID(); // Lấy questionID từ danh sách câu hỏi
            String answer = jsonArray.getString(i);
            userAnswers.put(questionID, answer);
        }
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

            // Đặt màu nền của các nút về màu mặc định
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

            // Trích xuất ký tự đầu tiên của đáp án đúng (A, B, C, D)
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

            // Nếu người dùng chọn sai, tô màu đỏ cho đáp án sai
            if (!userAnswer.isEmpty() && !userAnswer.equals(correctAnswerChar)) {
                System.out.println("User selected wrong answer: " + userAnswer);
                if (userAnswer.equals("A")) {
                    btnA.setBackground(Color.RED);
                } else if (userAnswer.equals("B")) {
                    btnB.setBackground(Color.RED);
                } else if (userAnswer.equals("C")) {
                    btnC.setBackground(Color.RED);
                } else if (userAnswer.equals("D")) {
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

        jLabel5 = new javax.swing.JLabel();
        number = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCauHoi = new javax.swing.JTextArea();
        pnlCauHoi = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDiem = new javax.swing.JTextField();
        txtSoCauDung = new javax.swing.JTextField();
        txtSoCauSai = new javax.swing.JTextField();
        txtThoiGian = new javax.swing.JTextField();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        txtexCode = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(600, 986));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Câu");

        number.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        number.setText("1:");

        txtCauHoi.setColumns(20);
        txtCauHoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCauHoi.setRows(5);
        jScrollPane1.setViewportView(txtCauHoi);

        pnlCauHoi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnlCauHoiLayout = new javax.swing.GroupLayout(pnlCauHoi);
        pnlCauHoi.setLayout(pnlCauHoiLayout);
        pnlCauHoiLayout.setHorizontalGroup(
            pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlCauHoiLayout.setVerticalGroup(
            pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Điểm:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Số câu đúng: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Số câu sai: ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Thời gian: ");

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
                    .addComponent(jLabel6)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiem, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(txtSoCauDung)
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
                    .addComponent(jLabel6)
                    .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnA.setBackground(new java.awt.Color(0, 153, 153));
        btnA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnA.setForeground(java.awt.Color.white);
        btnA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
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

        btnPrev.setBackground(new java.awt.Color(0, 153, 153));
        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrev.setForeground(java.awt.Color.white);
        btnPrev.setText("Trước");
        btnPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(0, 153, 153));
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNext.setForeground(java.awt.Color.white);
        btnNext.setText("Sau");
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        txtexCode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtexCode.setText("Mã đề");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(number)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlCauHoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 594, Short.MAX_VALUE)
                                .addComponent(txtexCode, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnC, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                                    .addComponent(btnA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(368, 368, 368)))))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(txtexCode)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(number))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiemActionPerformed

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed

    }//GEN-LAST:event_btnAActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed

    }//GEN-LAST:event_btnBActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed

    }//GEN-LAST:event_btnCActionPerformed

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDActionPerformed

    }//GEN-LAST:event_btnDActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel number;
    private javax.swing.JPanel pnlCauHoi;
    private javax.swing.JTextArea txtCauHoi;
    private javax.swing.JTextField txtDiem;
    private javax.swing.JTextField txtSoCauDung;
    private javax.swing.JTextField txtSoCauSai;
    private javax.swing.JTextField txtThoiGian;
    private javax.swing.JLabel txtexCode;
    // End of variables declaration//GEN-END:variables
}
