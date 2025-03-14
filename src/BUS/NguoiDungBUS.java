/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NguoiDungDAO;
import DTO.NguoiDungDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class NguoiDungBUS {

    NguoiDungDAO ndDAO = new NguoiDungDAO();
    private ArrayList<NguoiDungDTO> listnd = null;

    public NguoiDungBUS() {
        docDanhSach();
    }

    public void docDanhSach() {
        this.listnd = ndDAO.getDanhSachNguoiDung();
    }

    //goi danh sach
    public ArrayList<NguoiDungDTO> getDanhSachNguoiDung() {
        if (this.listnd == null) {
            docDanhSach();
        }
        return this.listnd;
    }

    public ArrayList<NguoiDungDTO> getNguoiDungTheoTuKhoa(String tenDN) {
        ArrayList<NguoiDungDTO> dsusers = new ArrayList<>();
        NguoiDungBUS nguoiDungBUS = new NguoiDungBUS();
        for (NguoiDungDTO users : listnd) {
            String tenDangNhap = users.getTenDN().toLowerCase();
            String vaiTro = (users.getVaiTro().equals("1")) ? "Admin" : "User";
            if (tenDangNhap.contains(tenDN.toLowerCase()) || (users.getMaND() + "").contains(tenDN)
                    || (users.getHoTen().toLowerCase().contains(tenDN.toLowerCase()))
                    || (users.getEmail().toLowerCase().contains(tenDN.toLowerCase()))
                    || (vaiTro.toLowerCase().contains(tenDN.toLowerCase()))) {
                dsusers.add(users);
            }
        }
        return dsusers;
    }

    public NguoiDungDTO getNguoiDungTheoMa(int maNguoiDung) {
        ArrayList<NguoiDungDTO> ds = ndDAO.getNguoiDung();
        for (NguoiDungDTO nd : ds) {
            if (nd.getMaND() == maNguoiDung) {
                return nd;
            }
        }
        return null;
    }

    public void xoaNguoiDung(int maNguoiDung) {
        ndDAO.xoaNguoiDung(maNguoiDung);
    }

        public boolean addNguoiDung(NguoiDungDTO nd) {
        return ndDAO.add(nd);
    }

    public boolean updateNguoiDung(NguoiDungDTO nd) {
        return ndDAO.update(nd);
    }

    public ArrayList<String> getVaiTro() {
        return ndDAO.getVaiTro();
    }

    public ArrayList<String> getListTenDangNhap() {
        return ndDAO.getListTenDangNhap();
    }

    public NguoiDungDTO getNguoiDungTheoID(int id) {
        return ndDAO.getNguoiDungTheoID(id);
    }

    public NguoiDungDTO getUserLogin(String username, String password) {
        return ndDAO.getUserLogin(username, password);
    }

    public NguoiDungDTO getThongTinNguoiDung(String username, String password) {
        return ndDAO.getThongTinNguoiDung(username, password);
    }

    public boolean kiemTraTrungEmail(String email) {
        return ndDAO.kiemTraTrungEmail(email);
    }

    public boolean kiemTraTrungTenDangNhap(String tenDN) {
        return ndDAO.kiemTraTrungTenDangNhap(tenDN);
    }

    public boolean dangKyUser(NguoiDungDTO user) {
        return ndDAO.dangKyUser(user);
    }

    public boolean updateUser(NguoiDungDTO nd) {
        return ndDAO.updateUser(nd);
    }

    public boolean nhapNguoiDungTuExcel(String tenDN, String hoTen, String email) {
        NguoiDungDTO user = new NguoiDungDTO();
        user.setTenDN(tenDN);
        user.setEmail(email);
        user.setHoTen(hoTen);
        return ndDAO.nhapNguoiDungTuExcel(user);
    }
    
    public boolean doiMatKhau(String matKhauCu, String matKhauMoi, String nhapLaiMatKhau) {
        return ndDAO.doiMatKhau(matKhauCu, matKhauMoi);
    }
}
