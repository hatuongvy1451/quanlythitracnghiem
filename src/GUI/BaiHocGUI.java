/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;


import DAO.ChuDeDAO;
import DTO.ChuDeDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nhu
 */
public class BaiHocGUI extends javax.swing.JPanel {
    private int topicID;
    private String topicName;
    ChuDeDAO tpDAL = new ChuDeDAO();
    DefaultTableModel model = new DefaultTableModel();
    
    public BaiHocGUI(int topicID, String topicName) {
        initComponents();
        this.topicID = topicID;
        this.topicName = topicName;
        model.addColumn("Mã bài học");
        model.addColumn("Tên bài học");
        model.addColumn("Tên chủ đề");
        model.addColumn("Trạng thái");
        tbTopics.setModel(model);
        showNameTp(); 
        if(tpDAL.getIdTp(topicID)!=null){
        showTable();
    }
        
    }
    private void showNameTp(){
        JlableNameTp.setText(topicName);
    }
     private void showTable(){
        model.setRowCount(0);
        String nameParent = topicName;
        ArrayList<ChuDeDTO> arr = tpDAL.getListChuDe();
        for (ChuDeDTO topicsDTO : arr) {
            if(topicsDTO.getTpParentID()!=0){
            Object[] row ={topicsDTO.getTpID(),topicsDTO.getTpTitle(),nameParent,topicsDTO.getTpStatus()};
                model.addRow(row);
            }
        }
        tbTopics.addMouseListener(new MouseAdapter(){ 
             public void mouseClicked(MouseEvent e) {
        // Lấy chỉ mục của dòng được chọn
        int selectedRow = tbTopics.getSelectedRow();

        // Truy xuất dữ liệu từ dòng được chọn
        int id = (int) tbTopics.getValueAt(selectedRow, 0);
        String name = (String) tbTopics.getValueAt(selectedRow, 1);
        String nameParent = (String) tbTopics.getValueAt(selectedRow, 2);
        int status = (int) tbTopics.getValueAt(selectedRow, 3);
       
        // Đặt dữ liệu lên các TextField
       jLabelID.setText(String.valueOf(id));
       TextName.setText(name);
       JlableNameTp.setText(nameParent);
       if(status==1){
           CmbStatus.setSelectedIndex(0);
       }else{
           CmbStatus.setSelectedIndex(1);
       }
        
    }
        });
     
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelID = new javax.swing.JLabel();
        JlableNameTp = new javax.swing.JLabel();
        TextName = new javax.swing.JTextField();
        BtnAdd = new javax.swing.JButton();
        BtnUpdate = new javax.swing.JButton();
        BtnDelete = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTopics = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        CmbStatus = new javax.swing.JComboBox<>();

        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("BÀI HỌC");

        jLabel2.setText("Mã bài học");

        jLabel3.setText("Chủ đề");

        jLabel4.setText("Tên bài học");

        jLabelID.setBackground(new java.awt.Color(204, 204, 204));

        BtnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-icon.png"))); // NOI18N
        BtnAdd.setText("Thêm");
        BtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddActionPerformed(evt);
            }
        });

        BtnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-icon.png"))); // NOI18N
        BtnUpdate.setText("Sửa");
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });

        BtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-icon.png"))); // NOI18N
        BtnDelete.setText("Xóa");
        BtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeleteActionPerformed(evt);
            }
        });

        tbTopics.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbTopics);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );

        jLabel6.setText("Trạng thái");

        CmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(BtnAdd)
                .addGap(36, 36, 36)
                .addComponent(BtnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(BtnDelete)
                .addGap(23, 23, 23))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(JlableNameTp, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelID, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabelID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JlableNameTp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnUpdate)
                    .addComponent(BtnDelete)
                    .addComponent(BtnAdd))
                .addGap(28, 28, 28))
        );

        add(jPanel2);
        jPanel2.setBounds(0, 40, 400, 460);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddActionPerformed
        // TODO add your handling code here:
         int status = CmbStatus.getSelectedIndex();
        ChuDeDTO tpDTO = new ChuDeDTO();
        tpDTO.setTpTitle(TextName.getText());
        tpDTO.setTpParentID(topicID);
        if(status==0){
        tpDTO.setTpStatus(1);
        }else{
        tpDTO.setTpStatus(0);  
        }
        try {
            tpDAL.addTopics(tpDTO);
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(BaiHocGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        showTable();
    }//GEN-LAST:event_BtnAddActionPerformed

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        // TODO add your handling code here:
         int status = CmbStatus.getSelectedIndex();
        ChuDeDTO tpDTO = new ChuDeDTO();
        tpDTO.setTpID(Integer.parseInt(jLabelID.getText()));
        tpDTO.setTpTitle(TextName.getText());
         if(status==0){
        tpDTO.setTpStatus(1);
        }else{
        tpDTO.setTpStatus(0);  
        }
        tpDAL.updateTopics(tpDTO);
        JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
        System.out.print( tpDAL.updateTopics(tpDTO));
         showTable();
    }//GEN-LAST:event_BtnUpdateActionPerformed

    private void BtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeleteActionPerformed
        // TODO add your handling code here:
        
         int status = CmbStatus.getSelectedIndex();
        ChuDeDTO tpDTO = new ChuDeDTO();
        tpDTO.setTpParentID(0);
        tpDTO.setTpTitle(TextName.getText());
        if(status==0){
        tpDTO.setTpStatus(1);
        }else{
        tpDTO.setTpStatus(0);  
        }
        try {
            tpDAL.addTopics(tpDTO);
            JOptionPane.showMessageDialog(null, "Thêm thành công!");
        } catch (SQLException ex) {
            Logger.getLogger(ChuDe.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Thêm thất bại!");
        }
        showTable();
    }//GEN-LAST:event_BtnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdd;
    private javax.swing.JButton BtnDelete;
    private javax.swing.JButton BtnUpdate;
    private javax.swing.JComboBox<String> CmbStatus;
    private javax.swing.JLabel JlableNameTp;
    private javax.swing.JTextField TextName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbTopics;
    // End of variables declaration//GEN-END:variables
}
