/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.product.ProductWizard;

/**
 *
 * @author maine
 */
public interface PageController {

    public boolean allFieldsValid();
    public void createValidator();
    public String getInstruction();
}
