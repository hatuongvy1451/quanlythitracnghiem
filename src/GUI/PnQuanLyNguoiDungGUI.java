/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import DAO.MyConnect;
import BUS.NguoiDungBUS;
import Customs.XuLyFileExcel;
import DTO.NguoiDungDTO;
import static Main.Main.changLNF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tuong Vy Ha
 */
public class PnQuanLyNguoiDungGUI extends javax.swing.JPanel {

    NguoiDungBUS ndBUS = new NguoiDungBUS();
    DefaultTableModel modelNguoiDung;
    private JButton btnTim = new JButton();
    Font font = new Font("Tahoma", Font.BOLD, 13);

    /**
     * Creates new form PnQuanLyNguoiDung
     */
    public PnQuanLyNguoiDungGUI() {
        initComponents();
        addPlaceholder(txtTimKiemND, "Tìm kiếm người dùng...");
        controls();
        events();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnThemND = new javax.swing.JButton();
        btnSuaND = new javax.swing.JButton();
        btnXoaND = new javax.swing.JButton();
        btnXemChiTietND = new javax.swing.JButton();
        btnXuatExcelND = new javax.swing.JButton();
        btnResetND = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblND = new javax.swing.JTable();
        txtTimKiemND = new javax.swing.JTextField();
        btnNhapExcelND = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        btnThemND.setBackground(new java.awt.Color(0, 153, 153));
        btnThemND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThemND.setForeground(java.awt.Color.white);
        btnThemND.setText("Thêm");
        btnThemND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNDActionPerformed(evt);
            }
        });

        btnSuaND.setBackground(new java.awt.Color(0, 153, 153));
        btnSuaND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSuaND.setForeground(java.awt.Color.white);
        btnSuaND.setText("Sửa");
        btnSuaND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNDActionPerformed(evt);
            }
        });

        btnXoaND.setBackground(new java.awt.Color(0, 153, 153));
        btnXoaND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoaND.setForeground(java.awt.Color.white);
        btnXoaND.setText("Xóa");
        btnXoaND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNDActionPerformed(evt);
            }
        });

        btnXemChiTietND.setBackground(new java.awt.Color(0, 153, 153));
        btnXemChiTietND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXemChiTietND.setForeground(java.awt.Color.white);
        btnXemChiTietND.setText("Chi tiết");
        btnXemChiTietND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietNDActionPerformed(evt);
            }
        });

        btnXuatExcelND.setBackground(new java.awt.Color(0, 153, 153));
        btnXuatExcelND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXuatExcelND.setForeground(java.awt.Color.white);
        btnXuatExcelND.setText("Xuất excel");

        btnResetND.setBackground(new java.awt.Color(0, 153, 153));
        btnResetND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnResetND.setForeground(java.awt.Color.white);

        tblND.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblND.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã người dùng", "Tên đăng nhập", "Họ tên", "Email", "Vai trò"
            }
        ));
        jScrollPane1.setViewportView(tblND);

        txtTimKiemND.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTimKiemND.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnNhapExcelND.setBackground(new java.awt.Color(0, 153, 153));
        btnNhapExcelND.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNhapExcelND.setForeground(java.awt.Color.white);
        btnNhapExcelND.setText("Nhập excel");
        btnNhapExcelND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapExcelNDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThemND)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuaND)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoaND)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXemChiTietND)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNhapExcelND)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXuatExcelND)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiemND, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetND, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiemND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSuaND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXoaND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXemChiTietND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnResetND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNhapExcelND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnXuatExcelND, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNDActionPerformed
        themND();
    }//GEN-LAST:event_btnThemNDActionPerformed

    private void btnXoaNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNDActionPerformed
        xoaND();
    }//GEN-LAST:event_btnXoaNDActionPerformed

    private void btnSuaNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNDActionPerformed
        suaND();
    }//GEN-LAST:event_btnSuaNDActionPerformed

    private void btnXemChiTietNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietNDActionPerformed
        xemChiTietND();
    }//GEN-LAST:event_btnXemChiTietNDActionPerformed

    private void btnNhapExcelNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapExcelNDActionPerformed
        xuLyNhapFileExcel();
    }//GEN-LAST:event_btnNhapExcelNDActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNhapExcelND;
    private javax.swing.JButton btnResetND;
    private javax.swing.JButton btnSuaND;
    private javax.swing.JButton btnThemND;
    private javax.swing.JButton btnXemChiTietND;
    private javax.swing.JButton btnXoaND;
    private javax.swing.JButton btnXuatExcelND;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblND;
    private javax.swing.JTextField txtTimKiemND;
    // End of variables declaration//GEN-END:variables

    public void controls() {
        modelNguoiDung = new DefaultTableModel();
        // Thêm các cột vào model
        modelNguoiDung.addColumn("Mã người dùng");
        modelNguoiDung.addColumn("Tên đăng nhập");
        modelNguoiDung.addColumn("Họ Tên");
        modelNguoiDung.addColumn("Email");
        modelNguoiDung.addColumn("Vai Trò");

        // Gán model cho bảng
        tblND.setModel(modelNguoiDung);

        // layout bảng
        tblND.setFocusable(false);
        tblND.setIntercellSpacing(new Dimension(0, 0));
        tblND.getTableHeader().setFont(font);
        tblND.setRowHeight(50);
        tblND.setShowVerticalLines(false);
        tblND.getTableHeader().setOpaque(false);
        tblND.setFillsViewportHeight(true);
        tblND.getTableHeader().setBackground(new Color(0, 153, 153));
        tblND.getTableHeader().setForeground(Color.WHITE);
        tblND.setSelectionBackground(new Color(232, 232, 232));
        tblND.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loadDataLenBangNguoiDung();

        // setCursor cho các button
        btnThemND.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSuaND.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXoaND.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXemChiTietND.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNhapExcelND.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXuatExcelND.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnResetND.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // setIcon cho các button
        btnThemND.setIcon(new ImageIcon(getClass().getResource("/images/add-icon.png")));
        btnSuaND.setIcon(new ImageIcon(getClass().getResource("/images/edit-icon.png")));
        btnXoaND.setIcon(new ImageIcon(getClass().getResource("/images/delete-icon.png")));
        btnXemChiTietND.setIcon(new ImageIcon(getClass().getResource("/images/details-icon.png")));
        btnNhapExcelND.setIcon(new ImageIcon(getClass().getResource("/images/excel-icon.png")));
        btnXuatExcelND.setIcon(new ImageIcon(getClass().getResource("/images/excel-icon.png")));
        btnResetND.setIcon(new ImageIcon(getClass().getResource("/images/reset-icon.png")));
    }

    public void events() {
        btnResetND.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetND();
            }
        });

        txtTimKiemND.getDocument().addDocumentListener(new DocumentListener() {
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
                loadDataLenBangNguoiDung(txtTimKiemND.getText());
            }
        });

        btnXuatExcelND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXuatFileExcel();
            }
        });
    }

    public void loadDataLenBangNguoiDung() {
        ndBUS.docDanhSach();
        ArrayList<NguoiDungDTO> ds = ndBUS.getDanhSachNguoiDung();
        loadDataLenBangNguoiDung(ds);
    }

    private void loadDataLenBangNguoiDung(ArrayList<NguoiDungDTO> ds) {
        modelNguoiDung.setRowCount(0);
        if (ds != null) {
            for (NguoiDungDTO nd : ds) {
                Vector vec = new Vector();
                vec.add(nd.getMaND());
                vec.add(nd.getTenDN());
                vec.add(nd.getHoTen());
                vec.add(nd.getEmail());
                String vaiTro = nd.getVaiTro().equals("1") ? "Admin" : "User";
                vec.add(vaiTro);
                modelNguoiDung.addRow(vec);
            }
        } else {
            System.out.println("Danh sách người dùng là null.");
        }
    }

    private void loadDataLenBangNguoiDung(String tuKhoa) {
        modelNguoiDung.setRowCount(0);

        ArrayList<NguoiDungDTO> ds = ndBUS.getNguoiDungTheoTuKhoa(tuKhoa);

        if (ds != null) {
            for (NguoiDungDTO nd : ds) {
                Vector vec = new Vector();
                vec.add(nd.getMaND());
                vec.add(nd.getTenDN());
                vec.add(nd.getHoTen());
                vec.add(nd.getEmail());
                String vaiTro = nd.getVaiTro().equals("1") ? "Admin" : "User";
                vec.add(vaiTro);
                modelNguoiDung.addRow(vec);
            }
        } else {
            System.out.println("Danh sách người dùng là null.");
        }
    }

    private void resetND() {
        addPlaceholder(txtTimKiemND, "Tìm kiếm người dùng...");
        tblND.clearSelection();
        loadDataLenBangNguoiDung();
    }

    private void xoaND() {
        int selectedRow = tblND.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa người dùng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) tblND.getModel();
                int maNguoiDung = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
                ndBUS.xoaNguoiDung(maNguoiDung);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Xóa người dùng thành công!");
                loadDataLenBangNguoiDung();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn người dùng cần xóa.");
        }
    }

    private void xemChiTietND() {
        int selectedRow = tblND.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblND.getModel();
            int maNguoiDung = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

            NguoiDungDTO nd = ndBUS.getNguoiDungTheoMa(maNguoiDung);

            if (nd != null) {
                XemNguoiDungGUI xemNDGUI = new XemNguoiDungGUI();
                xemNDGUI.setThongTinNguoiDung(nd);
                JFrame frame = new JFrame("Xem Chi Tiết Người Dùng");
                frame.setContentPane(xemNDGUI);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin người dùng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn người dùng để xem chi tiết.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void xuLyNhapFileExcel(){
        XuLyFileExcel nhapFile = new XuLyFileExcel();
        nhapFile.nhapExcel(tblND);

        int row = tblND.getRowCount();
        for (int i = 0; i < row; i++) {
            String tenDN = tblND.getValueAt(i, 1) + "";
            String hoTen = tblND.getValueAt(i, 2) + "";
            String email = tblND.getValueAt(i, 3) + "";

            ndBUS.nhapNguoiDungTuExcel(tenDN, hoTen, email);
        }
    }
    
    private void xuLyXuatFileExcel() {
        XuLyFileExcel xuatFile = new XuLyFileExcel();
        xuatFile.xuatExcel(tblND);
    }
    
    private void themND() {
        ThemNguoiDungGUI themNguoiDungGui = new ThemNguoiDungGUI();
        JDialog dialog = new JDialog();
        dialog.setTitle("Thêm người dùng");
        dialog.setModal(true);
        dialog.getContentPane().add(themNguoiDungGui);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        loadDataLenBangNguoiDung();
    }

    private void suaND() {
        int selectedRow = tblND.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) tblND.getModel();
            int maNguoiDung = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
            System.out.println(maNguoiDung);
            NguoiDungDTO nd = ndBUS.getNguoiDungTheoID(maNguoiDung);

            if (nd != null) {
                SuaNguoiDungGUI suaNDGUI = new SuaNguoiDungGUI();
                suaNDGUI.setThongTinNguoiDung(nd);
                JFrame frame = new JFrame("Sửa thông tin Người Dùng");
                frame.setContentPane(suaNDGUI);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        loadDataLenBangNguoiDung();
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin người dùng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn người dùng để sửa chi tiết.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
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
    
    public static void main(String[] args) {
        new MyConnect();
        PnQuanLyNguoiDungGUI ndGUI = new PnQuanLyNguoiDungGUI();
        JFrame frame = new JFrame("Quản lý người dùng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ndGUI);
        frame.pack();
        frame.setSize(1100, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
    }
}
