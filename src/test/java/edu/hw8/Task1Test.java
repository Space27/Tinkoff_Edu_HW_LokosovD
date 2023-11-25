package edu.hw8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task1Test {

    @Test
    @DisplayName("Тест на одном клиенте")
    void server_shouldExecuteOneClientRequest() throws IOException {
        Thread client = new Thread(() -> {
            try {
                ThreadClientSocket clientSocket = new ThreadClientSocket("localhost", 8080);

                String answer = clientSocket.sendMessageAndGetAnswer("оскорбления");

                assertThat(answer)
                    .isEqualTo(
                        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ThreadServerSocket threadServerSocket = new ThreadServerSocket(8080, 2);
        client.start();
        threadServerSocket.run();
        threadServerSocket.close();

        try {
            client.join();
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    @DisplayName("Тест на двух клиентах")
    void server_shouldExecuteTwoClientRequests() throws IOException {
        Thread client1 = new Thread(() -> {
            try {
                ThreadClientSocket clientSocket = new ThreadClientSocket("localhost", 8080);

                String answer = clientSocket.sendMessageAndGetAnswer("оскорбления");

                assertThat(answer)
                    .isEqualTo(
                        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread client2 = new Thread(() -> {
            try {
                ThreadClientSocket clientSocket = new ThreadClientSocket("localhost", 8080);

                String answer = clientSocket.sendMessageAndGetAnswer("личности");

                assertThat(answer)
                    .isEqualTo(
                        "Не переходи на личности там, где их нет");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ThreadServerSocket threadServerSocket = new ThreadServerSocket(8080, 2);
        client1.start();
        client2.start();
        threadServerSocket.run();
        threadServerSocket.close();

        try {
            client1.join();
            client2.join();
        } catch (InterruptedException ignored) {
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 6, 10, 30})
    @DisplayName("Одинаковый запрос на нескольких клиентах")
    void server_shouldExecuteSeveralEqualClientRequests(int n) throws IOException {
        Runnable task = () -> {
            try {
                ThreadClientSocket clientSocket = new ThreadClientSocket("localhost", 8080);

                String answer = clientSocket.sendMessageAndGetAnswer("оскорбления");

                assertThat(answer)
                    .isEqualTo(
                        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        ExecutorService threads = Executors.newFixedThreadPool(n);
        var tasks = Stream.generate(() -> CompletableFuture.runAsync(task, threads))
            .limit(n)
            .toArray(CompletableFuture[]::new);

        ThreadServerSocket threadServerSocket = new ThreadServerSocket(8080, 2);

        var future = CompletableFuture.allOf(tasks);
        threadServerSocket.run();
        future.join();

        threadServerSocket.close();
        threads.shutdown();
    }
}
