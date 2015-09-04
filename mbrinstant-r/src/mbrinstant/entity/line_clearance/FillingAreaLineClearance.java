/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.line_clearance;

import java.io.Serializable;

/**
 *
 * @author maine
 */
public class FillingAreaLineClearance implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Short stepNo;
    private String instruction;
    private String checkedBy;
    private String verifiedBy;
    private Type type;

    public FillingAreaLineClearance() {
    }

    public FillingAreaLineClearance(Integer id) {
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.line_clearance.FillingAreaLineClearance[ id=" + id + " ]";
    }

}
