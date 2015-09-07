/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.report_generation.mbr_generation;

import java.util.ArrayList;
import java.util.List;
import mbrinstant.entity.line_clearance.CodingRoomLineClearance;
import mbrinstant.entity.line_clearance.CompoundingAreaLineClearance;
import mbrinstant.entity.line_clearance.DispensingAreaLineClearance;
import mbrinstant.entity.line_clearance.FillingAreaLineClearance;
import mbrinstant.entity.line_clearance.LabelingPackgAreaLineClearance;
import mbrinstant.entity.main.Area;

/**
 *
 * @author maine
 */
public class MbrLineClearanceHelper {

    public MbrLineClearanceHelper() {
    }

    public static List<DispensingAreaLineClearance> getLineClearanceForStartOfDispensing(Area area) {
        List<DispensingAreaLineClearance> dispensingAreaLineClearanceList = area.getDispensingAreaLineClearanceList();
        List<DispensingAreaLineClearance> list = new ArrayList();
        dispensingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("START")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public static List<DispensingAreaLineClearance> getLineClearanceForEndOfDispensing(Area area) {
        List<DispensingAreaLineClearance> dispensingAreaLineClearanceList = area.getDispensingAreaLineClearanceList();
        List<DispensingAreaLineClearance> list = new ArrayList();
        dispensingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("END")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public static List<CompoundingAreaLineClearance> getLineClearanceForStartOfCompounding(Area area) {
        List<CompoundingAreaLineClearance> compoundingAreaLineClearanceList = area.getCompoundingAreaLineClearanceList();
        List<CompoundingAreaLineClearance> list = new ArrayList();
        compoundingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("START")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public static List<CompoundingAreaLineClearance> getLineClearanceForEndOfCompounding(Area area) {
        List<CompoundingAreaLineClearance> compoundingAreaLineClearanceList = area.getCompoundingAreaLineClearanceList();
        List<CompoundingAreaLineClearance> list = new ArrayList();
        compoundingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("END")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public static List<FillingAreaLineClearance> getLineClearanceForStartOfFilling(Area area) {
        List<FillingAreaLineClearance> fillingAreaLineClearanceList = area.getFillingAreaLineClearanceList();
        List<FillingAreaLineClearance> list = new ArrayList();
        fillingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("START")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public static List<FillingAreaLineClearance> getLineClearanceForEndOfFilling(Area area) {
        List<FillingAreaLineClearance> fillingAreaLineClearanceList = area.getFillingAreaLineClearanceList();
        List<FillingAreaLineClearance> list = new ArrayList();
        fillingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("END")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public static List<CodingRoomLineClearance> getLineClearanceForEndOfOperation(Area area) {
        List<CodingRoomLineClearance> codingAreaLineClearanceList = area.getCodingRoomLineClearanceList();
        List<CodingRoomLineClearance> list = new ArrayList();
        codingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("END")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public static List<CodingRoomLineClearance> getLineClearanceForStartUpOperation(Area area) {
        List<CodingRoomLineClearance> codingAreaLineClearanceList = area.getCodingRoomLineClearanceList();
        List<CodingRoomLineClearance> list = new ArrayList();
        codingAreaLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("START")) {
                list.add(dlc);
            }
        });
        return list;
    }

    //labeling/packg line clearance
    public static List<LabelingPackgAreaLineClearance> getLineClearanceForEndOfLabelingPackg(Area area) {
        List<LabelingPackgAreaLineClearance> labelingPackgLineClearanceList = area.getLabelingPakcgAreaLineClearanceList();
        List<LabelingPackgAreaLineClearance> list = new ArrayList();
        labelingPackgLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("END")) {
                list.add(dlc);
            }
        });
        return list;
    }

    public static List<LabelingPackgAreaLineClearance> getLineClearanceForStartOfLabelingPackg(Area area) {
        List<LabelingPackgAreaLineClearance> labelingPackgLineClearanceList = area.getLabelingPakcgAreaLineClearanceList();
        List<LabelingPackgAreaLineClearance> list = new ArrayList();
        labelingPackgLineClearanceList.forEach(dlc -> {
            if (dlc.getType().getCode().equals("START")) {
                list.add(dlc);
            }
        });
        return list;
    }
}
