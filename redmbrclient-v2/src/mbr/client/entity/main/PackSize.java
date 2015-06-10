/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbr.client.entity.main;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maine
 */
@XmlRootElement
public class PackSize implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Short quantity;
    private Container containerId;
    private Unit unitId;

    public PackSize() {
    }

    public PackSize(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }


    public Container getContainerId() {
        return containerId;
    }

    public void setContainerId(Container containerId) {
        this.containerId = containerId;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
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
        if (!(object instanceof PackSize)) {
            return false;
        }
        PackSize other = (PackSize) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+quantity+" " + unitId+" per "+containerId;
    }
    
}
