/*
 * This file is generated by jOOQ.
 */
package com.moneytransfer.model;


import com.moneytransfer.model.tables.Accounts;
import com.moneytransfer.model.tables.Users;

import javax.annotation.processing.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>accounts</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index ACCOUNTS_IDX_ACC = Indexes0.ACCOUNTS_IDX_ACC;
    public static final Index ACCOUNTS_PRIMARY = Indexes0.ACCOUNTS_PRIMARY;
    public static final Index USERS_IDX_UE = Indexes0.USERS_IDX_UE;
    public static final Index USERS_PRIMARY = Indexes0.USERS_PRIMARY;
    public static final Index USERS_USERS_USER_LOGIN_UINDEX = Indexes0.USERS_USERS_USER_LOGIN_UINDEX;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index ACCOUNTS_IDX_ACC = Internal.createIndex("idx_acc", Accounts.ACCOUNTS_, new OrderField[] { Accounts.ACCOUNTS_.ACCOUNT_NAME, Accounts.ACCOUNTS_.CCY }, true);
        public static Index ACCOUNTS_PRIMARY = Internal.createIndex("PRIMARY", Accounts.ACCOUNTS_, new OrderField[] { Accounts.ACCOUNTS_.ACCOUNT_ID }, true);
        public static Index USERS_IDX_UE = Internal.createIndex("idx_ue", Users.USERS, new OrderField[] { Users.USERS.USER_LOGIN }, true);
        public static Index USERS_PRIMARY = Internal.createIndex("PRIMARY", Users.USERS, new OrderField[] { Users.USERS.USER_ID }, true);
        public static Index USERS_USERS_USER_LOGIN_UINDEX = Internal.createIndex("users_User_Login_uindex", Users.USERS, new OrderField[] { Users.USERS.USER_LOGIN }, true);
    }
}