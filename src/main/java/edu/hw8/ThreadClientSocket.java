package edu.hw8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClientSocket {

    private final Socket clientSocket;

    public ThreadClientSocket(String ip, int port) {
        Socket tmp;
        while (true) {
            try {
                tmp = new Socket(ip, port);
                break;
            } catch (IOException ignored) {
            }
        }
        clientSocket = tmp;
    }

    public String sendMessageAndGetAnswer(String message) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.println(message);

            return in.readLine();
        }
    }
}
