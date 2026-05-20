package com.fintech.trading;

public class Trade {
    private String id;
    private double amount;
    private double fxRate;
    private String currency;
    private String status;
    private String counterparty;

    public double getAmount()       { return amount; }
    public double getFxRate()       { return fxRate; }
    public String getCurrency()     { return currency; }
    public String getStatus()       { return status; }
    public String getCounterparty() { return counterparty; }
}
