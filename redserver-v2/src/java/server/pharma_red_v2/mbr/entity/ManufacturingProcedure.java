/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server.pharma_red_v2._main.entity.Product;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "manufacturing_procedure", schema = "mbr")
@XmlRootElement
public class ManufacturingProcedure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private Product productId;

    @XmlTransient
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<EquipmentRequirement> equipmentRequirementList;

    @OneToMany(mappedBy = "manufacturingProcedureId")
    @OrderBy("stepNumber")
    private List<CompoundingProcedure> compoundingProcedureList;

    @OneToMany(mappedBy = "manufacturingProcedureId")
    @OrderBy("stepNumber")
    private List<PackagingProcedure> packagingProcedureOperationList;

    @OneToMany(mappedBy = "manufacturingProcedureId")
    @OrderBy("stepNumber")
    private List<PowderFillingProcedure> powderFillingProcedureList;

    @OneToMany(mappedBy = "manufacturingProcedureId")
    @OrderBy("stepNumber")
    private List<BottlingProcedure> bottlingProcedureList;

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

    public List<PackagingProcedure> getPackagingProcedureOperationList() {
        return packagingProcedureOperationList;
    }

    public void setPackagingProcedureOperationList(List<PackagingProcedure> packagingProcedureOperationList) {
        this.packagingProcedureOperationList = packagingProcedureOperationList;
    }

    public List<PowderFillingProcedure> getPowderFillingProcedureList() {
        return powderFillingProcedureList;
    }

    public void setPowderFillingProcedureList(List<PowderFillingProcedure> powderFillingProcedureList) {
        this.powderFillingProcedureList = powderFillingProcedureList;
    }

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
