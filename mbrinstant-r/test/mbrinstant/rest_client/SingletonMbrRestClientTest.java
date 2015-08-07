/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client;

import mbrinstant.rest_client.mbr.SingletonMbrRestClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maine
 */
public class SingletonMbrRestClientTest {

    public SingletonMbrRestClientTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetMbrListWithAuthorizedUser() {
        try {
            System.out.println("=================TESTING GET PRODUCT LIST ===================");
            SingletonMbrRestClient mbrRestClient = SingletonMbrRestClient.getInstance();
            mbrRestClient.setUsernameAndPassword("mainevillarias@gmail.com", "password");

            System.out.println(mbrRestClient.getBatchList());
            assert mbrRestClient.getResponseHandler().getCode() == 200;

            mbrRestClient.destroy();

            System.out.println(mbrRestClient.getBatchList());
            assert mbrRestClient.getResponseHandler().getCode() == 401;
            System.out.println("===============================================================================");
        } catch (Exception e) {
        }
    }
}
