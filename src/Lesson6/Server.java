package Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    private static boolean running;

    public static void main(String[] args) {
        try {
            startServer();
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startServer() throws IOException {
        socket = null;
        serverSocket = new ServerSocket(8181);
        System.out.println("Сервер запущен, ожидаем подключения...");
        socket = serverSocket.accept();
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        running = true;

        Thread trIn = new Thread(() -> {
            System.out.println("Клиент подключился");
            try {
                while (running) {
                    if (!socket.isConnected()) {
                        System.out.println("Клиент отключился");
                        running = false;
                        break;
                    }

                    String str = dataInputStream.readUTF();

                    if (str.equalsIgnoreCase("/end")) {
                        System.out.println("Клиент отключился");
                        running = false;
                        break;
                    }

                    System.out.println("Клиент сказал:" + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread trOut = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            try {
                while (running) {
                    if (!socket.isConnected()) {
                        running = false;
                        break;
                    }

                    String str = sc.nextLine();
                    dataOutputStream.writeUTF(str);

                    if (str.equals("/end")) {
                        running = false;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        trIn.start();
        trOut.start();

        try {
            trIn.join();
            trOut.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() throws IOException {
        dataInputStream.close();
        dataOutputStream.close();

        if (!socket.isClosed()) {
            socket.close();
        }
        if (!serverSocket.isClosed()) {
            serverSocket.close();
        }
    }
}