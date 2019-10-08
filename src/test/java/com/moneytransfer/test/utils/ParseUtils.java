package com.moneytransfer.test.utils;

import com.google.gson.Gson;
import com.moneytransfer.model.tables.records.AccountsRecord;
import com.moneytransfer.model.tables.records.UsersRecord;

import java.util.*;


public class ParseUtils {
    public static List<AccountsRecord> parseToAccountsRecordList(String resultEntity) {
        ArrayList<HashMap> listObject = ParseUtils.parseToList(resultEntity);

        if (listObject == null) {
            return null;
        }

        List<AccountsRecord> accountsRecords = new ArrayList<>();

        for (HashMap record: listObject) {
            if (record != null && record.size() >= 4) {
                accountsRecords.add(new AccountsRecord(Integer.parseInt(record.get("accountId").toString()),
                        record.get("accountName").toString(), record.get("ccy").toString(),
                        Double.valueOf(record.get("balance").toString())));
            }
        }

        return accountsRecords;
    }

    public static List<UsersRecord> parseToUsersRecordList(String resultEntity) {
        ArrayList<HashMap> listObject = ParseUtils.parseToList(resultEntity);

        if (listObject == null) {
            return null;
        }

        List<UsersRecord> usersRecords = new ArrayList<>();

        for (HashMap record: listObject) {
            if (record != null && record.size() >= 2) {
                usersRecords.add(new UsersRecord(Integer.parseInt(record.get("userId").toString()),
                        record.get("userLogin").toString()));
            }
        }

        return usersRecords;
    }

    private static ArrayList<HashMap> parseToList(String resultEntity) {
        ArrayList<HashMap> result = new ArrayList<>();
        HashMap dataMap = new Gson().fromJson(resultEntity, HashMap.class);

        if (!dataMap.containsKey("data")) {
            return null;
        }

        Object data = dataMap.get("data");

        if (data instanceof ArrayList) {
            List dataList = (ArrayList) data;

            for (Object dataRec: dataList) {
                result.add(new Gson().fromJson(dataRec.toString(), HashMap.class));
            }
        } else {
            result.add(new Gson().fromJson(data.toString(), HashMap.class));
        }
        
        return result;
    }

    public static AccountsRecord parseToAccountsRecord(String resultEntity) {
        HashMap values = ParseUtils.parseToValueMap(resultEntity);

        return values != null && values.size() >= 4
                ? new AccountsRecord(Integer.parseInt(values.get("accountId").toString()),
                values.get("accountName").toString(), values.get("ccy").toString(),
                Double.valueOf(values.get("balance").toString()))
                : null;
    }

    public static UsersRecord parseToUsersRecord(String resultEntity) {
        HashMap values = ParseUtils.parseToValueMap(resultEntity);

        return values != null && values.size() >= 2
                ? new UsersRecord(Integer.parseInt(values.get("userId").toString()), values.get("userLogin").toString())
                : null;
    }

    private static HashMap parseToValueMap(String resultEntity) {
        HashMap dataMap = new Gson().fromJson(resultEntity, HashMap.class);

        if (!dataMap.containsKey("message")) {
            return null;
        }

        return new Gson().fromJson(dataMap.get("message").toString(), HashMap.class);
    }
}
