/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import java.io.Serializable;

/**
 *
 * @author maine
 */
//@XmlRootElement
public class Dosage implements Serializable {

    @Expose
    private Integer id;
    @Expose
    private RawMaterialRequirement rawMaterialRequirementId;
    @Expose
    private Double percentMultiplier;

    public Dosage(RawMaterialRequirement rawMaterialRequirementId, double percentMultiplier) {
        this.rawMaterialRequirementId = rawMaterialRequirementId;
        this.percentMultiplier = percentMultiplier;
    }

    public Dosage() {
    }

    public Dosage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RawMaterialRequirement getRawMaterialRequirementId() {
        return rawMaterialRequirementId;
    }

    public void setRawMaterialRequirementId(RawMaterialRequirement rawMaterialRequirementId) {
        this.rawMaterialRequirementId = rawMaterialRequirementId;
    }

    public Double getQuantity() {
        return rawMaterialRequirementId.getNewQuantity() * percentMultiplier;
        // return quantity;
    }

//    public void setQuantity(Double quantity) {
//        this.quantity = quantity;
//    }
    public String getUnit() {
        return rawMaterialRequirementId.getNewUnit();
    }

//    public void setUnit(String unit) {
//        this.unit = unit;
//    }
    public Double getPercentMultiplier() {
        return percentMultiplier;
    }

    public void setPercentMultiplier(Double percentMultiplier) {
        this.percentMultiplier = percentMultiplier;
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
        if (!(object instanceof Dosage)) {
            return false;
        }
        Dosage other = (Dosage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rawMaterialRequirementId + " qty: " + percentMultiplier;
    }

}
