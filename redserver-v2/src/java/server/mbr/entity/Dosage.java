/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.mbr.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "dosage", schema="mbr")
@XmlRootElement
public class Dosage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
   
    @JoinColumn(name="raw_material_requirement_id", referencedColumnName="id")
    @ManyToOne
    private RawMaterialRequirement rawMaterialRequirementId;
    
    /*
    @Column(name = "quantity")
    private Double quantity;
    
  /*
    @Column(name = "unit_id")
    private Short unitId;
    */
    
    @Column(name = "percent_multiplier")
    private Double percentMultiplier;
    
    
    @JoinColumn(name = "compounding_procedure_id", referencedColumnName = "id")
    @ManyToOne
    private CompoundingProcedure compoundingProcedureId;

    public Dosage() {
    }

    
    @XmlTransient
    public CompoundingProcedure getCompoundingProcedureId() {
        return compoundingProcedureId;
    }

    public void setCompoundingProcedureId(CompoundingProcedure compoundingProcedureId) {
        this.compoundingProcedureId = compoundingProcedureId;
    }
    

    public Dosage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RawMaterialRequirement getRawMaterialRequirementId() {
        return rawMaterialRequirementId;
    }

    public void setRawMaterialRequirementId(RawMaterialRequirement rawMaterialRequirementId) {
        this.rawMaterialRequirementId = rawMaterialRequirementId;
    }

   /*
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /*
    public Short getUnitId() {
        return unitId;
    }

    public void setUnitId(Short unitId) {
        this.unitId = unitId;
    }*/

    public Double getPercentMultiplier() {
        return percentMultiplier;
    }

    public void setPercentMultiplier(Double percentMultiplier) {
        this.percentMultiplier = percentMultiplier;
    }
    
    /*

    public CompoundingProcedure getCompoundingProcedureId() {
        return compoundingProcedureId;
    }

    public void setCompoundingProcedureId(CompoundingProcedure compoundingProcedureId) {
        this.compoundingProcedureId = compoundingProcedureId;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosage)) {
            return false;
        }
        Dosage other = (Dosage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.Dosage[ id=" + id + " ]";
    }
    
}
