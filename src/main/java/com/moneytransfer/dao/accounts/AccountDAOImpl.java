package com.moneytransfer.dao.accounts;

import com.google.inject.Inject;
import com.moneytransfer.exception.DifferentCCYException;
import com.moneytransfer.exception.NotEnoughMoneyException;
import com.moneytransfer.model.tables.records.AccountsRecord;
import com.moneytransfer.models.Transaction;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static com.moneytransfer.model.tables.Accounts.ACCOUNTS_;

public class AccountDAOImpl implements AccountDAO {
    private final Logger logger;
    private DSLContext dslContext;

    @Inject
    public AccountDAOImpl(Logger logger) {
        this.logger = logger;
    }

    public Result<AccountsRecord> getAllAccounts() {
        return dslContext.select().from(ACCOUNTS_).fetch().into(ACCOUNTS_);
    }

    public AccountsRecord getAccount(int accountId) {
        Result<Record> records = dslContext
                .select()
                .from(ACCOUNTS_)
                .where(ACCOUNTS_.ACCOUNT_ID.eq(accountId))
                .fetch();
        return records.isNotEmpty() ? records.get(0).into(ACCOUNTS_) : null;
    }

    public void createAccount(String accountName, String ccy, BigDecimal balance) {
        dslContext
                .insertInto(ACCOUNTS_)
                .columns(ACCOUNTS_.ACCOUNT_NAME, ACCOUNTS_.BALANCE, ACCOUNTS_.CCY)
                .values(accountName, balance.doubleValue(), ccy)
                .execute();
        logger.info("New account was created");
    }

    public void deleteAccount(int accountId) {
        int deleteResult = dslContext.delete(ACCOUNTS_).where(ACCOUNTS_.ACCOUNT_ID.eq(accountId)).execute();
        logger.info("There was deleted " + deleteResult + " records");
    }

    public void updateAccountBalance(int accountId, BigDecimal amountMoney) {
        int updateResult = dslContext
                .update(ACCOUNTS_)
                .set(ACCOUNTS_.BALANCE, amountMoney.doubleValue())
                .where(ACCOUNTS_.ACCOUNT_ID.eq(accountId))
                .execute();

        boolean success = updateResult > 0;

        if (success) {
            logger.info("Account balance(id = " + accountId + ") was updated to " + amountMoney);
        }

    }

    public void setDslContext(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void transferMoney(Transaction transaction) throws NotEnoughMoneyException, DifferentCCYException {
        AccountsRecord oldAccountRecord = this.getAccount(transaction.getOldAccountId());
        AccountsRecord newAccountRecord = this.getAccount(transaction.getNewAccountId());
        BigDecimal oldAccountBalance = new BigDecimal(oldAccountRecord.getBalance());
        BigDecimal newAccountBalance = new BigDecimal(newAccountRecord.getBalance());

        if (oldAccountBalance.compareTo(transaction.getAmount()) < 0) {
            throw new NotEnoughMoneyException();
        }

        if (!oldAccountRecord.getCcy().equals(transaction.getCurrencyCode())
                || !oldAccountRecord.getCcy().equals(newAccountRecord.getCcy())) {
            throw new DifferentCCYException();
        }

        this.updateAccountBalance( transaction.getOldAccountId(), oldAccountBalance.subtract(transaction.getAmount()));
        this.updateAccountBalance(transaction.getNewAccountId(), newAccountBalance.add(transaction.getAmount()));
    }
}
