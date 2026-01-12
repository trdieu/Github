/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.entity;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class InformationEntity {

    public InformationEntity() {
    }

    public String getMssv() {
        return mssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getLop() {
        return lop;
    }

    public String getHeHoc() {
        return heHoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getEmail() {
        return email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void setHeHoc(String heHoc) {
        this.heHoc = heHoc;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public InformationEntity(String mssv, String hoTen, Date ngaySinh, String lop, String heHoc, String trangThai, String email, String diaChi, String soDT, byte[] image) {
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.lop = lop;
        this.heHoc = heHoc;
        this.trangThai = trangThai;
        this.email = email;
        this.diaChi = diaChi;
        this.soDT = soDT;
        this.image = image;
    }
    private String mssv;
    private String hoTen;
    private Date ngaySinh;
    private String lop;
    private String heHoc;
    private String trangThai;
    private String email;
    private String diaChi;
    private String soDT;
    private byte[] image;
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
}
