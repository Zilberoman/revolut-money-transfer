package com.moneytransfer.dao.accounts;

import com.google.inject.ImplementedBy;
import com.moneytransfer.exception.DifferentCCYException;
import com.moneytransfer.exception.NotEnoughMoneyException;
import com.moneytransfer.model.tables.records.AccountsRecord;
import com.moneytransfer.models.Transaction;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.math.BigDecimal;

@ImplementedBy(AccountDAOImpl.class)
public interface AccountDAO {
    Result<AccountsRecord> getAllAccounts();

    AccountsRecord getAccount(int accountId);

    void createAccount(String accountName, String ccy, BigDecimal balance);

    void deleteAccount(int accountId);

    void updateAccountBalance(int accountId, BigDecimal amountMoney);

    void transferMoney(Transaction transaction) throws NotEnoughMoneyException, DifferentCCYException;

    void setDslContext(DSLContext dslContext);
}
