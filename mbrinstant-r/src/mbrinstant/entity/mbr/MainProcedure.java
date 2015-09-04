/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import java.util.List;

/**
 *
 * @author maine
 */
public class MainProcedure {

    private Short id;
    private String heading;
    private String headingTitle;
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

    public List<SubProcedure> getSubProcedureList() {
        return subProcedureList;
    }

    public void setSubProcedureList(List<SubProcedure> subProcedureList) {
        this.subProcedureList = subProcedureList;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.mbr.entity.MainProcedure[ id=" + id + " ]";
    }

}
