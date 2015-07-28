/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.sqlsvr_copy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server.pharma_red_v2.transaction.entity.StockCardTxn;

/**
 *
 * @author maine
 */
@Entity(name="StockCardC")
@Table(name = "stock_card", schema="sqlsvr_copy")
@XmlRootElement
public class StockCardC implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 1)
    @Column(name = "inout_mode")
    private String inoutMode; 
    
    @Column(name = "unit_cost")
    private Double unitCost;
    
    @Column(name = "qty")
    private Double qty;
    
    @Size(max = 100)
    @Column(name = "lot_no")
    private String lotNo;
    
    @Column(name = "mfg_date")
    @Temporal(TemporalType.DATE)
    private Date mfgDate;
    
    @Column(name = "exp_date")
    @Temporal(TemporalType.DATE)
    private Date expDate;
    
    @Size(max = 20)
    @Column(name = "control_no")
    private String controlNo;
    
    @Size(max = 10)
    @Column(name = "status")
    private String status;
    
    @Size(max = 10)
    @Column(name = "uom")
    private String uom;
    
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
    private CompanyC companyId;
    
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne
    private ItemC itemId;
    
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    @ManyToOne
    private WarehouseC warehouseId;
    
    @OneToMany(mappedBy = "stockCardId")
    private List<StockCardTxn> stockCardTxnList;
    
    @Column(name = "stock_status")
    @Size(max = 10)
    private String stockStatus;

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

    public CompanyC getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyC companyId) {
        this.companyId = companyId;
    }

    public ItemC getItemId() {
        return itemId;
    }

    public void setItemId(ItemC itemId) {
        this.itemId = itemId;
    }

    public WarehouseC getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseC warehouseId) {
        this.warehouseId = warehouseId;
    }

  
    public List<StockCardTxn> getStockCardTxnList() {
        return stockCardTxnList;
    }

    public void setStockCardTxnList(List<StockCardTxn> stockCardTxnList) {
        this.stockCardTxnList = stockCardTxnList;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
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
