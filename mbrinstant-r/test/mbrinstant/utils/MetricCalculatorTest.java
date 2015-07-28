/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.utils;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
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

//        BigDecimal x1 = new BigDecimal("721.50065");
//        BigDecimal x2 = new BigDecimal("333");
//        BigDecimal xDiff = x1.add(x2);
//        System.out.println("BigDecimal Result (float value): " + x1.add(x2));
//         System.out.println("bin: "+Long.toBinaryString(Double.doubleToLongBits(xDiff.doubleValue())));
////
//        Double b1 = 721.50065;
//        Double b2 = 333.0;
//        Double diff = b1 - b2;
//        System.out.println();
//        System.out.println("Calculate: " + b1 + " - " + b2);
//        System.out.println("Double Result : " + diff);
//        System.out.println("Double Result (float value): " + diff.floatValue()+'\n');
//
//
//        Double value1 = 721.50065;
//        Double value2 = 333.0;
//
//        StringTokenizer tokenizer = new StringTokenizer(String.valueOf(value1), ".");
//        String intg = tokenizer.nextToken();
//        String frac = tokenizer.nextToken();
//
//        System.out.println("integral = " + Double.parseDouble(intg));
//        System.out.println("fractional = " + (Double.parseDouble("." + frac)));
//        Integer iFrac = Integer.parseInt(frac);
//        Double dFrac = Double.parseDouble("." + frac);
//        System.out.println("fraction in integer format: " + iFrac);
//        System.out.println("exponent: "+Math.floor(Math.log(dFrac) / Math.log(10)));
        Quantity q1 = new Quantity(1, "mg");
        Quantity q2 = new Quantity(1, "kg");
        Quantity qResult = MetricCalculator.add(q1, q2);

        System.out.println("unit: " + qResult);
        fail("The test case is a prototype.");
    }

}
