package com.moneytransfer.service.json;

import com.google.gson.Gson;
import com.moneytransfer.model.tables.records.UsersRecord;

public class UsersRecordJson extends UsersRecord implements RecordJson {
    public UsersRecordJson(UsersRecord usersRecord) {
        this(usersRecord.getUserId(), usersRecord.getUserLogin());
    }

    public UsersRecordJson(Integer userId, String userLogin) {
        super(userId, userLogin);
        this.recordMap.put("userId", userId.toString());
        this.recordMap.put("userLogin", userLogin);
    }

    public UsersRecordJson(String userLogin) {
        super(null, userLogin);
        this.recordMap.put("userId", "");
        this.recordMap.put("userLogin", userLogin);
    }


    public UsersRecordJson() {
        this.recordMap.put("userId", "");
        this.recordMap.put("userLogin", "");
    }

    @Override
    public void setUserId(Integer value) {
        super.setUserId(value);
        this.recordMap.put("userId", value.toString());
    }

    @Override
    public void setUserLogin(String value) {
        super.setUserLogin(value);
        this.recordMap.put("userLogin", value);
    }

    public String toJson() {
        return new Gson().toJson(this.recordMap);
    }
}
