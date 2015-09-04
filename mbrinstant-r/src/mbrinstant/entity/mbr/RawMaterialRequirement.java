/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.sqlsvr_copy.Item;

/**
 *
 * @author maine
 */
public class RawMaterialRequirement {

    @Expose
    private Integer id;
    @Expose
    private Double quantity;
    @Expose
    private Unit unitId;
    @Expose
    private short part;
    @Expose
    private Item itemId;

    private double newQuantity;
    private String newUnit;

    public RawMaterialRequirement(Item itemId, double quantity, Unit unitId, short part) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitId = unitId;
        this.part = part;
    }

    public short getPart() {
        return part;
    }

    public void setPart(short part) {
        this.part = part;
    }

    public double getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(double newQuantity) {
        this.newQuantity = newQuantity;
    }

    public String getNewUnit() {
        return newUnit;
    }

    public void setNewUnit(String newUnit) {
        this.newUnit = newUnit;
    }

    public RawMaterialRequirement() {
    }

    public RawMaterialRequirement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        String p = "";
        System.out.println(part);
        if (part > 0) {
            p = " (Part " + part + ")";
        }

        return itemId + " - " + quantity + " " + unitId + p;
    }

}
