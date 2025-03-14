/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CauHoiBUS;
import BUS.ChuDeBUS;
import DAO.CauHoiDAO;
import DTO.CauHoiDTO;
import DTO.ChuDeDTO;
import java.awt.Color;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author nhu
 */
public class TaoCauHoiGUI extends javax.swing.JPanel {

    CauHoiBUS quesBUS = new CauHoiBUS();
    CauHoiDAO quesDAL = new CauHoiDAO();
    ChuDeBUS tpBUS = new ChuDeBUS();
    JDialog dialog = new JDialog();
    // private static final String DEFAULT_IMAGE_PATH = "src/main/resources/Images/Pictures/default.png";

    /**
     * Creates new form Cr_Questions
     */
    public TaoCauHoiGUI() {
        initComponents();

        //TextContent.setEditable(false);
        // btnImages.setEnabled(false);
        jLabelImages.setOpaque(true); // Bật chế độ có màu nền
        jLabelImages.setBackground(Color.GRAY); // Màu nền vàng 
        HienthiHinhthuc();
        comboboxTopic();
        showTopic();
        showBaihoc();
    }

    private void showCustomDialog(JPanel panel) {
        dialog.setSize(800, 800);
        dialog.setLocationRelativeTo(null);
        dialog.setContentPane(panel);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    public void comboboxTopic() {

        ArrayList<ChuDeDTO> arr = tpBUS.getListChuDe();
        jComboBoxTopic.addItem("0 - Chọn loại");
        for (ChuDeDTO topicDTO : arr) {
            if (topicDTO.getTpParentID() == 0) {
                jComboBoxTopic.addItem(topicDTO.getTpID() + " - " + topicDTO.getTpTitle());
            }
        }
        jComboBoxTopic.addItem("Khác");
    }

    private void showTopic() {
        jComboBoxTopic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int loai = jComboBoxTopic.getSelectedIndex();
                if (loai == jComboBoxTopic.getItemCount() - 1) {
                    showCustomDialog(new ChuDe());
                    jComboBoxTopic.removeAllItems();
                    comboboxTopic();
                    return;
                }
                if (loai > 0) {
                    String selectedItem = (String) jComboBoxTopic.getSelectedItem();
                    String[] parts = selectedItem.split(" - ");
                    int selectedID = Integer.parseInt(parts[0]);
                    comboboxBaiHoc(selectedID);
                }
            }

        });
    }
//        public void comboboxBaiHoc(int topParentID){
//        jComboBoxBaiHoc.removeAllItems();
//        ArrayList<TopicsDTO> arr = tpBUS.getTp();
//        jComboBoxBaiHoc.addItem("0 - Chọn loại");
//        for (TopicsDTO topicDTO : arr) {
//            if (topicDTO.getTpParentID() == topParentID) { 
//            jComboBoxBaiHoc.addItem(topicDTO.getTpID() + " - " + topicDTO.getTpTitle());
//        }
//        }
//        jComboBoxBaiHoc.addItem("Khác");
//    }
//      private void HienthiHinhthuc(){
//          if(Cmbox.getSelectedIndex()==0){
//              TextContent.setEditable(true);
//              btnImages.setEnabled(false);
//          }else if(Cmbox.getSelectedIndex() == 1){
//               btnImages.setEnabled(true);
//               TextContent.setEditable(false);
//          }
//      }

    private void HienthiHinhthuc() {

        Cmbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = Cmbox.getSelectedItem().toString(); // Lấy giá trị đã chọn trong ComboBox

                if ("Văn bản".equals(selectedItem)) {
                    TextContent.setEnabled(true);
                    TextContent.setEditable(true);
                    btnImages.setEnabled(false);
                    jLabelImages.setIcon(null);
                } else if ("Hình ảnh".equals(selectedItem)) {
                    TextContent.setEnabled(false);
                    TextContent.setText("");
                    btnImages.setEnabled(true);
                }

            }
        });

    }

    public void comboboxBaiHoc(int topParentID) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // Tạo model mới
        model.addElement("0 - Chọn loại");

        ArrayList<ChuDeDTO> arr = tpBUS.getListChuDe();
        for (ChuDeDTO topicDTO : arr) {
            if (topicDTO.getTpParentID() == topParentID) {
                model.addElement(topicDTO.getTpID() + " - " + topicDTO.getTpTitle());
            }
        }
        model.addElement("Khác");

        jComboBoxBaiHoc.setModel(model);
        jComboBoxBaiHoc.revalidate();
        jComboBoxBaiHoc.repaint();
    }

    private void showBaihoc() {
        jComboBoxBaiHoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int loai = jComboBoxBaiHoc.getSelectedIndex();
                if (loai == jComboBoxBaiHoc.getItemCount() - 1) {
                    String selectedTopic = (String) jComboBoxTopic.getSelectedItem();
                    if (selectedTopic != null && !selectedTopic.equals("0 - Chọn loại")) {
                        String[] parts = selectedTopic.split(" - ");
                        int topicID = Integer.parseInt(parts[0]);
                        String topicName = parts[1];
                        showCustomDialog(new BaiHocGUI(topicID, topicName));
                    }
                }
            }
        });
    }

    private String getSelectedRadioButton() {
        if (jRadioDe.isSelected()) {
            return jRadioDe.getText();
        }
        if (jRadioTB.isSelected()) {
            return jRadioTB.getText();
        }
        if (jRadioKho.isSelected()) {
            return jRadioKho.getText();
        }
        return null;
    }

    private void loadAnh(String anh) {

        jLabelImages.setIcon(getAnhSP(anh));
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxBaiHoc = new javax.swing.JComboBox<>();
        jRadioDe = new javax.swing.JRadioButton();
        jRadioTB = new javax.swing.JRadioButton();
        jRadioKho = new javax.swing.JRadioButton();
        JlableIDQues = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxTopic = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Create_Questions = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButtonAnswers = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextContent = new javax.swing.JTextArea();
        jLabelImages = new javax.swing.JLabel();
        btnImages = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Cmbox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        CmbStatus = new javax.swing.JComboBox<>();

        setMaximumSize(new java.awt.Dimension(600, 700));
        setMinimumSize(new java.awt.Dimension(600, 700));
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(600, 700));
        setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 40));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("TẠO CÂU HỎI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        add(jPanel2);
        jPanel2.setBounds(0, 0, 570, 60);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 560));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 560));

        jLabel6.setText("Nội dung văn bản");

        jComboBoxBaiHoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(jRadioDe);
        jRadioDe.setText("Dễ");
        jRadioDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDeActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioTB);
        jRadioTB.setText("Trung bình");

        buttonGroup1.add(jRadioKho);
        jRadioKho.setText("Khó");
        jRadioKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioKhoActionPerformed(evt);
            }
        });

        JlableIDQues.setBackground(new java.awt.Color(204, 204, 204));
        JlableIDQues.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setText("Chủ đề");

        jComboBoxTopic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jComboBoxTopic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTopicActionPerformed(evt);
            }
        });

        jLabel4.setText("Bài học");

        jLabel5.setText("Mức dộ");

        Create_Questions.setBackground(new java.awt.Color(0, 153, 153));
        Create_Questions.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Create_Questions.setForeground(java.awt.Color.white);
        Create_Questions.setText("Tạo câu hỏi");
        Create_Questions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_QuestionsActionPerformed(evt);
            }
        });

        jLabel9.setText("Mã câu hỏi");

        jButtonAnswers.setBackground(new java.awt.Color(0, 153, 153));
        jButtonAnswers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonAnswers.setForeground(java.awt.Color.white);
        jButtonAnswers.setText("Tạo đáp án");
        jButtonAnswers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnswersActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Hình thức ");

        Cmbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hình ảnh", "Văn bản" }));
        Cmbox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Trang thái");

        CmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabelImages, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImages, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(114, 114, 114)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(106, 106, 106)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jComboBoxTopic, 0, 164, Short.MAX_VALUE)
                                                    .addComponent(jComboBoxBaiHoc, 0, 164, Short.MAX_VALUE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(Cmbox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(JlableIDQues, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel7)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(Create_Questions)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonAnswers)
                                    .addGap(25, 25, 25))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(254, 254, 254)
                                .addComponent(jRadioDe)
                                .addGap(42, 42, 42)
                                .addComponent(jRadioTB, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioKho))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(291, 291, 291)
                                .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(94, 108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabelImages, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(btnImages, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JlableIDQues, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxTopic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jComboBoxBaiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cmbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioDe)
                    .addComponent(jRadioTB)
                    .addComponent(jRadioKho)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Create_Questions, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAnswers, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 60, 570, 680);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioDeActionPerformed

    private void jComboBoxTopicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTopicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTopicActionPerformed

    private void Create_QuestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_QuestionsActionPerformed
        // TODO add your handling code here:
        String anh = (file != null) ? file.getName() : "null";
        String loai = (String) jComboBoxBaiHoc.getSelectedItem();
        String[] loaiTmp = loai.split(" - ");
        int maLoai = Integer.parseInt(loaiTmp[0]);
        String level = getSelectedRadioButton();
        int status = CmbStatus.getSelectedIndex();
        CauHoiDTO ques = new CauHoiDTO();
        ques.setqContent(TextContent.getText());
        ques.setqPictures(anh);
        ques.setqTopicID(maLoai);
        ques.setqLevel(level);
        ques.setqStatus(status == 0 ? 1 : 0);
        try {
            int getID = quesDAL.addQuestions(ques);
            if (getID > 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công!");
                JlableIDQues.setText(String.valueOf(getID));
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaoCauHoiGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        luuFileAnh();

    }//GEN-LAST:event_Create_QuestionsActionPerformed

    private void jRadioKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioKhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioKhoActionPerformed

    private void jButtonAnswersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnswersActionPerformed
        // TODO add your handling code here:

        if (JlableIDQues.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng tạo câu hỏi!");
        } else {
            int getID = Integer.parseInt(JlableIDQues.getText());
            String content = null;
            CauHoiDTO ques = quesDAL.getQuestionById(getID);
            if (ques.getqContent().isEmpty()) {
                content = ques.getqPictures();
                showCustomDialog(new TaoDapAnGUI(getID, content));
            } else {
                content = ques.getqContent();
                showCustomDialog(new TaoDapAnGUI(getID, content));
            }
        }


    }//GEN-LAST:event_jButtonAnswersActionPerformed

    private void btnImagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImagesActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser("src/images/");
        //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp hình ảnh", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVl = fileChooser.showOpenDialog(null);
        if (returnVl == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();

            jLabelImages.setIcon(getAnhSP(file.getPath()));
        }
    }//GEN-LAST:event_btnImagesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CmbStatus;
    private javax.swing.JComboBox<String> Cmbox;
    private javax.swing.JButton Create_Questions;
    private javax.swing.JLabel JlableIDQues;
    private javax.swing.JTextArea TextContent;
    private javax.swing.JButton btnImages;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAnswers;
    private javax.swing.JComboBox<String> jComboBoxBaiHoc;
    private javax.swing.JComboBox<String> jComboBoxTopic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImages;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioDe;
    private javax.swing.JRadioButton jRadioKho;
    private javax.swing.JRadioButton jRadioTB;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
