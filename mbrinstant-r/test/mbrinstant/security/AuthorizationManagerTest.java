/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.security;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maine
 */
public class AuthorizationManagerTest {

    public AuthorizationManagerTest() {
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
            System.out.println("=================TESTING ===================");
            SingletonAuthorizationManager authManager = SingletonAuthorizationManager.getInstance();
            authManager.setUsernameAndPassword("tester", "password");
            authManager.isUserPermitted("g_batch_list");
            assert authManager.getResponseHandler().getCode() == 200;

            System.out.println("===============================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
