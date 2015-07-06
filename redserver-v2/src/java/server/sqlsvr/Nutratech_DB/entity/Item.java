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
@Table(name = "item")
@XmlRootElement
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "item_cd")
    @Size(max = 20)
    private String itemCd;

    @Column(name = "descs")
    @Size(max = 255)
    private String descs;
    
    @Column(name = "uom")
    @Size(max = 10)
    private String uom;
    
    @Column(name = "item_type_cd")
    @Size(max = 10)
    private String itemTypeCd;
    
    @Column(name = "item_category_cd")
    @Size(max = 10)
    private String itemCategoryCd;
    
    @Column(name = "remarks")
    @Size(max = 255)
    private String remarks;
    
    @Column(name = "standard_cost")
    private float standardCost;
    
    @Column(name = "latest_cost")
    private float latestCost;
    
    @Column(name = "item_weight")
    private float itemWeight;
    
    @Column(name = "audit_user")
    @Size(max = 20)
    private String auditUser;
    
    @Column(name = "audit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditDate;
    
    @Column(name = "item_class_cd")
    @Size(max = 10)
    private String itemClassCd;
    
    @Column(name ="status")
    private boolean status;

    public Item() {
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getItemTypeCd() {
        return itemTypeCd;
    }

    public void setItemTypeCd(String itemTypeCd) {
        this.itemTypeCd = itemTypeCd;
    }

    public String getItemCategoryCd() {
        return itemCategoryCd;
    }

    public void setItemCategoryCd(String itemCategoryCd) {
        this.itemCategoryCd = itemCategoryCd;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public float getStandardCost() {
        return standardCost;
    }

    public void setStandardCost(float standardCost) {
        this.standardCost = standardCost;
    }

    public float getLatestCost() {
        return latestCost;
    }

    public void setLatestCost(float latestCost) {
        this.latestCost = latestCost;
    }

    public float getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(float itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getItemClassCd() {
        return itemClassCd;
    }

    public void setItemClassCd(String itemClassCd) {
        this.itemClassCd = itemClassCd;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    public String getItemCd() {
        return itemCd;
    }

    public void setItemCd(String itemCd) {
        this.itemCd = itemCd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCd != null ? itemCd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.itemCd == null && other.itemCd != null) || (this.itemCd != null && !this.itemCd.equals(other.itemCd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.sqlsvr.Nutratech_DB.entity.Item[ id=" + itemCd + " ]";
    }
    
}
