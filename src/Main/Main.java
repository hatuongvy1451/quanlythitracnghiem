/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Tuong Vy Ha
 */
import DAO.MyConnect;
import GUI.DangNhapGUI;
import GUI.PnQuanLyNguoiDungGUI;
import GUI.PnlKetQua;
import GUI.PnlLamBaiThi;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        new MyConnect();
        DangNhapGUI dnGUI = new DangNhapGUI();
//PnlKetQua dnGUI = new PnlKetQua();
        // Tạo JFrame để chứa JPanel
        JFrame frame = new JFrame("Đăng nhập");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(dnGUI); // Thêm JPanel vào JFrame
        frame.pack();
//        frame.setSize(1000, 600); // Set kích thước cho JFrame
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true); // Hiển thị JFrame
    }
    
    public static void changLNF(String nameLNF) {
        
    }
}

