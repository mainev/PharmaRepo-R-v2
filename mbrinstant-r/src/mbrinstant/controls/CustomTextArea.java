/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.scene.control.TextArea;

/**
 *
 * @author maine
 */
public class CustomTextArea extends TextArea implements Validatable{
    
     private int maxLength = 500;
    private int minLength = 0;
    private boolean notNull = true;
    private boolean betweenRange;//text length is between min and max length

    public CustomTextArea() {

    }

    @Override
    public boolean isValid() {
        int textLength = getText().length();

        betweenRange = (textLength >= minLength && textLength <= maxLength);
        if (notNull ) {
            return (!getText().equals("") && betweenRange);
        }
        return betweenRange;
    }


    @Override
    public void replaceText(int start, int end, String text) {
        if ((getText().length() <= maxLength)|| text.equals("") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

 

}
