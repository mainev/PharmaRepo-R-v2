/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.report_generation.mbr_generation;

import java.util.ArrayList;
import java.util.List;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.PackagingProcedure;

/**
 *
 * @author maine
 */
public class MbrProcedureHelper {

    public static List<PackagingProcedure> getPackagingProcedurePartOne(Mbr batch) {
        List<PackagingProcedure> result = new ArrayList();
        List<PackagingProcedure> list = batch.getProductId().getManufacturingProcedureId().getPackagingProcedureOperationList();

        list.forEach(po -> {
            if (po.getPart() == 1) {
                result.add(po);
            }
        });

        return result;
    }

    public static List<PackagingProcedure> getPackagingProcedurePartTwo(Mbr batch) {
        List<PackagingProcedure> result = new ArrayList();
        List<PackagingProcedure> list = batch.getProductId().getManufacturingProcedureId().getPackagingProcedureOperationList();

        list.forEach(po -> {
            if (po.getPart() == 2) {
                result.add(po);
            }
        });

        return result;
    }

}
