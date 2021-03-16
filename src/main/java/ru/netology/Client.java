package ru.netology;

import java.io.*;
import java.net.*;

public class Client {
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;
        try {
            try {
                clientSocket = new Socket(host, port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                System.out.println("Введите свое имя: ");
                String messageForServer = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                out.println(messageForServer);

                String fromServerMessage = in.readLine();
                System.out.println(fromServerMessage);

            } finally {
                System.out.println("Закрываем потоки от клиента ...");
                clientSocket.close();
                out.close();
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
