/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.ThongKeBUS;
import Customs.ColorfulMessageBox;
import DAO.MyConnect;
import java.awt.Cursor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author HP
 */
public class PnThongkeGUIdetail extends javax.swing.JPanel {

    ThongKeBUS thongkeBUS = new ThongKeBUS();

    /**
     * Creates new form ThongkeGUIdetail
     */
    public PnThongkeGUIdetail() {
        initComponents();
        customizeComponents();
        updateThongKeTongQuat();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void updateThongKeTongQuat() {

        SLbaithi.setText("    Bài thi :   " + thongkeBUS.getBaiThi());
        SLduthi.setText("   Số lượng dự thi :   " + thongkeBUS.getSLDuThi());
        SLdat.setText("    Số lượng đạt (>=50%) :   " + thongkeBUS.getSLDat());
        SLrot.setText("   Số lượng không đạt :    " + thongkeBUS.getSLRot());
    }

    private void customizeComponents() {

        btnThongke.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnThongke.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleThongKe(evt);
            }
        });
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResettMouseClicked(evt);
            }
        });
    }

    private void hienThiThongkeGUI() {
        PnThongKeGUI TongQuat = new PnThongKeGUI();
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(TongQuat);
        frame.pack();
        frame.setSize(850, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void btnResettMouseClicked(java.awt.event.MouseEvent evt) {
        // Đặt lại các trường JDateChooser thành null
        TuNgay.setDate(null);
        DenNgay.setDate(null);
        updateThongKeTongQuat();

    }

    private void btnTongQuatMouseClicked(java.awt.event.MouseEvent evt) {

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.dispose();
        }
        hienThiThongkeGUI();
    }

    private void hienThiThongkeGUIdetail() {
    PnThongkeGUIdetail detail = new PnThongkeGUIdetail();
    JFrame frame = new JFrame("Chi tiết Thống kê");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(detail);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }

    private void btnDetailMouseClicked(java.awt.event.MouseEvent evt) {

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.dispose();
        }
        hienThiThongkeGUIdetail();
    }

    private void handleThongKe(java.awt.event.MouseEvent evt) {
        try {
            // Lấy ngày từ JDateChooser
            Date tuNgay = TuNgay.getDate();
            Date denNgay = DenNgay.getDate();

            // Định dạng ngày thành chuỗi nếu cần
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Kiểm tra logic "Từ ngày" lớn hơn "Đến ngày"
            if (tuNgay != null && denNgay != null && tuNgay.after(denNgay)) {
                ColorfulMessageBox.showColorfulMessage(
                        "Ngày bắt đầu phải trước hoặc bằng ngày kết thúc!",
                        "Thông báo"
                );
            }
            if (denNgay != null) {
                // Lấy ngày hiện tại dưới dạng java.util.Date
                Date today = new Date();

                // So sánh denNgay với today
                if (denNgay.after(today)) {
                    ColorfulMessageBox.showColorfulMessage(
                            "Ngày kết thúc không được sau ngày hiện tại!",
                            "Thông báo"
                    );

                }
            }

            // Lấy dữ liệu từ BUS
            int baiThi = thongkeBUS.getBaiThiTheoNgay(tuNgay, denNgay);
            int soLuongDuThi = thongkeBUS.getSLDuThiTheoNgay(tuNgay, denNgay);
            int soLuongDat = thongkeBUS.getSLDatTheoNgay(tuNgay, denNgay);
            int soLuongKhongDat = thongkeBUS.getSLRotTheoNgay(tuNgay, denNgay);

            // Hiển thị kết quả
            SLbaithi.setText("    Bài thi :   " + baiThi);
            SLduthi.setText("   Số lượng dự thi :   " + soLuongDuThi);
            SLdat.setText("    Số lượng đạt (>=50%) :   " + soLuongDat);
            SLrot.setText("   Số lượng không đạt :    " + soLuongKhongDat);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnReset = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        DenNgay = new com.toedter.calendar.JDateChooser();
        TuNgay = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        SLduthi = new javax.swing.JLabel();
        SLbaithi = new javax.swing.JLabel();
        SLdat = new javax.swing.JLabel();
        SLrot = new javax.swing.JLabel();
        btnThongke = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        btnReset.setBackground(new java.awt.Color(0, 153, 153));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnReset.setText("Làm mới");
        btnReset.setToolTipText("");
        btnReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnReset.setOpaque(true);
        jPanel1.add(btnReset);
        btnReset.setBounds(710, 150, 100, 50);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("THỐNG KÊ CHI TIẾT");
        jLabel6.setOpaque(true);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(280, 60, 270, 50);

        DenNgay.setBackground(new java.awt.Color(255, 255, 153));
        DenNgay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DenNgay.setDateFormatString("dd/MM/yyyy");
        DenNgay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(DenNgay);
        DenNgay.setBounds(500, 150, 200, 50);

        TuNgay.setBackground(new java.awt.Color(255, 255, 153));
        TuNgay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TuNgay.setToolTipText("");
        TuNgay.setAutoscrolls(true);
        TuNgay.setDateFormatString("dd/MM/yyyy");
        TuNgay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TuNgay.setMaxSelectableDate(new java.util.Date(253370743307000L));
        jPanel1.add(TuNgay);
        TuNgay.setBounds(160, 150, 200, 50);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Đến ngày :  ");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(400, 160, 110, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Từ ngày :  ");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(70, 160, 100, 30);

        SLduthi.setBackground(new java.awt.Color(0, 153, 153));
        SLduthi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SLduthi.setForeground(new java.awt.Color(255, 255, 255));
        SLduthi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SLduthi.setToolTipText("");
        SLduthi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        SLduthi.setOpaque(true);
        jPanel1.add(SLduthi);
        SLduthi.setBounds(460, 280, 320, 120);

        SLbaithi.setBackground(new java.awt.Color(0, 153, 153));
        SLbaithi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SLbaithi.setForeground(new java.awt.Color(255, 255, 255));
        SLbaithi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SLbaithi.setToolTipText("");
        SLbaithi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        SLbaithi.setOpaque(true);
        jPanel1.add(SLbaithi);
        SLbaithi.setBounds(70, 280, 320, 120);

        SLdat.setBackground(new java.awt.Color(0, 153, 153));
        SLdat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SLdat.setForeground(new java.awt.Color(255, 255, 255));
        SLdat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SLdat.setToolTipText("");
        SLdat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        SLdat.setOpaque(true);
        jPanel1.add(SLdat);
        SLdat.setBounds(70, 440, 320, 120);

        SLrot.setBackground(new java.awt.Color(0, 153, 153));
        SLrot.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SLrot.setForeground(new java.awt.Color(255, 255, 255));
        SLrot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SLrot.setToolTipText("");
        SLrot.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        SLrot.setOpaque(true);
        jPanel1.add(SLrot);
        SLrot.setBounds(460, 440, 320, 120);

        btnThongke.setBackground(new java.awt.Color(0, 153, 153));
        btnThongke.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThongke.setForeground(new java.awt.Color(255, 255, 255));
        btnThongke.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThongke.setText("Thống kê");
        btnThongke.setToolTipText("");
        btnThongke.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnThongke.setOpaque(true);
        jPanel1.add(btnThongke);
        btnThongke.setBounds(370, 210, 120, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
//public static void main(String[] args) {
//        new MyConnect();
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Thống Kê");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(850, 650); // Đặt kích thước cho JFrame
//            frame.setResizable(false); // Không cho phép thay đổi kích thước
//            frame.add(new PnThongkeGUIdetail()); // Thêm PnThongKeGUI vào JFrame
//            frame.setLocationRelativeTo(null); // Hiển thị JFrame ở giữa màn hình
//            frame.setVisible(true); // Hiển thị JFrame
//        });
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DenNgay;
    private javax.swing.JLabel SLbaithi;
    private javax.swing.JLabel SLdat;
    private javax.swing.JLabel SLduthi;
    private javax.swing.JLabel SLrot;
    private com.toedter.calendar.JDateChooser TuNgay;
    private javax.swing.JLabel btnReset;
    private javax.swing.JLabel btnThongke;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
