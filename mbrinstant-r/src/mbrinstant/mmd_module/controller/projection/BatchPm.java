/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.mmd_module.controller.projection;

import java.util.Map;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import mbrinstant.entity.main.PackagingMaterial;
import mbrinstant.utils.MetricCalculator;
import mbrinstant.utils.Quantity;

/**
 *
 * @author maine
 */
public class BatchPm {

    PackagingMaterial packagingMaterial;
    private ObservableList<Quantity> quantityList = FXCollections.observableArrayList();
    private Quantity requiredQuantity = new Quantity();//calculated qty of all items in the map

    public BatchPm() {
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

    private Quantity computeRequiredQuantity() {
        Quantity result = new Quantity();
        for (Quantity q : quantityList) {
            result = MetricCalculator.add(result, q);
        }
        return result;
    }

    public void addToMap(String hashcode, Quantity qty) {
        qtyMap.put(hashcode, qty);

    }

    public void removeFromMap(String hashcode) {
        qtyMap.remove(hashcode);
    }

    public PackagingMaterial getPackagingMaterial() {
        return packagingMaterial;
    }

    public void setPackagingMaterial(PackagingMaterial packagingMaterial) {
        this.packagingMaterial = packagingMaterial;
    }

    public ObservableList<Quantity> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(ObservableList<Quantity> quantityList) {
        this.quantityList = quantityList;
    }

    public Quantity getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(Quantity requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    @Override
    public String toString() {
        return requiredQuantity.toString();
    }
}
