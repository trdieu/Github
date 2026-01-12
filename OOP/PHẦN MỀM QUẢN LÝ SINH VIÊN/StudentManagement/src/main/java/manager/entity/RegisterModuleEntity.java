/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

/**
 *
 * @author Dell
 */
public class RegisterModuleEntity  implements Serializable{

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getMaHp() {
        return maHp;
    }

    public void setMaHp(String maHp) {
        this.maHp = maHp;
    }

    public String getTenHp() {
        return tenHp;
    }

    public void setTenHp(String tenHp) {
        this.tenHp = tenHp;
    }

    public Date getNgayDk() {
        return ngayDk;
    }

    public void setNgayDk(Date ngayDk) {
        this.ngayDk = ngayDk;
    }

    public String getTrangThaiDk() {
        return trangThaiDk;
    }

    public void setTrangThaiDk(String trangThaiDk) {
        this.trangThaiDk = trangThaiDk;
    }

    public int getTinChi() {
        return tinChi;
    }

    public void setTinChi(int tinChi) {
        this.tinChi = tinChi;
    }

    public RegisterModuleEntity() {
    }

    public RegisterModuleEntity(String mssv, String maHp, String tenHp, Date ngayDk, String trangThaiDk, int tinChi, String hocKy) {
        this.mssv = mssv;
        this.maHp = maHp;
        this.tenHp = tenHp;
        this.ngayDk = ngayDk;
        this.trangThaiDk = trangThaiDk;
        this.tinChi = tinChi;
        this.hocKy = hocKy;
    }
    
    private String hocKy;

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }
    private String mssv;
    private String maHp;
    private String tenHp;
    private Date ngayDk;
    private String trangThaiDk;
    @Column(name = "tinchi")
    private int tinChi;
}
