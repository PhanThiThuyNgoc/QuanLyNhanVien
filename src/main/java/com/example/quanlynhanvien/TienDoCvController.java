package com.example.quanlynhanvien;

import com.example.quanlynhanvien.DAO.CongViecDAO;
import com.example.quanlynhanvien.Model.CongViec;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TienDoCvController implements Initializable {
    @FXML
    private AnchorPane acp_TienDo;

    @FXML
    private Label lb_PhanTramCV;

    @FXML
    private Label lb_ngaybatdau;

    @FXML
    private Label lb_ngayketthuc;

    @FXML
    private Label lb_nguoithuchien;

    @FXML
    private TextField tf_tenCV;

    @FXML
    private TableView<CongViec> table_congviec;

    @FXML
    private TableColumn<CongViec, String> tableclum_congviec;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load thông tin
        loadDataToTable();

        // thay đổi giá trị tổng quan
        // số lượng công việc
        int congviec = CongViecDAO.getCoutCongviec();
        // tiến độ công vệc đã hoàn thành
        int congviechoanthanh = CongViecDAO.getCoutCongviechoanthanh();
        int phantram = (congviechoanthanh*100)/congviec;
        // tỉ lệ độ dài
        int tiledodai = phantram*4;
        acp_TienDo.setPrefWidth(tiledodai);
        lb_PhanTramCV.setText(phantram + "%");


        // lấy thông tin người dùng chọn
        table_congviec.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table_congviec.getSelectionModel().getSelectedItem() != null) { // Kiểm tra nếu người dùng double click vào một row
                CongViec selectedCongViec = table_congviec.getSelectionModel().getSelectedItem();
                String IdCongViec = selectedCongViec.getIDCongviec();
                // Lấy thông tin công việc tương ứng
                CongViec cv = CongViecDAO.getInfoCongViec(IdCongViec);
                // load thông tin lên giao diện

                tf_tenCV.setText(cv.getTencongviec());
                lb_ngaybatdau.setText(String.valueOf(cv.getNgayBatDau()));
                lb_ngayketthuc.setText(String.valueOf(cv.getHanNop()));
                lb_nguoithuchien.setText(cv.getNhanVien());
                lb_nguoithuchien.setText(cv.getTrangthaithuchien());
            }
        });

    }
    public void loadDataToTable(){
        table_congviec.setItems(null);
        ObservableList<CongViec> listCV= CongViecDAO.getDanhSachCongViec();
        tableclum_congviec.setCellValueFactory(new PropertyValueFactory<>("Tencongviec"));
        table_congviec.setItems(listCV);
    }
}
