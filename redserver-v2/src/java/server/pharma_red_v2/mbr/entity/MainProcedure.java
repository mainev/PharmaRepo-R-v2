/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.entity;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;
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
@Table(name = "main_procedure", schema = "mbr")
@XmlRootElement
public class MainProcedure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;

    @Size(max = 1)
    @Column(name = "heading")
    private String heading;

    @Size(max = 50)
    @Column(name = "heading_title")
    private String headingTitle;

    @JoinColumn(name = "area", referencedColumnName = "id")
    @ManyToOne
    private Area area;

    @OneToMany(mappedBy = "mainProcedure")
    @OrderBy("stepNo")
    private List<SubProcedure> subProcedureList;

    public MainProcedure() {
    }

    public MainProcedure(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeadingTitle() {
        return headingTitle;
    }

    public void setHeadingTitle(String headingTitle) {
        this.headingTitle = headingTitle;
    }

    @XmlTransient
    @JsonIgnore
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<SubProcedure> getSubProcedureList() {
        return subProcedureList;
    }

    public void setSubProcedureList(List<SubProcedure> subProcedureList) {
        this.subProcedureList = subProcedureList;
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
        if (!(object instanceof MainProcedure)) {
            return false;
        }
        MainProcedure other = (MainProcedure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.mbr.entity.MainProcedure[ id=" + id + " ]";
    }

}
