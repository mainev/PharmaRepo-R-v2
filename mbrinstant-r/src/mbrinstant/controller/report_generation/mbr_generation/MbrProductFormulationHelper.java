/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.report_generation.mbr_generation;

import java.util.ArrayList;
import java.util.List;
import mbrinstant.entity.mbr.BatchItemRequirement;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.exceptions.ServerException;
import mbrinstant.rest_client.transaction.SingletonStockCardTxnRestClient;
import mbrinstant.utils.Quantity;

/**
 *
 * @author maine
 */
public class MbrProductFormulationHelper {

    private final Mbr batch;

    //   private List<StockCardTxn> stockCardTxnList = new ArrayList();
    SingletonStockCardTxnRestClient stkTxnRestClient = SingletonStockCardTxnRestClient.getInstance();

    public MbrProductFormulationHelper(Mbr batch) {
        this.batch = batch;

    }

    public List<MbrRawMaterialSpecification> deriveRawMaterialSpecification() throws ServerException {

        List<MbrRawMaterialSpecification> rmSpecs = new ArrayList();

        List<BatchItemRequirement> batchItemReqList = batch.getBatchItemRequirementList();
        for (BatchItemRequirement bir : batchItemReqList) {
            if (bir.getItemId().getItemCategoryId().getCode().equals("RM")) {
                List<StockCardTxn> stkTxnList = bir.getStockCardTxnList();
                List<ControlNo> cnList = new ArrayList();
                for (StockCardTxn txn : stkTxnList) {

                    Quantity actualQty = new Quantity(txn.getQty(), txn.getUnitId().getName());
                    String controlNo = stkTxnRestClient.getStockCard(txn).getControlNo();
                    cnList.add(new ControlNo(controlNo, actualQty));

                }
                Quantity udfQuantity = new Quantity(bir.getUdfQty(), bir.getUdfQtyUnitId().getName());
                Quantity requiredQty = new Quantity(bir.getRequiredQty(), bir.getRequiredQtyUnitId().getName());
                short part = bir.getPart();
                MbrRawMaterialSpecification rmSpec
                        = new MbrRawMaterialSpecification(bir.getItemId(), udfQuantity, requiredQty, cnList, part);
                rmSpecs.add(rmSpec);
            }

        }

        return rmSpecs;
    }

    public List<MbrPackgMaterialRequirement> derivePackagingMaterial() throws ServerException {
        List<MbrPackgMaterialRequirement> pmReqList = new ArrayList();

        List<BatchItemRequirement> batchItemReqList = batch.getBatchItemRequirementList();
        for (BatchItemRequirement bir : batchItemReqList) {
            if (bir.getItemId().getItemCategoryId().getCode().equals("PM")) {
                List<StockCardTxn> stkTxnList = bir.getStockCardTxnList();
                List<ControlNo> cnList = new ArrayList();
                for (StockCardTxn txn : stkTxnList) {
                    Quantity actualQty = new Quantity(txn.getQty(), txn.getUnitId().getName());
                    String controlNo = stkTxnRestClient.getStockCard(txn).getControlNo();
                    cnList.add(new ControlNo(controlNo, actualQty));
                }
                Quantity udfQuantity = new Quantity(bir.getUdfQty(), bir.getUdfQtyUnitId().getName());
                Quantity requiredQty = new Quantity(bir.getRequiredQty(), bir.getRequiredQtyUnitId().getName());

                MbrPackgMaterialRequirement rmSpec
                        = new MbrPackgMaterialRequirement(bir.getItemId(), udfQuantity, requiredQty, cnList);
                pmReqList.add(rmSpec);
            }

        }

        return pmReqList;
    }

    public List<MbrPackgMaterialRequirement> deriveDirectPackagingMaterial() throws ServerException {
        List<MbrPackgMaterialRequirement> pmReqList = new ArrayList();

        List<BatchItemRequirement> batchItemReqList = batch.getBatchItemRequirementList();
        for (BatchItemRequirement bir : batchItemReqList) {
            String code = bir.getItemId().getItemCategoryId().getCode();
            String type = bir.getItemId().getItemTypeId().getCode();
            if (code.equals("PM") && type.equals("DM")) {
                List<StockCardTxn> stkTxnList = bir.getStockCardTxnList();
                List<ControlNo> cnList = new ArrayList();
                for (StockCardTxn txn : stkTxnList) {
                    Quantity actualQty = new Quantity(txn.getQty(), txn.getUnitId().getName());
                    String controlNo = stkTxnRestClient.getStockCard(txn).getControlNo();
                    cnList.add(new ControlNo(controlNo, actualQty));
                }
                Quantity udfQuantity = new Quantity(bir.getUdfQty(), bir.getUdfQtyUnitId().getName());
                Quantity requiredQty = new Quantity(bir.getRequiredQty(), bir.getRequiredQtyUnitId().getName());

                MbrPackgMaterialRequirement rmSpec
                        = new MbrPackgMaterialRequirement(bir.getItemId(), udfQuantity, requiredQty, cnList);
                pmReqList.add(rmSpec);
            }

        }

        return pmReqList;
    }

    public List<MbrPackgMaterialRequirement> deriveInDirectPackagingMaterial() throws ServerException {
        List<MbrPackgMaterialRequirement> pmReqList = new ArrayList();

        List<BatchItemRequirement> batchItemReqList = batch.getBatchItemRequirementList();
        for (BatchItemRequirement bir : batchItemReqList) {
            String code = bir.getItemId().getItemCategoryId().getCode();
            String type = bir.getItemId().getItemTypeId().getCode();
            if (code.equals("PM") && type.equals("IDM")) {
                List<StockCardTxn> stkTxnList = bir.getStockCardTxnList();
                List<ControlNo> cnList = new ArrayList();
                for (StockCardTxn txn : stkTxnList) {
                    Quantity actualQty = new Quantity(txn.getQty(), txn.getUnitId().getName());
                    String controlNo = stkTxnRestClient.getStockCard(txn).getControlNo();
                    cnList.add(new ControlNo(controlNo, actualQty));
                }
                Quantity udfQuantity = new Quantity(bir.getUdfQty(), bir.getUdfQtyUnitId().getName());
                Quantity requiredQty = new Quantity(bir.getRequiredQty(), bir.getRequiredQtyUnitId().getName());

                MbrPackgMaterialRequirement rmSpec
                        = new MbrPackgMaterialRequirement(bir.getItemId(), udfQuantity, requiredQty, cnList);
                pmReqList.add(rmSpec);
            }

        }

        return pmReqList;
    }
}
