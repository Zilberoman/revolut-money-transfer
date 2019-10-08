package com.moneytransfer.test.services;

import com.moneytransfer.model.tables.records.AccountsRecord;
import com.moneytransfer.model.tables.records.UsersRecord;
import com.moneytransfer.service.json.AccountsRecordJson;
import com.moneytransfer.service.json.UsersRecordJson;
import com.moneytransfer.test.spark.SparkServerRule;
import com.moneytransfer.test.utils.ParseUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Logger;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class UsersServiceTest extends ServiceTest {
    @ClassRule
    public static final SparkServerRule SPARK_SERVER =
            new SparkServerRule(Logger.getLogger(SparkServerRule.class.getName()),
                    http -> {
                        http.post("/users/add", userService::addUser);
                        http.get("/users", userService::getAllUsers);
                        http.get("/users/:id",  userService::getUser);
                        http.get("/users/login/:login", userService::getUserByLogin);
                        http.put("/users/rename/:id", userService::renameUser);
                        http.delete("/users/remove/:id",  userService::deleteUser);
                    });

    @Test
    public void testGetAllUsers() throws URISyntaxException {
        URI uri = builder.setPath("/users").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        Response response = client.target(uri).request().get();
        assertEquals(200, response.getStatus());
        String resultEntity = response.readEntity(String.class);
        List<UsersRecord> records = ParseUtils.parseToUsersRecordList(resultEntity);
        assertNotNull(records);
        assertTrue(records.size() > 0);
    }

    @Test
    public void testGetUser() throws URISyntaxException {
        URI uri = builder.setPath("/users/1").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        Response response = client.target(uri).request().get();
        assertEquals(200, response.getStatus());
        String resultEntity = response.readEntity(String.class);
        UsersRecord record = ParseUtils.parseToUsersRecord(resultEntity);
        assertNotNull(record);
        assertEquals(1, record.getUserId().intValue());
    }

    @Test
    public void testGetUserByLogin() throws URISyntaxException {
        URI uri = builder.setPath("/users/login/testingUser").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        Response response = client.target(uri).request().get();
        assertEquals(200, response.getStatus());
        String resultEntity = response.readEntity(String.class);
        UsersRecord record = ParseUtils.parseToUsersRecord(resultEntity);
        assertNotNull(record);
        assertEquals("testingUser", record.getUserLogin());
    }

    @Test
    public void testAddUser() throws URISyntaxException {
        URI uri = builder.setPath("/users/add").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        UsersRecordJson record = new UsersRecordJson("sekkl");
        Entity<String> entity = Entity.text(record.toJson());
        Response response = client.target(uri).request().post(entity);
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDeleteAccount() throws URISyntaxException {
        URI uri = builder.setPath("/users/remove/1").build();
        ResteasyClient client = new ResteasyClientBuilderImpl().build();
        Response response = client.target(uri).request().delete();
        assertEquals(200, response.getStatus());
    }
}
