/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "compounding_procedure", schema = "mbr")
@XmlRootElement
public class CompoundingProcedure implements Serializable, Comparable<CompoundingProcedure> {

    @Expose
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Expose
    @Column(name = "step_number")
    private Short stepNumber;

    @Expose
    @Size(max = 2147483647)
    @Column(name = "instruction")
    private String instruction;

    @Expose
    @Column(name = "time_monitored")
    private Boolean timeMonitored;

    @Expose
    @Size(max = 100)
    @Column(name = "done_by")
    private String doneBy;

    @Expose
    @Size(max = 100)
    @Column(name = "checked_by")
    private String checkedBy;

    @OneToMany(mappedBy = "compoundingProcedureId")
    private List<Dosage> dosageList;

    @Expose
    @JoinColumn(name = "manufacturing_procedure_id", referencedColumnName = "id")
    @ManyToOne
    private ManufacturingProcedure manufacturingProcedureId;

    @Column(name = "requires_equipment")
    private Boolean requiresEquipment;

    @Column(name = "requires_rmreq")
    private Boolean requiresRawMaterialRequirementList;

    @Size(max = 2147483647)
    @Column(name = "remarks")
    private String remarks;

    public CompoundingProcedure() {
    }

    @XmlTransient
    public ManufacturingProcedure getManufacturingProcedureId() {
        return manufacturingProcedureId;
    }

    public void setManufacturingProcedureId(ManufacturingProcedure manufacturingProcedureId) {
        this.manufacturingProcedureId = manufacturingProcedureId;
    }

    public CompoundingProcedure(Integer id) {
        this.id = id;
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

    public Boolean getTimeMonitored() {
        return timeMonitored;
    }

    public void setTimeMonitored(Boolean timeMonitored) {
        this.timeMonitored = timeMonitored;
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

    public List<Dosage> getDosageList() {
        return dosageList;
    }

    public void setDosageList(List<Dosage> dosageList) {
        this.dosageList = dosageList;
    }

    public Boolean getRequiresEquipment() {
        return requiresEquipment;
    }

    public void setRequiresEquipment(Boolean requiresEquipment) {
        this.requiresEquipment = requiresEquipment;
    }

    public Boolean getRequiresRawMaterialRequirementList() {
        return requiresRawMaterialRequirementList;
    }

    public void setRequiresRawMaterialRequirementList(Boolean requiresRawMaterialRequirementList) {
        this.requiresRawMaterialRequirementList = requiresRawMaterialRequirementList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        if (!(object instanceof CompoundingProcedure)) {
            return false;
        }
        CompoundingProcedure other = (CompoundingProcedure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.CompoundingProcedure[ id=" + id + " ]";
    }

    @Override
    public int compareTo(CompoundingProcedure cp) {
        short comparedNumber = cp.getStepNumber();
        if (stepNumber < comparedNumber) {
            return 1;
        } else if (stepNumber == comparedNumber) {
            return 0;
        } else {
            return -1;
        }
    }

}
