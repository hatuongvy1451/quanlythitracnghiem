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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nhu
 */
public class ChuDe extends javax.swing.JPanel {
    DefaultTableModel model = new DefaultTableModel();
    ChuDeDAO tpDAL = new ChuDeDAO();
    public ChuDe() {
        initComponents();
        model.addColumn("Mã chủ đề");
        model.addColumn("Tên chủ đề");
        model.addColumn("Trạng thái");
        tbTopics.setModel(model);
        
       showTable();
       
        
    }
    private void showTable(){
        model.setRowCount(0);
        ArrayList<ChuDeDTO> arr = tpDAL.getListChuDe();
        for (ChuDeDTO topicsDTO : arr) {
            if(topicsDTO.getTpParentID()==0){
            Object[] row ={topicsDTO.getTpID(),topicsDTO.getTpTitle(),topicsDTO.getTpStatus()};
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
        int status = (int) tbTopics.getValueAt(selectedRow, 2);
       
        // Đặt dữ liệu lên các TextField
       jLabelID.setText(String.valueOf(id));
       TextTp.setText(name);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAddTp = new javax.swing.JButton();
        ButtonUpdate = new javax.swing.JButton();
        jLabelID = new javax.swing.JLabel();
        ButtonDelete = new javax.swing.JButton();
        TextTp = new javax.swing.JTextField();
        CmbStatus = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTopics = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("CHỦ ĐỀ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabel1)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 40);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 560));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Trạng thái");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã chủ đề");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tên chủ đề");

        btnAddTp.setBackground(new java.awt.Color(0, 153, 153));
        btnAddTp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAddTp.setForeground(java.awt.Color.white);
        btnAddTp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-icon.png"))); // NOI18N
        btnAddTp.setText("Thêm");
        btnAddTp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTpActionPerformed(evt);
            }
        });

        ButtonUpdate.setBackground(new java.awt.Color(0, 153, 153));
        ButtonUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ButtonUpdate.setForeground(java.awt.Color.white);
        ButtonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-icon.png"))); // NOI18N
        ButtonUpdate.setText("Sửa");
        ButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdateActionPerformed(evt);
            }
        });

        jLabelID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ButtonDelete.setBackground(new java.awt.Color(0, 153, 153));
        ButtonDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ButtonDelete.setForeground(java.awt.Color.white);
        ButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-icon.png"))); // NOI18N
        ButtonDelete.setText("Xóa");
        ButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDeleteActionPerformed(evt);
            }
        });

        TextTp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        CmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt động", "Không hoạt động" }));
        CmbStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jScrollPane3.setViewportView(tbTopics);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2))
                                .addGap(35, 35, 35))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAddTp)
                                .addGap(15, 15, 15)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TextTp)
                                    .addComponent(CmbStatus, 0, 166, Short.MAX_VALUE))
                                .addGap(15, 15, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(ButtonUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(ButtonDelete))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addGap(28, 33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextTp, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddTp)
                    .addComponent(ButtonUpdate)
                    .addComponent(ButtonDelete))
                .addGap(52, 52, 52))
        );

        add(jPanel2);
        jPanel2.setBounds(0, 40, 400, 460);
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdateActionPerformed
        // TODO add your handling code here:
        int status = CmbStatus.getSelectedIndex();
        ChuDeDTO tpDTO = new ChuDeDTO();
        tpDTO.setTpID(Integer.parseInt(jLabelID.getText()));
        tpDTO.setTpTitle(TextTp.getText());
         if(status==0){
        tpDTO.setTpStatus(1);
        }else{
        tpDTO.setTpStatus(0);  
        }
        tpDAL.updateTopics(tpDTO);
        JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
        System.out.print( tpDAL.updateTopics(tpDTO));
        showTable();
    }//GEN-LAST:event_ButtonUpdateActionPerformed

    private void btnAddTpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTpActionPerformed
        // TODO add your handling code here:
        int status = CmbStatus.getSelectedIndex();
        ChuDeDTO tpDTO = new ChuDeDTO();
        tpDTO.setTpParentID(0);
        tpDTO.setTpTitle(TextTp.getText());
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
    }//GEN-LAST:event_btnAddTpActionPerformed

    private void ButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDeleteActionPerformed
        // TODO add your handling code here:
        ChuDeDTO tpDTO = new ChuDeDTO();
        tpDTO.setTpID(Integer.parseInt(jLabelID.getText()));
        int status = CmbStatus.getSelectedIndex();
         if(status==0){
        tpDTO.setTpStatus(0);
        }else{
        JOptionPane.showMessageDialog(null, "Không thể xóa được");
        return;
        }
        tpDAL.DeleteStatus(tpDTO);
        JOptionPane.showMessageDialog(null, "Xóa thành công!");
        showTable();
    }//GEN-LAST:event_ButtonDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonDelete;
    private javax.swing.JButton ButtonUpdate;
    private javax.swing.JComboBox<String> CmbStatus;
    private javax.swing.JTextField TextTp;
    private javax.swing.JButton btnAddTp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tbTopics;
    // End of variables declaration//GEN-END:variables
}
