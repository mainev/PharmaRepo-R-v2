/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;

/**
 *
 * @author maine
 */
public class PowderFillingProcedure {

    @Expose
    private Integer id;
    @Expose
    private Short stepNumber;
    @Expose
    private String instruction;
    @Expose
    private boolean requiresEquipment;
    @Expose
    private String doneBy;
    @Expose
    private String checkedBy;

    public PowderFillingProcedure(String instruction, boolean requiresEquipment, String doneBy, String checkedBy) {
        this.instruction = instruction;
        this.requiresEquipment = requiresEquipment;
        this.doneBy = doneBy;
        this.checkedBy = checkedBy;
    }

    public PowderFillingProcedure() {
    }

    public PowderFillingProcedure(Integer id) {
        this.id = id;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public boolean isRequiresEquipment() {
        return requiresEquipment;
    }

    public void setRequiresEquipment(boolean requiresEquipment) {
        this.requiresEquipment = requiresEquipment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Short stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /*
     public ManufacturingProcedure getManufacturingProcedureId() {
     return manufacturingProcedureId;
     }

     public void setManufacturingProcedureId(ManufacturingProcedure manufacturingProcedureId) {
     this.manufacturingProcedureId = manufacturingProcedureId;
     }*/
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
//        if (!(object instanceof PowderFillingProcedure)) {
//            return false;
//        }
//        PowderFillingProcedure other = (PowderFillingProcedure) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
    @Override
    public String toString() {
        return "server.mbr.entity.PackagingProcedure[ id=" + id + " ]";
    }

}
