package com.example.quanlynhanvien.Get;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetInfo {
    public static String getEmail(){
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
