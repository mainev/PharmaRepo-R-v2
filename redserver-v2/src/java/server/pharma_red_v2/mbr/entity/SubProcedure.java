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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sub_procedure", schema = "mbr")
@XmlRootElement
public class SubProcedure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Size(max = 200)
    @Column(name = "sub_procedure")
    private String subProcedure;

    @JoinColumn(name = "main_procedure", referencedColumnName = "id")
    @ManyToOne
    private MainProcedure mainProcedure;

    @Column(name = "step_no")
    private Short stepNo;

    public SubProcedure() {
    }

    public SubProcedure(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubProcedure() {
        return subProcedure;
    }

    public void setSubProcedure(String subProcedure) {
        this.subProcedure = subProcedure;
    }

    @XmlTransient
    @JsonIgnore
    public MainProcedure getMainProcedure() {
        return mainProcedure;
    }

    public void setMainProcedure(MainProcedure mainProcedure) {
        this.mainProcedure = mainProcedure;
    }

    public Short getStepNo() {
        return stepNo;
    }

    public void setStepNo(Short stepNo) {
        this.stepNo = stepNo;
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
        if (!(object instanceof SubProcedure)) {
            return false;
        }
        SubProcedure other = (SubProcedure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.mbr.entity.SubProcedure[ id=" + id + " ]";
    }

}
