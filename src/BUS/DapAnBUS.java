/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DapAnDAO;
import DTO.DapAnDTO;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class DapAnBUS {

    private DapAnDAO cdDAO = new DapAnDAO();
    ;
    private ArrayList<DapAnDTO> listda = null;

    public DapAnBUS() {
        doclistdapan();
    }

    public void doclistdapan() {
        this.listda = cdDAO.getDanhSachDapAn();
    }

    public ArrayList<DapAnDTO> getListDapAn() {
        if (listda == null) {
            doclistdapan();
        }
        return listda;
    }

    public String addAnswers(DapAnDTO aw) throws SQLException {
        if (cdDAO.addAnswers(aw)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    public void saveAnswer(int awID, int qID, String awContent, String awPictures, int isRight, int awStatus) {
        cdDAO.saveAnswer(awID, qID, awContent, awPictures, isRight, awStatus);
    }
}
