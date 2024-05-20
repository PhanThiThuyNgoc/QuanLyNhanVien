package com.example.quanlynhanvien;

import com.example.quanlynhanvien.DAO.CongViecDAO;
import com.example.quanlynhanvien.Get.GetInfo;
import com.example.quanlynhanvien.Model.CongViec;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GDCV_ChuathuchienController implements Initializable {

    CongViec selectedCongViec = null;
    private String IdCongViec = null;

    @FXML
    private Button bt_xacnhan;
    @FXML
    private Label lb_idCV;
    @FXML
    private TextField tf_CongViec;

    @FXML
    private TableColumn<CongViec, String> colum_Hannop;

    @FXML
    private TableColumn<CongViec, String> colum_IDcongviec;

    @FXML
    private TableColumn<CongViec, String> colum_congviec;

    @FXML
    private TableView<CongViec> table_taca;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load giao diện dữ liệu
        loadDataToTable();

        // lấy thông tin người dùng chọn
        table_taca.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_taca.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                selectedCongViec = table_taca.getSelectionModel().getSelectedItem();
                IdCongViec = selectedCongViec.getIDCongviec();
                lb_idCV.setText(IdCongViec);
                tf_CongViec.setText(selectedCongViec.getTencongviec());
            }
        });

        bt_xacnhan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String query = "UPDATE `congviec` SET `Trangthaithuchien`='2' WHERE `IDCongViec`= '"+IdCongViec+"' ";
                try {
                    new ConnectDB().getStmt().executeUpdate(query);
                    DBUtils.printAlertMsg("Thông báo", "Chuyển trạng thái công việc thành công!");
                    loadDataToTable();

                } catch (SQLException e) {
                    DBUtils.printAlertMsg("Thông báo", "Chuyển trạng thái công việc thất!");
                    throw new RuntimeException(e);
                }
            }
        });

    }
    public void loadDataToTable(){
        ObservableList<CongViec> listCV= CongViecDAO.getListCongViec(GetInfo.getEmail(), "0");
        colum_IDcongviec.setCellValueFactory(new PropertyValueFactory<>("IDCongviec"));
        colum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        colum_Hannop.setCellValueFactory(new PropertyValueFactory<>("hanNop"));
        table_taca.setItems(listCV);
    }
}
