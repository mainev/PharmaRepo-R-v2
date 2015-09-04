/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.rest_client;

import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maine
 */
public class SingletonStockCardTxnRestClientTest {

    public SingletonStockCardTxnRestClientTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testGetStockCardByBatchNo() {
        try {
            System.out.println("=================TESTING =========================================");
            SingletonStockCardTxnRestClient stockCardTxnRestClient = SingletonStockCardTxnRestClient.getInstance();
            stockCardTxnRestClient.setUsernameAndPassword("mainevillarias@gmail.com", "password");

            System.out.println("===============================================================================");
        } catch (Exception e) {
        }
    }
}
