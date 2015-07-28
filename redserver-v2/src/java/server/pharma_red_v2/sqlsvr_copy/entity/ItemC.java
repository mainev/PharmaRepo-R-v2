/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.sqlsvr_copy.entity;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maine
 */
@Entity(name = "ItemC")
@Table(name = "item", schema = "sqlsvr_copy")
@XmlRootElement
public class ItemC implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 20)
    @Column(name ="item_cd")
    private String itemCd;
    
    @Size(max = 255)
    @Column(name = "descs")
    private String descs;
    
    @Size(max = 255)
    @Column(name = "remarks")
    private String remarks;
    
    @JoinColumn(name = "item_category_id", referencedColumnName = "id")
    @ManyToOne
    private ItemCategoryC itemCategoryId;
    
    @JoinColumn(name = "item_type_id", referencedColumnName = "id")
    @ManyToOne
    private ItemTypeC itemTypeId;
    
    @OneToMany(mappedBy = "itemId")
    private List<StockCardC> stockCardList;

    public ItemC() {
    }

    public ItemC(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemCd() {
        return itemCd;
    }

    public void setItemCd(String itemCd) {
        this.itemCd = itemCd;
    }

    
    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ItemCategoryC getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(ItemCategoryC itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public ItemTypeC getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(ItemTypeC itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    @XmlTransient
    public List<StockCardC> getStockCardList() {
        return stockCardList;
    }

    public void setStockCardList(List<StockCardC> stockCardList) {
        this.stockCardList = stockCardList;
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
        if (!(object instanceof ItemC)) {
            return false;
        }
        ItemC other = (ItemC) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Item[ id=" + id + " ]";
    }
    
}
