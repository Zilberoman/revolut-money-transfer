/*
 * This file is generated by jOOQ.
 */
package com.moneytransfer.model.tables.records;


import com.moneytransfer.model.tables.Users;

import javax.annotation.processing.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersRecord extends UpdatableRecordImpl<UsersRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 726178581;

    /**
     * Setter for <code>accounts.users.User_ID</code>.
     */
    public void setUserId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>accounts.users.User_ID</code>.
     */
    public Integer getUserId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>accounts.users.User_Login</code>.
     */
    public void setUserLogin(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>accounts.users.User_Login</code>.
     */
    public String getUserLogin() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Users.USERS.USER_ID;
    }

    @Override
    public Field<String> field2() {
        return Users.USERS.USER_LOGIN;
    }

    @Override
    public Integer component1() {
        return getUserId();
    }

    @Override
    public String component2() {
        return getUserLogin();
    }

    @Override
    public Integer value1() {
        return getUserId();
    }

    @Override
    public String value2() {
        return getUserLogin();
    }

    @Override
    public UsersRecord value1(Integer value) {
        setUserId(value);
        return this;
    }

    @Override
    public UsersRecord value2(String value) {
        setUserLogin(value);
        return this;
    }

    @Override
    public UsersRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersRecord
     */
    public UsersRecord() {
        super(Users.USERS);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(Integer userId, String userLogin) {
        super(Users.USERS);

        set(0, userId);
        set(1, userLogin);
    }
}
