/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controls;

import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

/**
 *
 * @author maine
 */
public class MyNotifications {

    public static void displayInformation(String text) {
        Notifications
                .create()
                .position(Pos.TOP_RIGHT)
                .text(text)
                .showInformation();
    }

    public static void displayConfirm(String text) {
        Notifications
                .create()
                .position(Pos.TOP_RIGHT)
                .text(text)
                .showConfirm();
    }

    public static void displayError(String text) {
        Notifications
                .create()
                .position(Pos.TOP_RIGHT)
                .text(text)
                .showError();
    }

    public static void displayWarning(String text) {
        Notifications
                .create()
                .position(Pos.TOP_RIGHT)
                .text(text)
                .showWarning();
    }
}
