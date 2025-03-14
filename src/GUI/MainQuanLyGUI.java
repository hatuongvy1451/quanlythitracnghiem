/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.DangNhapBUS;
import BUS.NguoiDungBUS;
import GUI.PnDanhSachKetQuaGUI;
import GUI.PnLamBaiThiGUI;
import GUI.PnQuanLyCaiDatGUI;
import GUI.PnQuanLyCauHoiGUI;
import GUI.PnQuanLyNguoiDungGUI;
import GUI.PnThongKeGUI;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Thu Huyền
 */
public class MainQuanLyGUI extends JFrame {

    private Container con;

    public MainQuanLyGUI() {
        this.setTitle("Phần mềm thi trắc nghiệm");
        this.setSize(1250, 730);
        addControls();
        addEvents();
    }

    public void showWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    JButton btnDoiMatKhau;
    JButton btnDangXuat;

    JPanel pnTitle, pnMenuLeft, pnCard;
    JPanel pnUser, pnThongKe, pnCauHoi, pnDeThi, pnLamBaiThi, pnDanhSachKetQua, pnCaiDat;
    JPanel pnUserChoose, pnThongKeChoose, pnCauHoiChoose, pnDeThiChoose, pnLamBaiThiChoose, pnDanhSachKetQuaChoose, pnCaiDatChoose;

    JLabel btnClose, btnMinimize;
    JLabel lblUser, lblThongKe, lblCauHoi, lblDeThi, lblLamBaiThi, lblDanhSachKetQua, lblCaiDat;
    final Color clLeftItem = new Color(63, 74, 89);
    final Color clLeftItemHover = new Color(72, 88, 107);
    final Color clLeftItemSelected = new Color(51, 202, 187);
    final Font fontChoose = new Font("Arial", Font.BOLD, 18);
    ArrayList<JPanel> listMenuLeft;
    CardLayout cardMenuLeftGroup = new CardLayout();
    JPanel pnMain = new JPanel();
    
    PnQuanLyNguoiDungGUI ndPanel;
    PnQuanLyDeThiGUI dtPanel;
    PnQuanLyCauHoiGUI chPanel;
    PnThongKeGUI tkPanel;
    PnLamBaiThiGUI lbtPanel;
    PnDanhSachKetQuaGUI dskqPanel;
    PnQuanLyCaiDatGUI cdPanel;

    private void addControls() {
        int width = this.getWidth();
        int height = this.getHeight();

        con = getContentPane();

        pnMain.setLayout(new BorderLayout());
        pnMain.setBackground(Color.WHITE);

        /*
        ============================================================
                                SIDE BAR MENU
        ============================================================
         */
        pnMenuLeft = new JPanel();
        pnMenuLeft.setPreferredSize(new Dimension(250, height - width));
        pnMenuLeft.setBackground(clLeftItem);
        pnMenuLeft.setLayout(new BoxLayout(pnMenuLeft, BoxLayout.Y_AXIS));

        pnTitle = new JPanel(null);
        pnTitle.setPreferredSize(new Dimension(pnMenuLeft.getWidth(), 46));

        btnDoiMatKhau = new JButton("Đổi mật khẩu");
        btnDoiMatKhau.setBounds(0, 0, 130, 42);
        btnDoiMatKhau.setBackground(new Color(151, 255, 255));
        btnDoiMatKhau.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnDoiMatKhau);

        btnDangXuat = new JButton("Thoát");
        btnDangXuat.setBounds(135, 0, 120, 42);
        btnDangXuat.setBackground(new Color(151, 255, 255));
        btnDangXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnTitle.add(btnDangXuat);
        pnMenuLeft.add(pnTitle);

        pnUserChoose = new JPanel(new BorderLayout());
        pnThongKeChoose = new JPanel(new BorderLayout());
        pnCauHoiChoose = new JPanel(new BorderLayout());
        pnDeThiChoose = new JPanel(new BorderLayout());
        pnLamBaiThiChoose = new JPanel(new BorderLayout());
        pnDanhSachKetQuaChoose = new JPanel(new BorderLayout());
        pnCaiDatChoose = new JPanel(new BorderLayout());

        JLabel lblAvatar = new JLabel(new ImageIcon("images/logo.png"), JLabel.CENTER);
        lblAvatar.setPreferredSize(new Dimension(250, 140));
        lblAvatar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pnMenuLeft.add(lblAvatar);

        lblUser = new JLabel("Quản lý Người Dùng");
        lblThongKe = new JLabel("Quản lý Thống Kê");
        lblCauHoi = new JLabel("Quản lý Câu Hỏi");
        lblDeThi = new JLabel("Quản lý Đề Thi");
        lblLamBaiThi = new JLabel("Làm Bài Thi");
        lblDanhSachKetQua = new JLabel("Danh Sách Kết Qủa");
        lblCaiDat = new JLabel("Cài Đặt");

        lblUser.setForeground(Color.WHITE);
        lblThongKe.setForeground(Color.WHITE);
        lblCauHoi.setForeground(Color.WHITE);
        lblDeThi.setForeground(Color.WHITE);
        lblLamBaiThi.setForeground(Color.WHITE);
        lblDanhSachKetQua.setForeground(Color.WHITE);
        lblCaiDat.setForeground(Color.WHITE);

        lblUser.setFont(fontChoose);
        lblThongKe.setFont(fontChoose);
        lblCauHoi.setFont(fontChoose);
        lblDeThi.setFont(fontChoose);
        lblLamBaiThi.setFont(fontChoose);
        lblDanhSachKetQua.setFont(fontChoose);
        lblCaiDat.setFont(fontChoose);

        pnUserChoose.add(lblUser, BorderLayout.CENTER);
        pnThongKeChoose.add(lblThongKe, BorderLayout.CENTER);
        pnCauHoiChoose.add(lblCauHoi, BorderLayout.CENTER);
        pnDeThiChoose.add(lblDeThi, BorderLayout.CENTER);
        pnLamBaiThiChoose.add(lblLamBaiThi, BorderLayout.CENTER);
        pnDanhSachKetQuaChoose.add(lblDanhSachKetQua, BorderLayout.CENTER);
        pnCaiDatChoose.add(lblCaiDat, BorderLayout.CENTER);

        lblUser.setHorizontalAlignment(JLabel.CENTER);
        lblThongKe.setHorizontalAlignment(JLabel.CENTER);
        lblCauHoi.setHorizontalAlignment(JLabel.CENTER);
        lblDeThi.setHorizontalAlignment(JLabel.CENTER);
        lblLamBaiThi.setHorizontalAlignment(JLabel.CENTER);
        lblDanhSachKetQua.setHorizontalAlignment(JLabel.CENTER);
        lblCaiDat.setHorizontalAlignment(JLabel.CENTER);

        pnUserChoose.setPreferredSize(new Dimension(200, 60));
        pnThongKeChoose.setPreferredSize(new Dimension(200, 60));
        pnCauHoiChoose.setPreferredSize(new Dimension(200, 60));
        pnDeThiChoose.setPreferredSize(new Dimension(200, 60));
        pnLamBaiThiChoose.setPreferredSize(new Dimension(200, 60));
        pnDanhSachKetQuaChoose.setPreferredSize(new Dimension(200, 60));
        pnCaiDatChoose.setPreferredSize(new Dimension(200, 60));

        listMenuLeft = new ArrayList<>();
        listMenuLeft.add(pnUserChoose);
        listMenuLeft.add(pnCauHoiChoose);
        listMenuLeft.add(pnDeThiChoose);
        listMenuLeft.add(pnThongKeChoose);
        listMenuLeft.add(pnLamBaiThiChoose);
        listMenuLeft.add(pnDanhSachKetQuaChoose);
        listMenuLeft.add(pnCaiDatChoose);

        for (JPanel pnl : listMenuLeft) {
            pnl.setBorder(BorderFactory.createLineBorder(Color.white));
            pnl.setVisible(false);
            pnl.setOpaque(true);
            pnl.setBackground(clLeftItem);
            pnl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pnMenuLeft.add(pnl);
        }

        lblUser.setBackground(clLeftItemSelected);
        lblUser.setVisible(true);

        pnMain.add(pnMenuLeft, BorderLayout.WEST);

        /*
        ============================================================
                                CARD PANEL           
        ============================================================
         */
        pnCard = new JPanel(cardMenuLeftGroup);

        pnUser = new JPanel();
        pnThongKe = new JPanel();
        pnCauHoi = new JPanel();
        pnDeThi = new JPanel();
        pnLamBaiThi = new JPanel();
        pnDanhSachKetQua = new JPanel();
        pnCaiDat = new JPanel();

        pnCard.add(pnUser, "1");
        pnCard.add(pnThongKe, "2");
        pnCard.add(pnCauHoi, "3");
        pnCard.add(pnDeThi, "4");
        pnCard.add(pnLamBaiThi, "5");
        pnCard.add(pnDanhSachKetQua, "6");
        pnCard.add(pnCaiDat, "7");

        xuLyPhanQuyen();

    }

    

    private void xuLyPhanQuyen() {

        String vaitro = DangNhapBUS.nguoidungLogin.getVaiTro(); // Lấy mã chức vụ từ tài khoản đang đăng nhập

        String firstPanel = null;
        JPanel firstPanelChoose = null;

// Nếu vai trò là 1 (Quản trị viên)
        if (vaitro.contains("1")) {
            ndPanel = new PnQuanLyNguoiDungGUI();
            pnUser.setLayout(new BorderLayout());
            pnUser.add(ndPanel, BorderLayout.CENTER);

            if (firstPanel == null) {
                firstPanel = "1";
                firstPanelChoose = pnUserChoose;
            }
            pnUserChoose.setVisible(true);
        }
        if (vaitro.contains("1")) {
            chPanel = new PnQuanLyCauHoiGUI();
            pnCauHoi.setLayout(new BorderLayout());
            pnCauHoi.add(chPanel, BorderLayout.CENTER);
            if (firstPanel == null) {
                firstPanel = "2";
                firstPanelChoose = pnCauHoiChoose;
            }
            pnCauHoiChoose.setVisible(true);
        }
        if (vaitro.contains("1")) {
            dtPanel = new PnQuanLyDeThiGUI();
            pnDeThi.setLayout(new BorderLayout());
            pnDeThi.add(dtPanel, BorderLayout.CENTER);
            if (firstPanel == null) {
                firstPanel = "3";
                firstPanelChoose = pnDeThiChoose;
            }
            pnDeThiChoose.setVisible(true);
        }
        if (vaitro.contains("1")) {
            tkPanel = new PnThongKeGUI();
            pnThongKe.setLayout(new BorderLayout());
            pnThongKe.add(tkPanel, BorderLayout.CENTER);
            if (firstPanel == null) {
                firstPanel = "4";
                firstPanelChoose = pnThongKeChoose;
            }
            pnThongKeChoose.setVisible(true);
        }
        if (!vaitro.contains("1")) {
            lbtPanel = new PnLamBaiThiGUI();
            pnLamBaiThi.setLayout(new BorderLayout());
            pnLamBaiThi.add(lbtPanel, BorderLayout.CENTER);
            if (firstPanel == null) {
                firstPanel = "5";
                firstPanelChoose = pnLamBaiThiChoose;
                lblLamBaiThi.setBackground(clLeftItemSelected);
                lblLamBaiThi.setVisible(true);

            }
            pnLamBaiThiChoose.setVisible(true);
        }

        if (!vaitro.contains("1")) {
            dskqPanel = new PnDanhSachKetQuaGUI();
            pnDanhSachKetQua.setLayout(new BorderLayout());
            pnDanhSachKetQua.add(dskqPanel, BorderLayout.CENTER);
            if (firstPanel == null) {
                firstPanel = "6";
                firstPanelChoose = pnDanhSachKetQuaChoose;
            }
            pnDanhSachKetQuaChoose.setVisible(true);
        }
        if (!vaitro.contains("1")) {
            cdPanel = new PnQuanLyCaiDatGUI();
            pnCaiDat.setLayout(new BorderLayout());
            pnCaiDat.add(cdPanel, BorderLayout.CENTER);
            if (firstPanel == null) {
                firstPanel = "7";
                firstPanelChoose = pnCaiDatChoose;
            }
            pnCaiDatChoose.setVisible(true);
        }

// Hiển thị panel đầu tiên dựa trên quyền của người dùng
        if (firstPanel != null) {

            cardMenuLeftGroup.show(pnCard, firstPanel);

            firstPanelChoose.setBackground(clLeftItemSelected);
        } else { }
        pnMain.add(pnCard);
        con.add(pnMain);

    }

    int xMouse, yMouse;

    private void addEvents() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moverFrame(e.getXOnScreen(), e.getYOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });

        btnDoiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DlgDoiMatKhau().setVisible(true);
            }
        });

        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DangNhapGUI dnGUI = new DangNhapGUI();
                dnGUI.setVisible(true);
            }
        });

        for (JPanel pnl : listMenuLeft) {
            pnl.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    for (JPanel pnlDisable : listMenuLeft) {
                        pnlDisable.setBackground(clLeftItem);
                    }
                    pnl.setBackground(clLeftItemSelected);

                    // Xử lý lật trang theo menu
                    String cardName = "";
                    if (pnl == pnUserChoose) {
                        cardName = "1";
                    } else if (pnl == pnThongKeChoose) {
                        cardName = "2";
                    } else if (pnl == pnCauHoiChoose) {
                        cardName = "3";
                    } else if (pnl == pnDeThiChoose) {
                        cardName = "4";
                    } else if (pnl == pnLamBaiThiChoose) {
                        cardName = "5";
                    } else if (pnl == pnDanhSachKetQuaChoose) {
                        cardName = "6";
                    } else if (pnl == pnCaiDatChoose) {
                        cardName = "7";
                    } else {
                        cardName = "8";
                    }
                    cardMenuLeftGroup.show(pnCard, cardName);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (pnl.getBackground().equals(clLeftItem)) {
                        pnl.setBackground(clLeftItemHover);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (pnl.getBackground().equals(clLeftItemHover)) {
                        pnl.setBackground(clLeftItem);
                    }
                }
            });
        }
    }

    private void moverFrame(int x, int y) {
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void thuNhoFrame() {
        this.setState(Frame.ICONIFIED);
    }

    private void thoatChuongTrinh() {
        Main.Main.changLNF("Nimbus");
        System.exit(0);
    }
}
