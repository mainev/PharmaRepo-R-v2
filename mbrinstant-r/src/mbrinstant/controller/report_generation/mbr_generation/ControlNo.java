/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.report_generation.mbr_generation;

import mbrinstant.utils.Quantity;

/**
 *
 * @author maine
 */
public class ControlNo {

    private String controlNo;
    private Quantity actualQty;

    public ControlNo(String controlNo, Quantity actualQty) {
        this.controlNo = controlNo;
        this.actualQty = actualQty;
    }

    public String getControlNo() {
        return controlNo;
    }

    public void setControlNo(String controlNo) {
        this.controlNo = controlNo;
    }

    public Quantity getActualQty() {
        return actualQty;
    }

    public void setActualQty(Quantity actualQty) {
        this.actualQty = actualQty;
    }

    @Override
    public String toString() {
        return actualQty + " " + controlNo;
    }

}
