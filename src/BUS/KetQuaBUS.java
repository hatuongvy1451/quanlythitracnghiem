/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DapAnDAO;
import DAO.KetQuaDAO;
import DTO.CauHoiDTO;
import DTO.DapAnDTO;
import DTO.KetQuaDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class KetQuaBUS {
    KetQuaDAO kqDAO = new KetQuaDAO();
    DapAnDAO daDAO = new DapAnDAO();
    ArrayList<KetQuaDTO> listkq = null;
    
    public KetQuaBUS(){
        doclistkq();
    }

    public void doclistkq() {
        this.listkq =  kqDAO.getListKetQua();
    }
    
    //goi danh sach ket qua
    
    public ArrayList<KetQuaDTO> getDanhSachKetQua(){
        if(listkq == null){
            doclistkq();
        }
        return this.listkq;
    }
    
    public boolean addKetQua(KetQuaDTO kq){
        return kqDAO.addKetQua(kq);
    }
    
    public float getDiemTheoUserIDvaExams(int userID, String exCode){
        return kqDAO.getDiemTheoUserIDvaExams(userID, exCode);
    }
    
    public String getThoiGianTheoUserIDvaExams(int userID, String exCode){
        return kqDAO.getThoiGianTheoUserIDvaExams(userID, exCode);
    }
    
    public ArrayList<KetQuaDTO> getListKetQuaTheoID(int userID){
        return kqDAO.getListKetQuaTheoID(userID);
    }
    
    public String getUserAnswers(int userID, String exCode){
        return kqDAO.getUserAnswers(userID, exCode);
    }
    
    public Map<Integer, List<DapAnDTO>> getQuestionsAndAnswersByTopicID(int topicID) {
        Map<Integer, List<DapAnDTO>> questionsAndAnswers = new HashMap<>();
        List<CauHoiDTO> questions = kqDAO.getQuestionsByTopicID(topicID);
        for (CauHoiDTO question : questions) {
            List<DapAnDTO> answers = daDAO.getAnswersByQuestionID(question.getqID());
            questionsAndAnswers.put(question.getqID(), answers);
        }
        return questionsAndAnswers;
    }
  
    
    public String getexCodeTheoUserIDvaExams(int userID){
        return kqDAO.getexCodeTheoUserIDvaExams(userID);
    }
    
    
    
    
    
}
