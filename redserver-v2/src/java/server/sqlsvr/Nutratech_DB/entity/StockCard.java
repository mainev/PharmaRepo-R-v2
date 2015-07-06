/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.sqlsvr.Nutratech_DB.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "stock_card")
@XmlRootElement
public class StockCard implements Serializable {
    //unique identifier for this entity is the itemCd and auditDate
    //change this if necessary
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "item_cd")
    private String itemCd;
    
    @Id
    @Column(name = "audit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditDate;
    
    @Column(name = "mfg_date")
    @Temporal(TemporalType.DATE)
    private Date mfgDate;
    
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    
    @Column(name ="inout_mode")
    @Size(max = 1)
    private char inOutMode;
    
    @Column(name = "status")
    @Size(max = 10)
    private String status;
    
    @Column(name = "qty")
    private float qty;
    
    @Column(name = "uom")
    @Size(max = 10)
    private String uom;

    @Column(name = "company_cd")
    @Size(max = 5)
    private String companyCd;

    public StockCard() {
    }

    public Date getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    
    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public char getInOutMode() {
        return inOutMode;
    }

    public void setInOutMode(char inOutMode) {
        this.inOutMode = inOutMode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    
    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    
    
    public String getItemCd() {
        return itemCd;
    }

    public void setItemCd(String itemCd) {
        this.itemCd = itemCd;
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
//        if (!(object instanceof StockCard)) {
//            return false;
//        }
//        StockCard other = (StockCard) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "server.sqlsvr.Nutratech_DB.entity.StockCard[ id=" + itemCd + " ]";
    }
    
}
