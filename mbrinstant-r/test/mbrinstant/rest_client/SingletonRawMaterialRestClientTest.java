/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client;

import mbrinstant.rest_client.main.SingletonRawMaterialRestClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maine
 */
public class SingletonRawMaterialRestClientTest {

    public SingletonRawMaterialRestClientTest() {
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
    public void testMethod() {
        try {
            System.out.println("=================TESTING=======================================");
            SingletonRawMaterialRestClient restClient = SingletonRawMaterialRestClient.getInstance();
            restClient.setUsernameAndPassword("mainevillarias@gmail.com", "password");

            System.out.println(restClient.getRawMaterialList());
            assert restClient.getResponseHandler().getCode() == 200;

            restClient.setUsernameAndPassword("rr@gmail.com", "password");

            System.out.println(restClient.getRawMaterialList());
            assert restClient.getResponseHandler().getCode() == 401;
            System.out.println("===============================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
