/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author maine
 */
public class InputValidator {

    ObservableList<Object> nodes = FXCollections.observableArrayList();

    public InputValidator(Object... n) {
        this.nodes.addAll(n);
    }
    
    /***
     * 
     * @return true if all field values are valid
     */
    public boolean validateFields(){
     if(!nodes.isEmpty()){
        for(Object n: nodes){
          if(!validate(n)) 
              return false;
        }
        return true;
     }
     return true;
    }
    
    /***
     * 
     * @param n - contains value to be validated
     * @return true if the object's value is valid
     */
    public boolean validate(Object n){
        //objects are classified based on their classes
        if(n.getClass() == CharacterTextField.class){
          CharacterTextField n1 = (CharacterTextField) n;
          return n1.isValid();
        }
        else if(n.getClass() == ChoiceBox.class){
            ChoiceBox n1 = (ChoiceBox) n;
            if((Boolean)n1.getUserData() == true)
                return n1.getValue() != null;
            return false;
        }
        else if(n.getClass() == NumberTextField.class){
            NumberTextField n1 = (NumberTextField) n;
            return n1.isValid();
        }
        else if(n.getClass() == TextFieldWithSearch.class){
            TextFieldWithSearch n1 = (TextFieldWithSearch) n;
            return n1.isValid();
        }
        return true;
      
    }
}
