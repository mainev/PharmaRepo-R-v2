/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.mmd_module.controller.reservation;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.mbr.Mbr;
import mbrinstant.entity.mbr.PackagingMaterialRequirement;
import mbrinstant.entity.mbr.RawMaterialRequirement;
import mbrinstant.entity.sqlsvr_copy.StockCardC;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.rest_client.sqlsvr_copy.SingletonStockCardRestClient;
import mbrinstant.utils.MetricCalculator;
import mbrinstant.utils.Quantity;
import mbrinstant.utils.UDFCalculator;

/**
 *
 * @author maine Limited for raw materials at this moment.
 */
public class MbrRequirementHandler {

    public boolean MATERIALS_AVAILABLE = true;

    SingletonStockCardRestClient stockCardRestClient = SingletonStockCardRestClient.getInstance();

    public enum Status {

        OK, NOT_OK
    }

    public class ControlledPackagingMaterial {

        private PackagingMaterialRequirement pmReq;
        private Quantity requiredQty;
        private Quantity availableQty;
        private ObservableList<StockCardTxn> txnList = FXCollections.observableArrayList();
        private Status status;

        public ControlledPackagingMaterial(PackagingMaterialRequirement pmReq, ObservableList<StockCardTxn> txnList, Quantity availableQty) {
            this.pmReq = pmReq;
            this.txnList = txnList;
            this.requiredQty = new Quantity(pmReq.getNewQuantity(), pmReq.getUnitId().getName());
            this.availableQty = availableQty;

            if (availableQty.getValue() != 0 && MetricCalculator.isGreaterThanOrEqual(availableQty, requiredQty)) {
                this.status = (Status.OK);
            } else {
                this.status = (Status.NOT_OK);
            }
        }

        public PackagingMaterialRequirement getPmReq() {
            return pmReq;
        }

        public void setPmReq(PackagingMaterialRequirement pmReq) {
            this.pmReq = pmReq;
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

    public class ControlledRawMaterial {

        private RawMaterialRequirement rmReq;
        private Quantity requiredQty;
        private Quantity availableQty;
        private ObservableList<StockCardTxn> txnList = FXCollections.observableArrayList();
        private Status status;

        public ControlledRawMaterial(RawMaterialRequirement rmReq, ObservableList<StockCardTxn> txnList, Quantity availableQty) {
            this.rmReq = rmReq;
            this.txnList = txnList;
            this.requiredQty = new Quantity(rmReq.getNewQuantity(), rmReq.getNewUnit());
            this.availableQty = availableQty;

            if (availableQty.getValue() != 0 && MetricCalculator.isGreaterThanOrEqual(availableQty, requiredQty)) {
                this.status = (Status.OK);
            } else {
                this.status = (Status.NOT_OK);
            }
        }

        public RawMaterialRequirement getRmReq() {
            return rmReq;
        }

        public void setRmReq(RawMaterialRequirement rmReq) {
            this.rmReq = rmReq;
        }

        public List<StockCardTxn> getTxnList() {
            return txnList;
        }

        public void setTxnList(ObservableList<StockCardTxn> txnList) {
            this.txnList = txnList;
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

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

    }

    ObservableList<ControlledRawMaterial> controlledRmList = FXCollections.observableArrayList();
    ObservableList<ControlledPackagingMaterial> controlledPmList = FXCollections.observableArrayList();
    List<StockCardC> depletedStockCardList = new ArrayList();

    public MbrRequirementHandler() {
    }

    //mbr product formulation must already be computed
    public MbrRequirementHandler(Mbr mbr) throws Exception {
        String companyCd = mbr.getProductId().getClientId().getCode();
        UDFCalculator udfCalculator = new UDFCalculator();
        udfCalculator.calculateRawMaterialBatchReq(mbr);
        udfCalculator.calculatePackMatBatchReq(mbr);
        List<RawMaterialRequirement> rmReqList = mbr.getProductId().getUdfId().getRawMaterialRequirementList();
        List<PackagingMaterialRequirement> pmReqList = mbr.getProductId().getUdfId().getPackagingMaterialRequirementList();
        for (RawMaterialRequirement rmReq : rmReqList) {

            List<StockCardC> availableStocks = stockCardRestClient.getStockCardByCompanyCdAndItemCd(companyCd, rmReq.getRawMaterialId().getCode());
            ObservableList<StockCardTxn> txnList = FXCollections.observableArrayList();
            Quantity overallAvailableQty = new Quantity();
            if (availableStocks.isEmpty()) {
                MATERIALS_AVAILABLE = false;
            } else {
                System.out.println("AVAILABLE STOCKCARD LIST : " + availableStocks);
                Quantity requiredQty = new Quantity(rmReq.getNewQuantity(), rmReq.getNewUnit());

                //availableStocks has values
                for (int i = 0; (requiredQty.getValue() > 0 && i < availableStocks.size()); i++) {
                    StockCardC stk = availableStocks.get(i);
                    Quantity availableQty = stk.getAvailableQuantity();

                    //if availableQty is greater than requiredQty
                    if (MetricCalculator.isGreaterThan(availableQty, requiredQty)) {
                        System.out.println(stk.getQty() + " " + stk.getUom() + " is greater than " + requiredQty);
                        StockCardTxn stxn = new StockCardTxn();
                        stxn.setQty(requiredQty.getValue());
                        stxn.setUnitId(getEquivalentUnit(requiredQty.getUnit()));
                        stxn.setStockCard(stk);
                        // stxn.setStkId(stk.getId());
                        txnList.add(stxn);
                        System.out.println("RESERVED TXN: " + stxn);
                        System.out.println();
                        requiredQty.setValue(0);

                    } //if availableQty is equal to requiredQty
                    else if (MetricCalculator.isEqual(availableQty, requiredQty)) {
                        System.out.println(stk.getQty() + " " + stk.getUom() + " is equals to " + requiredQty);
                        StockCardTxn stxn = new StockCardTxn();
                        stxn.setQty(requiredQty.getValue());
                        stxn.setUnitId(getEquivalentUnit(requiredQty.getUnit()));
                        stxn.setStockCard(stk);
                        //  stxn.setStkId(stk.getId());
                        txnList.add(stxn);
                        System.out.println("RESERVED TXN: " + stxn);
                        System.out.println();
                        requiredQty.setValue(0);
                        depletedStockCardList.add(stk);
                    } //if availableQty is less than the requiredQty
                    else {
                        System.out.println(stk.getQty() + " " + stk.getUom() + " is less than " + requiredQty);
                        StockCardTxn stxn = new StockCardTxn();
                        stxn.setQty(availableQty.getValue());
                        stxn.setUnitId(getEquivalentUnit(availableQty.getUnit()));
                        stxn.setStockCard(stk);
                        //   stxn.setStkId(stk.getId());
                        txnList.add(stxn);
                        requiredQty = MetricCalculator.subtract(requiredQty, availableQty);
                        System.out.println("RESERVED TXN: " + stxn);
                        depletedStockCardList.add(stk);
                    }

                    overallAvailableQty = MetricCalculator.add(overallAvailableQty, stk.getAvailableQuantity());
                }

                //if overalAvailableQty is less than the requiredQty
                if (!MetricCalculator.isGreaterThanOrEqual(overallAvailableQty, new Quantity(rmReq.getNewQuantity(), rmReq.getNewUnit()))) {
                    MATERIALS_AVAILABLE = false;
                }

            }
            controlledRmList.add(new ControlledRawMaterial(rmReq, txnList, overallAvailableQty));
        }

        for (PackagingMaterialRequirement pmReq : pmReqList) {

            List<StockCardC> availableStocks = stockCardRestClient.getStockCardByCompanyCdAndItemCd(companyCd, pmReq.getPackagingMaterialId().getCode());
            ObservableList<StockCardTxn> txnList = FXCollections.observableArrayList();
            Quantity overallAvailableQty = new Quantity();
            if (availableStocks.isEmpty()) {
                MATERIALS_AVAILABLE = false;
            } else {
                System.out.println("AVAILABLE STOCKCARD LIST : " + availableStocks);
                Quantity requiredQty = new Quantity(pmReq.getNewQuantity(), pmReq.getUnitId().getName());

                //availableStocks has values
                for (int i = 0; (requiredQty.getValue() > 0 && i < availableStocks.size()); i++) {
                    StockCardC stk = availableStocks.get(i);
                    Quantity availableQty = stk.getAvailableQuantity();

                    //if availableQty is greater than requiredQty
                    if (MetricCalculator.isGreaterThan(availableQty, requiredQty)) {
                        System.out.println(stk.getQty() + " " + stk.getUom() + " is greater than " + requiredQty);
                        StockCardTxn stxn = new StockCardTxn();
                        stxn.setQty(requiredQty.getValue());
                        stxn.setUnitId(getEquivalentUnit(requiredQty.getUnit()));
                        stxn.setStockCard(stk);
                        //  stxn.setStkId(stk.getId());
                        txnList.add(stxn);
                        System.out.println("RESERVED TXN: " + stxn);
                        System.out.println();
                        requiredQty.setValue(0);

                    } //if availableQty is equal to requiredQty
                    else if (MetricCalculator.isEqual(availableQty, requiredQty)) {
                        System.out.println(stk.getQty() + " " + stk.getUom() + " is equals to " + requiredQty);
                        StockCardTxn stxn = new StockCardTxn();
                        stxn.setQty(requiredQty.getValue());
                        stxn.setUnitId(getEquivalentUnit(requiredQty.getUnit()));
                        stxn.setStockCard(stk);
                        //  stxn.setStkId(stk.getId());
                        txnList.add(stxn);
                        System.out.println("RESERVED TXN: " + stxn);
                        System.out.println();
                        requiredQty.setValue(0);
                        depletedStockCardList.add(stk);
                    } //if availableQty is less than the requiredQty
                    else {
                        System.out.println(stk.getQty() + " " + stk.getUom() + " is less than " + requiredQty);
                        StockCardTxn stxn = new StockCardTxn();
                        stxn.setQty(availableQty.getValue());
                        stxn.setUnitId(getEquivalentUnit(availableQty.getUnit()));
                        stxn.setStockCard(stk);
                        //   stxn.setStkId(stk.getId());
                        txnList.add(stxn);
                        requiredQty = MetricCalculator.subtract(requiredQty, availableQty);
                        System.out.println("RESERVED TXN: " + stxn);
                        depletedStockCardList.add(stk);
                    }

                    overallAvailableQty = MetricCalculator.add(overallAvailableQty, stk.getAvailableQuantity());
                }

                //if overalAvailableQty is less than the requiredQty
                if (!MetricCalculator.isGreaterThanOrEqual(overallAvailableQty, new Quantity(pmReq.getNewQuantity(), pmReq.getUnitId().getName()))) {
                    MATERIALS_AVAILABLE = false;
                }

            }
            controlledPmList.add(new ControlledPackagingMaterial(pmReq, txnList, overallAvailableQty));
        }

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

    public ObservableList<ControlledRawMaterial> getControlledRmList() {
        return controlledRmList;
    }

    public void setControlledRmList(ObservableList<ControlledRawMaterial> controlledRmList) {
        this.controlledRmList = controlledRmList;
    }

    public List<StockCardC> getDepletedStockCardList() {
        return depletedStockCardList;
    }

    public void setDepletedStockCardList(List<StockCardC> depletedStockCardList) {
        this.depletedStockCardList = depletedStockCardList;
    }

    public ObservableList<ControlledPackagingMaterial> getControlledPmList() {
        return controlledPmList;
    }

    public void setControlledPmList(ObservableList<ControlledPackagingMaterial> controlledPmList) {
        this.controlledPmList = controlledPmList;
    }

}
