package Customs;
import javax.swing.*;
import java.awt.*;

public class ColorfulMessageBox {
    public static void showColorfulMessage(String message, String title) {
        // Tạo JDialog để làm cửa sổ thông báo
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setSize(500, 250);
        dialog.setLocationRelativeTo(null); // Hiển thị giữa màn hình
        dialog.setModal(true);

        // Tạo JPanel làm nền
        JPanel panel = new JPanel();
        panel.setBackground(new Color(173, 216, 230)); // Màu xanh nhạt
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm JLabel để hiển thị nội dung thông báo
        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(new Color(0, 102, 204)); // Màu xanh đậm
        panel.add(messageLabel, BorderLayout.CENTER);

        // Tạo nút "OK" để đóng thông báo
        JButton okButton = new JButton("OK");
        okButton.setBackground(new Color(0, 102, 204)); // Màu xanh đậm
        okButton.setForeground(Color.WHITE); // Màu chữ trắng
        okButton.setFocusPainted(false);
        okButton.setFont(new Font("Arial", Font.PLAIN, 14));
        okButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okButton.addActionListener(e -> dialog.dispose());
        panel.add(okButton, BorderLayout.SOUTH);

        // Thêm JPanel vào JDialog
        dialog.add(panel);
        dialog.setVisible(true);
    }

 }
