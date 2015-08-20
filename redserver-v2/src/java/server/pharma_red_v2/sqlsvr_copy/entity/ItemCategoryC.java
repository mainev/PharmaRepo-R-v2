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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maine
 */
@Entity(name = "ItemCategoryC")
@Table(name = "item_category", schema="sqlsvr_copy")
@XmlRootElement
public class ItemCategoryC implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Size(max = 10)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "descs")
    private String descs;
    @OneToMany(mappedBy = "itemCategoryId")
    private List<Item> itemList;
    @JoinColumn(name = "item_class_id", referencedColumnName = "id")
    @ManyToOne
    private ItemClassC itemClassId;

    public ItemCategoryC() {
    }

    public ItemCategoryC(Short id) {
        this.id = id;
    }

    public ItemCategoryC(Short id, String code) {
        this.id = id;
        this.code = code;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    @XmlTransient
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public ItemClassC getItemClassId() {
        return itemClassId;
    }

    public void setItemClassId(ItemClassC itemClassId) {
        this.itemClassId = itemClassId;
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
        if (!(object instanceof ItemCategoryC)) {
            return false;
        }
        ItemCategoryC other = (ItemCategoryC) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ItemCategory[ id=" + id + " ]";
    }
    
}
