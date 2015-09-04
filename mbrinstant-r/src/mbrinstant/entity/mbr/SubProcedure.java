/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

/**
 *
 * @author maine
 */
public class SubProcedure {

    private Integer id;
    private String subProcedure;
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
