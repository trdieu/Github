/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.entity;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class AcademicTranscriptEntity implements Serializable{

    public String getMssv() {
        return mssv;
    }

    public String getHocKy() {
        return hocKy;
    }

    public Float getDiemQt() {
        return diemQt;
    }

    public Float getDiemCk() {
        return diemCk;
    }

    public String getDiemChu() {
        return diemChu;
    }

    public String getTenHp() {
        return tenHp;
    }

    public String getMaHp() {
        return maHp;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public void setDiemQt(Float diemQt) {
        this.diemQt = diemQt;
    }

    public void setDiemCk(Float diemCk) {
        this.diemCk = diemCk;
    }

    public void setDiemChu(String diemChu) {
        this.diemChu = diemChu;
    }

    public void setTenHp(String tenHp) {
        this.tenHp = tenHp;
    }

    public void setMaHp(String maHp) {
        this.maHp = maHp;
    }

    public int getTc() {
        return tc;
    }

    public void setTc(int tc) {
        this.tc = tc;
    }
    
    
    public String getMaLopDK() {
        return maLopDK;
    }

    public void setMaLopDK(String maLopDK) {
        this.maLopDK = maLopDK;
    }
    
    public AcademicTranscriptEntity(String mssv, String maLopDK, String hocKy, Float diemQt, Float diemCk, String diemChu, String tenHp, String maHp, int tc) {
        this.mssv = mssv;
        this.maLopDK = maLopDK;
        this.hocKy = hocKy;
        this.diemQt = diemQt;
        this.diemCk = diemCk;
        this.diemChu = diemChu;
        this.tenHp = tenHp;
        this.maHp = maHp;
        this.tc = tc;
    }

    public AcademicTranscriptEntity() {
    }
    
    private int tc;
    private String mssv;
    private String maLopDK;
    private String hocKy;
    private Float diemQt;
    private Float diemCk;
    private String diemChu;
    private String tenHp;
    private String maHp;
}
