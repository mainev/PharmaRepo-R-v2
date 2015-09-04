/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.line_clearance;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "type", schema = "line_clearance")
@XmlRootElement
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;

    @Size(max = 5)
    @Column(name = "code")
    private String code;

    @Size(max = 50)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "type")
    private List<DispensingAreaLineClearance> dispensingAreaLineClearanceList;
    @OneToMany(mappedBy = "type")
    private List<CompoundingAreaLineClearance> compoundingAreaLineClearanceList;
    @OneToMany(mappedBy = "type")
    private List<FillingAreaLineClearance> fillingAreaLineClearanceList;
    @OneToMany(mappedBy = "type")
    private List<CodingRoomLineClearance> codingRoomLineClearanceList;
    @OneToMany(mappedBy = "type")
    private List<LabelingPackgAreaLineClearance> labelingPackgAreaLineClearanceList;

    public Type() {
    }

    public Type(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    @JsonIgnore
    public List<DispensingAreaLineClearance> getDispensingAreaLineClearanceList() {
        return dispensingAreaLineClearanceList;
    }

    public void setDispensingAreaLineClearanceList(List<DispensingAreaLineClearance> dispensingAreaLineClearanceList) {
        this.dispensingAreaLineClearanceList = dispensingAreaLineClearanceList;
    }

    @XmlTransient
    @JsonIgnore
    public List<CompoundingAreaLineClearance> getCompoundingAreaLineClearanceList() {
        return compoundingAreaLineClearanceList;
    }

    public void setCompoundingAreaLineClearanceList(List<CompoundingAreaLineClearance> compoundingAreaLineClearanceList) {
        this.compoundingAreaLineClearanceList = compoundingAreaLineClearanceList;
    }

    @XmlTransient
    @JsonIgnore
    public List<FillingAreaLineClearance> getFillingAreaLineClearanceList() {
        return fillingAreaLineClearanceList;
    }

    public void setFillingAreaLineClearanceList(List<FillingAreaLineClearance> fillingAreaLineClearanceList) {
        this.fillingAreaLineClearanceList = fillingAreaLineClearanceList;
    }

    @XmlTransient
    @JsonIgnore
    public List<CodingRoomLineClearance> getCodingRoomLineClearanceList() {
        return codingRoomLineClearanceList;
    }

    public void setCodingRoomLineClearanceList(List<CodingRoomLineClearance> codingRoomLineClearanceList) {
        this.codingRoomLineClearanceList = codingRoomLineClearanceList;
    }

    @XmlTransient
    @JsonIgnore
    public List<LabelingPackgAreaLineClearance> getLabelingPackgAreaLineClearanceList() {
        return labelingPackgAreaLineClearanceList;
    }

    public void setLabelingPackgAreaLineClearanceList(List<LabelingPackgAreaLineClearance> labelingPackgAreaLineClearanceList) {
        this.labelingPackgAreaLineClearanceList = labelingPackgAreaLineClearanceList;
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
        if (!(object instanceof Type)) {
            return false;
        }
        Type other = (Type) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.line_clearance.Type[ id=" + id + " ]";
    }

}
