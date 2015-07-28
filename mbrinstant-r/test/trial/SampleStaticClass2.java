/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial;

import static trial.SampleStaticClass1.variable;

/**
 *
 * @author maine
 */
public class SampleStaticClass2 {
    public static String variable;
    
    public SampleStaticClass2(String var){
       variable = var;
    }
    
    public static String getVariableString(){
        return variable;
    }
}
