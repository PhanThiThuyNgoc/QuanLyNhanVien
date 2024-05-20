package com.example.quanlynhanvien;

import com.example.quanlynhanvien.DAO.CongViecDAO;
import com.example.quanlynhanvien.Get.GetInfo;
import com.example.quanlynhanvien.Model.CongViec;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GDCV_DangthuchienController implements Initializable {
    CongViec selectedCongViec = null;
    @FXML
    private Label lb_idCongViec;

    @FXML
    private TextArea tf_baoCao;

    @FXML
    private TextField tf_linkSP;

    @FXML
    private TableColumn<CongViec, String> colum_Hannop;

    @FXML
    private TableColumn<CongViec, String> colum_IDcongviec;

    @FXML
    private TableColumn<CongViec, String> colum_congviec;

    @FXML
    private TableView<CongViec> table_taca;

    @FXML
    private Button bt_nopcongviec;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // load dữ liệu lên table
        loadDataToTable();
        // lấy thông tin người dùng chọn

        table_taca.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_taca.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                selectedCongViec = table_taca.getSelectionModel().getSelectedItem();
                lb_idCongViec.setText(selectedCongViec.getIDCongviec());
            }
        });

        bt_nopcongviec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String query = "UPDATE `congviec` SET `BaoCaoCongViec`='"+tf_baoCao.getText()+"',`LinkNopSanPham`='"+tf_linkSP.getText()+"',`Trangthaithuchien`='1' WHERE `IDCongViec` = '"+lb_idCongViec.getText()+"' ";
                try {
                    new ConnectDB().getStmt().executeUpdate(query);
                    DBUtils.printAlertMsg("Thông báo", "Nộp công việc thành công!");
                    loadDataToTable();
                    lb_idCongViec.setText(null);
                    tf_baoCao.clear();
                    tf_linkSP.clear();
                } catch (SQLException e) {
                    DBUtils.printAlertMsg("Thông báo", "Vui lòng nhập đầy đủ thông tin!");
                    throw new RuntimeException(e);
                }
            }
        });


    }

    public void loadDataToTable(){
        ObservableList<CongViec> listCV= CongViecDAO.getListCongViec(GetInfo.getEmail(), "2");
        colum_IDcongviec.setCellValueFactory(new PropertyValueFactory<>("IDCongviec"));
        colum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        colum_Hannop.setCellValueFactory(new PropertyValueFactory<>("hanNop"));
        table_taca.setItems(listCV);
    }
}
