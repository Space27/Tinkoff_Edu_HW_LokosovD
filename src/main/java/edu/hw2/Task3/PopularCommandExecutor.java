package edu.hw2.Task3;

public final class PopularCommandExecutor {

    private final ConnectionManager manager;
    private final int maxAttempts;
    public ConnectionException cause;

    public PopularCommandExecutor(int maxAttempts, ConnectionManager manager) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) throws ConnectionException {
        boolean success = false;

        for (int i = 0; i < maxAttempts && !success; ++i) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                success = true;
            } catch (ConnectionException e) {
                success = false;
                cause = e;
            } catch (Exception ignored) {
            }
        }
        if (!success) {
            throw new ConnectionException();
        }
    }
}
