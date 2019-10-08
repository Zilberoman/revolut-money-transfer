package com.moneytransfer.test.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.moneytransfer.dao.DAOFactory;
import com.moneytransfer.dao.accounts.AccountDAO;
import com.moneytransfer.dao.users.UserDAO;
import com.moneytransfer.service.AccountsService;
import com.moneytransfer.service.UserService;
import com.moneytransfer.test.modules.ApplicationModuleTest;
import org.apache.http.client.utils.URIBuilder;
import org.junit.AfterClass;


import static spark.Spark.*;

public abstract class ServiceTest {
    static AccountsService accountsService;
    static UserService userService;

    private static DAOFactory daoFactory;
    URIBuilder builder = new URIBuilder().setScheme("http").setHost("localhost:4567");

    static  {
        Injector injector = Guice.createInjector(new ApplicationModuleTest());
        daoFactory = injector.getInstance(DAOFactory.class);
        AccountDAO accountDAO = daoFactory.getAccountDAO();
        UserDAO userDAO = daoFactory.getUserDAO();
        accountsService = new AccountsService(accountDAO);
        userService = new UserService(userDAO);
    }

    @AfterClass
    public static void closeClient() {
        stop();
        daoFactory.closeConnection();
    }
}
