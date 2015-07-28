/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.transaction.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server.pharma_red_v2._main.entity.Unit;
import server.pharma_red_v2.mbr.entity.Mbr;
import server.pharma_red_v2.sqlsvr_copy.entity.StockCardC;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "stock_card_txn", schema = "transaction")
@XmlRootElement
public class StockCardTxn implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @JoinColumn(name = "stock_card_id", referencedColumnName = "id")
    @ManyToOne
    private StockCardC stockCardId;
    
    @Column(name = "qty")
    private Double qty;
    
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    @ManyToOne
    private Unit unitId;
    
 
    @JoinColumn(name = "mbr_id", referencedColumnName = "id")
    @ManyToOne
    private Mbr mbrId;

    public StockCardTxn() {
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

    @XmlTransient
    public StockCardC getStockCardId() {
        return stockCardId;
    }

    public void setStockCardId(StockCardC stockCardId) {
        this.stockCardId = stockCardId;
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
        return "server.pharma_red_v2.transaction.entity.StockCardTxn[ id=" + id + " ]";
    }

}
