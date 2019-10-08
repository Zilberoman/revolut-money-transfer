package com.moneytransfer.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.moneytransfer.dao.users.UserDAO;
import com.moneytransfer.model.tables.records.UsersRecord;
import com.moneytransfer.service.responses.StandardResponse;
import com.moneytransfer.service.responses.StatusResponse;
import org.jooq.Result;
import spark.Request;
import spark.Response;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String getUserByLogin(Request request, Response response) {
        response.type("application/json");
        UsersRecord usersRecord = userDAO.getUserByLogin(request.params(":login"));
        JsonElement jsonTree = new Gson().toJsonTree(usersRecord.formatJSON());
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, jsonTree));
    }

    public String renameUser(Request request, Response response) {
        response.type("application/json");
        UsersRecord renamedUser = new Gson().fromJson(request.body(), UsersRecord.class);
        boolean success = userDAO.renameUser(renamedUser.getUserId(), renamedUser.getUserLogin());

        if (success) {
            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        } else {
            String errorJson = new Gson().toJson("User not found or error in edit");
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, errorJson));
        }
    }

    public String deleteUser(Request request, Response response) {
        response.type("application/json");
        userDAO.deleteUser(Integer.parseInt(request.params(":id")));
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "user deleted"));
    }

    public String getUser(Request request, Response response) {
        response.type("application/json");
        UsersRecord usersRecord = userDAO.getUser(Integer.parseInt(request.params(":id")));
        JsonElement jsonTree = new Gson().toJsonTree(usersRecord.formatJSON());
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, jsonTree));
    }

    public String addUser(Request request, Response response) {
        response.type("application/json");
        UsersRecord user = new Gson().fromJson(request.body(), UsersRecord.class);
        userDAO.addUser(user.getUserLogin());
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    }

    public String getAllUsers(Request request, Response response) {
        response.type("application/json");
        JsonElement jsonTree = new Gson().toJsonTree(userDAO.getAllUsers().formatJSON());
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, jsonTree));
    }
}
