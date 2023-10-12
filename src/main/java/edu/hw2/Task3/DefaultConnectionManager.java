package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {

    final int faultChance = 5;

    public Connection getConnection() {
        if ((int) (Math.random() * faultChance) == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
