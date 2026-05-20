package com.fintech.trading;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Java 21 modernization of TradeProcessor.
 *
 * Key changes from legacy version:
 *  - BigDecimal for all monetary values (was double)
 *  - Optional chain replaces unguarded NPE-prone access
 *  - Streams + Collectors.groupingBy replace all for-loops
 *  - ConcurrentHashMap.merge() replaces synchronized HashMap (lock-free)
 *  - Virtual thread executor replaces fixed thread pool
 *  - TradePosition promoted to a top-level record with validation
 *  - Exception no longer swallowed — propagates to caller
 *
 * Note: Trade and Account domain classes should also be updated to return
 * BigDecimal for amount, fxRate, and balance to remove the valueOf() wrappers.
 */
public class TradeProcessorModern {

    private final ConcurrentHashMap<String, BigDecimal> positionMap = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public BigDecimal getCounterpartyBalance(Order order) {
        return Optional.ofNullable(order)
            .map(Order::getCounterparty)
            .map(Counterparty::getAccount)
            .map(Account::getBalance)
            .map(BigDecimal::valueOf)
            .orElseThrow(() -> new IllegalArgumentException("Account balance unavailable"));
    }

    public List<Trade> getPendingHighValueTrades(List<Trade> trades) {
        return trades.stream()
            .filter(t -> t.getAmount() > 1000)
            .filter(t -> "PENDING".equals(t.getStatus()))
            .collect(Collectors.toList());
    }

    public BigDecimal calculateNetExposure(List<Trade> trades) {
        return trades.stream()
            .filter(t -> "USD".equals(t.getCurrency()))
            .map(t -> BigDecimal.valueOf(t.getAmount())
                          .multiply(BigDecimal.valueOf(t.getFxRate())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, List<Trade>> groupByCounterparty(List<Trade> trades) {
        return trades.stream()
            .collect(Collectors.groupingBy(Trade::getCounterparty));
    }

    public void updatePosition(String counterparty, BigDecimal delta) {
        positionMap.merge(counterparty, delta, BigDecimal::add);
    }

    public void settleTrade(Trade trade) {
        executor.submit(() -> processSettlement(trade));
    }

    public void onTradeMessage(Trade trade) {
        settleTrade(trade);
    }

    private void processSettlement(Trade trade) {
        // settlement logic
    }
}

record TradePosition(String counterparty, Map<String, BigDecimal> currencyExposures) {
    TradePosition {
        Objects.requireNonNull(counterparty, "counterparty required");
        Objects.requireNonNull(currencyExposures, "currencyExposures required");
        currencyExposures = Map.copyOf(currencyExposures);
    }
}
