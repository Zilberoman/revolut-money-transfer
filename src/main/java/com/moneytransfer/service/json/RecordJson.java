package com.moneytransfer.service.json;

import com.google.gson.Gson;

import java.util.HashMap;

public interface RecordJson {
    HashMap<String, String> recordMap = new HashMap<>();
    String toJson();
}
