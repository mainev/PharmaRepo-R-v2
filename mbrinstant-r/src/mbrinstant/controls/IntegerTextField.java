/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.scene.control.TextField;

/**
 *
 * @author maine
 */
public class IntegerTextField extends TextField implements Validatable {

    private int maxValue;
    private int minValue = 0;//this textfield only allows value greater than or equal 0

    public IntegerTextField() {
        super();
        this.setPromptText("Enter only numbers");
    }

    public int getValue(){
        if(!getText().isEmpty()){
            return Integer.parseInt(getText());
        }
        return 0;
    }
    
    @Override
    public boolean isValid() {
        if (!getText().isEmpty() || !getText().equals("")) {
            double value = Double.parseDouble(getText());
            if (maxValue != 0) {
                return value >= minValue && value <= maxValue;
            }
            return value >= minValue;
        }
        
       return false;
        //   return value <= maxValue;
        // return true;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[0-9]") || text.isEmpty() || text.equals("")) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement); //To change body of generated methods, choose Tools | Templates.
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

   

}
