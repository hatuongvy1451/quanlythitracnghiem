/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.CauHoiDAO;
import DAO.DapAnDAO;
import DTO.CauHoiDTO;
import DTO.DapAnDTO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author nhu
 */
public class SuaDapAnGUI extends javax.swing.JPanel {
     private int id ;
     private String content ;
    DapAnDAO anDAL = new DapAnDAO();
    private boolean isClickable = true;
    CauHoiDAO quesDAO = new CauHoiDAO();
    /**
     * Creates new form SuaDapAnGUI
     */
    public SuaDapAnGUI(int id) {
        initComponents();
         HienthiHinhthuc();
         
        this.id=id;
         ArrayList<DapAnDTO> arr = anDAL.getIDAw(id);
        JlableIDAw.setText(String.valueOf(id));
        
        for (DapAnDTO answersDTO : arr) {
            String anh =answersDTO.getAwPictures();
            if(answersDTO.getAwContent().isEmpty()){
                jlableImages.setText(anh);
            }else{
                jTextAreaAnswers.setText(answersDTO.getAwContent());
            }
            CauHoiDTO ques = quesDAO.getQuestionById(answersDTO.getqID());
           
            if(ques.getqContent().isEmpty()){
            content=ques.getqPictures();
                showQuestion(content);
        }else{
             content=ques.getqContent();
                showQuestion(content);
        }
            
             int isRight =answersDTO.getIsRight();
             jRadioA.setSelected("1".equals(String.valueOf(isRight)));
             jRadioB.setSelected("0".endsWith(String.valueOf(isRight)));
             CmbStatus.setSelectedIndex(answersDTO.getAwStatus() == 1 ? 0 : 1);
             loadAnh(anh);
        }
    }
     private void showQuestion(String content) {
 //   jLabelqID.setText(String.valueOf(qID));

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
    private void loadAnh(String anh) {

        jlableImages.setIcon(getAnhSP(anh));
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelContent = new javax.swing.JLabel();
        jButtonUpdateAw = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jRadioA = new javax.swing.JRadioButton();
        jRadioB = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        JlableIDAw = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        CmbStatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAnswers = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        Cmbox = new javax.swing.JComboBox<>();
        jlableImages = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("SỬA ĐÁP ÁN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel2)
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 600, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 560));

        jLabel6.setText("Nội dung câu hỏi");

        jLabelContent.setBackground(new java.awt.Color(102, 255, 255));
        jLabelContent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonUpdateAw.setBackground(new java.awt.Color(0, 153, 153));
        jButtonUpdateAw.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonUpdateAw.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdateAw.setText("Sửa đáp án");
        jButtonUpdateAw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateAwActionPerformed(evt);
            }
        });

        jLabel3.setText("Đáp án đúng");

        jRadioA.setText("Đúng");
        jRadioA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAActionPerformed(evt);
            }
        });

        jRadioB.setText("Sai");
        jRadioB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBActionPerformed(evt);
            }
        });

        jLabel4.setText("Trạng thái");

        JlableIDAw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Mã câu trả lời");

        CmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        CmbStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextAreaAnswers.setColumns(20);
        jTextAreaAnswers.setRows(5);
        jTextAreaAnswers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTextAreaAnswers);

        jLabel5.setText("Hình thức");

        Cmbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Văn bản", "Hình ảnh" }));
        Cmbox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jlableImages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Default.png"))); // NOI18N
        jlableImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlableImagesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(241, 241, 241)
                                .addComponent(jButtonUpdateAw))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlableImages, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(60, 60, 60)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(JlableIDAw, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(CmbStatus, 0, 167, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(jLabel3)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jRadioA)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jRadioB)
                                            .addGap(31, 31, 31)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelContent, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JlableIDAw, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioA)
                    .addComponent(jRadioB))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelContent, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlableImages, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButtonUpdateAw, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        add(jPanel2);
        jPanel2.setBounds(0, 40, 600, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUpdateAwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateAwActionPerformed
        // TODO add your handling code here:
            int status = CmbStatus.getSelectedIndex();
            String anh = (file != null) ? file.getName() : "null";
            DapAnDTO answ = new DapAnDTO();
            answ.setAwContent(!jTextAreaAnswers.getText().trim().isEmpty() ? jTextAreaAnswers.getText() : "null");
            answ.setAwPictures(anh);
            answ.setIsRight(jRadioA.isSelected() ? 1 : 0);
            answ.setqID(id);
            answ.setAwStatus(status==0 ? 1 : 0);
            anDAL.updateAnswers(answ);
            luuFileAnh();
            JOptionPane.showMessageDialog(null, "Sửa thành công!");
        

    }//GEN-LAST:event_jButtonUpdateAwActionPerformed

    private void jRadioAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioAActionPerformed

    private void jRadioBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioBActionPerformed

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
    private javax.swing.JLabel JlableIDAw;
    private javax.swing.JButton jButtonUpdateAw;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelContent;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioA;
    private javax.swing.JRadioButton jRadioB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaAnswers;
    private javax.swing.JLabel jlableImages;
    // End of variables declaration//GEN-END:variables
}
