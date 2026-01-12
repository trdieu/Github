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
public class ModuleEntity {

    public ModuleEntity() {
    }

    public ModuleEntity(String maHp, String tenHp, String thoiLuong, int tinChi, float tinChiHocPhi, float trongSo, String tenKhoa) {
        this.maHp = maHp;
        this.tenHp = tenHp;
        this.thoiLuong = thoiLuong;
        this.tinChi = tinChi;
        this.tinChiHocPhi = tinChiHocPhi;
        this.trongSo = trongSo;
        this.tenKhoa = tenKhoa;
    }

    public String getMaHp() {
        return maHp;
    }

    public String getTenHp() {
        return tenHp;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public int getTinChi() {
        return tinChi;
    }

    public float getTinChiHocPhi() {
        return tinChiHocPhi;
    }

    public float getTrongSo() {
        return trongSo;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setMaHp(String maHp) {
        this.maHp = maHp;
    }

    public void setTenHp(String tenHp) {
        this.tenHp = tenHp;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public void setTinChi(int tinChi) {
        this.tinChi = tinChi;
    }

    public void setTinChiHocPhi(float tinChiHocPhi) {
        this.tinChiHocPhi = tinChiHocPhi;
    }

    public void setTrongSo(float trongSo) {
        this.trongSo = trongSo;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }
    
    private String maHp;
    private String tenHp;
    private String thoiLuong;
    private int tinChi;
    private float tinChiHocPhi;
    private float trongSo;
    private String tenKhoa;
    
}
