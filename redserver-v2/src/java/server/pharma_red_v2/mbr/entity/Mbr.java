/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.entity;

import com.google.gson.annotations.Expose;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import server.pharma_red_v2._main.entity.Product;
import server.pharma_red_v2._main.entity.Unit;
import server.pharma_red_v2.transaction.entity.StockCardTxn;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "mbr", schema = "mbr")
@XmlRootElement
public class Mbr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne
    private Product productId;

    //for audit entry only
    @Expose
    @Column(name = "product_id", insertable = false, updatable = false)
    private int product_id;

    @Expose
    @Column(name = "batch_size")
    private Double batchSize;

    @Expose
    @Size(max = 10)
    @Column(name = "batch_no")
    private String batchNo;

    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    @ManyToOne
    private Unit unitId;

    //for audit only
    @Expose
    @Column(name = "unit_id", insertable = false, updatable = false)
    private short unit_id;

    @Expose
    @Column(name = "mfg_date")
    @Temporal(TemporalType.DATE)
    private Date mfgDate;

    @Expose
    @Column(name = "exp_date")
    @Temporal(TemporalType.DATE)
    private Date expDate;

    @Expose
    @Size(max = 15)
    @Column(name = "po_no")
    private String poNo;

    @OneToMany(mappedBy = "mbrId")
    private List<StockCardTxn> stockCardTxnList;

    @Expose
    @Column(name = "status")
    @Size(max = 20)
    private String status;

    public Mbr() {
    }

    public Mbr(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Double batchSize) {
        this.batchSize = batchSize;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
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

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<StockCardTxn> getStockCardTxnList() {
        return stockCardTxnList;
    }

    public void setStockCardTxnList(List<StockCardTxn> stockCardTxnList) {
        this.stockCardTxnList = stockCardTxnList;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public short getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(short unit_id) {
        this.unit_id = unit_id;
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
        if (!(object instanceof Mbr)) {
            return false;
        }
        Mbr other = (Mbr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server.mbr.entity.Mbr[ id=" + id + " ]";
    }

}
