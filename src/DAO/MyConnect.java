package DAO;

//import MyCustom.MyDialog;
import com.mysql.cj.jdbc.Driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class MyConnect {

    public static Connection conn = null;
    private String severName;
    private String dbName;
    private String userName;
    private String password;

    public MyConnect() {
        docFileText();

        String strConnect = "jdbc:mysql://" + severName + "/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
        Properties pro = new Properties();
        pro.put("user", userName);
        pro.put("password", password);
        try {
            com.mysql.cj.jdbc.Driver driver = new Driver();
            conn = driver.connect(strConnect, pro);
//             JOptionPane.showMessageDialog(null, "Kết nối được tới CSDL!", "Kết nối", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Không kết nối được tới CSDL!", "Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

    }

    private void docFileText() {
        // Xử lý đọc file để lấy ra 4 tham số
        severName = "";
        dbName = "";
        userName = "";
        password = "";

        try {
            FileInputStream fis = new FileInputStream("ConnectDatabase.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            severName = br.readLine();
            dbName = br.readLine();
            userName = br.readLine();
            password = br.readLine();

            if (password == null) {
                password = "";
            }

        } catch (Exception e) {
        }
    }
}
