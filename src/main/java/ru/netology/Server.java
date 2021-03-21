package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Client client;

    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            System.out.println("Принято новое подключение");

            out.println("Write your name: ");

            String nameClient = in.readLine();

            out.println("Are you child? (yes/no)");

            String stringAgeVerification = in.readLine();
            boolean ageVerification;
            if (stringAgeVerification.equals("yes")) {
                ageVerification = true;
                out.println(String.format("Welcome to the kids area, %s ! Let's play!", nameClient));
            } else {
                ageVerification = false;
                out.println(String.format("Welcome to the adult zone, %s ! Have a good rest, or a good working day!", nameClient));
            }

            client = new Client(nameClient, ageVerification);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


/*Вкратце сервер начинает слушать порт, и когда первый клиент подключится к этому порту,
сервер ожидает от него строчку (25 строка) и затем отправляет ответ.
А клиент открывает сокет(подключаясь этим сокетом к серверу) и после подключения сразу отправляет своё имя.
Затем читает строку из ответа и выводит её на экран.

PrintWriter объявляется с output stream от сокета - то бишь он пишет в сокет. А println пишет в консоль.
*/