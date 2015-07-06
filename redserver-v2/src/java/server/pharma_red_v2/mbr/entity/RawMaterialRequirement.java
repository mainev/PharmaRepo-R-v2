/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server.pharma_red_v2._main.entity.RawMaterial;
import server.pharma_red_v2._main.entity.Unit;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "raw_material_requirement", schema="mbr")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RawMaterialRequirement.findAll", query = "SELECT r FROM RawMaterialRequirement r")})
public class RawMaterialRequirement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
   @JoinColumn(name = "raw_material_id", referencedColumnName = "id")
   @ManyToOne
    private RawMaterial rawMaterialId;
    
   @Column(name = "quantity")
    private Double quantity;
   
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    @ManyToOne
    private Unit unitId;
    
    
    @JoinColumn(name = "udf_id", referencedColumnName = "id")
    @ManyToOne
    private Udf udfId;
    
    @Column(name = "part")
    private short part;

    public RawMaterialRequirement() {
    }

    public short getPart() {
        return part;
    }

    public void setPart(short part) {
        this.part = part;
    }

    
    
    @XmlTransient
    public Udf getUdfId() {
        return udfId;
    }

    public void setUdfId(Udf udfId) {
        this.udfId = udfId;
    }
    
    

    public RawMaterialRequirement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RawMaterial getRawMaterialId() {
        return rawMaterialId;
    }

    public void setRawMaterialId(RawMaterial rawMaterialId) {
        this.rawMaterialId = rawMaterialId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
    }
    
    /*

    public Udf getUdfId() {
        return udfId;
    }

    public void setUdfId(Udf udfId) {
        this.udfId = udfId;
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
        if (!(object instanceof RawMaterialRequirement)) {
            return false;
        }
        RawMaterialRequirement other = (RawMaterialRequirement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.RawMaterialRequirement[ id=" + id + " ]";
    }
    
}
