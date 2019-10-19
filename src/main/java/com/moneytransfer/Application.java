package com.moneytransfer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.moneytransfer.dao.DAOFactory;
import com.moneytransfer.service.AccountsService;
import com.moneytransfer.service.UserService;
import org.jooq.exception.IOException;

/**
 * Task is completed
 * BigDecimal amount, but database type is wrong
 *
 * However, I regret to inform you that after much consideration
 * we have decided not to progress further with your application due to some critical issues that we've found:
     * Database is not in-memory (MySql)
     * Balance is double in database
     * Build and tests do not work without started database
     * Not enough test cases for money transfer
     * Verbs in resources URLs
     * Negative amount transfers are possible
     * Non-thread safe solution
     * Accounts are not linked with users
     * Users seem to be redundant entity
 */
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