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
public class WarehouseC {

    @Expose
    private Short id;
    @Expose
    private String code;
    @Expose
    private String descs;

    public WarehouseC() {
    }

    public WarehouseC(Short id) {
        this.id = id;
    }

    public WarehouseC(Short id, String code) {
        this.id = id;
        this.code = code;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
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
        if (!(object instanceof WarehouseC)) {
            return false;
        }
        WarehouseC other = (WarehouseC) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Warehouse[ id=" + id + " ]";
    }

}
