package com.example.quanlynhanvien;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GiaoDienNhanVienController implements Initializable {
    @FXML
    private AnchorPane Acp_nhanvien_ShowFile;

    @FXML
    private Button bt_congviec;

    @FXML
    private Button bt_dangnhap;

    @FXML
    private Button bt_home;

    @FXML
    private Button bt_lylich;

    @FXML
    private Button bt_nhom;

    @FXML
    private TextField tf_timkiem;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //xóa các giá trị con đang tồn tại trên AnchoPane Acp_nhanvien_ShowFile
        Acp_nhanvien_ShowFile.getChildren().clear();

        AnchorPane anchorPane = null;
        try {
            // lấy ra file fxml con đưa vào biến AnchorPane
            anchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //load file fxml con vào Acp_nhanvien_ShowFile để hiển thij ra màn giao diện
        Acp_nhanvien_ShowFile.getChildren().add(anchorPane);


        // Thiết lập sự kiện cho button home
        bt_home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //xóa các giá trị con đang tồn tại trên AnchoPane Acp_nhanvien_ShowFile
                Acp_nhanvien_ShowFile.getChildren().clear();

                AnchorPane anchorPane = null;
                try {
                    // lấy ra file fxml con đưa vào biến AnchorPane
                    anchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //load file fxml con vào Acp_nhanvien_ShowFile để hiển thij ra màn giao diện
                Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
            }
        });
        // Thiết lập sự kiện cho button lí lịch
        bt_lylich.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // xoa cac gia trị ton tại trong  Acp_nhanvien_ShowFile
               Acp_nhanvien_ShowFile.getChildren().clear();
               AnchorPane  anchorPane= null;
               try {
                   // lấy ra file fxml con đưa vào biến AnchorPane
                   anchorPane = FXMLLoader.load(getClass().getResource("giaodienlilich.fxml"));

               }catch(IOException e){
                   throw  new RuntimeException(e);
                }
                //load file fxml con vào Acp_nhanvien_ShowFile để hiển thij ra màn giao diện
                Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
            }
        });
        // Thiết lập sự kiện cho button cong việc
        bt_congviec.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    DBUtils.changeScene(actionEvent, "GiaoDienCongViec.fxml", "Trang nhân viên", 540, 810);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // Thiết lập sự kiện cho button nhom
        bt_nhom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // xóa
                Acp_nhanvien_ShowFile.getChildren().clear();
                AnchorPane anchorPane= null;
                try{
                    anchorPane = FXMLLoader.load(getClass().getResource("GiaoDienNV_Chat.fxml"));
                } catch(Exception e){
                    throw new RuntimeException(e);

                }
                Acp_nhanvien_ShowFile.getChildren().add(anchorPane);
            }
        });
        bt_dangnhap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    DBUtils.changeScene(actionEvent,"GiaoDien.fxml", "Trang đăng nhập", 500, 800);
                } catch(IOException e){
                    throw  new RuntimeException(e);
                }
            }
        });
        tf_timkiem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }
}
