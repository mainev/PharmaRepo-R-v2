/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.line_clearance;

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
@Table(name = "labeling_packg_area_line_clearance", schema = "line_clearance")
@XmlRootElement
public class LabelingPackgAreaLineClearance implements Serializable {

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

    @Size(max = 100)
    @Column(name = "checked_by")
    private String checkedBy;

    @Size(max = 100)
    @Column(name = "verified_by")
    private String verifiedBy;

    @JoinColumn(name = "area", referencedColumnName = "id")
    @ManyToOne
    private Area area;

    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private Type type;

    public LabelingPackgAreaLineClearance() {
    }

    public LabelingPackgAreaLineClearance(Integer id) {
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

    public String getCheckedBy() {
        return checkedBy;
    }

    public void setCheckedBy(String checkedBy) {
        this.checkedBy = checkedBy;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    @XmlTransient
    @JsonIgnore
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        if (!(object instanceof LabelingPackgAreaLineClearance)) {
            return false;
        }
        LabelingPackgAreaLineClearance other = (LabelingPackgAreaLineClearance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.line_clearance.LabelingPackgAreaLineClearance[ id=" + id + " ]";
    }

}
