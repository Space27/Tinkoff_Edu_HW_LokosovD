package edu.hw3.Task6;

import java.util.PriorityQueue;

public class PriorityQueueStockMarket implements StockMarket {

    PriorityQueue<Stock> market = new PriorityQueue<>((Stock s1, Stock s2) -> Integer.compare(s2.price(), s1.price()));

    @Override
    public void add(Stock stock) {
        market.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        market.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return market.peek();
    }

}
