package com.fintech.trading;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Legacy Java 8 trade processor — intentionally contains patterns
 * that the java-modernization-review skill is designed to detect.
 */
public class TradeProcessor {

    private HashMap<String, Double> positionMap = new HashMap<>();
    private ExecutorService executor = Executors.newFixedThreadPool(10);

    // NPE risk: unguarded chained access
    public double getCounterpartyBalance(Order order) {
        return order.getCounterparty().getAccount().getBalance();
    }

    // for-loop that should be a stream
    public List<Trade> getPendingHighValueTrades(List<Trade> trades) {
        List<Trade> results = new ArrayList<>();
        for (Trade t : trades) {
            if (t.getAmount() > 1000 && t.getStatus().equals("PENDING")) {
                results.add(t);
            }
        }
        return results;
    }

    // double arithmetic for money — precision loss
    public double calculateNetExposure(List<Trade> trades) {
        double total = 0.0;
        for (Trade t : trades) {
            if (t.getCurrency().equals("USD")) {
                total += t.getAmount() * t.getFxRate();
            }
        }
        return total;
    }

    // Manual grouping — should use Collectors.groupingBy
    public Map<String, List<Trade>> groupByCounterparty(List<Trade> trades) {
        Map<String, List<Trade>> grouped = new HashMap<String, List<Trade>>();
        for (Trade t : trades) {
            String cp = t.getCounterparty();
            if (grouped.get(cp) == null) {
                grouped.put(cp, new ArrayList<Trade>());
            }
            grouped.get(cp).add(t);
        }
        return grouped;
    }

    // Synchronized block — candidate for ConcurrentHashMap
    public synchronized void updatePosition(String counterparty, double delta) {
        Double current = positionMap.get(counterparty);
        if (current == null) {
            positionMap.put(counterparty, delta);
        } else {
            positionMap.put(counterparty, current + delta);
        }
    }

    // Generic Exception catch, empty handler
    public void settleTrade(Trade trade) {
        try {
            executor.submit(() -> processSettlement(trade));
        } catch (Exception e) {
            // TODO: handle later
        }
    }

    // Legacy thread-based message listener placeholder
    // @Queue(name = "TRADE_QUEUE")
    // @Solace(threadPoolSize = 10)
    public void onTradeMessage(Trade trade) {
        settleTrade(trade);
    }

    private void processSettlement(Trade trade) {
        // settlement logic
    }

    // Rigid DTO — candidate for Record
    static class TradePosition {
        String counterparty;
        double usdAmount;
        double eurAmount;
        double jpyAmount;

        TradePosition(String counterparty, double usdAmount, double eurAmount, double jpyAmount) {
            this.counterparty = counterparty;
            this.usdAmount = usdAmount;
            this.eurAmount = eurAmount;
            this.jpyAmount = jpyAmount;
        }
    }

}
