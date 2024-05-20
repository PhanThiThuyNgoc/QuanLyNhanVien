package com.example.quanlynhanvien.DAO;

import com.example.quanlynhanvien.ConnectDB;
import com.example.quanlynhanvien.Model.CongViec;
import com.example.quanlynhanvien.Model.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class NhanvienDAO {
    // đếm số lượng nhân viên. trạng thái bao gồm: 1= đang lm việc , 0: là nghĩ việc
    public static  String  getCoutNhanvien(String trangthai){
        String query = "SELECT COUNT(*) FROM nhanvien WHERE `Trangthai` = '"+trangthai+"'";
        String soluongnhanvien = null;
        try {
            ResultSet nhanvien = new ConnectDB().getStmt().executeQuery(query);
            while(nhanvien.next()){
                soluongnhanvien = nhanvien.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return soluongnhanvien;
    }
    public static NhanVien getInfoUser(String email){
        String query = "SELECT `IDNhanVien` , `HoVaTen`, `ChucVu`, `DiaChi`, `NgaySinh`, `GioiTinh`, `SoDienThoai`, `Gmail`, `NgayBatDauLamViec` , `Avatar` FROM `nhanvien` WHERE `Gmail` = '"+email+"'";

        NhanVien nhanVien = new NhanVien();
        try {
            ResultSet data = new ConnectDB().getStmt().executeQuery(query);
            while (data.next()){
                nhanVien.setHovaten(data.getString("HoVaTen"));
                nhanVien.setChucvu(data.getString("ChucVu"));
                nhanVien.setIDNhanvien(data.getString("IDNhanVien"));
                nhanVien.setDiachi(data.getString("DiaChi"));
                nhanVien.setNgaysinh(data.getObject("NgaySinh", LocalDate.class));
                nhanVien.setGioitinh(data.getString("GioiTinh"));
                nhanVien.setSodienthoai(data.getString("SoDienThoai"));
                nhanVien.setNgaybatdaulamviec(data.getObject("NgayBatDauLamViec", LocalDate.class));
                nhanVien.setLinkAvatar(data.getString("Avatar"));
                nhanVien.setGmail(data.getString("Gmail"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nhanVien;
    }
    public static ObservableList<NhanVien> getDanhSachNhanVien(){
        ObservableList<NhanVien> listNV = FXCollections.observableArrayList();
        String query = "SELECT `IDNhanVien`, `HoVaTen` FROM `nhanvien` WHERE `Trangthai` ='1'";
        try {
            // lấy danh sách nhân viên
            ResultSet nv=  new ConnectDB().getStmt().executeQuery(query);
            while (nv.next()){
                NhanVien nhanVien = new NhanVien();
                nhanVien.setIDNhanvien(nv.getString("IDNhanVien"));
                nhanVien.setHovaten(nv.getString("HoVaTen"));
                listNV.add(nhanVien);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listNV;
    }
    public static NhanVien getInfoUserByID(String idUser){
        String query = "SELECT `IDNhanVien` , `HoVaTen`, `ChucVu`, `DiaChi`, `NgaySinh`, `GioiTinh`, `SoDienThoai`, `Gmail`, `NgayBatDauLamViec` , `Avatar` FROM `nhanvien` WHERE `IDNhanVien` = '"+idUser+"'";

        NhanVien nhanVien = new NhanVien();
        try {
            ResultSet data = new ConnectDB().getStmt().executeQuery(query);
            while (data.next()){
                nhanVien.setHovaten(data.getString("HoVaTen"));
                nhanVien.setChucvu(data.getString("ChucVu"));
                nhanVien.setIDNhanvien(data.getString("IDNhanVien"));
                nhanVien.setDiachi(data.getString("DiaChi"));
                nhanVien.setNgaysinh(data.getObject("NgaySinh", LocalDate.class));
                nhanVien.setGioitinh(data.getString("GioiTinh"));
                nhanVien.setSodienthoai(data.getString("SoDienThoai"));
                nhanVien.setNgaybatdaulamviec(data.getObject("NgayBatDauLamViec", LocalDate.class));
                nhanVien.setLinkAvatar(data.getString("Avatar"));
                nhanVien.setGmail(data.getString("Gmail"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nhanVien;
    }

    public static ObservableList<String> getDanhSachTenNhanVien(){
        ObservableList<String> listNV = FXCollections.observableArrayList();
        String query = "SELECT `HoVaTen` FROM `nhanvien` WHERE `Trangthai` ='1'";
        try {
            // lấy danh sách nhân viên
            ResultSet nv=  new ConnectDB().getStmt().executeQuery(query);
            while (nv.next()){
                listNV.add(nv.getString("HoVaTen"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listNV;
    }
    public static  void themnhanvien (String email){
        String query = "UPDATE `nhanvien` SET `Trangthai`='1' WHERE `Gmail` = '"+email+"'";
        try {
            new ConnectDB().getStmt().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  static void xoanhanvien(String IdNhanvien){
        String query ="UPDATE `nhanvien` SET `Trangthai`='0' WHERE `IDNhanVien`='"+IdNhanvien+"'";
        try {
            new ConnectDB().getStmt().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
