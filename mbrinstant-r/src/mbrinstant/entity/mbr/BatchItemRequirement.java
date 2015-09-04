/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.List;
import mbrinstant.entity.ItemReqStatusEnum;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.sqlsvr_copy.Item;
import mbrinstant.entity.transaction.StockCardTxn;

/**
 *
 * @author maine
 */
public class BatchItemRequirement {

    @Expose
    private Integer id;
    @Expose
    private Item itemId;
    @Expose
    private Double udfQty;
    @Expose
    private Unit udfQtyUnitId;
    @Expose
    private Double requiredQty;
    @Expose
    private Unit requiredQtyUnitId;
    @Expose
    private Date dateAllocated;
    @Expose
    private Date dateDispensed;
    @Expose
    private Short part;
    @Expose
    private ItemReqStatusEnum itemReqStatus;
    @Expose
    private List<StockCardTxn> stockCardTxnList;

    public BatchItemRequirement() {
    }

    public BatchItemRequirement(Item itemId, Double udfQty, Unit udfQtyUnitId, Double requiredQty, Unit requiredQtyUnitId, Short part) {
        this.itemId = itemId;
        this.udfQty = udfQty;
        this.udfQtyUnitId = udfQtyUnitId;
        this.requiredQty = requiredQty;
        this.requiredQtyUnitId = requiredQtyUnitId;
        this.part = part;
    }

    public BatchItemRequirement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getUdfQty() {
        return udfQty;
    }

    public void setUdfQty(Double udfQty) {
        this.udfQty = udfQty;
    }

    public Double getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(Double requiredQty) {
        this.requiredQty = requiredQty;
    }

    public Date getDateAllocated() {
        return dateAllocated;
    }

    public void setDateAllocated(Date dateAllocated) {
        this.dateAllocated = dateAllocated;
    }

    public Date getDateDispensed() {
        return dateDispensed;
    }

    public void setDateDispensed(Date dateDispensed) {
        this.dateDispensed = dateDispensed;
    }

    public Short getPart() {
        return part;
    }

    public void setPart(Short part) {
        this.part = part;
    }

    public ItemReqStatusEnum getItemReqStatus() {
        return itemReqStatus;
    }

    public void setItemReqStatus(ItemReqStatusEnum itemReqStatus) {
        this.itemReqStatus = itemReqStatus;
    }

    public List<StockCardTxn> getStockCardTxnList() {
        return stockCardTxnList;
    }

    public void setStockCardTxnList(List<StockCardTxn> stockCardTxnList) {
        this.stockCardTxnList = stockCardTxnList;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public Unit getUdfQtyUnitId() {
        return udfQtyUnitId;
    }

    public void setUdfQtyUnitId(Unit udfQtyUnitId) {
        this.udfQtyUnitId = udfQtyUnitId;
    }

    public Unit getRequiredQtyUnitId() {
        return requiredQtyUnitId;
    }

    public void setRequiredQtyUnitId(Unit requiredQtyUnitId) {
        this.requiredQtyUnitId = requiredQtyUnitId;
    }

    @Override
    public String toString() {
        String p = "";
        System.out.println(part);
        if (part > 0) {
            p = " (Part " + part + ")";
        }

        return itemId + " - " + requiredQty + " " + requiredQtyUnitId + p;
    }

}
