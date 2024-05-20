package com.example.quanlynhanvien;

import com.example.quanlynhanvien.DAO.NhanvienDAO;
import com.example.quanlynhanvien.Model.NhanVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GiaoDienLiLichController implements Initializable {
    @FXML
    private Button bt_chinhSua;

    @FXML
    private ChoiceBox<Integer> cb_nam;

    @FXML
    private ChoiceBox<Integer> cb_ngay;

    @FXML
    private ChoiceBox<Integer> cb_thang;

    @FXML
    private Label lb_ngayThamGia;

    @FXML
    private RadioButton rb_nu;

    @FXML
    private RadioButton rdb_nam;

    @FXML
    private TextField tf_diachi;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_hoten;

    @FXML
    private TextField tf_sdt;

    @FXML
    private ImageView img_Avatar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String email;
        email = getEmail();

        NhanVien nhanVien = null;
        try {
            nhanVien = NhanvienDAO.getInfoUser(email);
        } catch (Exception e) {
            System.out.println("Lỗi lấy thông tin nhân viên");
            throw new RuntimeException(e);
        }
        // load dữ liệu lên giao diện
        tf_diachi.setText(nhanVien.getDiachi());
        tf_email.setText(nhanVien.getGmail());
        tf_hoten.setText(nhanVien.getHovaten());
        tf_sdt.setText(nhanVien.getSodienthoai());
        // check giới tínhx
        if (nhanVien.getGioitinh().equals("Nữ")){
            rb_nu.setSelected(true);
        } else {
            rdb_nam.setSelected(true);
        }
        lb_ngayThamGia.setText(String.valueOf(nhanVien.getNgaybatdaulamviec()));
        // Tạo đối tượng Image từ URL
        Image image = new Image(nhanVien.getLinkAvatar());

        img_Avatar.setImage(image);

        //load ngày tháng lên giao diện
        Integer[] day = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        Integer[] month = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        Integer[] year = {2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024};

        ObservableList<Integer> optionsDay = FXCollections.observableArrayList();
        ObservableList<Integer> optionsMonth = FXCollections.observableArrayList();
        ObservableList<Integer> optionsYear = FXCollections.observableArrayList();

// Chuyển đổi mảng thành ObservableList
        for (Integer d : day) {
            optionsDay.add(d);
        }

        for (Integer m : month) {
            optionsMonth.add(m);
        }

        for (Integer y : year) {
            optionsYear.add(y);
        }

        cb_thang.setItems(optionsMonth);
        cb_nam.setItems(optionsYear);
        cb_ngay.setItems(optionsDay);
        // set giá trị ngày tháng năm sinh
        cb_ngay.setValue(nhanVien.getNgaysinh().getDayOfMonth());
        cb_thang.setValue(nhanVien.getNgaysinh().getMonthValue());
        cb_nam.setValue(nhanVien.getNgaysinh().getYear());


        bt_chinhSua.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Tạo một Alert
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn lưu thông tin đã thay đổi?");

                // Hiển thị Alert và đợi người dùng chọn
                ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

                // Xử lý kết quả
                if (result == ButtonType.OK) {
                    // lấy gias trị ngày sinh từ checkbox
                    LocalDate ngaySinh = LocalDate.of(cb_nam.getValue(), cb_thang.getValue(), cb_ngay.getValue());
                    // lấy giá trị giới tính thay đổi
                    String GioiTinh = "Nam";
                    if(rb_nu.isSelected()){
                        GioiTinh = "Nữ";
                    }
                    // nêu người dùng nhấn ok  thì lấy dữ liệu trên giao diện và cập nhật lên cơ sở dữ liệu
                    NhanVien nhanVien = new NhanVien(tf_hoten.getText(),tf_diachi.getText(),ngaySinh, GioiTinh,tf_sdt.getText(),tf_email.getText());

                    // update dữ liệu lên csdl
                    String query = "UPDATE `nhanvien` SET `HoVaTen`='"+nhanVien.getHovaten()+"',`DiaChi`='"+nhanVien.getDiachi()+"',`NgaySinh`='"+nhanVien.getNgaysinh()+"',`GioiTinh`='"+nhanVien.getGioitinh()+"',`SoDienThoai`='"+nhanVien.getSodienthoai()+"' WHERE `Gmail` = '"+getEmail()+"'";
                    try {
                        new ConnectDB().getStmt().executeUpdate(query);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Người dùng không thay đổi thông tin.");
                    // Thực hiện hành động tương ứng
                }
            }
        });
    }

    public String getEmail(){
        String email = null, fileName = "infoUser.txt";
        try {
            // Tạo một đối tượng FileReader để đọc từ tệp
            FileReader fileReader = new FileReader(fileName);

            // Tạo một đối tượng BufferedReader để đọc dòng văn bản từ FileReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            email = bufferedReader.readLine();

            // Đóng luồng đọc
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc tệp văn bản: " + e.getMessage());
            e.printStackTrace();
        }
        return email;
    }
}
