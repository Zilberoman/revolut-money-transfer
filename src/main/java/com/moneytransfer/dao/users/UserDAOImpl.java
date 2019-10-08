package com.moneytransfer.dao.users;

import com.google.inject.Inject;
import com.moneytransfer.model.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import java.util.logging.Logger;

import static com.moneytransfer.model.Tables.USERS;

public class UserDAOImpl implements UserDAO {
    private final Logger logger;
    private DSLContext dslContext;

    @Inject
    public UserDAOImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public Result<UsersRecord> getAllUsers() {
        return dslContext.select().from(USERS).fetch().into(USERS);
    }

    @Override
    public UsersRecord getUser(int userId) {
        return dslContext
                .select()
                .from(USERS)
                .where(USERS.USER_ID.eq(userId))
                .fetch()
                .get(0)
                .into(USERS);
    }

    @Override
    public UsersRecord getUserByLogin(String userLogin) {
        Result<Record> records = dslContext
                .select()
                .from(USERS)
                .where(USERS.USER_LOGIN.eq(userLogin))
                .fetch();
        return records.isNotEmpty() ? records.get(0).into(USERS) : null;
    }

    @Override
    public void addUser(String userLogin) {
        if (getUserByLogin(userLogin) == null) {
            dslContext
                    .insertInto(USERS)
                    .columns(USERS.USER_LOGIN)
                    .values(userLogin)
                    .execute();
            logger.info("New account was created");
        }
    }

    @Override
    public boolean renameUser(int userId, String newUserLogin) {
        int updateResult = dslContext
                .update(USERS)
                .set(USERS.USER_LOGIN, newUserLogin)
                .where(USERS.USER_ID.eq(userId))
                .execute();

        boolean success = updateResult > 0;

        if (success) {
            logger.info("User (id = " + userId + ") was renamed to " + newUserLogin);
        }

        return success;
    }

    public void deleteUser(int userId) {
        int deleteResult = dslContext.delete(USERS).where(USERS.USER_ID.eq(userId)).execute();
        logger.info("There was deleted " + deleteResult + " records");
    }

    public void setDslContext(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
