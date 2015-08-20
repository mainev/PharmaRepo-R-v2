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
public class PackagingMaterialRequirement {

    @Expose
    private Integer id;
    @Expose
    private Double quantity;
    @Expose
    private Unit unitId;
    @Expose
    private Item itemId;

    private double newQuantity;

    public PackagingMaterialRequirement(Item itemId, double quantity, Unit unitId) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitId = unitId;
    }

    public double getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(double newQuantity) {
        this.newQuantity = newQuantity;
    }

    public PackagingMaterialRequirement() {
    }

    public PackagingMaterialRequirement(Integer id) {
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
        return itemId + " - " + quantity + " " + unitId;
    }

}
