/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.scene.control.ChoiceBox;

/**
 *
 * @author maine
 */
public class CustomizedChoiceBox<T> extends ChoiceBox<T> implements Validatable {

    private boolean notNull = true;
    
    public CustomizedChoiceBox() {
        super();
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

   
    @Override
    public boolean isValid() {
        if (notNull) {
            return this.getValue() != null;
        }
        return true;
    }

}
