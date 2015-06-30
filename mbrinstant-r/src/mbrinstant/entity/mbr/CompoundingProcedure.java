/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maine
 */
public class CompoundingProcedure implements Serializable {
    @Expose
    private Integer id;
    @Expose
    private Short stepNumber;
    @Expose
    private String header;
    @Expose
    private Boolean footer;
    @Expose
    private String doneBy;
    @Expose
    private String checkedBy;
    @Expose(serialize=false)
    private List<Dosage> dosageList;

   
    
    
    
    public CompoundingProcedure(short stepNumber, String header, boolean footer, String doneBy, String checkedBy, List<Dosage> dosageList){
    
        this.stepNumber = stepNumber;
        this.header = header;
        this.footer = footer;
        this.doneBy = doneBy;
        this.checkedBy = checkedBy;
        this.dosageList = dosageList;
    }
    public CompoundingProcedure() {
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Boolean getFooter() {
        return footer;
    }

    public void setFooter(Boolean footer) {
        this.footer = footer;
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
        return stepNumber + " " + header;
    }
    
   
    
}
