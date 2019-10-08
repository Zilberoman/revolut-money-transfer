package com.moneytransfer.dao.users;

import com.google.inject.ImplementedBy;
import com.moneytransfer.model.tables.records.UsersRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
//import com.moneytransfer.model.User;

import java.util.List;

@ImplementedBy(UserDAOImpl.class)
public interface UserDAO {
    Result<UsersRecord> getAllUsers();

    UsersRecord getUser(int userId);

    UsersRecord getUserByLogin(String userLogin);

    void addUser(String userLogin);

    boolean renameUser(int userId, String newUserLogin);

    void deleteUser(int userId);

    void setDslContext(DSLContext dslContext);
}
