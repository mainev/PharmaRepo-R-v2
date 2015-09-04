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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import server.pharma_red_v2._main.entity.Area;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "coding_spec", schema = "mbr")
@XmlRootElement
public class CodingSpecification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @Column(name = "step_no")
    private Short stepNo;

    @Size(max = 2147483647)
    @Column(name = "instruction")
    private String instruction;

    @Column(name = "requires_equipment")
    private Boolean requiresEquipment;

    @Size(max = 100)
    @Column(name = "done_by")
    private String doneBy;

    @Size(max = 100)
    @Column(name = "checked_by")
    private String checkedBy;

    @JoinColumn(name = "area", referencedColumnName = "id")
    @ManyToOne
    private Area area;

    public CodingSpecification() {
    }

    public CodingSpecification(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getStepNo() {
        return stepNo;
    }

    public void setStepNo(Short stepNo) {
        this.stepNo = stepNo;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Boolean getRequiresEquipment() {
        return requiresEquipment;
    }

    public void setRequiresEquipment(Boolean requiresEquipment) {
        this.requiresEquipment = requiresEquipment;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    @XmlTransient
    @JsonIgnore
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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
        if (!(object instanceof CodingSpecification)) {
            return false;
        }
        CodingSpecification other = (CodingSpecification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.mbr.entity.CodingSpec[ id=" + id + " ]";
    }

}
