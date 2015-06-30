/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maine
 */
@XmlRootElement
public class PackagingOperation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    private Integer id;
    @Expose
    private Short stepNumber;
    @Expose
    private String header;
    @Expose
    private Short part;
    @Expose
    private String doneBy;
    @Expose
    private String checkedBy;

    public PackagingOperation(String header, Short part, String doneBy, String checkedBy) {
        this.header = header;
        this.part = part;
        this.doneBy = doneBy;
        this.checkedBy = checkedBy;
    }
    
  
    
    

    public PackagingOperation() {
    }

    public PackagingOperation(Integer id) {
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

    public Short getPart() {
        return part;
    }

    public void setPart(Short part) {
        this.part = part;
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    /*
     public ManufacturingProcedure getManufacturingProcedureId() {
     return manufacturingProcedureId;
     }

     public void setManufacturingProcedureId(ManufacturingProcedure manufacturingProcedureId) {
     this.manufacturingProcedureId = manufacturingProcedureId;
     }*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PackagingOperation)) {
            return false;
        }
        PackagingOperation other = (PackagingOperation) object;
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
