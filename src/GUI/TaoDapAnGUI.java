/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.DapAnBUS;
import DTO.DapAnDTO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nhu
 */
public class TaoDapAnGUI extends javax.swing.JPanel {
    private int qID;
    private String content;
    DapAnBUS anBUS = new DapAnBUS();
    private boolean isClickable = true;
    /**
     * Creates new form AnswersQuestionsGUI
     */
    public TaoDapAnGUI(int qID,String content) {
        this.qID =qID;
        this.content=content;
        initComponents();
       // getSelectedRadioButton();
       showQuestion();
       HienthiHinhthuc();
    }
     private void HienthiHinhthuc() {
    
    Cmbox.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
       String selectedItem = Cmbox.getSelectedItem().toString(); // Lấy giá trị đã chọn trong ComboBox

    if ("Văn bản".equals(selectedItem)) {
        jTextAreaAnswers.setEnabled(true);
        jTextAreaAnswers.setEditable(true);
        jlableImages.setEnabled(false);
        jlableImages.setIcon(null);
        isClickable=false;
    } else if ("Hình ảnh".equals(selectedItem)) {
        jTextAreaAnswers.setEnabled(false);
        jTextAreaAnswers.setText("");
        jlableImages.setEnabled(true);
         isClickable=true;
    }
   
    }
});

}
   private void showQuestion() {
    jLabelqID.setText(String.valueOf(qID));

    // Debug: In ra content để kiểm tra nó chứa gì
    System.out.println("📌 Giá trị content: " + content);

    if (content == null || content.trim().isEmpty() || content.equalsIgnoreCase("null")) {
        System.out.println("⚠ Content rỗng hoặc null -> Hiển thị ảnh mặc định");
        jLabelContent.setIcon(getAnhSP(DEFAULT_IMAGE_PATH)); // Hiển thị ảnh mặc định
        jLabelContent.setText("");
    } else {
        // Kiểm tra nếu content là đường dẫn ảnh hợp lệ
        if (content.toLowerCase().endsWith(".jpg") || content.toLowerCase().endsWith(".png") || content.toLowerCase().endsWith(".jpeg")) {
            File fileImg = new File(content);
            if (fileImg.exists()) {
                System.out.println("✅ Ảnh tồn tại -> Hiển thị: " + content);
                jLabelContent.setIcon(getAnhSP(content));
                jLabelContent.setText("");
            } else {
                System.out.println("❌ Ảnh không tồn tại -> Hiển thị ảnh mặc định");
                jLabelContent.setIcon(getAnhSP(DEFAULT_IMAGE_PATH));
                jLabelContent.setText("");
            }
        } else {
            System.out.println("📄 Content không phải ảnh -> Hiển thị text");
            jLabelContent.setText(content);
            jLabelContent.setIcon(null);
        }
    }
       loadAnh(content);
    // Cập nhật lại giao diện
    jLabelContent.revalidate();
    jLabelContent.repaint();
}


    
      

   
        private void loadAnh(String anh) {
        
        jLabelContent.setIcon(getAnhSP(anh));
    }
   File file;
private static final String DEFAULT_IMAGE_PATH = "src/images/default.png";

private void luuFileAnh() {
    // Kiểm tra nếu file null hoặc là ảnh mặc định thì không lưu
    if (file == null || file.getPath().equals(DEFAULT_IMAGE_PATH)) {
        System.out.println("⚠ Ảnh là mặc định hoặc không có file để lưu.");
        return;
    }

    try {
        // Đọc ảnh từ file
        BufferedImage bImage = ImageIO.read(file);
        if (bImage == null) {
            System.out.println("❌ Không thể đọc dữ liệu ảnh từ file.");
            return;
        }

        // Tạo đường dẫn mới và lưu ảnh
        File savedFile = new File("src/images/" + file.getName());
        ImageIO.write(bImage, "png", savedFile);
        System.out.println("✅ Ảnh đã được lưu: " + savedFile.getAbsolutePath());

        // Cập nhật đường dẫn file sau khi lưu
        file = savedFile;

    } catch (IOException e) {
        System.out.println("❌ Lỗi khi lưu ảnh: " + e.getMessage());
    }
}

private ImageIcon getAnhSP(String src) {
    if (src == null || src.trim().isEmpty() || src.equalsIgnoreCase("null")) {
        src = DEFAULT_IMAGE_PATH;
    }

    File fileImg = new File(src);
    if (!fileImg.exists()) {
        System.out.println("⚠ Ảnh không tồn tại, dùng ảnh mặc định.");
        fileImg = new File(DEFAULT_IMAGE_PATH);
    }

    try {
        BufferedImage img = ImageIO.read(fileImg);
        if (img == null) {
            System.out.println("❌ Không thể đọc ảnh từ file, dùng ảnh mặc định.");
            return new ImageIcon(DEFAULT_IMAGE_PATH);
        }

        Image dimg = img.getScaledInstance(220, 148, Image.SCALE_SMOOTH);
        return new ImageIcon(dimg);

    } catch (IOException e) {
        System.out.println("❌ Lỗi khi đọc ảnh: " + e.getMessage());
        return new ImageIcon(DEFAULT_IMAGE_PATH);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelContent = new javax.swing.JLabel();
        jButtonCreAw = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jRadioA = new javax.swing.JRadioButton();
        jRadioB = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelqID = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        JlableAwID = new javax.swing.JLabel();
        cmbNumberAws = new javax.swing.JComboBox<>();
        CmbStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAnswers = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        Cmbox = new javax.swing.JComboBox<>();
        jlableImages = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setMinimumSize(new java.awt.Dimension(600, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("TẠO CÂU HỎI");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("TẠO ĐÁP ÁN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel2)
                .addContainerGap(211, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 530, 60);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 560));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Nội dung câu hỏi");

        jLabelContent.setBackground(new java.awt.Color(102, 255, 255));
        jLabelContent.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelContent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonCreAw.setBackground(new java.awt.Color(0, 153, 153));
        jButtonCreAw.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonCreAw.setForeground(java.awt.Color.white);
        jButtonCreAw.setText("Tạo đáp án");
        jButtonCreAw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreAwActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Đáp án đúng");

        buttonGroup1.add(jRadioA);
        jRadioA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioA.setText("Đúng");
        jRadioA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioB);
        jRadioB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioB.setText("Sai");
        jRadioB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Trạng thái");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Số đáp án");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Mã câu hỏi");

        jLabelqID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelqID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mã câu trả lời");

        JlableAwID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JlableAwID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbNumberAws.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbNumberAws.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5" }));
        cmbNumberAws.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        CmbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        CmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        CmbStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextAreaAnswers.setColumns(20);
        jTextAreaAnswers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextAreaAnswers.setRows(5);
        jTextAreaAnswers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTextAreaAnswers);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Hình thức");

        Cmbox.setEditable(true);
        Cmbox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Cmbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Văn bản", "Hình ảnh" }));
        Cmbox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jlableImages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Default.png"))); // NOI18N
        jlableImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlableImagesMouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setMinimumSize(new java.awt.Dimension(600, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(java.awt.Color.white);
        jLabel11.setText("TẠO ĐÁP ÁN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelContent, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRadioA)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRadioB)
                                        .addGap(9, 9, 9))
                                    .addComponent(CmbStatus, 0, 167, Short.MAX_VALUE)
                                    .addComponent(Cmbox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbNumberAws, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JlableAwID, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                    .addComponent(jLabelqID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jButtonCreAw)
                                .addGap(222, 222, 222))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jlableImages, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelqID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(JlableAwID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cmbNumberAws, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioA)
                    .addComponent(jRadioB))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabelContent, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel6)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jlableImages, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 51, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButtonCreAw, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(jPanel2);
        jPanel2.setBounds(0, 50, 530, 650);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioBActionPerformed

    private void jRadioAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioAActionPerformed
    private int currentCount = 0;
    private void jButtonCreAwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreAwActionPerformed
        // TODO add your handling code here:
        // int status = getSelectedRadioButton();
            int n = Integer.parseInt((String) cmbNumberAws.getSelectedItem());
             if (currentCount >= n) {
        jLabelqID.setText(""); // Khi đạt n lần, label bị xóa
        JOptionPane.showMessageDialog(null, "Bạn đã đạt giới hạn số lần tạo!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        return; // Dừng thực thi hàm này
    }

    // Nếu chưa đạt n lần, tiếp tục tạo
    if (!jLabelqID.getText().trim().isEmpty()) {
        String anh = (file != null) ? file.getName() : "null";
        DapAnDTO answ = new DapAnDTO();
        answ.setAwContent(!jTextAreaAnswers.getText().trim().isEmpty() ? jTextAreaAnswers.getText() : "null");
        answ.setAwPictures(anh);
        answ.setIsRight(jRadioA.isSelected() ? 1 : 0);
        answ.setqID(qID);
        answ.setAwStatus(1);
        
        try {
            JOptionPane.showInternalMessageDialog(null, anBUS.addAnswers(answ));
        } catch (SQLException ex) {
            Logger.getLogger(TaoDapAnGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        luuFileAnh();

        // Tăng số lần bấm
        currentCount++;
    } else {
        JOptionPane.showMessageDialog(null, "Thêm thất bại");
    }
           
        

    }//GEN-LAST:event_jButtonCreAwActionPerformed

    private void jlableImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlableImagesMouseClicked
        // TODO add your handling code here:
        if (!isClickable) return;
        JFileChooser fileChooser = new JFileChooser("src/images/");
                    //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp hình ảnh", "jpg","png","jpeg");
                    fileChooser.setFileFilter(filter);
                    int returnVl = fileChooser.showOpenDialog(null);
                    if(returnVl==JFileChooser.APPROVE_OPTION){
                            file = fileChooser.getSelectedFile();
                          
                           jlableImages.setIcon(getAnhSP(file.getPath()));
                       }
    }//GEN-LAST:event_jlableImagesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbStatus;
    private javax.swing.JComboBox<String> Cmbox;
    private javax.swing.JLabel JlableAwID;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbNumberAws;
    private javax.swing.JButton jButtonCreAw;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelContent;
    private javax.swing.JLabel jLabelqID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioA;
    private javax.swing.JRadioButton jRadioB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaAnswers;
    private javax.swing.JLabel jlableImages;
    // End of variables declaration//GEN-END:variables
}
