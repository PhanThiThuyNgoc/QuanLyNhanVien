package com.example.quanlynhanvien.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class OpenConnect {
    private String IP = "localhost";
    private int Port = 7749;
    private static Set<Socket> clients = new HashSet<>();

    public OpenConnect() throws IOException {

        ServerSocket serverSocket = new ServerSocket(Port);
        System.out.println("Server open ip:port :[ " +IP + ":" + Port+ " ]");
        while (true){
            Socket clientSocket = serverSocket.accept();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    clients.add(clientSocket);

                    // nhận dữ liệu từ client và gửi đến các người dùng khác
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                        while (true){
                            if(clientSocket.isClosed()){
                                clients.remove(clientSocket);
                                clientSocket.close();
                            }
                            String mess = reader.readLine();
                            System.out.println("Server nhận được: "+ mess);
                            for (Socket sk : clients){
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sk.getOutputStream(), "UTF-8"));
                                writer.write(mess + "\n");
                                writer.flush();
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("1 Client đã đóng kết nối!");
                    }
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        try {
            new OpenConnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
