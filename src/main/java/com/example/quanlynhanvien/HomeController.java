package com.example.quanlynhanvien;

import com.example.quanlynhanvien.DAO.CongViecDAO;
import com.example.quanlynhanvien.DAO.NhanvienDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label lb_congviec;

    @FXML
    private Label lb_nv_ngiviec;

    @FXML
    private Label lb_soluongnhanvien;

    @FXML
    private Label lb_tiendo;

    @FXML
    private Label new_1;

    @FXML
    private Label new_2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // cập nhập số lương nhân viên ddang làm việc
        String soluongnhanvien = NhanvienDAO.getCoutNhanvien("1");
        lb_soluongnhanvien.setText(soluongnhanvien);

        // cập nhập số lương nhân viên  đã nghĩ  việc
        String nhanviennghiviec = NhanvienDAO.getCoutNhanvien("0");
        lb_nv_ngiviec.setText(nhanviennghiviec);

        // cập nhập ố lượng công việc
        int congviec = CongViecDAO.getCoutCongviec();
        lb_congviec.setText(String.valueOf(congviec));

        // cập nhập tiến độ công vệc
        int congviechoanthanh = CongViecDAO.getCoutCongviechoanthanh();
        int phantram = (congviechoanthanh*100)/congviec;
        lb_tiendo.setText(phantram+"%");
    }
}
