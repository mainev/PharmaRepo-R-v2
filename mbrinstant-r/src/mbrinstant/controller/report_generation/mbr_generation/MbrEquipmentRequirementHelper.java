/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.report_generation.mbr_generation;

import java.util.ArrayList;
import java.util.List;
import mbrinstant.entity.ProcedureCategory;
import mbrinstant.entity.mbr.EquipmentRequirement;
import mbrinstant.entity.mbr.Mbr;

/**
 *
 * @author maine
 */
public class MbrEquipmentRequirementHelper {

    public static List<EquipmentRequirement> getEquipmentRequirement(Mbr batch, ProcedureCategory cat) {
        List<EquipmentRequirement> list = new ArrayList();
        List<EquipmentRequirement> equipmentReqList = batch.getProductId().getManufacturingProcedureId().getEquipmentRequirementList();
        for (EquipmentRequirement er : equipmentReqList) {
            if (er.getProcedureCategory() == cat) {
                list.add(er);
            }
        }

        return list;

    }
}
