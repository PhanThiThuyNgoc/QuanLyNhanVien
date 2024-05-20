package com.example.quanlynhanvien;

import com.example.quanlynhanvien.DAO.CongViecDAO;
import com.example.quanlynhanvien.DAO.NhanvienDAO;
import com.example.quanlynhanvien.Model.CongViec;
import com.example.quanlynhanvien.Model.NhanVien;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PhanCongCvController implements Initializable {
    public String IdCongViec;

    @FXML
    private Button sua;

    @FXML
    private TableColumn<CongViec, String> table_Id;

    @FXML
    private TableColumn<CongViec, String> table_cv;
    @FXML
    private TextField tf_Ngaybd;

    @FXML
    private TextField tf_Ngayketthuc;

    @FXML
    private TextField tf_Nguoithuchien;

    @FXML
    private TextField tf_SanPham;


    @FXML
    private TextArea tf_baoCao;

    @FXML
    private TextField tf_cv;

    @FXML
    private Button them;

    @FXML
    private Button xoa;

    @FXML
    private Label lb_ClearGiaoDien;

    @FXML
    private TableView<CongViec> table_tong;
    @FXML
    private ComboBox<String> cb_NhanVien;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load thông tin
        loadDataToTable();
        // lấy danh sách nhân viên
        ObservableList<String> listNV = NhanvienDAO.getDanhSachTenNhanVien();
        cb_NhanVien.setItems(listNV);



        // lấy thông tin người dùng chọn
        table_tong.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_tong.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                CongViec selectedCongViec = table_tong.getSelectionModel().getSelectedItem();
                IdCongViec = selectedCongViec.getIDCongviec();
                // Lấy thông tin công việc tương ứng
                CongViec cv = CongViecDAO.getInfoCongViec(IdCongViec);
                // load thông tin lên giao diện
                tf_baoCao.setText(cv.getBaocaocongviec());
                tf_cv.setText(cv.getTencongviec());
                tf_Ngaybd.setText(String.valueOf(cv.getNgayBatDau()));
                tf_Ngayketthuc.setText(String.valueOf(cv.getHanNop()));
                tf_SanPham.setText(cv.getLinkNopsanpham());
                tf_Nguoithuchien.setText(cv.getNhanVien());

            }
        });

        lb_ClearGiaoDien.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearGiaoDien();
            }
        });

        them.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //String tenNV, String CongViec, LocalDate HanNop
                CongViecDAO.addCongViec(cb_NhanVien.getValue(), tf_cv.getText(), LocalDate.parse(tf_Ngayketthuc.getText()));
                // thông báo
                DBUtils.printAlertMsg("Thông báo", "Phân công công việc thành công!");
                // làm sạch giao diện
                clearGiaoDien();
            }
        });

        sua.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tf_cv.getText().equals("")){
                    DBUtils.printAlertMsg("Thông báo", "Chưa chọn công việc!");
                }else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Xác nhận");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn có muốn lưu sự thay đổi dữ liệu không?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        String nhanVien = cb_NhanVien.getValue();
                        // Người dùng chọn OK (true)
                        //String IdCongViec,String tenNV, String CongViec, LocalDate HanNop, LocalDate NgayBatDau
                        if(cb_NhanVien.getValue().equals("")){
                            nhanVien = tf_Nguoithuchien.getText();
                        }
                        CongViecDAO.UpdateCongViec(IdCongViec,cb_NhanVien.getValue(), tf_cv.getText(), LocalDate.parse(tf_Ngayketthuc.getText()),  LocalDate.parse(tf_Ngaybd.getText()));
                        // thông báo
                        DBUtils.printAlertMsg("Thông báo", "Lưu sự thay đổi thành công!");
                    }
                    // làm sạch giao diện
                    clearGiaoDien();
                }

            }
        });


        xoa.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có muốn lưu sự thay đổi dữ liệu không?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String nhanVien = cb_NhanVien.getValue();
                    // Người dùng chọn OK (true)
                    //String IdCongViec,String tenNV, String CongViec, LocalDate HanNop, LocalDate NgayBatDau
                    CongViecDAO.DeleteCongViec(IdCongViec);
                    DBUtils.printAlertMsg("Thông báo", "Xoá công việc thành công!");
                    loadDataToTable();
                }

            }
        });
    }

    public void loadDataToTable(){
        table_tong.setItems(null);
        ObservableList<CongViec> listCV= CongViecDAO.getDanhSachCongViec();
        table_Id.setCellValueFactory(new PropertyValueFactory<>("IDCongviec"));
        table_cv.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        table_tong.setItems(listCV);
    }
    public void clearGiaoDien(){
        tf_baoCao.clear();
        tf_cv.clear();
        tf_Ngaybd.clear();
        tf_Ngayketthuc.clear();
        tf_SanPham.clear();
        tf_Nguoithuchien.clear();
        cb_NhanVien.setValue(null);
    }

}

