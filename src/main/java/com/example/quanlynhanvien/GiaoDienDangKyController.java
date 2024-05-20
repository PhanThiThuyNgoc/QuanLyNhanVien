package com.example.quanlynhanvien;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GiaoDienDangKyController implements Initializable {
    @FXML
    private TextField tf_fullname;
    @FXML
    private TextField tf_mail;

    @FXML
    private PasswordField tf_password;
    @FXML
    private Button bt_signup;
    @FXML
    private Button bt_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String ten = tf_fullname.getText();
                String Email = tf_mail.getText();
                String password = tf_password.getText();
                if (!ten.isEmpty() && !Email.isEmpty() && !password.isEmpty()){
                    ConnectDB connectDB = new ConnectDB();
                    String query ="INSERT INTO `taikhoan`(`Ten`, `password`, `Email`) VALUES ('"+ten+"','"+password+"','"+Email+"')";
                    try {
                        int check = connectDB.getStmt().executeUpdate(query);
                        if (check != 0){
                            System.out.println(" Tạo tài khoản thành công !");
                            DBUtils.printAlertMsg("THÔNG BÁO ","Tạo tài khoản thành công.");
                            try {
                                // sau khi đăng ký thành công thì load giao diện đăng nhập
                                DBUtils.changeScene(actionEvent, "GiaoDien.fxml", "Đăng nhập",500,800 );
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    DBUtils.printAlertMsg("Thông Báo","Vui lòng nhập đầy đủ thông tin!");
                }

            }
        });
        bt_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // sau khi nhấn nút đăng nhập thì load giao diện đăng nhập
                    DBUtils.changeScene(actionEvent, "GiaoDien.fxml", "Đăng nhập",500,800 );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
