/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.controller.projection;

import java.util.Map;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import mbrinstant.entity.main.RawMaterial;
import mbrinstant.utils.MetricCalculator;
import mbrinstant.utils.Quantity;

/**
 *
 * @author maine
 */
public class BatchRm {

    RawMaterial rawMaterial;
    double stock;
    String uom;//for stock

    private ObservableList<Quantity> quantityList = FXCollections.observableArrayList();
    MetricCalculator metricC = new MetricCalculator();
    private Quantity requiredQuantity = new Quantity();//calculated qty of all items in the map

    private Quantity computeRequiredQuantity() {
        Quantity result = new Quantity();
        for (Quantity q : quantityList) {
            result = metricC.add(result, q);
        }
        return result;
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
            requiredQuantity = computeRequiredQuantity();
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

    public BatchRm(RawMaterial rawMaterial, double stock, double required) {
        this.rawMaterial = rawMaterial;
        this.stock = stock;
        //    this.required = required;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

//    public double getRequired() {
//        return required;
//    }
//
//    public void setRequired(double required) {
//        this.required = required;
//    }
    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

//    public String getRequiredUom() {
//        return requiredUom;
//    }
//
//    public void setRequiredUom(String requiredUom) {
//        this.requiredUom = requiredUom;
//    }
    public void addToMap(String poNo, Quantity qty) {
        qtyMap.put(poNo, qty);

    }

    public void removeFromMap(String poNo) {
        qtyMap.remove(poNo);
    }

    @Override
    public String toString() {
        return requiredQuantity.toString();
    }

}
