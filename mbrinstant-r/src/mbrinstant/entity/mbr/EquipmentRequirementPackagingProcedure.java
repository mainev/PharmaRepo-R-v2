/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import mbrinstant.entity.main.Equipment;

/**
 *
 * @author maine
 */
@XmlRootElement
public class EquipmentRequirementPackagingProcedure implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Equipment equipmentId;
    public EquipmentRequirementPackagingProcedure() {
    }

    public EquipmentRequirementPackagingProcedure(Integer id) {
        this.id = id;
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
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipmentRequirementPackagingProcedure)) {
            return false;
        }
        EquipmentRequirementPackagingProcedure other = (EquipmentRequirementPackagingProcedure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.EquipmentRequirementPackagingProcedure[ id=" + id + " ]";
    }
    
}
