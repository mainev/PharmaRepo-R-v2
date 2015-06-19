/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mbrinstant.entity.main.RawMaterial;
import mbrinstant.entity.main.Unit;

/**
 *
 * @author maine
 */
@XmlRootElement
public class RawMaterialRequirement implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private RawMaterial rawMaterialId;
    private Double quantity;
    private Unit unitId;

    @XmlTransient
    private double newQuantity;
    @XmlTransient
    private String newUnit;
    
    public RawMaterialRequirement(RawMaterial rawMaterialId, double quantity, Unit unitId){
        this.rawMaterialId = rawMaterialId;
        this.quantity = quantity;
        this.unitId = unitId;
    }

    public double getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(double newQuantity) {
        this.newQuantity = newQuantity;
    }

    public String getNewUnit() {
        return newUnit;
    }

    public void setNewUnit(String newUnit) {
        this.newUnit = newUnit;
    }

    public RawMaterialRequirement() {
    }

    public RawMaterialRequirement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RawMaterial getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(RawMaterial rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
    }

    /*

     public Udf getUdfId() {
     return udfId;
     }

     public void setUdfId(Udf udfId) {
     this.udfId = udfId;
     }*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RawMaterialRequirement)) {
            return false;
        }
        RawMaterialRequirement other = (RawMaterialRequirement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return rawMaterialId + " - " + quantity + " " + unitId;
    }

}
