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

public class ClassListEntity {
    private String ho;
    private String dem;
    private String ten;
    private Date ngaySinh;
    private String tenLop;
    private String mssv;

    public ClassListEntity() {
    }
    
    public ClassListEntity(String ho, String dem, String ten, Date ngaySinh, String tenLop, String mssv) {
        this.ho = ho;
        this.dem = dem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.tenLop = tenLop;
        this.mssv = mssv;
    }

    public String getMssv() {
        return mssv;
    }

    public String getHo() {
        return ho;
    }

    public String getDem() {
        return dem;
    }

    public String getTen() {
        return ten;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public void setDem(String dem) {
        this.dem = dem;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
