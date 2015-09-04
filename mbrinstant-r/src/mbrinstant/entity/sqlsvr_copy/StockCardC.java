/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.sqlsvr_copy;

import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.List;
import mbrinstant.entity.StockStatus;
import mbrinstant.entity.main.Unit;
import mbrinstant.entity.transaction.StockCardTxn;
import mbrinstant.rest_client.main.SingletonUnitRestClient;
import mbrinstant.utils.MetricCalculator;
import mbrinstant.utils.Quantity;

/**
 *
 * @author maine
 */
public class StockCardC {

    @Expose
    private Integer id;
    @Expose
    private String inoutMode;
    @Expose
    private Double unitCost;
    @Expose
    private Double qty;
    @Expose
    private String lotNo;
    @Expose
    private Date mfgDate;
    @Expose
    private Date expDate;
    @Expose
    private String controlNo;
    @Expose
    private String status;
    @Expose
    private String uom;
    @Expose
    private Company companyId;
    @Expose
    private Item itemId;
    @Expose
    private WarehouseC warehouseId;
    @Expose
    private StockStatus stockStatus;

    private List<StockCardTxn> stockCardTxnList;

    private Quantity quantity;

    public StockCardC() {
    }

    public StockCardC(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInoutMode() {
        return inoutMode;
    }

    public void setInoutMode(String inoutMode) {
        this.inoutMode = inoutMode;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public Date getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getControlNo() {
        return controlNo;
    }

    public void setControlNo(String controlNo) {
        this.controlNo = controlNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public WarehouseC getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseC warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    public List<StockCardTxn> getStockCardTxnList() {
        return stockCardTxnList;
    }

    public void setStockCardTxnList(List<StockCardTxn> stockCardTxnList) {
        this.stockCardTxnList = stockCardTxnList;
    }

    public Unit getEquivalentUnit(String unitName) throws Exception {

        List<Unit> unitList = SingletonUnitRestClient.getInstance().getUnitList();
        for (Unit u : unitList) {
            if (u.getName().toUpperCase().trim().equals(unitName.toUpperCase().trim())) {
                return u;
            }
        }
        return null;//throw exception
    }

    public Quantity getStockQuantity() throws Exception {
        return new Quantity(qty, getEquivalentUnit(uom).getName());
    }

    public Quantity getAvailableQuantity() throws Exception {
        Quantity used = new Quantity();
        for (StockCardTxn stxn : stockCardTxnList) {
//            String stat = stxn.getMbrId().getStatus();
//
//            if (!stat.equalsIgnoreCase(MbrStatus.PENDING.toString())) {
//                used = MetricCalculator.add(used, new Quantity(stxn.getQty(), stxn.getUnitId().getName()));
//            }

            used = MetricCalculator.add(used, new Quantity(stxn.getQty(), stxn.getUnitId().getName()));
        }

        return MetricCalculator.subtract(getStockQuantity(), used);
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StockCardC)) {
            return false;
        }
        StockCardC other = (StockCardC) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StockCard[ id=" + id + " ]";
    }

}
