package com.moneytransfer.test.services;

import com.google.gson.Gson;
import com.moneytransfer.model.tables.records.AccountsRecord;
import com.moneytransfer.models.Transaction;
import com.moneytransfer.service.json.AccountsRecordJson;
import com.moneytransfer.test.spark.SparkServerRule;
import com.moneytransfer.test.utils.ParseUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class AccountServiceTest extends ServiceTest {
    @ClassRule
    public static final SparkServerRule SPARK_SERVER =
            new SparkServerRule(Logger.getLogger(SparkServerRule.class.getName()),
                    http -> {
                        http.get("/accounts", accountsService::getAllAccounts);
                        http.get("/accounts/:id", accountsService::getAccount);
                        http.post("/accounts/add", accountsService::createAccount);
                        http.delete("/accounts/remove/:id", accountsService::deleteAccount);
                        http.post("/accounts/transfer", accountsService::transferMoney);
                    });

    @Test
    public void testGetAllAccounts() throws URISyntaxException {
        URI uri = builder.setPath("/accounts").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        Response response = client.target(uri).request().get();
        assertEquals(200, response.getStatus());
        String resultEntity = response.readEntity(String.class);
        List<AccountsRecord> records = ParseUtils.parseToAccountsRecordList(resultEntity);
        assertNotNull(records);
        assertTrue(records.size() > 0);
    }

    @Test
    public void testGetAccount() throws URISyntaxException {
        URI uri = builder.setPath("/accounts/1").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        Response response = client.target(uri).request().get();
        assertEquals(200, response.getStatus());
        String resultEntity = response.readEntity(String.class);
        AccountsRecord record = ParseUtils.parseToAccountsRecord(resultEntity);
        assertNotNull(record);
        assertEquals(1, record.getAccountId().intValue());
    }

    @Test
    public void testAddAccount() throws URISyntaxException {
        URI uri = builder.setPath("/accounts/add").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        AccountsRecordJson record = new AccountsRecordJson( "weeee", "RUB", 2000.0);
        Entity<String> entity = Entity.text(record.toJson());
        Response response = client.target(uri).request().post(entity);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDeleteAccount() throws URISyntaxException {
        URI uri = builder.setPath("/accounts/remove/3").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        Response response = client.target(uri).request().delete();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testTransferMoneyAccount() throws URISyntaxException {
        URI uri = builder.setPath("/accounts/transfer").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        Transaction transaction =
                new Transaction("USD", new BigDecimal(200), 1, 2);
        Entity<String> entity = Entity.text(new Gson().toJson(transaction));
        Response response = client.target(uri).request().post(entity);
        assertEquals(200, response.getStatus());
    }
}
