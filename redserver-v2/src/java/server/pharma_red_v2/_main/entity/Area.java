/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import server.pharma_red_v2.line_clearance.CodingRoomLineClearance;
import server.pharma_red_v2.line_clearance.CompoundingAreaLineClearance;
import server.pharma_red_v2.line_clearance.DispensingAreaLineClearance;
import server.pharma_red_v2.line_clearance.FillingAreaLineClearance;
import server.pharma_red_v2.line_clearance.LabelingPackgAreaLineClearance;
import server.pharma_red_v2.mbr.entity.CodingSpecification;
import server.pharma_red_v2.mbr.entity.MainProcedure;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "area", schema = "main")
@XmlRootElement
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;

    @Expose
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "area")
    @OrderBy("stepNo")
    private List<CodingRoomLineClearance> codingRoomLineClearanceList;

    @OneToMany(mappedBy = "area")
    @OrderBy("stepNo")
    private List<CompoundingAreaLineClearance> compoundingAreaLineClearanceList;

    @OneToMany(mappedBy = "area")
    @OrderBy("stepNo")
    private List<DispensingAreaLineClearance> dispensingAreaLineClearanceList;

    @OneToMany(mappedBy = "area")
    @OrderBy("stepNo")
    private List<FillingAreaLineClearance> fillingAreaLineClearanceList;

    @OneToMany(mappedBy = "area")
    @OrderBy("stepNo")
    private List<LabelingPackgAreaLineClearance> labelingPakcgAreaLineClearanceList;

    @OneToMany(mappedBy = "area")
    @OrderBy("stepNo")
    private List<CodingSpecification> codingSpecificationList;

    @OneToMany(mappedBy = "area")
    private List<MainProcedure> mainProcedureList;

    public Area() {
    }

    public Area(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CodingRoomLineClearance> getCodingRoomLineClearanceList() {
        return codingRoomLineClearanceList;
    }

    public void setCodingRoomLineClearanceList(List<CodingRoomLineClearance> codingRoomLineClearanceList) {
        this.codingRoomLineClearanceList = codingRoomLineClearanceList;
    }

    public List<CompoundingAreaLineClearance> getCompoundingAreaLineClearanceList() {
        return compoundingAreaLineClearanceList;
    }

    public void setCompoundingAreaLineClearanceList(List<CompoundingAreaLineClearance> compoundingAreaLineClearanceList) {
        this.compoundingAreaLineClearanceList = compoundingAreaLineClearanceList;
    }

    public List<DispensingAreaLineClearance> getDispensingAreaLineClearanceList() {
        return dispensingAreaLineClearanceList;
    }

    public void setDispensingAreaLineClearanceList(List<DispensingAreaLineClearance> dispensingAreaLineClearanceList) {
        this.dispensingAreaLineClearanceList = dispensingAreaLineClearanceList;
    }

    public List<FillingAreaLineClearance> getFillingAreaLineClearanceList() {
        return fillingAreaLineClearanceList;
    }

    public void setFillingAreaLineClearanceList(List<FillingAreaLineClearance> fillingAreaLineClearanceList) {
        this.fillingAreaLineClearanceList = fillingAreaLineClearanceList;
    }

    public List<LabelingPackgAreaLineClearance> getLabelingPakcgAreaLineClearanceList() {
        return labelingPakcgAreaLineClearanceList;
    }

    public void setLabelingPakcgAreaLineClearanceList(List<LabelingPackgAreaLineClearance> labelingPakcgAreaLineClearanceList) {
        this.labelingPakcgAreaLineClearanceList = labelingPakcgAreaLineClearanceList;
    }

    public List<CodingSpecification> getCodingSpecificationList() {
        return codingSpecificationList;
    }

    public void setCodingSpecificationList(List<CodingSpecification> codingSpecificationList) {
        this.codingSpecificationList = codingSpecificationList;
    }

    public List<MainProcedure> getMainProcedureList() {
        return mainProcedureList;
    }

    public void setMainProcedureList(List<MainProcedure> mainProcedureList) {
        this.mainProcedureList = mainProcedureList;
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
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server._main.entity.Area[ id=" + id + " ]";
    }

}
