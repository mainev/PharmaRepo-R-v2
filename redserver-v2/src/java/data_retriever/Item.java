/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_retriever;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author maine
 */
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemCd;
    private String descs;
    private String uom;
    private String itemTypeCd;
    private String itemCategoryCd;
    private String remarks;
    private float standardCost;
    private float latestCost;
    private float itemWeight;
    private String auditUser;
    private Date auditDate;
    private String itemClassCd;
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
