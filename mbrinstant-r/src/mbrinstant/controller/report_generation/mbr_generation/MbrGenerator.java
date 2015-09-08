/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.report_generation.mbr_generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mbrinstant.entity.ProcedureCategory;
import mbrinstant.entity.main.Area;
import mbrinstant.entity.mbr.CompoundingProcedure;
import mbrinstant.entity.mbr.Dosage;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.entity.sqlsvr_copy.Item;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.main.SingletonProductRestClient;
import mbrinstant.utils.Quantity;
import mbrinstant.utils.UDFCalculator;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author maine
 */
public class MbrGenerator {

    SingletonProductRestClient productRestClient = SingletonProductRestClient.getInstance();
    private Mbr batch;
    private List<MbrRawMaterialSpecification> batchRmSpecs = new ArrayList();
    private List<MbrPackgMaterialRequirement> batchPmReq = new ArrayList();
    private MbrProductFormulationHelper productFormulationHelper;

    public MbrGenerator(Mbr batch) {
        this.batch = batch;
        productFormulationHelper = new MbrProductFormulationHelper(this.batch);
    }

    public void generateMbr() throws ServerException {

        batchRmSpecs = productFormulationHelper.deriveRawMaterialSpecification();
        batchPmReq = productFormulationHelper.derivePackagingMaterial();
        System.out.println("batch raw material specification list: " + batchRmSpecs);
        System.out.println("batch packg material requirement list: " + batchPmReq);
        System.out.println("COMPOUNDING PROC LIST: " + batch.getProductId().getManufacturingProcedureId().getCompoundingProcedureList());

        createMbrJasperReport();
    }

    public void createMbrJasperReport() throws ServerException {

        UDFCalculator udfCalculator = new UDFCalculator();

        List<RawMaterialRequirement> rmReqCollection = batch.getProductId().getUdfId().getRawMaterialRequirementList();

        udfCalculator.calculateRawMaterialBatchReq(batch);
        udfCalculator.calculatePackMatBatchReq(batch);

        for (CompoundingProcedure cp : batch.getProductId().getManufacturingProcedureId().getCompoundingProcedureList()) {
            for (Dosage d : cp.getDosageList()) {
                for (RawMaterialRequirement rmr : rmReqCollection) {
                    if (rmr.getId().equals(d.getRawMaterialRequirementId().getId())) {
                        d.setRawMaterialRequirementId(rmr);
                    }
                }
            }
        }

        try {

            Area area = batch.getProductId().getAreaId();
            Map<String, Object> params = new HashMap();
            params.put("batch", batch);
            params.put("batch_rm_specs", batchRmSpecs);
            params.put("batch_pm_req", batchPmReq);

            //filling procedure
            params.put("filling_proc", batch.getProductId().getManufacturingProcedureId().getPowderFillingProcedureList());
            params.put("filling_equip_req_list", MbrEquipmentRequirementHelper.getEquipmentRequirement(batch, ProcedureCategory.POWDER_FILLING));

            //compounding procedure
            params.put("compounding_proc", batch.getProductId().getManufacturingProcedureId().getCompoundingProcedureList());
            params.put("compounding_equip_req_list", MbrEquipmentRequirementHelper.getEquipmentRequirement(batch, ProcedureCategory.COMPOUNDING));

            //packaging material requirements
            params.put("batch_direct_pm_req", productFormulationHelper.deriveDirectPackagingMaterial());
            params.put("batch_indirect_pm_req", productFormulationHelper.deriveInDirectPackagingMaterial());

            //compounding area line clearance
            params.put("compounding_area_line_clearance_start", MbrLineClearanceHelper.getLineClearanceForStartOfCompounding(area));

            //filling area line clearance
            params.put("filling_area_line_clearance_start", MbrLineClearanceHelper.getLineClearanceForStartOfFilling(area));
            params.put("filling_area_line_clearance_end", MbrLineClearanceHelper.getLineClearanceForEndOfFilling(area));

            //coding room line clearance
            params.put("coding_area_line_clearance_start", MbrLineClearanceHelper.getLineClearanceForStartUpOperation(area));
            params.put("coding_area_line_clearance_end", MbrLineClearanceHelper.getLineClearanceForEndOfOperation(area));

            //labeling/packg area line clearance
            params.put("labeling_packg_line_clearance_start", MbrLineClearanceHelper.getLineClearanceForStartOfLabelingPackg(area));
            params.put("labeling_packg_line_clearance_end", MbrLineClearanceHelper.getLineClearanceForEndOfLabelingPackg(area));

            //packagin procedure
            params.put("packaging_procedure_1", MbrProcedureHelper.getPackagingProcedurePartOne(batch));
            params.put("packaging_procedure_2", MbrProcedureHelper.getPackagingProcedurePartTwo(batch));

            //coding specification
            params.put("coding_specification", batch.getProductId().getAreaId().getCodingSpecificationList());
            params.put("coding_spec_equip_req_list", MbrEquipmentRequirementHelper.getEquipmentRequirement(batch, ProcedureCategory.CODING_SPECS));

            //filled weight monitoring sheet
            System.out.println(productRestClient.getPrimaryPackaging(batch.getProductId()));
            Item primaryPackgItem = productRestClient.getPrimaryPackaging(batch.getProductId());
            Quantity primaryPackgQty = getPrimaryPackagingQuantity(batchPmReq, primaryPackgItem.getId());
            Integer[] primaryPackagingNoOfRows = new Integer[getNumberOfReportRows(primaryPackgQty.getValue())];
            System.out.println("no of items = " + primaryPackgQty);
            System.out.println("no of rows = " + primaryPackagingNoOfRows.length);
            params.put("primary_packg_qty", primaryPackgQty);
            params.put("primary_packg_rows", primaryPackagingNoOfRows);

            //jasper report
            JasperPrint jasperPrint = JasperFillManager.fillReport("report/mbr/powder_area/main_template.jasper",
                    params, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public Quantity getPrimaryPackagingQuantity(List<MbrPackgMaterialRequirement> pmrList, int bottleId) {
        for (MbrPackgMaterialRequirement bpmr : pmrList) {
            if (bpmr.getItem().getId() == bottleId) {
                return bpmr.getRequiredQty();
            }
        }
        return null;
    }

    private int getNumberOfReportRows(double size) {
        int rows = 0;
        int item = 0;
        int counter = 0;
        while (item < size) {
            counter += 60;

            rows = counter / 3;
            item = item + 60;
            System.out.println("counter =" + counter);
            System.out.println("item =" + item);
            System.out.println("rows =" + rows + "\n");
        }
        return rows;
    }
    /*
     public int getSecondaryPackagingQuantity(List<PackagingMaterialRequirement> pmrList, int cBoxId) {
     for (PackagingMaterialRequirement pmr : pmrList) {
     if (pmr.getItemId().getId() == cBoxId) {
     return (int) pmr.getNewQuantity();
     }
     }
     return 0;
     }
     */
}
