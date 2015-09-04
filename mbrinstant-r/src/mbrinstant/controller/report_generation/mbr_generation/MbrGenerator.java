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
import mbrinstant.entity.mbr.CompoundingProcedure;
import mbrinstant.entity.mbr.Dosage;
import mbrinstant.entity.mbr.EquipmentRequirement;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.exceptions.ServerException;
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

    private Mbr batch;
    private List<MbrRawMaterialSpecification> batchRmSpecs = new ArrayList();
    private List<MbrPackgMaterialRequirement> batchPmReq = new ArrayList();
    private List<EquipmentRequirement> compoundingEquipReqList = new ArrayList();
    private List<EquipmentRequirement> powderFillingEquipReqList = new ArrayList();

    public MbrGenerator(Mbr batch) {
        this.batch = batch;
    }

    public void generateMbr() throws ServerException {
        MbrProductFormulationHelper productFormulationHelper = new MbrProductFormulationHelper(this.batch);
        batchRmSpecs = productFormulationHelper.deriveRawMaterialSpecification();
        batchPmReq = productFormulationHelper.derivePackagingMaterial();
        compoundingEquipReqList = getCompoundingEquipmentRequirements(batch);
        powderFillingEquipReqList = getPowderFillingEquipmentRequirements(batch);
        //  batchRmSpecs = deriveRawMaterialSpecification(batch);
        //  batchPmReq = derivePackgMaterialRequirements(batch);
        System.out.println("batch raw material specification list: " + batchRmSpecs);
        System.out.println("batch packg material requirement list: " + batchPmReq);
        System.out.println("COMPOUNDING PROC LIST: " + batch.getProductId().getManufacturingProcedureId().getCompoundingProcedureList());
        System.out.println("EQUIPMENT REQ for compounding: " + getCompoundingEquipmentRequirements(batch));
        System.out.println("filling procedure: " + batch.getProductId().getManufacturingProcedureId().getPowderFillingProcedureList());
        System.out.println("EQUIPMENT REQ for filling: " + powderFillingEquipReqList);
        createMbrJasperReport();
    }

    public List<EquipmentRequirement> getPowderFillingEquipmentRequirements(Mbr batch) {
        List<EquipmentRequirement> list = new ArrayList();
        List<EquipmentRequirement> equipmentReqList = batch.getProductId().getManufacturingProcedureId().getEquipmentRequirementList();
        for (EquipmentRequirement er : equipmentReqList) {
            if (er.getProcedure().equals("POWDER_FILLING")) {
                list.add(er);
            }
        }

        return list;

    }

    public List<EquipmentRequirement> getCompoundingEquipmentRequirements(Mbr batch) {
        List<EquipmentRequirement> list = new ArrayList();
        List<EquipmentRequirement> equipmentReqList = batch.getProductId().getManufacturingProcedureId().getEquipmentRequirementList();
        for (EquipmentRequirement er : equipmentReqList) {
            if (er.getProcedure().equals("COMPOUNDING")) {
                list.add(er);
            }
        }

        return list;

    }

    /*
     public List<BatchItemRequirement> deriveRawMaterialSpecification(Mbr batch) {

     List<BatchItemRequirement> rmSpecs = new ArrayList();

     for (BatchItemRequirement bir : batch.getBatchItemRequirementList()) {
     if (bir.getItemId().getItemCategoryId().getCode().equals("RM")) {
     rmSpecs.add(bir);
     }
     }
     return rmSpecs;
     }

     public List<BatchItemRequirement> derivePackgMaterialRequirements(Mbr batch) {

     List<BatchItemRequirement> rmSpecs = new ArrayList();

     for (BatchItemRequirement bir : batch.getBatchItemRequirementList()) {
     if (bir.getItemId().getItemCategoryId().getCode().equals("PM")) {
     rmSpecs.add(bir);
     }
     }
     return rmSpecs;
     }
     */
    public void createMbrJasperReport() {

        UDFCalculator udfCalculator = new UDFCalculator();

        List<RawMaterialRequirement> rmReqCollection = batch.getProductId().getUdfId().getRawMaterialRequirementList();
        List<PackagingMaterialRequirement> pmReqCollection = batch.getProductId().getUdfId().getPackagingMaterialRequirementList();

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
            Map<String, Object> params = new HashMap();
            params.put("batch", batch);
            params.put("batch_rm_specs", batchRmSpecs);
            params.put("batch_pm_req", batchPmReq);
            params.put("compounding_proc", batch.getProductId().getManufacturingProcedureId().getCompoundingProcedureList());
            params.put("compounding_equip_req_list", compoundingEquipReqList);
            params.put("filling_proc", batch.getProductId().getManufacturingProcedureId().getPowderFillingProcedureList());
            params.put("filling_equip_req_list", powderFillingEquipReqList);
            JasperPrint jasperPrint = JasperFillManager.fillReport("report/mbr/powder_area/main_template.jasper",
                    params, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}
