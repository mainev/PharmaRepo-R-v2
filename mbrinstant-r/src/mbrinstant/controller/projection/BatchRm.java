/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.projection;

import mbrinstant.entity.main.RawMaterial;

/**
 *
 * @author maine
 */
public class BatchRm {
    RawMaterial rawMaterial;
    double stock;
    double required;
    String uom;//for stock
    String requiredUom;
    
    public BatchRm(){
    }

    public BatchRm(RawMaterial rawMaterial, double stock, double required) {
        this.rawMaterial = rawMaterial;
        this.stock = stock;
        this.required = required;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getRequired() {
        return required;
    }

    public void setRequired(double required) {
        this.required = required;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getRequiredUom() {
        return requiredUom;
    }

    public void setRequiredUom(String requiredUom) {
        this.requiredUom = requiredUom;
    }
    
    
    @Override
    public String toString(){
        return required + " " + requiredUom;
    }
    
            
}
