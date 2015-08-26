/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maine
 */
public class MetricCalculatorTest {

    public MetricCalculatorTest() {
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

    /**
     * Test of add method, of class MetricCalculator.
     */
    @Test
    public void testAdd() {
        String val1 = "721.50065";
        String val2 = "333";
        BigDecimal x1 = new BigDecimal(val1);
        BigDecimal x2 = new BigDecimal(val2);
        System.out.println("BigDecimal Result  (ADD): " + x1.add(x2));
        System.out.println("BigDecimal Result  (DIFF): " + x1.subtract(x2));
        System.out.println("Double result: " + (Double.parseDouble(val1) - Double.parseDouble(val2)));
    }

}
