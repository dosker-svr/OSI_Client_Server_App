package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void main(String[] args) {
        int port = 8080;
        try {
            try {
                serverSocket = new ServerSocket(port);
                clientSocket = serverSocket.accept();

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                System.out.println("Принято новое подключение");

                final String name = in.readLine();

                out.println(String.format("Привет, %s. Номер твоего порта - %d", name, clientSocket.getPort()));

            } finally {
                System.out.println("Закрываем потоки с сервера ...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
