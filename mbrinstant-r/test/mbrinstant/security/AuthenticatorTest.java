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
public class AuthenticatorTest {

    public AuthenticatorTest() {
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

    //test for login
    @Test
    public void testAuthenticatorForValidUser() {
        try {
            System.out.println("=============================TEST AUTHENTICATOR FOR VALID USER===========================");
            Authenticator authenticator = new Authenticator();
            System.out.println(authenticator.loginUser("rnduser@gmail.com", "password"));

            assert authenticator.getResponseHandler().getCode() == 200;
            System.out.println("=========================================================================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     @Test
     public void testAuthenticatorForInvalidUser() {
     System.out.println("=============================TEST AUTHENTICATOR FOR INVALID USER===========================");
     Authenticator authenticator = new Authenticator();
     authenticator.loginUser("hawke@gmail.com", "password");
     assert authenticator.getResponseHandler().getCode() == 204;
     System.out.println("=========================================================================================");
     }
     */
}
