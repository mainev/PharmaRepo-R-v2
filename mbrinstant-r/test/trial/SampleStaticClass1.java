/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial;

/**
 *
 * @author maine
 */
public class SampleStaticClass1 {
    public static String variable;
    
    public SampleStaticClass1(String var){
       variable = var;
    }
    
    public static String getVariableString(){
        return variable;
    }

    public static String getVariable() {
        return variable;
    }

    public static void setVariable(String variable) {
        SampleStaticClass1.variable = variable;
    }
    
    
}
