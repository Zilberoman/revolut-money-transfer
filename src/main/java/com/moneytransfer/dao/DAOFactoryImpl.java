package com.moneytransfer.dao;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.moneytransfer.dao.accounts.AccountDAO;
import com.moneytransfer.dao.users.UserDAO;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DAOFactoryImpl implements DAOFactory {
    private final Logger logger;

    private final String driver;
    private final String url;
    private final String user;
    private final String password;
    private final UserDAO userDAO;
    private final AccountDAO accountDAO;

    private Connection connection;

    @Inject
    public DAOFactoryImpl(Logger logger, @Named("jdbc.driver") String driver, @Named("jdbc.url") String url,
                          @Named("jdbc.user") String user, @Named("jdbc.password") String password,
                          UserDAO userDAO, AccountDAO accountDAO) {
        this.logger = logger;
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.userDAO = userDAO;
        this.accountDAO = accountDAO;
        this.connect();
    }

    private void connect() {
        try {
            Connection connection = getConnection();
            DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
            this.accountDAO.setDslContext(dslContext);
            this.userDAO.setDslContext(dslContext);
        } catch (SQLException e) {
            this.logger.severe(e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {
            try {
                Class.forName(driver).newInstance();
                this.connection = DriverManager.getConnection(url, user, password);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                this.logger.severe(e.getMessage());
                throw new SQLException();
            }
        }

        return this.connection;
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    @Override
    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            this.logger.severe(e.getMessage());
        }
    }
}
