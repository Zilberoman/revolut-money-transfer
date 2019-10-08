package com.moneytransfer.service.json;

import com.google.gson.Gson;
import com.moneytransfer.model.tables.records.AccountsRecord;

import java.util.HashMap;

public class AccountsRecordJson extends AccountsRecord implements RecordJson {
    public AccountsRecordJson(AccountsRecord accountsRecord) {
        this(accountsRecord.getAccountId(), accountsRecord.getAccountName(), accountsRecord.getCcy(),
                accountsRecord.getBalance());
    }

    public AccountsRecordJson(Integer accountId, String accountName, String ccy, Double balance) {
        super(accountId, accountName, ccy, balance);
        this.recordMap.put("accountId", accountId.toString());
        this.recordMap.put("accountName", accountName);
        this.recordMap.put("ccy", ccy);
        this.recordMap.put("balance", balance.toString());
    }

    public AccountsRecordJson(String accountName, String ccy, Double balance) {
        super(null, accountName, ccy, balance);
        this.recordMap.put("accountId", "");
        this.recordMap.put("accountName", accountName);
        this.recordMap.put("ccy", ccy);
        this.recordMap.put("balance", balance.toString());
    }

    public AccountsRecordJson() {
        this.recordMap.put("accountId", "");
        this.recordMap.put("accountName", "");
        this.recordMap.put("ccy", "");
        this.recordMap.put("balance", "");
    }

    @Override
    public void setAccountId(Integer value) {
        super.setAccountId(value);
        this.recordMap.put("accountId", value.toString());
    }

    @Override
    public void setAccountName(String value) {
        super.setAccountName(value);
        this.recordMap.put("accountName", value);
    }

    @Override
    public void setCcy(String value) {
        super.setCcy(value);
        this.recordMap.put("ccy", value);
    }

    @Override
    public void setBalance(Double value) {
        super.setBalance(value);
        this.recordMap.put("balance", value.toString());
    }

    public String toJson() {
        return new Gson().toJson(this.recordMap);
    }
}
