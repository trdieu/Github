/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.entity;

/**
 *
 * @author Dell
 */
public class StudyResultEntity {

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public float getCpa() {
        return cpa;
    }

    public void setCpa(float cpa) {
        this.cpa = cpa;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public int getTcQua() {
        return tcQua;
    }

    public void setTcQua(int tcQua) {
        this.tcQua = tcQua;
    }

    public int getTcTichLuy() {
        return tcTichLuy;
    }

    public void setTcTichLuy(int tcTichLuy) {
        this.tcTichLuy = tcTichLuy;
    }

    public int getTcNoDk() {
        return tcNoDk;
    }

    public void setTcNoDk(int tcNoDk) {
        this.tcNoDk = tcNoDk;
    }

    public int getTcDk() {
        return tcDk;
    }

    public void setTcDk(int tcDk) {
        this.tcDk = tcDk;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getThieuDiem() {
        return thieuDiem;
    }

    public void setThieuDiem(String thieuDiem) {
        this.thieuDiem = thieuDiem;
    }

    public StudyResultEntity() {
    }

    public StudyResultEntity(String hocKy, float cpa, float gpa, int tcQua, int tcTichLuy, int tcNoDk, int tcDk, String trinhDo, String thieuDiem) {
        this.hocKy = hocKy;
        this.cpa = cpa;
        this.gpa = gpa;
        this.tcQua = tcQua;
        this.tcTichLuy = tcTichLuy;
        this.tcNoDk = tcNoDk;
        this.tcDk = tcDk;
        this.trinhDo = trinhDo;
        this.thieuDiem = thieuDiem;
    }
    
    private String hocKy;
    private float cpa;
    private float gpa;
    private int tcQua;
    private int tcTichLuy;
    private int tcNoDk;
    private int tcDk;
    private String trinhDo;
    private String thieuDiem;
    
}
