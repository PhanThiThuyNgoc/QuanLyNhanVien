package com.example.quanlynhanvien.DAO;

import com.example.quanlynhanvien.ConnectDB;
import com.example.quanlynhanvien.Model.CongViec;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CongViecDAO {
    public  static ObservableList<CongViec> getDanhSachCongViec(String email){
        String query = "SELECT `IDCongViec`,`HanNop`, `BaoCaoCongViec`, `LinkNopSanPham`, `Trangthaithuchien`, `Tencongviec` FROM `congviec` INNER JOIN nhanvien ON nhanvien.IDNhanVien = congviec.IDNhanVien WHERE nhanvien.Gmail = '"+email+"'  ";
        ObservableList<CongViec> listCongViec = FXCollections.observableArrayList();
        try {
            ResultSet cv=  new ConnectDB().getStmt().executeQuery(query);
            while(cv.next()){
                CongViec cvTmp = new CongViec();
                cvTmp.setIDCongviec(cv.getString("IDCongViec"));
                cvTmp.setHanNop(cv.getObject("HanNop", LocalDate.class));
                cvTmp.setTencongviec(cv.getString("Tencongviec"));

                // xử lý trạng thái công việc
                //0:chưa hoàn thành; 1: hoàn thành; 2: Đang làm ; 3: Bị hủy
                String trangthai  = null;
                if (cv.getString("Trangthaithuchien").equals("1")){
                    trangthai = "Hoàn thành";
                } else if(cv.getString("Trangthaithuchien").equals("2")){
                    trangthai = "Đang thực hiện";
                } else if (cv.getString("Trangthaithuchien").equals("3")) {
                    trangthai = "Bị hủy ";

                } else  {
                    trangthai = "Chưa hoàn thành ";

                }
                cvTmp.setTrangthaithuchien(trangthai);
                listCongViec.add(cvTmp);

            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    return listCongViec;
    }

    public  static ObservableList<CongViec> getDanhSachCongViec(){
        String query = "SELECT `IDCongViec`, `Tencongviec` FROM `congviec`";
        ObservableList<CongViec> listCongViec = FXCollections.observableArrayList();
        try {
            ResultSet cv=  new ConnectDB().getStmt().executeQuery(query);
            while(cv.next()){
                CongViec cvTmp = new CongViec();
                cvTmp.setIDCongviec(cv.getString("IDCongViec"));
                cvTmp.setTencongviec(cv.getString("Tencongviec"));
                listCongViec.add(cvTmp);

            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return listCongViec;
    }

    // lấy công việc đang thuwjc hiện
    public  static ObservableList<CongViec> getDanhSachCongViecDangThucHien(){
        String query = "SELECT `IDCongViec`, `HanNop`, `Tencongviec` FROM `congviec` WHERE `Trangthaithuchien` = '1' AND `IDNhanVien` = ''";
        ObservableList<CongViec> listCongViec = FXCollections.observableArrayList();
        try {
            ResultSet cv=  new ConnectDB().getStmt().executeQuery(query);
            while(cv.next()){
                CongViec cvTmp = new CongViec();
                cvTmp.setIDCongviec(cv.getString("IDCongViec"));
                cvTmp.setHanNop(cv.getObject("HanNop", LocalDate.class));
                cvTmp.setTencongviec(cv.getString("Tencongviec"));

                // xử lý trạng thái công việc
                //0:chưa hoàn thành; 1: hoàn thành; 2: Đang làm ; 3: Bị hủy
                String trangthai  = null;
                if (cv.getString("Trangthaithuchien").equals("1")){
                    trangthai = "Hoàn thành";
                } else if(cv.getString("Trangthaithuchien").equals("2")){
                    trangthai = "Đang thực hiện";
                } else if (cv.getString("Trangthaithuchien").equals("3")) {
                    trangthai = "Bị hủy ";

                } else  {
                    trangthai = "Chưa hoàn thành ";

                }
                cvTmp.setTrangthaithuchien(trangthai);
                listCongViec.add(cvTmp);

            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return listCongViec;
    }


    public static ObservableList<CongViec> getListCongViec(String email, String trangThai){
        ObservableList<CongViec> listCV = FXCollections.observableArrayList();
        listCV.clear();
        String query = "SELECT `IDCongViec`, `HanNop`, `Trangthaithuchien`, `Tencongviec` FROM `congviec` INNER JOIN `nhanvien` ON congviec.IDNhanVien = nhanvien.IDNhanVien AND nhanvien.Gmail = '"+email+"' AND `Trangthaithuchien` = '"+trangThai+"'";
        try {
            ResultSet Congviec = new ConnectDB().getStmt().executeQuery(query);
            while (Congviec.next()){
                CongViec cv = new CongViec();
                cv.setIDCongviec(Congviec.getString("IDCongViec"));
                cv.setTencongviec(Congviec.getString("Tencongviec"));
                cv.setHanNop(Congviec.getObject("HanNop", LocalDate.class));
                // xử lý trạng thái công việc
                //0:chưa hoàn thành; 1: hoàn thành; 2: Đang làm ; 3: Bị hủy
                String trangthai  = null;
                if (trangThai.equals("1")){
                    trangthai = "Hoàn thành";
                } else if(trangThai.equals("2")){
                    trangthai = "Đang thực hiện";
                } else if (trangThai.equals("3")) {
                    trangthai = "Bị hủy ";

                } else  {
                    trangthai = "Chưa hoàn thành ";
                }
                cv.setTrangthaithuchien(trangthai);
                listCV.add(cv);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCV;
    }

    public static CongViec getInfoCongViec(String idCongViec){
        String query = "SELECT  `HanNop`, `NgayBatDau`, `BaoCaoCongViec`, `LinkNopSanPham`, `Trangthaithuchien`, `Tencongviec` , nhanvien.HoVaTen  FROM `congviec` INNER JOIN nhanvien ON congviec.IDNhanVien = nhanvien.IDNhanVien AND `IDCongViec` = '"+idCongViec+"'";
        CongViec cvTmp = new CongViec();
        try {
            ResultSet cv=  new ConnectDB().getStmt().executeQuery(query);

            while(cv.next()){
                cvTmp.setTencongviec(cv.getString("Tencongviec"));
                cvTmp.setBaocaocongviec(cv.getString("BaoCaoCongViec"));
                cvTmp.setLinkNopsanpham(cv.getString("LinkNopSanPham"));
                cvTmp.setHanNop(cv.getObject("HanNop", LocalDate.class));
                cvTmp.setNgayBatDau(cv.getObject("NgayBatDau", LocalDate.class));
                //xử lý trạng thái
                int trangthai = cv.getInt("Trangthaithuchien");
                String TrangThai = null;
                //0:chưa hoàn thành; 1: hoàn thành; 2: Đang làm ; 3: Bị hủy
                if (trangthai == 0){
                    TrangThai = "Chưa hoàn thành";
                } else if (trangthai == 1) {
                    TrangThai = "Hoàn thành";
                } else if (trangthai == 2) {
                    TrangThai = "Đang thực hiện";
                }else {
                    TrangThai = "Bị huỷ";
                }
                cvTmp.setTrangthaithuchien(TrangThai);
                cvTmp.setNhanVien(cv.getString(7));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return cvTmp;
    }
    public  static  int getCoutCongviechoanthanh(){
        String query = "SELECT COUNT(*) FROM congviec WHERE `Trangthaithuchien` = '1'";
        int  congviechoanthanh =0;
        try {
            ResultSet data = new ConnectDB().getStmt().executeQuery(query);
            while (data.next()){
                congviechoanthanh = data.getInt(1);
            }

        }catch(SQLException e){
            throw  new RuntimeException(e);
        }
        return  congviechoanthanh;
    }
    public  static  int getCoutCongviec(){
        String query = "SELECT COUNT(*) FROM congviec ";
        int soluongcongviec = 0;
        try {
            ResultSet congviec = new ConnectDB().getStmt().executeQuery(query);
            while (congviec.next()){
                soluongcongviec = congviec.getInt(1);
            }
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return  soluongcongviec;
    }

    public static void addCongViec(String tenNV, String CongViec, LocalDate HanNop){
        String getIdNV = "SELECT `IDNhanVien` FROM `nhanvien` WHERE `HoVaTen` = '"+tenNV+"'";

        try {
            String idNV = null;
            ResultSet dataNV = new ConnectDB().getStmt().executeQuery(getIdNV);
            while (dataNV.next()){
                idNV = dataNV.getString("IDNhanVien");
            }

            // add công việc
            String query = "INSERT INTO `congviec`(`IDNhanVien`, `HanNop`, `NgayBatDau`, `Tencongviec`) VALUES ('"+idNV+"','"+HanNop+"','"+LocalDate.now()+"','"+CongViec+"'";
            new ConnectDB().getStmt().executeUpdate(query);

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void UpdateCongViec(String IdCongViec,String tenNV, String CongViec, LocalDate HanNop, LocalDate NgayBatDau){
        String getIdNV = "SELECT `IDNhanVien` FROM `nhanvien` WHERE `HoVaTen` = '"+tenNV+"'";

        try {
            String idNV = null;
            ResultSet dataNV = new ConnectDB().getStmt().executeQuery(getIdNV);
            while (dataNV.next()){
                idNV = dataNV.getString("IDNhanVien");
            }

            // add công việc
            String query = "UPDATE `congviec` SET `IDNhanVien`='"+idNV+"',`HanNop`='"+HanNop+"',`NgayBatDau`='"+NgayBatDau+"',`Tencongviec`='"+CongViec+"' WHERE `IDCongViec` = '"+IdCongViec+"'";
            new ConnectDB().getStmt().executeUpdate(query);

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public  static  void  DeleteCongViec(String IDCongViec){
        String query = "DELETE FROM `congviec` WHERE `IDCongViec` = '"+IDCongViec+"'";
        try {
            new ConnectDB().getStmt().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
