package com.example.quanlynhanvien;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GiaoDienController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private PasswordField matkhau;
    @FXML
    private AnchorPane idanchor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String Email = email.getText();
                String passWord = matkhau.getText();
                if (Email.equals("") || passWord.equals("")){
                    DBUtils.printAlertMsg("Cảnh Báo ","Vui lòng nhập thông tin đầy đủ");
                    return ;
                }
                if(Email != "" && passWord != "" && Email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                    ConnectDB connectDB = new ConnectDB();
                    String query = "SELECT `Email`, `Level` FROM `taikhoan` WHERE `password` = '" + passWord + "' AND `Email` = '" + Email + "'";
                    boolean check = false;
                    int level = -1;
                    try {
                        ResultSet datalogin = connectDB.getStmt().executeQuery(query);
                        while (datalogin.next()){
                            check = true;
                            level = datalogin.getInt("Level");
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);

                    }
                    // lưu email user vào file txt
                    createFileSaveInfo(Email);
                    // kiểm tra cấp bậc tài khoản
                    if(check == true) {
                        if(level == 1){
                            try {
                                DBUtils.changeScene(actionEvent, "GiaoDienNhanVien.fxml", "Trang nhân viên", 500, 800);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }else {
                            try {
                                DBUtils.changeScene(actionEvent, "Gocc.fxml", "Trang quản lý", 540, 810);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        DBUtils.printAlertMsg("Thông báo", "Không đúng thông tin đăng nhập!");
                    }
                }else{
                    DBUtils.printAlertMsg("Thông báo", "Định dạng email không đúng!");
                }
            }
        });
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // sau khi đăng ký thành công thì load giao diện đăng nhập
                    DBUtils.changeScene(actionEvent, "Giaodiendangki.fxml", "Đăng ký",500,800 );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void createFileSaveInfo(String email){
        String nameFile = "infoUser.txt";
        try {
            FileWriter writer = new FileWriter(nameFile);
            writer.write(email);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
