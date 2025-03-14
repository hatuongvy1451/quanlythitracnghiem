/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DeThiDAO;
import DTO.DeThiDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class DeThiBUS {

    DeThiDAO dtDAO = new DeThiDAO();
    ArrayList<String> listDt = null;
    private ArrayList<DeThiDTO> listDeThi = null;

    public DeThiBUS() {
        docDeThi();
    }

    public void docDeThi() {
        this.listDt = dtDAO.getDanhSachBaiThi();
    }

    public ArrayList<String> getDeThi() {
        if (listDt == null) {
            docDeThi();
        }
        return this.listDt;
    }

    public int getTongSoCauHoiTheoExCode(String exCode) {
        return dtDAO.getTongSoCauHoiTheoExCode(exCode);
    }

    public void docDanhSach() {
        this.listDeThi = dtDAO.getDanhSachDeThi();
    }

    //goi danh sach
    public ArrayList<DeThiDTO> getDanhSachDeThi() {
        if (this.listDeThi == null) {
            docDanhSach();
        }
        return this.listDeThi;
    }

    public ArrayList<DeThiDTO> getDeThiTheoTuKhoa(String tuKhoa) {
        ArrayList<DeThiDTO> dsdethi = new ArrayList<>();
        DeThiBUS dtBUS = new DeThiBUS();

        for (DeThiDTO deThi : listDeThi) {
            int testID = deThi.getTestID();
            String testCode = deThi.getTestCode();
            String testTitle = deThi.getTestTitle();
            int topicID = deThi.getTopicID();
            String testStatus = deThi.getTestStatus().equals("1") ? "Đang hoạt động" : "Ngưng hoạt động";

            // Convert integers to String for comparison
            if (String.valueOf(testID).contains(tuKhoa) || testCode.contains(tuKhoa)
                    || testTitle.contains(tuKhoa) || String.valueOf(topicID).contains(tuKhoa)
                    || testStatus.contains(tuKhoa)) {
                dsdethi.add(deThi);
            }
        }
        return dsdethi;
    }

    public DeThiDTO getDeThiTheoMa(int maDeThi) {
        DeThiDTO deThi = dtDAO.getDeThiTheoID(maDeThi);
        return deThi;
    }
    
    public int getListexOrders(String excode){
        return dtDAO.getListexOrders(excode);
    }

    public ArrayList<String> getExCodesByTestCode(String testCode) {
        return dtDAO.getExCodesByTestCode(testCode);
    }

    public int getTestLimitByTestCode(String testCode) {
        return dtDAO.getTestLimitByTestCode(testCode);
    }

    public int getTotalAttemptsByTestCode(String testCode, int userID) {
        return dtDAO.getTotalAttemptsByTestCode(testCode, userID);
    }

    public String getExCodeCuoCungTrongBang() {
        return dtDAO.getExCodeCuoCungTrongBang();
    }

    public boolean addDeThi(DeThiDTO dt) {
        return dtDAO.addDeThi(dt);
    }
    
    public String getExCodeTheoTestCodeTrongBang(String testCode) {
        return dtDAO.getExCodeTheoTestCodeTrongBang(testCode);
    }
    
    public boolean updateExQuesIDs(String testCode, String exOrder, String[] ex_quesIDs){
        return dtDAO.updateExQuesIDs(testCode, exOrder, ex_quesIDs);
    }

    public String getExCodeByTestCodeAndOrder(String testCode, String exOrder) {
        return dtDAO.getExCodeByTestCodeAndOrder(testCode, exOrder);
    }
}
