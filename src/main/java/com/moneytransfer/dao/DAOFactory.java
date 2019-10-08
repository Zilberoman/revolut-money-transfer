package com.moneytransfer.dao;

import com.google.inject.ImplementedBy;
import com.moneytransfer.dao.accounts.AccountDAO;
import com.moneytransfer.dao.users.UserDAO;

@ImplementedBy(DAOFactoryImpl.class)
public interface DAOFactory {
    UserDAO getUserDAO();

    AccountDAO getAccountDAO();

    void closeConnection();
}
