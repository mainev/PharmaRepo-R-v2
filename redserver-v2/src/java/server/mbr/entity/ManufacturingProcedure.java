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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server._main.entity.Product;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "manufacturing_procedure", schema = "mbr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManufacturingProcedure.findAll", query = "SELECT m FROM ManufacturingProcedure m")})
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
    private List<CompoundingProcedure> compoundingProcedureList;
    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<PackagingProcedureOperation> packagingProcedureOperationList;
    @OneToMany(mappedBy = "manufacturingProcedureId")
    private List<PackagingProcedure> packagingProcedureList;


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
