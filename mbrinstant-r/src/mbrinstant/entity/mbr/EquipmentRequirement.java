/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import mbrinstant.entity.ProcedureCategory;
import mbrinstant.entity.main.Equipment;

/**
 *
 * @author maine
 */
public class EquipmentRequirement {

    // private static final long serialVersionUID = 1L;
    @Expose
    private Integer id;
    @Expose
    private Equipment equipmentId;
    @Expose
    private ProcedureCategory procedureCategory;

    public EquipmentRequirement(Equipment equipmentId, ProcedureCategory cat) {
        this.equipmentId = equipmentId;
        this.procedureCategory = cat;
    }

    public EquipmentRequirement() {
    }

    public EquipmentRequirement(Integer id) {
        this.id = id;
    }

    public ProcedureCategory getProcedureCategory() {
        return procedureCategory;
    }

    public void setProcedureCategory(ProcedureCategory procedureCategory) {
        this.procedureCategory = procedureCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Equipment getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Equipment equipmentId) {
        this.equipmentId = equipmentId;
    }

    /*
     public ManufacturingProcedure getManufacturingProcedureId() {
     return manufacturingProcedureId;
     }

     public void setManufacturingProcedureId(ManufacturingProcedure manufacturingProcedureId) {
     this.manufacturingProcedureId = manufacturingProcedureId;
     }
     */
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
//        if (!(object instanceof EquipmentRequirement)) {
//            return false;
//        }
//        EquipmentRequirement other = (EquipmentRequirement) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
    @Override
    public String toString() {
        return "server.mbr.entity.EquipmentRequirementCoding[ id=" + id + " ]";
    }

}
