/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.CauHoiBUS;
import BUS.KetQuaBUS;
import DAO.MyConnect;
import DTO.CauHoiDTO;
import DTO.DapAnDTO;
import DTO.KetQuaDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.json.JSONArray;

/**
 *
 * @author HP
 */
public class TestGUI extends javax.swing.JFrame {

    CauHoiBUS chBUS = new CauHoiBUS();
    KetQuaBUS kqBUS = new KetQuaBUS();
    private ArrayList<CauHoiDTO> cauHoi;
    private ArrayList<DapAnDTO> dapAn;
    private JButton[] btnQues;
    private int currentQuestionIndex = 0;
    private String exCode = "EX001";
    private int userID = 2;

    //tạo mảng lưu câu nào đã được chọn , câu nào chưa được chọn
    private int[] selectedAnswer;
    //thời gian là 30 giây
    private LocalDateTime startTime; // Thời gian bắt đầu thi
    private LocalDateTime endTime; // Lưu thời gian kết thúc bài thi
    private Timer timer;
    private int timeEx = 100;

    /**
     * Creates new form TestGUI
     */
    public TestGUI() {
        initComponents();

        // Khởi tạo Timer với khoảng thời gian 1000ms (1 giây)
        timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTimer();
            }
        });

        // Lưu thời gian bắt đầu
        startTime = LocalDateTime.now(); // Gán thời gian bắt đầu khi timer được khởi độn
        // Thiết lập thời gian ban đầu cho label time
        time.setText(String.valueOf(timeEx)); // Hiển thị thời gian ban đầu

        loadDSCauHoi();
        controls();
        events();
        PrevandNext();

        // Bắt đầu Timer khi câu hỏi được load lên
        if (timer != null) { // Kiểm tra xem timer đã được khởi tạo chưa
            timer.start();
        } else {
            System.err.println("Timer chưa được khởi tạo!");
        }

    }

    private void updateTimer() {
        if (timeEx > 0) {
            timeEx--; // Giảm thời gian còn lại
            time.setText(String.valueOf(timeEx)); // Hiển thị thời gian còn lại
        } else {
            timer.stop(); // Dừng Timer khi hết thời gian
            JOptionPane.showMessageDialog(this, "Hết thời gian!");
            // Gán thời gian kết thúc ngay khi hết thời gian
            endTime = LocalDateTime.now();
//            checkResult(); 
// Tự động kiểm tra kết quả khi hết thời gian
            btnFinish.doClick();
            btnFinish.setEnabled(false);
        }
    }

    private void loadDSCauHoi() {
        cauHoi = chBUS.getCauHoiChoDeThi(exCode);
        if (cauHoi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có câu hỏi nào.");
        } else {

            btnQues = new javax.swing.JButton[cauHoi.size()];
            //khởi tạo giá trị mặc định cho mỗi câu hỏi là -1
            selectedAnswer = new int[cauHoi.size()];
            Arrays.fill(selectedAnswer, -1);

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
            txtQues.setText(ch.getqContent());
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
            updateAnswerColorsOnQuestionChange();
        }
    }

    private void updateAnswerColorsOnQuestionChange() {
        // Đặt lại màu tất cả các nút về mặc định
        btnA.setBackground(new Color(0, 153, 153));
        btnB.setBackground(new Color(0, 153, 153));
        btnC.setBackground(new Color(0, 153, 153));
        btnD.setBackground(new Color(0, 153, 153));

        // Nếu câu hiện tại đã chọn đáp án, tô màu cho đáp án đó
        if (selectedAnswer[currentQuestionIndex] != -1) {
            switch (selectedAnswer[currentQuestionIndex]) {
                case 0 ->
                    btnA.setBackground(Color.RED);
                case 1 ->
                    btnB.setBackground(Color.RED);
                case 2 ->
                    btnC.setBackground(Color.RED);
                case 3 ->
                    btnD.setBackground(Color.RED);
            }
        }
    }

    private void checkAnswer(java.awt.event.ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        if (actionCommand.equals("1")) {
            JOptionPane.showMessageDialog(this, "Đáp án đúng!");
        } else {
            JOptionPane.showMessageDialog(this, "Đáp án sai. Vui lòng thử lại.");
        }
    }

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentQuestionIndex < cauHoi.size() - 1) {
            currentQuestionIndex++;
            PrevandNext();
        }
    }

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            PrevandNext();
        }
    }

    public void controls() {
        btnA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnC.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPrev.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFinish.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void updateAnswerColors(JButton selectedButton) {
        // Đặt lại màu mặc định cho tất cả các nút
        btnA.setBackground(new Color(0, 153, 153));
        btnB.setBackground(new Color(0, 153, 153));
        btnC.setBackground(new Color(0, 153, 153));
        btnD.setBackground(new Color(0, 153, 153));

        // Đổi màu cho nút được chọn theo màu của số câu hỏi
        selectedButton.setBackground(Color.RED); // Lấy màu của số câu
    }

    public void events() {
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                checkAnswer(evt);
                //Lưu đáp án A với chỉ số là 0
                selectedAnswer[currentQuestionIndex] = 0;
                updateAnswerColors(btnA);
                //đổi màu cho nút đã chọn đáp án để biết dấu hiệu đã trả lời 
                btnQues[currentQuestionIndex].setBackground(Color.red);

            }
        });

        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                checkAnswer(evt);
//Lưu đáp án B với chỉ số là 1
                selectedAnswer[currentQuestionIndex] = 1;
                updateAnswerColors(btnB);
                //đổi màu cho nút đã chọn đáp án để biết dấu hiệu đã trả lời 
                btnQues[currentQuestionIndex].setBackground(Color.red);
            }
        });

        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                checkAnswer(evt);
//Lưu đáp án C với chỉ số là 2
                selectedAnswer[currentQuestionIndex] = 2;
                updateAnswerColors(btnC);
                //đổi màu cho nút đã chọn đáp án để biết dấu hiệu đã trả lời 
                btnQues[currentQuestionIndex].setBackground(Color.red);
            }
        });

        btnD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                checkAnswer(evt);
//Lưu đáp án D với chỉ số là 3
                selectedAnswer[currentQuestionIndex] = 3;
                updateAnswerColors(btnD);
                //đổi màu cho nút đã chọn đáp án để biết dấu hiệu đã trả lời 
                btnQues[currentQuestionIndex].setBackground(Color.red);
            }
        });
    }

    //Sau khi hoàn thành các câu thì ta sẽ có được bảng với danh sách câu trả lời
    private float checkResult() {
        int score = 0;
        //Gỉa sử mỗi đáp án chính xác có chỉ số đúng lưu trong dối tượng đáp án 
        for (int i = 0; i < cauHoi.size(); i++) {
            int correctAnswerIndex = getCorrectAnserIndex(cauHoi.get(i));
            if (selectedAnswer[i] == correctAnswerIndex) {
                score++;
            }
        }
        //cần chuyển sang gui hiển thị kết quả và đáp án 
        return score;
    }

    private int getCorrectAnserIndex(CauHoiDTO ch) {
        ArrayList<DapAnDTO> dapAnList = chBUS.getDapAnChoCauHoi(ch.getqID());
        for (int i = 0; i < dapAnList.size(); i++) {
            if (dapAnList.get(i).getIsRight() == 1) //gia su 1 nghia la dap an dung
            {
                return i;
            }
        }

        return -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        time = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQues = new javax.swing.JTextArea();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        pnlCauHoi = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        number = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        time.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        time.setText("Đồng hồ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Câu ");

        txtQues.setColumns(20);
        txtQues.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQues.setRows(5);
        jScrollPane1.setViewportView(txtQues);

        btnA.setBackground(new java.awt.Color(0, 153, 153));
        btnA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnA.setForeground(java.awt.Color.white);
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
            }
        });

        btnB.setBackground(new java.awt.Color(0, 153, 153));
        btnB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnB.setForeground(java.awt.Color.white);

        btnC.setBackground(new java.awt.Color(0, 153, 153));
        btnC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnC.setForeground(java.awt.Color.white);

        btnD.setBackground(new java.awt.Color(0, 153, 153));
        btnD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnD.setForeground(java.awt.Color.white);

        pnlCauHoi.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlCauHoiLayout = new javax.swing.GroupLayout(pnlCauHoi);
        pnlCauHoi.setLayout(pnlCauHoiLayout);
        pnlCauHoiLayout.setHorizontalGroup(
            pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlCauHoiLayout.setVerticalGroup(
            pnlCauHoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 175, Short.MAX_VALUE)
        );

        btnPrev.setBackground(new java.awt.Color(0, 153, 153));
        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrev.setForeground(java.awt.Color.white);
        btnPrev.setText("Prev");

        btnNext.setBackground(new java.awt.Color(0, 153, 153));
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNext.setForeground(java.awt.Color.white);
        btnNext.setText("Next");

        btnFinish.setBackground(new java.awt.Color(0, 153, 153));
        btnFinish.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFinish.setForeground(new java.awt.Color(255, 255, 255));
        btnFinish.setText("Finish");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        number.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        number.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(782, Short.MAX_VALUE)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(pnlCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnA, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                    .addComponent(btnC, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                    .addComponent(btnPrev, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnB, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                    .addComponent(btnD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNext))))))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(372, 372, 372))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(number))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnA, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(pnlCauHoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        timer.stop(); // Dừng timer

        // Đảm bảo thời gian kết thúc được lưu đúng
        if (endTime == null) {
            endTime = LocalDateTime.now();
        }

        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);
        btnPrev.setEnabled(false);
        btnNext.setEnabled(false);
        btnFinish.setEnabled(false);

        // Convert selectedAnswer from numbers to letters (A, B, C, D)
        String[] answerLetters = new String[selectedAnswer.length];
        for (int i = 0; i < selectedAnswer.length; i++) {
            switch (selectedAnswer[i]) {
                case 0 ->
                    answerLetters[i] = "A";
                case 1 ->
                    answerLetters[i] = "B";
                case 2 ->
                    answerLetters[i] = "C";
                case 3 ->
                    answerLetters[i] = "D";
                default ->
                    answerLetters[i] = "?";
            }
        }

        // Convert array to JSON string
        JSONArray jsonArray = new JSONArray();
        for (String answer : answerLetters) {
            jsonArray.put(answer);
        }
        String answerJson = jsonArray.toString();

        // Chỉ khai báo biến một lần
        long secondsUsed;

        if (timeEx == 0) {
            secondsUsed = 30; // Nếu hết thời gian, đặt cố định là 30 giây
        } else {
            Duration duration = Duration.between(startTime, endTime);
            secondsUsed = duration.getSeconds();
        }

        // Chuyển đổi thời gian đã dùng thành định dạng HH:mm:ss
        long hours = secondsUsed / 3600;
        long minutes = (secondsUsed % 3600) / 60;
        long seconds = secondsUsed % 60;
        String timeUsedFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        // Định dạng ngày tháng thi và thời gian đã dùng
        String formattedDateTime = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " " + timeUsedFormatted;

        // Create result object
        KetQuaDTO kq = new KetQuaDTO();
        float ketqua = checkResult();

        kq.setUserID(userID);
        kq.setExCode(exCode);
        kq.setRs_anwsers(answerJson);
        kq.setRs_mask(ketqua);
        kq.setDate(formattedDateTime); // Lưu ngày tháng và thời gian đã dùng

        // Save result and notify user
        if (kqBUS.addKetQua(kq)) {
            JOptionPane.showMessageDialog(this, "Bạn đã hoàn thành bài thi");
            System.out.println(answerJson);
            KetQuaGUI kqGUI = new KetQuaGUI();
            this.dispose();
            kqGUI.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Bài thi bị lỗi");
            System.err.println("Failed to save: " + kq.toString());
        }
    }//GEN-LAST:event_btnFinishActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MyConnect();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TestGUI testGUI = new TestGUI();
                testGUI.setLocationRelativeTo(null);
                testGUI.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel number;
    private javax.swing.JPanel pnlCauHoi;
    private javax.swing.JLabel time;
    private javax.swing.JTextArea txtQues;
    // End of variables declaration//GEN-END:variables
}
