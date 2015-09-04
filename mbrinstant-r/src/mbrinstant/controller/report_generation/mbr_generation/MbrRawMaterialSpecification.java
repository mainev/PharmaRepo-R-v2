/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.report_generation.mbr_generation;

import java.util.List;
import mbrinstant.entity.sqlsvr_copy.Item;
import mbrinstant.utils.Quantity;

/**
 *
 * @author maine
 */
public class MbrRawMaterialSpecification {

    private short part;
    private Item item;
    private Quantity udfQty;
    private Quantity requiredQty;
    private List<ControlNo> controlNoList;

    public MbrRawMaterialSpecification(Item item, Quantity udfQuantity, Quantity requiredQty, List<ControlNo> controlNoList, short part) {
        this.item = item;
        this.udfQty = udfQuantity;
        this.requiredQty = requiredQty;
        this.controlNoList = controlNoList;
        this.part = part;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Quantity getUdfQty() {
        return udfQty;
    }

    public void setUdfQty(Quantity udfQty) {
        this.udfQty = udfQty;
    }

    public Quantity getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(Quantity requiredQty) {
        this.requiredQty = requiredQty;
    }

    public List<ControlNo> getControlNoList() {
        return controlNoList;
    }

    public void setControlNoList(List<ControlNo> controlNoList) {
        this.controlNoList = controlNoList;
    }

    public short getPart() {
        return part;
    }

    public void setPart(short part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return "{"
                + "item: " + item + ", "
                + "part: " + part + ", "
                + "udfQuantity: " + udfQty + ", "
                + "requiredQty: " + requiredQty + ", "
                + "controlNo: " + controlNoList + "}";
    }

}
