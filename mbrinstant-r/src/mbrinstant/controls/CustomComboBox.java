/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.scene.control.ComboBox;

/**
 *
 * @author maine
 */
public class CustomComboBox<T> extends ComboBox<T> implements Validatable{
    
    //constraints
    private boolean notNull = true;
    
    public CustomComboBox(){
    
    }
    
    @Override
    public boolean isValid(){
        if(notNull){
            if(getValue()!=null)
                return true;
            return false;
        }
        return true;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
    
    
    
}
