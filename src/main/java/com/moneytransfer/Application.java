package com.moneytransfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.moneytransfer.dao.DAOFactory;
import com.moneytransfer.service.AccountsService;
import com.moneytransfer.service.UserService;
import org.jooq.exception.IOException;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ApplicationModule());
        DAOFactory daoFactory = injector.getInstance(DAOFactory.class);
        UserService userService = new UserService(daoFactory.getUserDAO());
        AccountsService accountsService = new AccountsService(daoFactory.getAccountDAO());

        try {
            path("/", () -> {
                path("users", () -> {
                    post("/add", userService::addUser);
                    get("", userService::getAllUsers);
                    get("/:id",  userService::getUser);
                    get("/login/:login",  userService::getUserByLogin);
                    put("/rename/:id",  userService::renameUser);
                    delete("/remove/:id",  userService::deleteUser);
                });
                path("accounts", () -> {
                    get("", accountsService::getAllAccounts);
                    get("/:id", accountsService::getAccount);
                    post("/add", accountsService::createAccount);
                    delete("/remove/:id", accountsService::deleteAccount);
                    post("/transfer", accountsService::transferMoney);
                });
            });
        } catch (IOException e) {
            stop();
            daoFactory.closeConnection();
        }
    }
}