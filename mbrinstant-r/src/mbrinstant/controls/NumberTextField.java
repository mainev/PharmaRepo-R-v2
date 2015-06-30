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
public class NumberTextField extends TextField implements Validatable {

    private double maxValue;
    private double minValue = 0;//this textfield only allows value greater than 0

    public NumberTextField() {
        super();
        this.setPromptText("Enter only numbers");
    }
    
    public double getValue(){
        if(!getText().isEmpty()){
            return Double.parseDouble(getText());
        }
        return 0;
    }

    @Override
    public boolean isValid() {
        if (!getText().isEmpty() || !getText().equals("")) {
            double value = Double.parseDouble(getText());
            if (maxValue != 0) {
                return value > minValue && value <= maxValue;
            }
            return value > minValue;
        }
        
       return false;
        //   return value <= maxValue;
        // return true;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[-+]?[0-9]*\\.?[0-9]*") || text.isEmpty() || text.equals("")) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement); //To change body of generated methods, choose Tools | Templates.
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

}
