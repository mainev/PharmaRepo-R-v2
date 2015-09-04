/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import java.io.Serializable;

/**
 *
 * @author maine
 */
public class CodingSpecification implements Serializable {

    private Integer id;

    private Short stepNo;
    private String instruction;
    private Boolean requiresEquipment;
    private String doneBy;
    private String checkedBy;

    public CodingSpecification() {
    }

    public CodingSpecification(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getStepNo() {
        return stepNo;
    }

    public void setStepNo(Short stepNo) {
        this.stepNo = stepNo;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Boolean getRequiresEquipment() {
        return requiresEquipment;
    }

    public void setRequiresEquipment(Boolean requiresEquipment) {
        this.requiresEquipment = requiresEquipment;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodingSpecification)) {
            return false;
        }
        CodingSpecification other = (CodingSpecification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.mbr.entity.CodingSpec[ id=" + id + " ]";
    }

}
