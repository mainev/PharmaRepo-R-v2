/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client;

import mbrinstant.rest_client.main.SingletonProductRestClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maine
 */
public class SingletonProductRestClientTest {

    public SingletonProductRestClientTest() {
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
    public void testGetProductListWithAuthorizedUser() {
        try {
            System.out.println("=================TESTING GET PRODUCT LIST WITH AUTHORIZED USER===================");
            SingletonProductRestClient pService = SingletonProductRestClient.getInstance();
            pService.setUsernameAndPassword("starlightlynx@gmail.com", "password");

            System.out.println(pService.getProductList());
            assert pService.getResponseHandler().getCode() == 200;
            System.out.println("===============================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetProductListWithUnknownUser() {
        try {
            System.out.println("=================TESTING GET PRODUCT LIST WITH UNAUTHORIZED USER===================");
            SingletonProductRestClient pService = SingletonProductRestClient.getInstance();
            pService.setUsernameAndPassword("miing@gmail.com", "password");

            System.out.println(pService.getProductList());
            assert pService.getResponseHandler().getCode() == 401;

            System.out.println("===============================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetProductListWithUnauthorizedUser() {
        try {
            System.out.println("=================TESTING GET PRODUCT LIST WITH UNAUTHORIZED USER===================");
            SingletonProductRestClient pService = SingletonProductRestClient.getInstance();
            pService.setUsernameAndPassword("rnduser@gmail.com", "password");

            System.out.println(pService.getProductList());
            assert pService.getResponseHandler().getCode() == 403;

            System.out.println("===============================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
