/*
 * This file is generated by jOOQ.
 */
package com.moneytransfer.model;


import com.moneytransfer.model.tables.Accounts;
import com.moneytransfer.model.tables.Users;

import javax.annotation.processing.Generated;


/**
 * Convenience access to all tables in accounts
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>accounts.accounts</code>.
     */
    public static final Accounts ACCOUNTS_ = Accounts.ACCOUNTS_;

    /**
     * The table <code>accounts.users</code>.
     */
    public static final Users USERS = Users.USERS;
}