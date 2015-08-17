/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.projection;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import mbrinstant.entity.main.RawMaterial;
import mbrinstant.entity.main.Unit;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.utils.MetricCalculator;
import mbrinstant.utils.Quantity;

/**
 *
 * @author maine
 */
public class BatchRm {

    RawMaterial rawMaterial;

    private ObservableList<Quantity> quantityList = FXCollections.observableArrayList();
    private Quantity requiredQuantity = new Quantity();//calculated qty of all items in the map

    private Quantity computeRequiredQuantity() {
        Quantity result = new Quantity();
        for (Quantity q : quantityList) {
            result = MetricCalculator.add(result, q);
        }
        return result;
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

    final MapChangeListener<String, Quantity> qtyMapChangeListener = new MapChangeListener<String, Quantity>() {
        @Override
        public void onChanged(MapChangeListener.Change<? extends String, ? extends Quantity> change) {
            if (change.wasAdded()) {
                final Quantity val = change.getValueAdded();
                quantityList.add(val);
            }
            if (change.wasRemoved()) {
                final Quantity val = change.getValueRemoved();
                quantityList.remove(val);
            }
            //   stockQuantity = countStockQuantity(stockCardService.getStockCardByItemCd(rawMaterial.getCode()));
            requiredQuantity = computeRequiredQuantity();
            //    reserved = countReserved(stockCardTxnService.getReservedAndApprovedtByItemCd(rawMaterial.getCode()));
            //    remainingQuantity = countRemainingQuantity();

        }
    };

    ObservableMap<String, Quantity> qtyMap;

    {
        final Map<String, Quantity> map = new TreeMap();
        qtyMap = FXCollections.observableMap(map);
        qtyMap.addListener(qtyMapChangeListener);
    }

    public Quantity getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Quantity requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public BatchRm() {

    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

//    public Quantity getRemainingQuantity() {
//        return remainingQuantity;
//    }
//
//    public void setRemainingQuantity(Quantity remainingQuantity) {
//        this.remainingQuantity = remainingQuantity;
//    }
//    public Quantity getReserved() {
//        return reserved;
//    }
//
//    public void setReserved(Quantity reserved) {
//        this.reserved = reserved;
//    }
    public void addToMap(String hashcode, Quantity qty) {
        qtyMap.put(hashcode, qty);

    }

    public void removeFromMap(String hashcode) {
        qtyMap.remove(hashcode);
    }

    @Override
    public String toString() {
        return requiredQuantity.toString();
    }

}
