package edu.hw8;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadServerSocket {

    private final ServerSocket serverSocket;
    private final ThreadPoolExecutor pool;
    private static final int TIMEOUT = 1000;

    public ThreadServerSocket(int port, int poolSize) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(TIMEOUT);
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
    }

    public void run() throws IOException {
        try {
            while (true) {
                if (pool.getQueue().size() < pool.getMaximumPoolSize()) {
                    pool.execute(new Handler(serverSocket.accept()));
                }
            }
        } catch (IOException e) {
            pool.shutdown();
        } finally {
            serverSocket.close();
            pool.shutdown();
        }
    }

    public void close() throws IOException {
        serverSocket.close();
        pool.shutdown();
    }
}
