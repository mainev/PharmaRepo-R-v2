/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.sqlsvr_copy;

import com.google.gson.annotations.Expose;

/**
 *
 * @author maine
 */
public class ItemC {

    @Expose
    private Integer id;
    @Expose
    private String itemCd;
    @Expose
    private String descs;
    @Expose
    private String remarks;
    @Expose
    private ItemCategoryC itemCategoryId;
    @Expose
    private ItemTypeC itemTypeId;

    public ItemC() {
    }

    public ItemC(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemCd() {
        return itemCd;
    }

    public void setItemCd(String itemCd) {
        this.itemCd = itemCd;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ItemCategoryC getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(ItemCategoryC itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public ItemTypeC getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(ItemTypeC itemTypeId) {
        this.itemTypeId = itemTypeId;
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
        if (!(object instanceof ItemC)) {
            return false;
        }
        ItemC other = (ItemC) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Item[ id=" + id + " ]";
    }

}
