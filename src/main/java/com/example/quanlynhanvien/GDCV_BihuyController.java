package com.example.quanlynhanvien;

import com.example.quanlynhanvien.DAO.CongViecDAO;
import com.example.quanlynhanvien.Get.GetInfo;
import com.example.quanlynhanvien.Model.CongViec;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class GDCV_BihuyController implements Initializable {

    @FXML
    private TableColumn<CongViec, String> colum_IDcongviec;

    @FXML
    private TableColumn<CongViec, String> colum_Trangthaithuchien;

    @FXML
    private TableColumn<CongViec, String> colum_congviec;

    @FXML
    private TableView<CongViec> table_taca;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<CongViec> listCV= CongViecDAO.getListCongViec(GetInfo.getEmail(), "3");
        colum_IDcongviec.setCellValueFactory(new PropertyValueFactory<>("IDCongviec"));
        colum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        colum_Trangthaithuchien.setCellValueFactory(new PropertyValueFactory<>("Trangthaithuchien"));
        table_taca.setItems(listCV);
    }
}
