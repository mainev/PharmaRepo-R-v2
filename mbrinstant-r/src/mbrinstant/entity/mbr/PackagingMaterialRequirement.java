/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import mbrinstant.entity.main.PackagingMaterial;
import mbrinstant.entity.main.Unit;

/**
 *
 * @author maine
 */
public class PackagingMaterialRequirement {

    @Expose
    private Integer id;
    @Expose
    private PackagingMaterial packagingMaterialId;
    @Expose
    private Double quantity;
    @Expose
    private Unit unitId;

    private double newQuantity;

    public PackagingMaterialRequirement(PackagingMaterial packagingMaterialId, double quantity, Unit unitId) {
        this.packagingMaterialId = packagingMaterialId;
        this.quantity = quantity;
        this.unitId = unitId;
    }

    public double getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(double newQuantity) {
        this.newQuantity = newQuantity;
    }

    public PackagingMaterialRequirement() {
    }

    public PackagingMaterialRequirement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PackagingMaterial getPackagingMaterialId() {
        return packagingMaterialId;
    }

    public void setPackagingMaterialId(PackagingMaterial packagingMaterialId) {
        this.packagingMaterialId = packagingMaterialId;
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

//    /*
//     public Udf getUdfId() {
//     return udfId;
//     }
//
//     public void setUdfId(Udf udfId) {
//     this.udfId = udfId;
//     }*/
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
//        if (!(object instanceof PackagingMaterialRequirement)) {
//            return false;
//        }
//        PackagingMaterialRequirement other = (PackagingMaterialRequirement) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
    @Override
    public String toString() {
        return packagingMaterialId + " - " + quantity + " " + unitId;
    }

}
