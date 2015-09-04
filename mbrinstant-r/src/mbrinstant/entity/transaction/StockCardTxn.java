/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.transaction;

import com.google.gson.annotations.Expose;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.BatchItemRequirement;
import mbrinstant.entity.sqlsvr_copy.StockCardC;

/**
 *
 * @author maine
 */
public class StockCardTxn {

    @Expose
    private Integer id;
    @Expose
    private Double qty;
    @Expose
    private Unit unitId;
    @Expose
    private TransactionType transactionTypeId;

    //for audit entry
    @Expose
    private int stock_card_id;
    @Expose
    private short unit_id;

    private StockCardC tempStockCard;//must not be serialized
    private BatchItemRequirement tempBatchItem;

    public StockCardTxn() {
    }

    public StockCardC getTempStockCard() {
        return tempStockCard;
    }

    public void setTempStockCard(StockCardC tempStockCard) {
        this.tempStockCard = tempStockCard;
    }

    public StockCardTxn(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
    }

    public TransactionType getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(TransactionType transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public int getStock_card_id() {
        return stock_card_id;
    }

    public void setStock_card_id(int stock_card_id) {
        this.stock_card_id = stock_card_id;
    }

    public short getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(short unit_id) {
        this.unit_id = unit_id;
    }

    public BatchItemRequirement getTempBatchItem() {
        return tempBatchItem;
    }

    public void setTempBatchItem(BatchItemRequirement tempBatchItem) {
        this.tempBatchItem = tempBatchItem;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof StockCardTxn)) {
//            return false;
//        }
//        StockCardTxn other = (StockCardTxn) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
    @Override
    public String toString() {
        return qty + " " + unitId;
    }

}
