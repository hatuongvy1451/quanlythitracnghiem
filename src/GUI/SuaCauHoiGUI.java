/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.ChuDeBUS;
import DAO.CauHoiDAO;
import DAO.ChuDeDAO;
import DTO.CauHoiDTO;
import DTO.ChuDeDTO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author nhu
 */
public class SuaCauHoiGUI extends javax.swing.JPanel {
    CauHoiDAO quesDAL = new CauHoiDAO();
     ChuDeDAO tpDAO = new ChuDeDAO();
     private int id;
    /**
     * Creates new form UpdateQuestionsGUI
     */
    public SuaCauHoiGUI(int id) {
        initComponents();
        this.id=id;
        HienthiHinhthuc();
        JlableIDQues.setText(String.valueOf(id));
       
        ArrayList<CauHoiDTO> arr = quesDAL.getIDAw(id);
        for (CauHoiDTO questionsDTO : arr) {
            String anh = questionsDTO.getqPictures();
            if(anh.equals("")){
                TextContent.setText(questionsDTO.getqContent());
               
            }else{
                jLabelImages.setText(anh);
            }
             ChuDeDTO tp = tpDAO.getIdTp(questionsDTO.getqTopicID());
             int idParent = tp.getTpParentID();
             String nameLesson = tp.getTpTitle();
             ChuDeDTO tp1 = tpDAO.getIdTp(idParent);
             String nameTp = tp1.getTpTitle();
            jLabelTopics.setText(nameTp);
            jLabelLesson.setText(nameLesson);
            String level = questionsDTO.getqLevel();
            jRadioDe.setSelected("Dễ".endsWith(level));
            jRadioTB.setSelected("Trung bình".endsWith(level));
            jRadioKho.setSelected("Khó".endsWith(level));
            CmbStatus.setSelectedIndex(questionsDTO.getqStatus() == 1 ? 1 : 0);
            loadAnh(anh);
        }
    }
     private String getSelectedRadioButton() {
    if (jRadioDe.isSelected()) return jRadioDe.getText();
    if (jRadioTB.isSelected()) return jRadioTB.getText();
    if (jRadioKho.isSelected()) return jRadioKho.getText();
    return null; 
}

       private void loadAnh(String anh) {
        jLabelImages.setIcon(null);
        jLabelImages.setIcon(getAnhSP(anh));
        jLabelImages.repaint();
    }
           private void HienthiHinhthuc() {
    
    Cmbox.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
       String selectedItem = Cmbox.getSelectedItem().toString(); 
       if ("Văn bản".equals(selectedItem)) {
        TextContent.setEnabled(true);
        TextContent.setEditable(true);
        btnImages.setEnabled(false);
      //  jLabelImages.setIcon(null);
    } else if ("Hình ảnh".equals(selectedItem)) {
        TextContent.setEnabled(false);
       // TextContent.setText("");
        btnImages.setEnabled(true);
    }
   
    }
});

}
    File file;
private static final String DEFAULT_IMAGE_PATH = "images/default.png";

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
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            fileImg = new File("src/images/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            file = new File(src);
        } catch (IOException e) {
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(220, 148, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jRadioDe = new javax.swing.JRadioButton();
        jRadioTB = new javax.swing.JRadioButton();
        jRadioKho = new javax.swing.JRadioButton();
        JlableIDQues = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Update_Questions = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextContent = new javax.swing.JTextArea();
        jLabelImages = new javax.swing.JLabel();
        btnImages = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Cmbox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        CmbStatus = new javax.swing.JComboBox<>();
        jLabelTopics = new javax.swing.JLabel();
        jLabelLesson = new javax.swing.JLabel();

        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("SỬA CÂU HỎI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(224, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 570, 50);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 560));

        jLabel6.setText("Nội dung văn bản");

        jRadioDe.setText("Dễ");
        jRadioDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDeActionPerformed(evt);
            }
        });

        jRadioTB.setText("Trung bình");

        jRadioKho.setText("Khó");
        jRadioKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioKhoActionPerformed(evt);
            }
        });

        JlableIDQues.setBackground(new java.awt.Color(204, 204, 204));
        JlableIDQues.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Chủ đề");

        jLabel4.setText("Bài học");

        jLabel5.setText("Mức dộ");

        Update_Questions.setBackground(new java.awt.Color(0, 153, 153));
        Update_Questions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Update_Questions.setForeground(java.awt.Color.white);
        Update_Questions.setText("Sửa câu hỏi");
        Update_Questions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_QuestionsActionPerformed(evt);
            }
        });

        jLabel9.setText("Mã câu hỏi");

        TextContent.setColumns(20);
        TextContent.setRows(5);
        TextContent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(TextContent);

        jLabelImages.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnImages.setBackground(new java.awt.Color(0, 153, 153));
        btnImages.setForeground(java.awt.Color.white);
        btnImages.setText("Chọn");
        btnImages.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnImages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImagesActionPerformed(evt);
            }
        });

        jLabel3.setText("Hình thức ");

        Cmbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hình ảnh", "Văn bản" }));
        Cmbox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Trang thái");

        CmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        CmbStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTopics.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelLesson.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Update_Questions)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRadioDe)
                                        .addGap(44, 44, 44)
                                        .addComponent(jRadioTB, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioKho))))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(114, 114, 114)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(106, 106, 106)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Cmbox, 0, 164, Short.MAX_VALUE)
                                    .addComponent(jLabelTopics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelLesson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(JlableIDQues, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabelImages, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImages, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelImages, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImages, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JlableIDQues, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabelTopics, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabelLesson, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(36, 36, 36)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel6))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jRadioDe)
                    .addComponent(jRadioTB)
                    .addComponent(jRadioKho))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Update_Questions, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        add(jPanel2);
        jPanel2.setBounds(0, 40, 570, 610);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioDeActionPerformed

    private void jRadioKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioKhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioKhoActionPerformed

    private void Update_QuestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_QuestionsActionPerformed
        // TODO add your handling code here:
 int status = CmbStatus.getSelectedIndex();
        String anh = (file != null) ? file.getName() : "null";
        String level = getSelectedRadioButton();
        CauHoiDTO ques = new CauHoiDTO();
        ques.setqID(id);
        ques.setqContent(TextContent.getText());
        ques.setqPictures(anh);
        ques.setqStatus(status==0 ? 1 : 0);
        ques.setqLevel(level);
            quesDAL.updateQuestions(ques);
       
        luuFileAnh();
        JOptionPane.showMessageDialog(null, "Sửa thành công!");

    }//GEN-LAST:event_Update_QuestionsActionPerformed

    private void btnImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagesActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser("src/images/");
        //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp hình ảnh", "jpg","png","jpeg");
        fileChooser.setFileFilter(filter);
        int returnVl = fileChooser.showOpenDialog(null);
        if(returnVl==JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();

            jLabelImages.setIcon(getAnhSP(file.getPath()));
        }
    }//GEN-LAST:event_btnImagesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbStatus;
    private javax.swing.JComboBox<String> Cmbox;
    private javax.swing.JLabel JlableIDQues;
    private javax.swing.JTextArea TextContent;
    private javax.swing.JButton Update_Questions;
    private javax.swing.JButton btnImages;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImages;
    private javax.swing.JLabel jLabelLesson;
    private javax.swing.JLabel jLabelTopics;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioDe;
    private javax.swing.JRadioButton jRadioKho;
    private javax.swing.JRadioButton jRadioTB;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
