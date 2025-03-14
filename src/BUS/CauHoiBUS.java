/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CauHoiDAO;
import DAO.DapAnDAO;
import DTO.CauHoiDTO;
import DTO.DapAnDTO;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CauHoiBUS {

    private CauHoiDAO chDAO = new CauHoiDAO();
    ;
    private DapAnDAO daDAO = new DapAnDAO();
    ;
    private ArrayList<CauHoiDTO> listch = null;

    public CauHoiBUS() {
        doclistcauhoi();
    }

    public void doclistcauhoi() {
        this.listch = chDAO.getListCauHoi();
    }

    public ArrayList<CauHoiDTO> getListCauHoi() {
        if (listch == null) {
            doclistcauhoi();
        }
        return listch;
    }

    public ArrayList<CauHoiDTO> getCauHoiChoDeThi(String exCode) {
        return chDAO.getCauHoiChoDeThi(exCode);
    }

    public ArrayList<DapAnDTO> getDapAnChoCauHoi(int questionID) {
        return daDAO.getDapAnByCauHoi(questionID);
    }

    public ArrayList<CauHoiDTO> getQuestion() {
        return chDAO.getQuestions();
    }

    public ArrayList<Integer> getCauHoiTheoqTpIDvaqLevel(int qTopicID, String qLevel) {
        return chDAO.getCauHoiTheoqTpIDvaqLevel(qTopicID, qLevel);
    }

    public void saveQuestion(int qID, String qContent, String qPictures, int qTopicID, String qLevel, int qStatus) {
        chDAO.saveQuestion(qID, qContent, qPictures, qTopicID, qLevel, qStatus);
    }
    
//    public boolean nhapCauHoiTuExcel(int idcauhoi, String noidung, int chude, String mucDo, int trangThai, String hinhAnh) {
//        CauHoiDTO cauHoi = new CauHoiDTO();
//        cauHoi.setqID(idcauhoi);
//        cauHoi.setqContent(noidung);
//        cauHoi.setqTopicID(chude);
//        cauHoi.setqLevel(mucDo);
//        cauHoi.setqStatus(trangThai);
//        cauHoi.setqPictures(hinhAnh);
//        return chDAO.nhapCauHoiTuExcel(cauHoi);
//    }
}
