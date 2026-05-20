package com.fintech.trading;

import java.math.BigDecimal;

public class Trade {
    private String id;
    private BigDecimal amount;
    private BigDecimal fxRate;
    private String currency;
    private String status;
    private String counterparty;

    public BigDecimal getAmount()     { return amount; }
    public BigDecimal getFxRate()     { return fxRate; }
    public String getCurrency()       { return currency; }
    public String getStatus()         { return status; }
    public String getCounterparty()   { return counterparty; }
}
