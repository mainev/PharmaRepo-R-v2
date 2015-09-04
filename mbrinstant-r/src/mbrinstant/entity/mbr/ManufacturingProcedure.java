/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maine
 */
@XmlRootElement
public class ManufacturingProcedure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    private Integer id;
    @Expose(serialize = false)
    private List<EquipmentRequirement> equipmentRequirementList;
    @Expose(serialize = false)
    private List<CompoundingProcedure> compoundingProcedureList;
    @Expose(serialize = false)
    private List<PackagingOperation> packagingProcedureOperationList;
    @Expose(serialize = false)
    private List<PowderFillingProcedure> powderFillingProcedureList;
    @Expose(serialize = false)
    private List<BottlingProcedure> bottlingProcedureList;

    public ManufacturingProcedure() {
    }

    public ManufacturingProcedure(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BottlingProcedure> getBottlingProcedureList() {
        return bottlingProcedureList;
    }

    public void setBottlingProcedureList(List<BottlingProcedure> bottlingProcedureList) {
        this.bottlingProcedureList = bottlingProcedureList;
    }

    public List<EquipmentRequirement> getEquipmentRequirementList() {
        return equipmentRequirementList;
    }

    public void setEquipmentRequirementList(List<EquipmentRequirement> equipmentRequirementList) {
        this.equipmentRequirementList = equipmentRequirementList;
    }

    public List<CompoundingProcedure> getCompoundingProcedureList() {
        return compoundingProcedureList;
    }

    public void setCompoundingProcedureList(List<CompoundingProcedure> compoundingProcedureList) {
        this.compoundingProcedureList = compoundingProcedureList;
    }

    public List<PackagingOperation> getPackagingProcedureOperationList() {
        return packagingProcedureOperationList;
    }

    public void setPackagingProcedureOperationList(List<PackagingOperation> packagingProcedureOperationList) {
        this.packagingProcedureOperationList = packagingProcedureOperationList;
    }

    public List<PowderFillingProcedure> getPowderFillingProcedureList() {
        return powderFillingProcedureList;
    }

    public void setPowderFillingProcedureList(List<PowderFillingProcedure> powderFillingProcedureList) {
        this.powderFillingProcedureList = powderFillingProcedureList;
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
        if (!(object instanceof ManufacturingProcedure)) {
            return false;
        }
        ManufacturingProcedure other = (ManufacturingProcedure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.ManufacturingProcedure[ id=" + id + " ]";
    }

}
