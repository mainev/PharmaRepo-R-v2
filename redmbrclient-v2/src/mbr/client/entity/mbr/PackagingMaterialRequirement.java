/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.entity.mbr;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import mbr.client.entity.main.PackagingMaterial;
import mbr.client.entity.main.Unit;

/**
 *
 * @author maine
 */
@XmlRootElement
public class PackagingMaterialRequirement implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private PackagingMaterial packagingMaterialId;
    private Double quantity;
    private Unit unitId;

    public PackagingMaterialRequirement() {
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

    public PackagingMaterial getPackagingMaterialId() {
        return packagingMaterialId;
    }

    public void setPackagingMaterialId(PackagingMaterial packagingMaterialId) {
        this.packagingMaterialId = packagingMaterialId;
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
