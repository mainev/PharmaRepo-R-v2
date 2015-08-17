/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.exceptions;

import mbrinstant.controls.CustomAlertDialog;

/**
 *
 * @author maine
 */
public class ServerException extends Exception {

    public ServerException(int status, String methodName) {
        super("A server exception has occured with http status code: " + status + " in the method: " + methodName + ". Please report this to the system administrator");
        CustomAlertDialog.showExceptionDialog(this);

    }

    public ServerException(int status) {

        super("A server exception has occured with http status code: " + status + ". Please report this to the system administrator");
        CustomAlertDialog.showExceptionDialog(this);
    }
}
