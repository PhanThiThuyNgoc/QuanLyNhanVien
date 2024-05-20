package com.example.quanlynhanvien;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GocController implements Initializable {
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button dangxuat;

    @FXML
    private Button home;

    @FXML
    private AnchorPane pane_load_file;

    @FXML
    private Button phan_cv;

    @FXML
    private Button thongtin;

    @FXML
    private Button tien_do;

    @FXML
    private TextField timkiemnhanh;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.pane_load_file.getChildren().clear();
            AnchorPane anchorPane = null;
            anchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
            pane_load_file.getChildren().add(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    pane_load_file.getChildren().clear();
                    AnchorPane anchorPane = null;
                    anchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
                    pane_load_file.getChildren().add(anchorPane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thongtin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    pane_load_file.getChildren().clear();
                    AnchorPane anchorPane = null;
                    anchorPane = FXMLLoader.load(getClass().getResource("ThongtinNV.fxml"));
                    pane_load_file.getChildren().add(anchorPane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        phan_cv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    pane_load_file.getChildren().clear();
                    AnchorPane anchorPane = null;
                    anchorPane = FXMLLoader.load(getClass().getResource("PhanCongCV.fxml"));
                    pane_load_file.getChildren().add(anchorPane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        tien_do.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    pane_load_file.getChildren().clear();
                    AnchorPane anchorPane = null;
                    anchorPane = FXMLLoader.load(getClass().getResource("TienDoCV.fxml"));
                    pane_load_file.getChildren().add(anchorPane);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
//        thanh_tich.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    pane_load_file.getChildren().clear();
//                    AnchorPane anchorPane = null;
//                    anchorPane = FXMLLoader.load(getClass().getResource("Thanhtich.fxml"));
//                    pane_load_file.getChildren().add(anchorPane);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
         dangxuat.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent actionEvent) {
                 try {
                     DBUtils.changeScene(actionEvent, "GiaoDien.fxml","Trang đăng nhập", 500,800);
                 } catch (IOException e){
                     throw new RuntimeException(e);
                 }

             }
         });
    }
}
