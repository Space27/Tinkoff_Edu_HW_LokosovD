package edu.hw2;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {

    @Test
    @DisplayName("Проблемный менеджер")
    void tryExecute_ShouldThrowExceptionForFaultyManager() {
        final int maxAttempts = 1;
        final ConnectionManager connectionManager = new FaultyConnectionManager();
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(maxAttempts, connectionManager);

        assertThrows(ConnectionException.class, () -> {
            for (int i = 0; i < 100; ++i) {
                popularCommandExecutor.updatePackages();
            }
        });
    }

    @Test
    @DisplayName("Обычный менеджер")
    void tryExecute_ShouldThrowExceptionForDefaultManager() {
        final int maxAttempts = 1;
        final ConnectionManager connectionManager = new DefaultConnectionManager();
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(maxAttempts, connectionManager);

        assertThrows(ConnectionException.class, () -> {
            for (int i = 0; i < 100; ++i) {
                popularCommandExecutor.updatePackages();
            }
        });
    }
}
