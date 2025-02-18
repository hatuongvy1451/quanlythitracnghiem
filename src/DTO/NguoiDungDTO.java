/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Tuong Vy Ha
 */
public class NguoiDungDTO {
    private int maND;
    private String tenDN, matKhau, hoTen, email, vaiTro;

    public int getMaND(){
        return maND;
    }
    
    public void setMaND(int maND){
        this.maND = maND;
    }
    
    public String getTenDN(){
        return tenDN;
    }
  
    public void setTenDN(String tenDN){
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
      @Override
    public String toString() {
        return "NguoiDungDTO{" +
                "maND=" + maND +
                ", tenDN='" + tenDN + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", email='" + email + '\'' +
                ", vaiTro='" + vaiTro + '\'' +
                '}';
    }
}
