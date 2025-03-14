/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DangNhapDAO;
import DAO.NguoiDungDAO;
import DTO.NguoiDungDTO;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author HP
 */
public class DangNhapBUS {
    public static NguoiDungDTO nguoidungLogin = null;
    public NguoiDungDAO ndDAO = new NguoiDungDAO();
    
    public NguoiDungDTO getTaiKhoanDangNhap(String user, String password, boolean selected) {
        NguoiDungDTO nd = new NguoiDungDTO();
        nd.setTenDN(user);
        nd.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        NguoiDungDTO account = dangNhapDAO.dangNhap(nd);
        nguoidungLogin = account;
        return account;
    }

    public String getTaiKhoanGhiNho() {
        try {
            FileInputStream fis = new FileInputStream("remember.dat");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            br.close();
            return line;
        }catch (Exception e) {
        }
        return "";
    }

    public boolean kiemTraDangNhap(String user, String password) {
        user = user.replaceAll("\\s+", "");
        password = password.replaceAll("\\s+", "");
        boolean result = false;

        NguoiDungDTO nd = new NguoiDungDTO();
       nd.setTenDN(user);
        nd.setMatKhau(password);

        DangNhapDAO dangNhapDAO = new DangNhapDAO();
        NguoiDungDTO account = dangNhapDAO.dangNhap(nd);

       if (account != null) {
            result =  true;
        }
        return result;
    }

        
    public NguoiDungDTO getTaiKhoan(String taikhoan, String matkhau) {
        return ndDAO.getUserLogin(taikhoan, matkhau);
    }
    
}

