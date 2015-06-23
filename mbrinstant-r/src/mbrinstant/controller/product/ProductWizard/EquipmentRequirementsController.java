/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import mbrinstant.controls.InputValidator;

/**
 * FXML Controller class
 *
 * @author maine
 */
public class EquipmentRequirementsController implements Initializable, PageController {

    InputValidator validator;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createValidator();
    }

    @Override
    public void createValidator() {
        validator = new InputValidator();
    }

    @Override
    public boolean allFieldsValid() {
        return validator.validateFields();
    }

    @Override
    public String getInstruction() {
        return "5. Set equipment requirements";
    }

}
