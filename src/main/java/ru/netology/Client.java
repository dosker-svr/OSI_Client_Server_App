package ru.netology;

import java.io.*;
import java.net.*;

public class Client {
    private String name;
    private boolean ageVerification;

    public Client (String name, boolean ageVerification) {
        this.name = name;
        this.ageVerification = ageVerification;
    }

    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8080;
        try {
            try {
                clientSocket = new Socket(host, port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String fromServerRequestName = in.readLine();
                System.out.println(fromServerRequestName);

                String transferName = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                out.println(transferName);

                String fromServerRequestAgeVerification = in.readLine();
                System.out.println(fromServerRequestAgeVerification);

                String transferAgeVerification = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                out.println(transferAgeVerification);

                String transferWelcomeMessage = in.readLine();
                System.out.println(transferWelcomeMessage);

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
