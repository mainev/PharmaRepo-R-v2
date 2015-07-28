/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.mbr;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import mbrinstant.entity.main.Product;
import mbrinstant.entity.main.Unit;
import mbrinstant.utils.Quantity;

/**
 *
 * @author maine
 */
@XmlRootElement
public class Mbr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    private Integer id;
    @Expose
    private Product productId;
    @Expose
    private Double batchSize;
    @Expose
    private String batchNo;
    @Expose
    private Unit unitId;
    @Expose
    private Date mfgDate;
    @Expose
    private Date expDate;
    @Expose
    private String poNo;
    @Expose
    private String status;
    
    

    public Mbr(Product productId, double batchSize, String batchNo, Date mfgDate, Date expDate,
            String poNo, Unit unitId) {
        this.productId = productId;
        this.batchSize = batchSize;
        this.batchNo = batchNo;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
        this.poNo = poNo;
        this.unitId = unitId;
        
    }

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
