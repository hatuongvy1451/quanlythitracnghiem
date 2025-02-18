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
import GUI.PnQuanLyNguoiDungGUI;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        new MyConnect();
        PnQuanLyNguoiDungGUI ndGUI = new PnQuanLyNguoiDungGUI();
        // Tạo JFrame để chứa JPanel
        JFrame frame = new JFrame("Quản lý người dùng");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ndGUI); // Thêm JPanel vào JFrame
        frame.pack();
        frame.setSize(1000, 600); // Set kích thước cho JFrame
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true); // Hiển thị JFrame
    }
    
    public static void changLNF(String nameLNF) {
        
    }
}

