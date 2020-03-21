package Lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8181;

    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    private static boolean running;

    public static void main(String[] args) {
        try {
            connectToServer();
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connectToServer() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        running = true;

        Thread tr1in = new Thread(() -> {
            try {
                while (running) {
                    if (!socket.isConnected()) {
                        System.out.println("Сервер отключился");
                        running = false;
                        break;
                    }

                    String strFromServer = dataInputStream.readUTF();

                    if (strFromServer.equalsIgnoreCase("/end")) {
                        System.out.println("Сервер отключился");
                        running = false;
                        break;
                    }

                    System.out.println("Ответ сервера: " + strFromServer);
                }
            } catch (Exception e) {
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

        tr1in.start();
        trOut.start();

        try {
            tr1in.join();
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
    }
}
