/*
 * This file is generated by jOOQ.
 */
package com.moneytransfer.model.tables.records;


import com.moneytransfer.model.tables.Accounts;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountsRecord extends UpdatableRecordImpl<AccountsRecord> implements Record4<Integer, String, String, Double> {

    private static final long serialVersionUID = -399300811;

    /**
     * Setter for <code>accounts.accounts.Account_ID</code>.
     */
    public void setAccountId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>accounts.accounts.Account_ID</code>.
     */
    public Integer getAccountId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>accounts.accounts.Account_Name</code>.
     */
    public void setAccountName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>accounts.accounts.Account_Name</code>.
     */
    public String getAccountName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>accounts.accounts.CCY</code>.
     */
    public void setCcy(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>accounts.accounts.CCY</code>.
     */
    public String getCcy() {
        return (String) get(2);
    }

    /**
     * Setter for <code>accounts.accounts.Balance</code>.
     */
    public void setBalance(Double value) {
        set(3, value);
    }

    /**
     * Getter for <code>accounts.accounts.Balance</code>.
     */
    public Double getBalance() {
        return (Double) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, Double> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, Double> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Accounts.ACCOUNTS_.ACCOUNT_ID;
    }

    @Override
    public Field<String> field2() {
        return Accounts.ACCOUNTS_.ACCOUNT_NAME;
    }

    @Override
    public Field<String> field3() {
        return Accounts.ACCOUNTS_.CCY;
    }

    @Override
    public Field<Double> field4() {
        return Accounts.ACCOUNTS_.BALANCE;
    }

    @Override
    public Integer component1() {
        return getAccountId();
    }

    @Override
    public String component2() {
        return getAccountName();
    }

    @Override
    public String component3() {
        return getCcy();
    }

    @Override
    public Double component4() {
        return getBalance();
    }

    @Override
    public Integer value1() {
        return getAccountId();
    }

    @Override
    public String value2() {
        return getAccountName();
    }

    @Override
    public String value3() {
        return getCcy();
    }

    @Override
    public Double value4() {
        return getBalance();
    }

    @Override
    public AccountsRecord value1(Integer value) {
        setAccountId(value);
        return this;
    }

    @Override
    public AccountsRecord value2(String value) {
        setAccountName(value);
        return this;
    }

    @Override
    public AccountsRecord value3(String value) {
        setCcy(value);
        return this;
    }

    @Override
    public AccountsRecord value4(Double value) {
        setBalance(value);
        return this;
    }

    @Override
    public AccountsRecord values(Integer value1, String value2, String value3, Double value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountsRecord
     */
    public AccountsRecord() {
        super(Accounts.ACCOUNTS_);
    }

    /**
     * Create a detached, initialised AccountsRecord
     */
    public AccountsRecord(Integer accountId, String accountName, String ccy, Double balance) {
        super(Accounts.ACCOUNTS_);

        set(0, accountId);
        set(1, accountName);
        set(2, ccy);
        set(3, balance);
    }
}
