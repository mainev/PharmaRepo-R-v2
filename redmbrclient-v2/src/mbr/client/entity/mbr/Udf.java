/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.entity.mbr;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import mbr.client.entity.main.Unit;

/**
 *
 * @author maine
 */
@XmlRootElement
public class Udf implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Double content;
    private Unit unitId;
    private Boolean isActive;
    private List<PackagingMaterialRequirement> packagingMaterialRequirementList;
    private List<RawMaterialRequirement> rawMaterialRequirementList;

    public Udf() {
    }

    public Udf(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getContent() {
        return content;
    }

    public void setContent(Double content) {
        this.content = content;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
    }

    /*
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
*/
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    
    public List<PackagingMaterialRequirement> getPackagingMaterialRequirementList() {
        return packagingMaterialRequirementList;
    }

    public void setPackagingMaterialRequirementList(List<PackagingMaterialRequirement> packagingMaterialRequirementList) {
        this.packagingMaterialRequirementList = packagingMaterialRequirementList;
    }

 
    public List<RawMaterialRequirement> getRawMaterialRequirementList() {
        return rawMaterialRequirementList;
    }

    public void setRawMaterialRequirementList(List<RawMaterialRequirement> rawMaterialRequirementList) {
        this.rawMaterialRequirementList = rawMaterialRequirementList;
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
        if (!(object instanceof Udf)) {
            return false;
        }
        Udf other = (Udf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.Udf[ id=" + id + " ]";
    }
    
}
