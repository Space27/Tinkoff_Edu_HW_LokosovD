package edu.hw2.Task3;

public class FaultyConnection implements Connection {

    final int exceptChance = 5;

    @Override
    public void execute(String command) throws ConnectionException {
        if ((int) (Math.random() * exceptChance) == 0) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {
    }
}
