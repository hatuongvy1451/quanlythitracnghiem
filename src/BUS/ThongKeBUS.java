/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThongKeDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HP
 */
public class ThongKeBUS {

    private ThongKeDAO thongKe;

    public ThongKeBUS() {
        thongKe = new ThongKeDAO();
    }
//Dữ liệu thống kê theo năm 
    // Thống kê theo năm

    public int getBaiThi() {
        return thongKe.getBaiThi();
    }

    public int getBaiThiTheoNam(int nam) {
        return thongKe.getBaiThiTheoNam(nam);
    }

    public int getSLDuThi() {
        return thongKe.getSLDuThi();
    }

    public int getSLDuThiTheoNam(int nam) {
        return thongKe.getSLDuThiTheoNam(nam);
    }

    public int getSLDat() {
        return thongKe.getSLDat();
    }

    public int getSLDatTheoNam(int nam) {
        return thongKe.getSLDatTheoNam(nam);
    }

    public int getSLRot() {
        return thongKe.getSLRot();
    }

    public int getSLRotTheoNam(int nam) {
        return thongKe.getSLRotTheoNam(nam);
    }

    public ArrayList<Integer> getDanhSachNam() {
        return thongKe.getDanhSachNam();
    }
//Dữ liệu thống kê theo tháng

    public HashMap<Integer, Integer> layDuLieuDuThi(int nam) {
        return thongKe.layDuLieuDuThi(nam);
    }

    public HashMap<Integer, Integer> layDuLieuBaiThi(int nam) {
        return thongKe.layDuLieuBaiThi(nam);
    }

    public HashMap<Integer, Integer> layDuLieuDat(int nam) {
        return thongKe.layDuLieuDat(nam);
    }

    public HashMap<Integer, Integer> layDuLieuRot(int nam) {
        return thongKe.layDuLieuRot(nam);
    }

    public int getBaiThiTheoNgay(Date fromDate, Date toDate) {
        return thongKe.getBaiThiTheoNgay(fromDate, toDate);
    }

    public int getSLDuThiTheoNgay(Date fromDate, Date toDate) {
        return thongKe.getSLDuThiTheoNgay(fromDate, toDate);
    }

    public int getSLDatTheoNgay(Date fromDate, Date toDate) {
        return thongKe.getSLDatTheoNgay(fromDate, toDate);
    }

    public int getSLRotTheoNgay(Date fromDate, Date toDate) {
        return thongKe.getSLRotTheoNgay(fromDate, toDate);
    }
}
