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
public class CharacterTextField extends TextField implements Validatable{

    private int maxLength;
    private int minLength = 1;
    private boolean notNull = true;//default text field doesn't allow null values
    private boolean betweenRange;//text length is between min and max length

    public CharacterTextField() {

    }

    @Override
    public boolean isValid() {
        int textLength = getText().length();

        betweenRange = (textLength >= minLength && textLength < maxLength);
        if (notNull ) {
            return (!getText().equals("") && betweenRange);
        }
    
        return betweenRange;
    }

//    public boolean isUnique() {
//        if (typeClass == Product.class) {
////            System.out.println(Product.class.getDeclaredFields().length);
//            ProductService productService = new ProductService();
//            return productService.isCodeValid(getText());
//        }
//
//        return false;
//    }

//    public void unique(boolean unique, Class typeClass) {
//        this.typeClass = typeClass;
//        this.unique = unique;
//    }
//    

    public void notNull(boolean notNull) {
        this.notNull = notNull;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        if ((getText().length() < maxLength) || text.equals("")) {
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

}
