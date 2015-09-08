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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "packaging_procedure", schema = "mbr")
@XmlRootElement
public class PackagingProcedure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Expose
    @Column(name = "step_number")
    private Short stepNumber;

    @Expose
    @Size(max = 1000)
    @Column(name = "instruction")
    private String instruction;

    @Expose
    @Column(name = "part")
    private Short part;

    @Expose
    @JoinColumn(name = "manufacturing_procedure_id", referencedColumnName = "id")
    @ManyToOne
    private ManufacturingProcedure manufacturingProcedureId;

    @Expose
    @Column(name = "done_by")
    @Size(max = 100)
    private String doneBy;

    @Expose
    @Column(name = "checked_by")
    @Size(max = 100)
    private String checkedBy;

    public PackagingProcedure() {
    }

    public PackagingProcedure(Integer id) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Short stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Short getPart() {
        return part;
    }

    public void setPart(Short part) {
        this.part = part;
    }

    @XmlTransient
    public ManufacturingProcedure getManufacturingProcedureId() {
        return manufacturingProcedureId;
    }

    public void setManufacturingProcedureId(ManufacturingProcedure manufacturingProcedureId) {
        this.manufacturingProcedureId = manufacturingProcedureId;
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
        if (!(object instanceof PackagingProcedure)) {
            return false;
        }
        PackagingProcedure other = (PackagingProcedure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.PackagingProcedureOperation[ id=" + id + " ]";
    }

}
