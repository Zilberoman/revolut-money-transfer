package com.moneytransfer.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.moneytransfer.dao.accounts.AccountDAO;
import com.moneytransfer.exception.DifferentCCYException;
import com.moneytransfer.exception.NotEnoughMoneyException;
import com.moneytransfer.model.tables.Accounts;
import com.moneytransfer.model.tables.records.AccountsRecord;
import com.moneytransfer.model.tables.records.UsersRecord;
import com.moneytransfer.models.Transaction;
import com.moneytransfer.service.json.AccountsRecordJson;
import com.moneytransfer.service.responses.StandardResponse;
import com.moneytransfer.service.responses.StatusResponse;
import spark.Request;
import spark.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountsService {
    private final AccountDAO accountDAO;

    public AccountsService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public String transferMoney(Request request, Response response) {
        response.type("application/json");
        Transaction transaction = new Gson().fromJson(request.body(), Transaction.class);
        StandardResponse standardResponse;

        try {
            accountDAO.transferMoney(transaction);
            standardResponse = new StandardResponse(StatusResponse.SUCCESS);
        } catch (NotEnoughMoneyException e) {
            standardResponse = new StandardResponse(StatusResponse.ERROR,
                    "Not enough money on the account balance");
        } catch (DifferentCCYException e) {
            standardResponse = new StandardResponse(StatusResponse.ERROR,
                    "Currencies should be similar");
        }

        return new Gson().toJson(standardResponse);
    }

    public String deleteAccount(Request request, Response response) {
        response.type("application/json");
        accountDAO.deleteAccount(Integer.parseInt(request.params(":id")));
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "account is deleted"));
    }

    public String getAccount(Request request, Response response) {
        response.type("application/json");
        AccountsRecord accountsRecord = accountDAO.getAccount(Integer.parseInt(request.params(":id")));
        AccountsRecordJson accountsRecordJson = new AccountsRecordJson(accountsRecord);
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, accountsRecordJson.toJson()));
    }

    public String createAccount(Request request, Response response) {
        response.type("application/json");
        HashMap accountsRecord = new Gson().fromJson(request.body(), HashMap.class);

        accountDAO.createAccount(accountsRecord.get("accountName").toString(), accountsRecord.get("ccy").toString(),
                new BigDecimal(accountsRecord.get("balance").toString()));
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    }

    public String getAllAccounts(Request request, Response response) {
        response.type("application/json");
        List<String> accountsRecordJsons = new ArrayList<>();
        accountDAO.getAllAccounts().forEach(record -> accountsRecordJsons.add(new AccountsRecordJson(record).toJson()));
        JsonElement jsonTree = new Gson().toJsonTree(accountsRecordJsons);
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, jsonTree));
    }
}
