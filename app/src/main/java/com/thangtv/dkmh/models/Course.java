package com.thangtv.dkmh.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by uendno on 04/04/2016.
 */
public class Course implements Serializable{
    @SerializedName("id")
    int id;

    @SerializedName("id_course")
    String idCourse;

    @SerializedName("name_course")
    String nameCourse;

    @SerializedName("tiet")
    String tiet;

    @SerializedName("si_so")
    int siSo;

    @SerializedName("giao_vien")
    String giaoVien;

    @SerializedName("phong_hoc")
    String phongHoc;

    @SerializedName("so_tin_chi")
    int soTinChi;

    @SerializedName("ghi_chu")
    String ghiChu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getTiet() {
        return tiet;
    }

    public void setTiet(String tiet) {
        this.tiet = tiet;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }
}
