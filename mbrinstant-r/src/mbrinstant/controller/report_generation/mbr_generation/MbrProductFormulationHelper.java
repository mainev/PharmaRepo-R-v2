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
    /*
     private StockCardTxn getMatchingStockCardTxnFromList(RawMaterialRequirement rmReq, List<StockCardTxn> stkTxnList) {
     for (StockCardTxn stkTxn : stkTxnList) {
     if (isMatch(rmReq, stkTxn)) {
     return stkTxn;
     }
     }
     return null;
     }

     private StockCardTxn getMatchingStockCardTxnFromList(PackagingMaterialRequirement pmReq, List<StockCardTxn> stkTxnList) {
     for (StockCardTxn stkTxn : stkTxnList) {
     if (isMatch(pmReq, stkTxn)) {
     return stkTxn;
     }
     }
     return null;
     }

     private boolean isMatch(RawMaterialRequirement rmReq, StockCardTxn stkTxn) {
     try {
     boolean codeMatch;
     boolean itemCategoryMatch;
     boolean partMatch;

     StockCardC stk = stkTxnRestClient.getStockCard(stkTxn);
     codeMatch = stk.getItemId().getItemCd().equals(rmReq.getItemId().getItemCd());
     itemCategoryMatch = stk.getItemId().getItemCategoryId().getCode().equals(rmReq.getItemId().getItemCategoryId().getCode());

     return codeMatch && itemCategoryMatch;
     } catch (ServerException ex) {
     Logger.getLogger(MbrProductFormulationHelper.class.getName()).log(Level.SEVERE, null, ex);
     }
     return false;
     }

     private boolean isMatch(PackagingMaterialRequirement pmReq, StockCardTxn stkTxn) {
     try {
     boolean codeMatch;
     boolean itemCategoryMatch;

     StockCardC stk = stkTxnRestClient.getStockCard(stkTxn);
     codeMatch = stk.getItemId().getItemCd().equals(pmReq.getItemId().getItemCd());
     itemCategoryMatch = stk.getItemId().getItemCategoryId().getCode().equals(pmReq.getItemId().getItemCategoryId().getCode());

     return codeMatch && itemCategoryMatch;
     } catch (ServerException ex) {
     Logger.getLogger(MbrProductFormulationHelper.class.getName()).log(Level.SEVERE, null, ex);
     }
     return false;
     }
     */
}
