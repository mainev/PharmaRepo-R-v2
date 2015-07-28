/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.transaction;

import com.google.gson.annotations.Expose;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Mbr;
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
    private Mbr mbrId;

    private StockCardC stockCard;//must not be serialized

    public StockCardTxn() {
    }

    public StockCardC getStockCard() {
        return stockCard;
    }

    public void setStockCard(StockCardC stockCard) {
        this.stockCard = stockCard;
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

    public Mbr getMbrId() {
        return mbrId;
    }

    public void setMbrId(Mbr mbrId) {
        this.mbrId = mbrId;
    }

//    public int getStkId() {
//        return stkId;
//    }
//
//    public void setStkId(int stkId) {
//        this.stkId = stkId;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockCardTxn)) {
            return false;
        }
        StockCardTxn other = (StockCardTxn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return qty + " " + unitId;
    }

}
