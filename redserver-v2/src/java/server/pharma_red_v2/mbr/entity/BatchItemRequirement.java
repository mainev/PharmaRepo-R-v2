/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.mbr.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import server.pharma_red_v2.ItemReqStatusEnum;
import server.pharma_red_v2._main.entity.Unit;
import server.pharma_red_v2.sqlsvr_copy.entity.Item;
import server.pharma_red_v2.transaction.entity.StockCardTxn;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "batch_item_requirement", schema = "mbr")
@XmlRootElement
public class BatchItemRequirement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @ManyToOne
    private Item itemId;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "udf_qty")
    private Double udfQty;

    @JoinColumn(name = "udf_qty_unit_id", referencedColumnName = "id")
    @ManyToOne
    private Unit udfQtyUnitId;

    @Column(name = "required_qty")
    private Double requiredQty;

    @JoinColumn(name = "required_qty_unit_id", referencedColumnName = "id")
    @ManyToOne
    private Unit requiredQtyUnitId;

    @Column(name = "date_allocated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAllocated;

    @Column(name = "date_dispensed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDispensed;

    @Column(name = "part")
    private Short part;

    @Column(name = "item_req_status")
    @Enumerated(EnumType.STRING)
    private ItemReqStatusEnum itemReqStatus;

    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    @ManyToOne
    private Mbr batchId;

    @OneToMany(mappedBy = "batchItemReqId")
    private List<StockCardTxn> stockCardTxnList;

    public BatchItemRequirement() {
    }

    public BatchItemRequirement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public Double getUdfQty() {
        return udfQty;
    }

    public void setUdfQty(Double udfQty) {
        this.udfQty = udfQty;
    }

    public Unit getUdfQtyUnitId() {
        return udfQtyUnitId;
    }

    public void setUdfQtyUnitId(Unit udfQtyUnitId) {
        this.udfQtyUnitId = udfQtyUnitId;
    }

    public Unit getRequiredQtyUnitId() {
        return requiredQtyUnitId;
    }

    public void setRequiredQtyUnitId(Unit requiredQtyUnitId) {
        this.requiredQtyUnitId = requiredQtyUnitId;
    }

    public Double getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(Double requiredQty) {
        this.requiredQty = requiredQty;
    }

    public Date getDateAllocated() {
        return dateAllocated;
    }

    public void setDateAllocated(Date dateAllocated) {
        this.dateAllocated = dateAllocated;
    }

    public Date getDateDispensed() {
        return dateDispensed;
    }

    public void setDateDispensed(Date dateDispensed) {
        this.dateDispensed = dateDispensed;
    }

    public Short getPart() {
        return part;
    }

    public void setPart(Short part) {
        this.part = part;
    }

    public ItemReqStatusEnum getItemReqStatus() {
        return itemReqStatus;
    }

    public void setItemReqStatus(ItemReqStatusEnum itemReqStatus) {
        this.itemReqStatus = itemReqStatus;
    }

    @XmlTransient
    @JsonIgnore
    public Mbr getBatchId() {
        return batchId;
    }

    public void setBatchId(Mbr batchId) {
        this.batchId = batchId;
    }

    public List<StockCardTxn> getStockCardTxnList() {
        return stockCardTxnList;
    }

    public void setStockCardTxnList(List<StockCardTxn> stockCardTxnList) {
        this.stockCardTxnList = stockCardTxnList;
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
        if (!(object instanceof BatchItemRequirement)) {
            return false;
        }
        BatchItemRequirement other = (BatchItemRequirement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BatchItemRequirement[ id=" + id + " ]";
    }

}
