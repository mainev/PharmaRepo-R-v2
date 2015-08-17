/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.exceptions;

import mbrinstant.controls.MyNotifications;

/**
 *
 * @author maine
 */
public class NoResponseException extends Exception {

    public NoResponseException(int status) {
        super("No results found.");
        MyNotifications.displayError("No results found");
    }
}
