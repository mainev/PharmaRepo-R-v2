/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import java.awt.Toolkit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author maine
 */
public class InputValidator {

    ObservableList<Object> nodes = FXCollections.observableArrayList();

    public InputValidator(Object... n) {
        this.nodes.addAll(n);
    }

    /**
     * @return Returns true if all field values are valid
     */
    public boolean validateFields() {
        if (!nodes.isEmpty()) {
            for (Object n : nodes) {
                if (!validate(n)) {
                     Toolkit.getDefaultToolkit().beep();
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    /**
     * *
     *
     * @param n - contains value to be validated
     * @return Returns true if the object's value is valid
     */
    public boolean validate(Object n) {
        //objects are classified based on their class
        if (n.getClass() == CustomTextField.class) {
            CustomTextField n1 = (CustomTextField) n;
            return n1.isValid();
        } else if (n.getClass() == CustomizedChoiceBox.class) {
            CustomizedChoiceBox n1 = (CustomizedChoiceBox) n;
            return n1.isValid();
        } else if (n.getClass() == ChoiceBox.class) {
            ChoiceBox n1 = (ChoiceBox) n;
            return n1.getValue() != null;
        } else if (n.getClass() == NumberTextField.class) {
            NumberTextField n1 = (NumberTextField) n;
            return n1.isValid();
        } else if (n.getClass() == IntegerTextField.class) {
            IntegerTextField n1 = (IntegerTextField) n;
            return n1.isValid();
        }else if (n.getClass() == TextFieldWithSearch.class) {
            TextFieldWithSearch n1 = (TextFieldWithSearch) n;
            return n1.isValid();
        }
        else if (n.getClass() == SearchTextField.class) {
            SearchTextField n1 = (SearchTextField) n;
            return n1.isValid();
        }
         else if (n.getClass() == TextArea.class) {
            TextArea n1 = (TextArea) n;
            
            return (!n1.getText().equals(""));
        }
         else if (n.getClass() == CustomTextArea.class) {
            CustomTextArea n1 = (CustomTextArea) n;
            return n1.isValid();
        }
        
        return true;

    }
}
