/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server._main.entity.Product;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "manufacturing_procedure", schema="mbr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManufacturingProcedure.findAll", query = "SELECT m FROM ManufacturingProcedure m")})
public class ManufacturingProcedure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product productId;
    
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<EquipmentRequirementPackagingProcedure> equipmentRequirementPackagingProcedureList;
    
    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<EquipmentRequirementCoding> equipmentRequirementCodingList;
    
    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<EquipmentRequirementCompounding> equipmentRequirementCompoundingList;
    
    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<EquipmentRequirementEncapsulation> equipmentRequirementEncapsulationList;
    
    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<CompoundingProcedure> compoundingProcedureList;
    
    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<PackagingProcedureOperation> packagingProcedureOperationList;
    
    @OneToMany(mappedBy = "manufacturingProcedureId")
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

    
    @XmlTransient
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

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
