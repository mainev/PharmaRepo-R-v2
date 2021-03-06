/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.entity.mbr;

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
    private Integer id;
    private Boolean isActive;
    private List<EquipmentRequirementPackagingProcedure> equipmentRequirementPackagingProcedureList;
    private List<EquipmentRequirementCoding> equipmentRequirementCodingList;
    private List<EquipmentRequirementCompounding> equipmentRequirementCompoundingList;
    private List<EquipmentRequirementEncapsulation> equipmentRequirementEncapsulationList;
    private List<CompoundingProcedure> compoundingProcedureList;
    private List<PackagingProcedureOperation> packagingProcedureOperationList;
    private List<PackagingProcedure> packagingProcedureList;

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

    /*
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }
*/
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    public List<EquipmentRequirementPackagingProcedure> getEquipmentRequirementPackagingProcedureList() {
        return equipmentRequirementPackagingProcedureList;
    }

    public void setEquipmentRequirementPackagingProcedureList(List<EquipmentRequirementPackagingProcedure> equipmentRequirementPackagingProcedureList) {
        this.equipmentRequirementPackagingProcedureList = equipmentRequirementPackagingProcedureList;
    }

  
    public List<EquipmentRequirementCoding> getEquipmentRequirementCodingList() {
        return equipmentRequirementCodingList;
    }

    public void setEquipmentRequirementCodingList(List<EquipmentRequirementCoding> equipmentRequirementCodingList) {
        this.equipmentRequirementCodingList = equipmentRequirementCodingList;
    }

    public List<EquipmentRequirementCompounding> getEquipmentRequirementCompoundingList() {
        return equipmentRequirementCompoundingList;
    }

    public void setEquipmentRequirementCompoundingList(List<EquipmentRequirementCompounding> equipmentRequirementCompoundingList) {
        this.equipmentRequirementCompoundingList = equipmentRequirementCompoundingList;
    }

   
    public List<EquipmentRequirementEncapsulation> getEquipmentRequirementEncapsulationList() {
        return equipmentRequirementEncapsulationList;
    }

    public void setEquipmentRequirementEncapsulationList(List<EquipmentRequirementEncapsulation> equipmentRequirementEncapsulationList) {
        this.equipmentRequirementEncapsulationList = equipmentRequirementEncapsulationList;
    }

   
    public List<CompoundingProcedure> getCompoundingProcedureList() {
        return compoundingProcedureList;
    }

    public void setCompoundingProcedureList(List<CompoundingProcedure> compoundingProcedureList) {
        this.compoundingProcedureList = compoundingProcedureList;
    }

  
    public List<PackagingProcedureOperation> getPackagingProcedureOperationList() {
        return packagingProcedureOperationList;
    }

    public void setPackagingProcedureOperationList(List<PackagingProcedureOperation> packagingProcedureOperationList) {
        this.packagingProcedureOperationList = packagingProcedureOperationList;
    }

    
    public List<PackagingProcedure> getPackagingProcedureList() {
        return packagingProcedureList;
    }

    public void setPackagingProcedureList(List<PackagingProcedure> packagingProcedureList) {
        this.packagingProcedureList = packagingProcedureList;
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
