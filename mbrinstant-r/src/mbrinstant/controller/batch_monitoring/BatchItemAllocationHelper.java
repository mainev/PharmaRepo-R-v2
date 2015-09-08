/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.batch_monitoring;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.BatchItemRequirement;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.sqlsvr_copy.StockCardC;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.utils.MetricCalculator;
import mbrinstant.utils.Quantity;

/**
 *
 * @author maine Limited for raw materials at this moment.
 */
public class BatchItemAllocationHelper {

    public boolean MATERIALS_AVAILABLE = true;

    SingletonStockCardRestClient stockCardRestClient = SingletonStockCardRestClient.getInstance();
    private ObservableList<ControlledItem> controlledItemList = FXCollections.observableArrayList();
    private List<StockCardC> depletedStockCardList = new ArrayList();

    public enum Status {

        OK, NOT_OK
    }

    public class ControlledItem {

        private BatchItemRequirement batchItemReq;
        private Quantity requiredQty;
        private Quantity availableQty;
        private ObservableList<StockCardTxn> txnList = FXCollections.observableArrayList();
        private Status status;

        public ControlledItem(BatchItemRequirement batchItemReq, ObservableList<StockCardTxn> txnList, Quantity availableQty) {
            this.batchItemReq = batchItemReq;
            this.txnList = txnList;
            this.requiredQty = new Quantity(batchItemReq.getRequiredQty(), batchItemReq.getRequiredQtyUnitId().getName());
            this.availableQty = availableQty;
            if (availableQty.getValue() != 0 && MetricCalculator.isGreaterThanOrEqual(availableQty, requiredQty)) {
                this.status = (Status.OK);
            } else {
                this.status = (Status.NOT_OK);
            }
        }

        public BatchItemRequirement getBatchItemReq() {
            return batchItemReq;
        }

        public void setBatchItemReq(BatchItemRequirement batchItemReq) {
            this.batchItemReq = batchItemReq;
        }

        public Quantity getRequiredQty() {
            return requiredQty;
        }

        public void setRequiredQty(Quantity requiredQty) {
            this.requiredQty = requiredQty;
        }

        public Quantity getAvailableQty() {
            return availableQty;
        }

        public void setAvailableQty(Quantity availableQty) {
            this.availableQty = availableQty;
        }

        public ObservableList<StockCardTxn> getTxnList() {
            return txnList;
        }

        public void setTxnList(ObservableList<StockCardTxn> txnList) {
            this.txnList = txnList;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

    }

    public BatchItemAllocationHelper(Mbr batch) throws Exception {
        List<BatchItemRequirement> batchItemReqList = batch.getBatchItemRequirementList();
        for (BatchItemRequirement batchItemReq : batchItemReqList) {
            List<StockCardC> availableStocks = stockCardRestClient.getAvailableStockCard(batch.getProductId().getCompanyId(), batchItemReq.getItemId());
            ObservableList<StockCardTxn> stkTransactionList = FXCollections.observableArrayList();

            Quantity overallAvailableQty = new Quantity();
            if (availableStocks.isEmpty()) {
                System.out.println("NO AVAILABLE STOCKS FOR " + batchItemReq.getItemId());
                MATERIALS_AVAILABLE = false;
            } else {
                System.out.println("AVAILABLE STOCKCARD LIST : " + availableStocks);
                Quantity requiredQty = new Quantity(batchItemReq.getRequiredQty(), batchItemReq.getRequiredQtyUnitId().getName());

                //availableStocks has values
                for (int i = 0; (requiredQty.getValue() > 0 && i < availableStocks.size()); i++) {

                    StockCardC stk = availableStocks.get(i);
                    Quantity stockCardAvailableQty = stk.getAvailableQuantity();
                    if (!depletedStockCardList.contains(stk)) {
                        //if stockCardAvailableQty is greater than requiredQty
                        if (MetricCalculator.isGreaterThan(stockCardAvailableQty, requiredQty)) {
                            System.out.println(stk.getQty() + " " + stk.getUom() + " is greater than " + requiredQty);
                            StockCardTxn stxn = new StockCardTxn();
                            stxn.setQty(requiredQty.getValue());
                            stxn.setUnitId(getEquivalentUnit(requiredQty.getUnit()));
                            stxn.setTempStockCard(stk);
                            stxn.setTempBatchItem(batchItemReq);

                            stkTransactionList.add(stxn);

                            //for audit entry
                            stxn.setUnit_id(getEquivalentUnit(requiredQty.getUnit()).getId());
                            stxn.setStock_card_id(stk.getId());

                            System.out.println("RESERVED TXN: " + stxn);
                            System.out.println();
                            requiredQty.setValue(0);

                        } //if availableQty is equal to requiredQty
                        else if (MetricCalculator.isEqual(stockCardAvailableQty, requiredQty)) {
                            System.out.println(stk.getQty() + " " + stk.getUom() + " is equals to " + requiredQty);
                            StockCardTxn stxn = new StockCardTxn();
                            stxn.setQty(requiredQty.getValue());
                            stxn.setUnitId(getEquivalentUnit(requiredQty.getUnit()));
                            stxn.setTempStockCard(stk);
                            stkTransactionList.add(stxn);
                            stxn.setTempBatchItem(batchItemReq);

                            //for audit entry
                            stxn.setUnit_id(getEquivalentUnit(requiredQty.getUnit()).getId());
                            stxn.setStock_card_id(stk.getId());

                            System.out.println("RESERVED TXN: " + stxn);
                            System.out.println();
                            requiredQty.setValue(0);
                            depletedStockCardList.add(stk);
                        } //if availableQty is less than the requiredQty
                        else {
                            System.out.println(stk.getQty() + " " + stk.getUom() + " is less than " + requiredQty);
                            StockCardTxn stxn = new StockCardTxn();
                            stxn.setQty(stockCardAvailableQty.getValue());
                            stxn.setUnitId(getEquivalentUnit(stockCardAvailableQty.getUnit()));
                            stxn.setTempStockCard(stk);
                            stkTransactionList.add(stxn);
                            stxn.setTempBatchItem(batchItemReq);

                            //for audit entry
                            stxn.setUnit_id(getEquivalentUnit(requiredQty.getUnit()).getId());
                            stxn.setStock_card_id(stk.getId());

                            requiredQty = MetricCalculator.subtract(requiredQty, stockCardAvailableQty);
                            System.out.println("RESERVED TXN: " + stxn);
                            depletedStockCardList.add(stk);
                        }

                        overallAvailableQty = MetricCalculator.add(overallAvailableQty, stk.getAvailableQuantity());
                    }

                }

                //if overalAvailableQty is less than the requiredQty
                if (!MetricCalculator.isGreaterThanOrEqual(overallAvailableQty, new Quantity(batchItemReq.getRequiredQty(), batchItemReq.getRequiredQtyUnitId().getName()))) {
                    MATERIALS_AVAILABLE = false;
                }

            }
            controlledItemList.add(new ControlledItem(batchItemReq, stkTransactionList, overallAvailableQty));
        }
        System.out.println("Depleted stockcard list: " + depletedStockCardList);
    }

    public ObservableList<ControlledItem> getControlledRawMatList() {
        ObservableList<ControlledItem> controlledRmList = FXCollections.observableArrayList();
        controlledItemList.forEach(ci -> {
            if (ci.getBatchItemReq().getItemId().getItemCategoryId().getCode().equals("RM")) {
                controlledRmList.add(ci);
            }
        });
        return controlledRmList;
    }

    public ObservableList<ControlledItem> getControlledPackgMatList() {
        ObservableList<ControlledItem> controlledRmList = FXCollections.observableArrayList();
        controlledItemList.forEach(ci -> {
            if (ci.getBatchItemReq().getItemId().getItemCategoryId().getCode().equals("PM")) {
                controlledRmList.add(ci);
            }
        });
        return controlledRmList;
    }

    public Unit getEquivalentUnit(String unitName) throws Exception {
        List<Unit> unitList = SingletonUnitRestClient.getInstance().getUnitList();
        for (Unit u : unitList) {
            if (u.getName().toUpperCase().trim().equals(unitName.toUpperCase().trim())) {
                return u;
            }
        }
        return null;
    }

    public boolean isManufacturable() {
        return MATERIALS_AVAILABLE;
    }

    public List<StockCardC> getDepletedStockCardList() {
        return depletedStockCardList;
    }

    public ObservableList<ControlledItem> getControlledItemList() {
        return controlledItemList;
    }

}
