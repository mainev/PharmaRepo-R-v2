/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maine
 */
public class SampleTestClass {
    
    public SampleTestClass() {
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
     public void hello() {
     
         SampleStaticClass1 s1 = new SampleStaticClass1("hehe");
         SampleStaticClass2 s2 = new SampleStaticClass2("huhu");
         
         System.out.println(SampleStaticClass1.getVariableString());
         SampleStaticClass1.setVariable("haha");
            System.out.println(SampleStaticClass1.getVariableString());
             System.out.println(SampleStaticClass2.getVariableString());
     }
}
