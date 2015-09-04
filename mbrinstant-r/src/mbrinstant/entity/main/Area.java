/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.main;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import mbrinstant.entity.line_clearance.CodingRoomLineClearance;
import mbrinstant.entity.line_clearance.CompoundingAreaLineClearance;
import mbrinstant.entity.line_clearance.DispensingAreaLineClearance;
import mbrinstant.entity.line_clearance.FillingAreaLineClearance;
import mbrinstant.entity.line_clearance.LabelingPackgAreaLineClearance;
import mbrinstant.entity.mbr.CodingSpecification;
import mbrinstant.entity.mbr.MainProcedure;

/**
 *
 * @author maine
 */
@XmlRootElement
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    private Short id;
    @Expose
    private String name;

    private List<CodingRoomLineClearance> codingRoomLineClearanceList;
    private List<CompoundingAreaLineClearance> compoundingAreaLineClearanceList;
    private List<DispensingAreaLineClearance> dispensingAreaLineClearanceList;
    private List<FillingAreaLineClearance> fillingAreaLineClearanceList;
    private List<LabelingPackgAreaLineClearance> labelingPakcgAreaLineClearanceList;
    private List<CodingSpecification> codingSpecificationList;
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

    public List<DispensingAreaLineClearance> getLineClearanceForStartOfDispensing() {
        List<DispensingAreaLineClearance> list = new ArrayList();
        dispensingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("START")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public List<DispensingAreaLineClearance> getLineClearanceForEndOfDispensing() {
        List<DispensingAreaLineClearance> list = new ArrayList();
        dispensingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("END")) {
                list.add(dlc);
            }
        });
        return list;
    }

    @Override
    public String toString() {
        return name;
    }

}
