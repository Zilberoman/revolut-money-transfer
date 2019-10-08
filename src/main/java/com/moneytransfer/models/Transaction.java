package com.moneytransfer.models;

import java.math.BigDecimal;

public class Transaction {
    private String currencyCode;

    private BigDecimal amount;

    private int oldAccountId;

    private int newAccountId;

    public Transaction() {}

    public Transaction(String currencyCode, BigDecimal amount, int fromAccountId, int toAccountId) {
        this.currencyCode = currencyCode;
        this.amount = amount;
        this.oldAccountId = fromAccountId;
        this.newAccountId = toAccountId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getOldAccountId() {
        return oldAccountId;
    }

    public int getNewAccountId() {
        return newAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass())
            return false;

        Transaction that = (Transaction) o;

        return currencyCode.equals(that.currencyCode)
                && amount.equals(that.amount)
                && oldAccountId == that.oldAccountId
                && newAccountId == that.newAccountId;
    }

    @Override
    public int hashCode() {
        int result = currencyCode.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + oldAccountId;
        result = 31 * result + newAccountId;
        return result;
    }

    @Override
    public String toString() {
        return "Transaction {" + "currencyCode = " + currencyCode + ", amount = " + amount + ", from AccountId = "
                + oldAccountId + ", to AccountId=" + newAccountId + '}';
    }
}
