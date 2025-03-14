/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.DeThiBUS;
import Customs.XuLyFileExcel;
import DAO.MyConnect;
import DTO.DeThiDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tuong Vy Ha
 */
public class PnQuanLyDeThiGUI extends javax.swing.JPanel {

    DeThiBUS dtBUS = new DeThiBUS();
    DefaultTableModel modelDeThi;
    private JButton btnTim = new JButton();
    Font font = new Font("Tahoma", Font.BOLD, 13);

    /**
     * Creates new form PnQuanLyDeThiGUI
     */
    public PnQuanLyDeThiGUI() {
        initComponents();
        addPlaceholder(txtTimKiemDT, "Tìm kiếm đề thi...");
        controls();
        events();
    }

    public void controls() {
        modelDeThi = new DefaultTableModel();
        // Thêm các cột vào model
        modelDeThi.addColumn("ID");
        modelDeThi.addColumn("Mã đề thi");
        modelDeThi.addColumn("Tên đề thi");
        modelDeThi.addColumn("Chủ đề");
        modelDeThi.addColumn("Tình trạng");

        // Gán model cho bảng
        tblDT.setModel(modelDeThi);

        // layout bảng
        tblDT.setFocusable(false);
        tblDT.setIntercellSpacing(new Dimension(0, 0));
        tblDT.getTableHeader().setFont(font);
        tblDT.setRowHeight(50);
        tblDT.setShowVerticalLines(false);
        tblDT.getTableHeader().setOpaque(false);
        tblDT.setFillsViewportHeight(true);
        tblDT.getTableHeader().setBackground(new Color(0, 153, 153));
        tblDT.getTableHeader().setForeground(Color.WHITE);
        tblDT.setSelectionBackground(new Color(232, 232, 232));
        tblDT.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loadDataLenBangDeThi();
    }

    public void events() {
        txtTimKiemDT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                btnTim.doClick();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                btnTim.doClick();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                btnTim.doClick();
            }
        });

        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataLenBangDeThi(txtTimKiemDT.getText());
            }
        });
    }

    public void loadDataLenBangDeThi() {
        dtBUS.docDanhSach();
        ArrayList<DeThiDTO> ds = dtBUS.getDanhSachDeThi();
        loadDataLenBangDeThi(ds);
    }

    private void loadDataLenBangDeThi(ArrayList<DeThiDTO> ds) {
        modelDeThi.setRowCount(0);
        if (ds != null) {
            for (DeThiDTO dt : ds) {
                Vector vec = new Vector();
                vec.add(dt.getTestID());
                vec.add(dt.getTestCode());
                vec.add(dt.getTestTitle());
                vec.add(dt.getTopicID());
                String tinhTrang = dt.getTestStatus().equals("1") ? "Đang hoạt động" : "Ngưng hoạt động";
                vec.add(tinhTrang);
                modelDeThi.addRow(vec);
            }
        } else {
            System.out.println("Danh sách đề thi là null.");
        }
    }

    private void loadDataLenBangDeThi(String tuKhoa) {
        modelDeThi.setRowCount(0);

        ArrayList<DeThiDTO> ds = dtBUS.getDeThiTheoTuKhoa(tuKhoa);

        if (ds != null) {
            for (DeThiDTO dt : ds) {
                Vector vec = new Vector();
                vec.add(dt.getTestID());
                vec.add(dt.getTestCode());
                vec.add(dt.getTestTitle());
                vec.add(dt.getTopicID());
                String tinhTrang = dt.getTestStatus().equals("1") ? "Đang hoạt động" : "Ngưng hoạt động";
                vec.add(tinhTrang);
                modelDeThi.addRow(vec);
            }
        } else {
            System.out.println("Danh sách đề thi là null.");
        }
    }

    private void xemChiTietDT() {
        int selectedRow = tblDT.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblDT.getModel();
            int maDeThi = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

            DeThiDTO deThi = dtBUS.getDeThiTheoMa(maDeThi);

            if (deThi != null) {
                XemDeThiGUI xemdtGUI = new XemDeThiGUI();
                xemdtGUI.setThongTinDeThi(deThi);
                JFrame frame = new JFrame("Xem Chi Tiết Đề Thi");
                frame.setContentPane(xemdtGUI);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin đề thi.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đề thi để xem chi tiết.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void xuLyXuatFileExcel() {
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblDT);
    }

    private void resetDT() {
        addPlaceholder(txtTimKiemDT, "Tìm kiếm đề thi...");
        tblDT.clearSelection();
        loadDataLenBangDeThi();
    }

    private void addPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
    
    private void themMoiChuDeChoDeThi(){
        int selectedRow = tblDT.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblDT.getModel();
            int maDeThi = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            
            DeThiDTO deThi = dtBUS.getDeThiTheoMa(maDeThi);

            if (deThi != null) {
                PnlThemChuDeChoDeThi themMoiChuDeChoDeThiGUI = new PnlThemChuDeChoDeThi();
                themMoiChuDeChoDeThiGUI.setThongTinDeThi(deThi);
                JFrame frame = new JFrame("Thêm mới chủ đề cho đề thi");
                frame.setContentPane(themMoiChuDeChoDeThiGUI);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(this, "Không thể thêm mới chủ đề vào đề thi.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đề thi để thêm mới chủ đề.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
        btnThemDT = new javax.swing.JButton();
        btnXemCTDT = new javax.swing.JButton();
        btnXuatExcelDT = new javax.swing.JButton();
        btnResetND = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDT = new javax.swing.JTable();
        txtTimKiemDT = new javax.swing.JTextField();
        btnThemCDDT = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnThemDT.setBackground(new java.awt.Color(0, 153, 153));
        btnThemDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemDT.setForeground(java.awt.Color.white);
        btnThemDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-icon.png"))); // NOI18N
        btnThemDT.setText("Thêm mới đề thi");
        btnThemDT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDTActionPerformed(evt);
            }
        });

        btnXemCTDT.setBackground(new java.awt.Color(0, 153, 153));
        btnXemCTDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXemCTDT.setForeground(java.awt.Color.white);
        btnXemCTDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/details-icon.png"))); // NOI18N
        btnXemCTDT.setText("Chi tiết");
        btnXemCTDT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXemCTDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemCTDTActionPerformed(evt);
            }
        });

        btnXuatExcelDT.setBackground(new java.awt.Color(0, 153, 153));
        btnXuatExcelDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXuatExcelDT.setForeground(java.awt.Color.white);
        btnXuatExcelDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excel-icon.png"))); // NOI18N
        btnXuatExcelDT.setText("Xuất excel");
        btnXuatExcelDT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXuatExcelDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExcelDTActionPerformed(evt);
            }
        });

        btnResetND.setBackground(new java.awt.Color(0, 153, 153));
        btnResetND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnResetND.setForeground(java.awt.Color.white);
        btnResetND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset-icon.png"))); // NOI18N
        btnResetND.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnResetND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetNDActionPerformed(evt);
            }
        });

        tblDT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblDT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã đề thi", "Tên đề thi", "Chủ đề", "Tình trạng"
            }
        ));
        jScrollPane1.setViewportView(tblDT);

        txtTimKiemDT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTimKiemDT.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnThemCDDT.setBackground(new java.awt.Color(0, 153, 153));
        btnThemCDDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemCDDT.setForeground(java.awt.Color.white);
        btnThemCDDT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-icon.png"))); // NOI18N
        btnThemCDDT.setText("Thêm mới chủ đề");
        btnThemCDDT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemCDDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCDDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThemDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThemCDDT, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXemCTDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuatExcelDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetND, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnResetND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiemDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatExcelDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXemCTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemCDDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDTActionPerformed
        PnlThemDeThi themNguoiDungGui = new PnlThemDeThi();
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm đề thi");
        dialog.setModal(true);
        dialog.getContentPane().add(themNguoiDungGui);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadDataLenBangDeThi();
    }//GEN-LAST:event_btnThemDTActionPerformed

    private void btnXemCTDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemCTDTActionPerformed
        xemChiTietDT();
    }//GEN-LAST:event_btnXemCTDTActionPerformed

    private void btnResetNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetNDActionPerformed
        resetDT();
    }//GEN-LAST:event_btnResetNDActionPerformed

    private void btnXuatExcelDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExcelDTActionPerformed
        xuLyXuatFileExcel();
    }//GEN-LAST:event_btnXuatExcelDTActionPerformed

    private void btnThemCDDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCDDTActionPerformed
        themMoiChuDeChoDeThi();
        loadDataLenBangDeThi();
    }//GEN-LAST:event_btnThemCDDTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnResetND;
    private javax.swing.JButton btnThemCDDT;
    private javax.swing.JButton btnThemDT;
    private javax.swing.JButton btnXemCTDT;
    private javax.swing.JButton btnXuatExcelDT;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDT;
    private javax.swing.JTextField txtTimKiemDT;
    // End of variables declaration//GEN-END:variables
}
