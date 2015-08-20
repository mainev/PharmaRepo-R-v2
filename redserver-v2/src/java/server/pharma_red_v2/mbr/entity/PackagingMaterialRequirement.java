/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.entity;

import com.google.gson.annotations.Expose;
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
import server.pharma_red_v2._main.entity.Unit;
import server.pharma_red_v2.sqlsvr_copy.entity.Item;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "packaging_material_requirement", schema = "mbr")
@XmlRootElement
public class PackagingMaterialRequirement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Expose
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne
    private Item itemId;

    @Expose
    @Column(name = "quantity")
    private Double quantity;

    @Expose
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    @ManyToOne
    private Unit unitId;

    @Expose
    @JoinColumn(name = "udf_id", referencedColumnName = "id")
    @ManyToOne
    private Udf udfId;

    public PackagingMaterialRequirement() {
    }

    @XmlTransient
    public Udf getUdfId() {
        return udfId;
    }

    public void setUdfId(Udf udfId) {
        this.udfId = udfId;
    }

    public PackagingMaterialRequirement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
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
        if (!(object instanceof PackagingMaterialRequirement)) {
            return false;
        }
        PackagingMaterialRequirement other = (PackagingMaterialRequirement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.PackagingMaterialRequirement[ id=" + id + " ]";
    }

}
