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
import DTO.KetQuaDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.json.JSONArray;

/**
 *
 * @author HP
 */
public class PnlLamBaiThi extends javax.swing.JPanel {

    DangNhapBUS dnBUS = new DangNhapBUS();
    CauHoiBUS chBUS = new CauHoiBUS();
    KetQuaBUS kqBUS = new KetQuaBUS();
    DeThiBUS dtBUS = new DeThiBUS();
    private ArrayList<CauHoiDTO> cauHoi;
    private ArrayList<DapAnDTO> dapAn;
    private JButton[] btnQues;
    private int currentQuestionIndex = 0;
    private String exCode;
    private int userID = dnBUS.nguoidungLogin.getMaND();

    //tạo mảng lưu câu nào đã được chọn , câu nào chưa được chọn
    private int[] selectedAnswer;
    //thời gian là 30 giây
    private LocalDateTime startTime; // Thời gian bắt đầu thi
    private LocalDateTime endTime; // Lưu thời gian kết thúc bài thi
    private Timer timer;
    private int timeEx;
    private static int testLimit;
    private int remainingAttempts;

    /**
     * Creates new form PnlLamBaiThi
     */
    public PnlLamBaiThi(String exCode, int timeEx, int testLimit) {
        initComponents();

        this.timeEx = timeEx;
        this.exCode = exCode;
        this.testLimit = testLimit; // Thêm dòng này để gán giá trị testLimit

        // Khởi tạo Timer với khoảng thời gian 1000ms (1 giây)
        timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateTimer();
            }
        });

        // Lưu thời gian bắt đầu
        startTime = LocalDateTime.now(); // Gán thời gian bắt đầu khi timer được khởi động

        // Thiết lập thời gian ban đầu cho label time
        long hours = TimeUnit.SECONDS.toHours(timeEx);
        long minutes = TimeUnit.SECONDS.toMinutes(timeEx) % 60;
        long seconds = timeEx % 60;

        time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));

        loadDSCauHoi();
        controls();
        events();
        PrevandNext();

        // Hiển thị số lần làm bài còn lại
        txtSoLan.setText(String.valueOf(testLimit));

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

            // Chuyển đổi thành giờ:phút:giây
            long hours = timeEx / 3600;
            long minutes = (timeEx % 3600) / 60;
            long seconds = timeEx % 60;

            // Hiển thị thời gian còn lại theo định dạng hh:mm:ss
            time.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        } else {
            timer.stop(); // Dừng Timer khi hết thời gian
            JOptionPane.showMessageDialog(this, "Hết thời gian!");

            // Gán thời gian kết thúc ngay khi hết thời gian
            endTime = LocalDateTime.now();

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
        jLabel2 = new javax.swing.JLabel();
        number = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQues = new javax.swing.JTextArea();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        pnlCauHoi = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnFinish = new javax.swing.JButton();
        soLanLamBai = new javax.swing.JLabel();
        txtSoLan = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(600, 986));

        time.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        time.setText("Đồng hồ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Câu ");

        number.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        number.setText("1:");

        txtQues.setColumns(20);
        txtQues.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQues.setRows(5);
        jScrollPane1.setViewportView(txtQues);

        btnA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnA.setForeground(java.awt.Color.white);

        btnB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnB.setForeground(java.awt.Color.white);
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });

        btnC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnC.setForeground(java.awt.Color.white);

        btnD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnD.setForeground(java.awt.Color.white);

        btnPrev.setBackground(new java.awt.Color(0, 153, 153));
        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPrev.setForeground(java.awt.Color.white);
        btnPrev.setText("Trước");

        btnNext.setBackground(new java.awt.Color(0, 153, 153));
        btnNext.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnNext.setForeground(java.awt.Color.white);
        btnNext.setText("Sau");

        pnlCauHoi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        btnFinish.setBackground(new java.awt.Color(0, 153, 153));
        btnFinish.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFinish.setForeground(java.awt.Color.white);
        btnFinish.setText("Hoàn thành");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(384, Short.MAX_VALUE)
                .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(369, 369, 369))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        soLanLamBai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        soLanLamBai.setText("Số lần làm bài còn lại:");

        txtSoLan.setEditable(false);
        txtSoLan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSoLan.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                            .addComponent(btnC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnB, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                                .addComponent(btnD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(number)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCauHoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(soLanLamBai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(time)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soLanLamBai, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(number))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnB, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnC, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btnD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        // Dừng timer
        timer.stop();

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

        // Convert selectedAnswer từ số thành chữ cái (A, B, C, D)
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

        // Convert mảng thành chuỗi JSON
        JSONArray jsonArray = new JSONArray();
        for (String answer : answerLetters) {
            jsonArray.put(answer);
        }
        String answerJson = jsonArray.toString();

        long secondsUsed;

        if (timeEx == 0) {
            secondsUsed = 30; // Nếu hết thời gian, đặt cố định là 30 giây
        } else {
            Duration duration = Duration.between(startTime, endTime);
            secondsUsed = duration.getSeconds();
        }

        long hours = secondsUsed / 3600;
        long minutes = (secondsUsed % 3600) / 60;
        long seconds = secondsUsed % 60;
        String timeUsedFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        String formattedDateTime = startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " " + timeUsedFormatted;

        KetQuaDTO kq = new KetQuaDTO();
        float ketqua = checkResult();

        kq.setUserID(userID);
        kq.setExCode(exCode);
        kq.setRs_anwsers(answerJson);
        kq.setRs_mask(ketqua);
        kq.setDate(formattedDateTime);

        // Lưu kết quả và thông báo cho người dùng
        if (kqBUS.addKetQua(kq)) {
            JOptionPane.showMessageDialog(this, "Bạn đã hoàn thành bài thi");
            
            JButton buttonThi = PnLamBaiThiGUI.btnThi;
            buttonThi.setEnabled(true);

            System.out.println(answerJson);

            // Lấy giá trị testCode từ combobox
            String testCodeold = (String) PnLamBaiThiGUI.cmbMaDe.getSelectedItem();
            String testCode = testCodeold.substring(0, 8);

            // Kiểm tra nếu không tìm thấy giá trị testCode
            if (testCode == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã đề thi");
                return;
            }

            // Lấy exCode từ testCode
            ArrayList<String> exCodes = dtBUS.getExCodesByTestCode(testCode);

            if (exCodes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy mã bài thi cho mã đề thi này.");
                return;
            }

            // Lấy exCode ngẫu nhiên từ danh sách exCodes
            Random rand = new Random();
            String exCode = exCodes.get(rand.nextInt(exCodes.size()));

            // Lấy testLimit từ testCode
            int testlimit = dtBUS.getTestLimitByTestCode(testCode);

            // Kiểm tra nếu không tìm thấy giá trị testLimit
            if (testlimit == -1) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giới hạn số lượt thi cho mã đề thi này.");
                return;
            }

            // Lấy tổng số lần thi đã thực hiện từ bảng result
            int totalAttempts = dtBUS.getTotalAttemptsByTestCode(testCode, userID);

            // Tính số lần thi còn lại
            remainingAttempts = testlimit - totalAttempts;

            // Đảm bảo số lần thi không bao giờ là số âm
            remainingAttempts = Math.max(0, remainingAttempts);

            int option = JOptionPane.showConfirmDialog(this, "Chuyển sang trang kết quả?", "Hoàn thành", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                // Lấy JPanel cha chứa bài thi
                javax.swing.JPanel parentPanel = (javax.swing.JPanel) this.getParent();
                // Thay thế giao diện bài thi bằng giao diện kết quả
                parentPanel.removeAll();
                PnlKetQua pnlKetQua = new PnlKetQua(); // Giao diện kết quả
                parentPanel.setLayout(new BorderLayout());
                parentPanel.add(pnlKetQua, BorderLayout.CENTER);
                parentPanel.revalidate();
                parentPanel.repaint();
            } else {
                this.setVisible(false); // Ẩn JPanel nếu người dùng không muốn chuyển trang
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bài thi bị lỗi");
            System.err.println("Failed to save: " + kq.toString());
        }
    }//GEN-LAST:event_btnFinishActionPerformed

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel number;
    private javax.swing.JPanel pnlCauHoi;
    private javax.swing.JLabel soLanLamBai;
    private javax.swing.JLabel time;
    private javax.swing.JTextArea txtQues;
    private javax.swing.JTextField txtSoLan;
    // End of variables declaration//GEN-END:variables
}
