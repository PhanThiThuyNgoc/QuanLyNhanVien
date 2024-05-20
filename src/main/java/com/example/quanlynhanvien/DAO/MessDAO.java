package com.example.quanlynhanvien.DAO;

import com.example.quanlynhanvien.ConnectDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessDAO {
    public static ArrayList<String> getMessenger(String IdMessUser) throws SQLException {
        ArrayList<String> listMess = new ArrayList<>();
        String sql = "SELECT `Messenger` FROM `messenger` WHERE `IdMessUser` = '"+IdMessUser+"' ORDER BY `IdMess` ASC";
        ResultSet data = new ConnectDB().getStmt().executeQuery(sql);
        while (data.next()){
            listMess.add(data.getString("Messenger"));
        }
        return listMess;
    }

    public static String getIdMessUser(String User1, String User2) throws SQLException {
        String IdMessUser = null;
        String sql = "SELECT `IdMessUser` FROM `idmessuser` WHERE (`User1` = '"+User1+"' AND `User2` = '"+User2+"') OR (`User1` = '"+User2+"' AND `User2` = '"+User1+"') ";
        ResultSet data = new ConnectDB().getStmt().executeQuery(sql);
        while (data.next()){
            IdMessUser = data.getString("IdMessUser");
        }
        return IdMessUser;
    }
}
