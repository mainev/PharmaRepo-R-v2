/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2._main.entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import server.ProductLogListener;
import server.pharma_red_v2.mbr.entity.ManufacturingProcedure;
import server.pharma_red_v2.mbr.entity.Udf;
import server.pharma_red_v2.sqlsvr_copy.entity.Company;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "product", schema = "main")
@XmlRootElement
@EntityListeners(ProductLogListener.class)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Expose
    @Size(max = 5)
    @Column(name = "code")
    private String code;

    @Expose
    @Size(max = 200)
    @Column(name = "brand_name")
    private String brandName;

    @Expose
    @Size(max = 200)
    @Column(name = "generic_name")
    private String genericName;

    @Expose
    @Size(max = 10)
    @Column(name = "vr_no")
    private String vrNo;

    @Expose
    @Column(name = "shelf_life")
    private Short shelfLife;

    @Expose
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    @ManyToOne
    private Area areaId;

    @Expose
    @JoinColumn(name = "classification_id", referencedColumnName = "id")
    @ManyToOne
    private Classification classificationId;

    @Expose
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
    private Company companyId;

    @Expose
    @JoinColumn(name = "pack_size_id", referencedColumnName = "id")
    @ManyToOne
    private PackSize packSizeId;

    @OneToOne(mappedBy = "productId")
    private ManufacturingProcedure manufacturingProcedureId;

    @OneToOne(mappedBy = "productId")
    private Udf udfId;

    public Product() {

    }

    public Udf getUdfId() {
        return udfId;
    }

    public void setUdfId(Udf udfId) {
        this.udfId = udfId;
    }

    public ManufacturingProcedure getManufacturingProcedureId() {
        return manufacturingProcedureId;
    }

    public void setManufacturingProcedureId(ManufacturingProcedure manufacturingProcedureId) {
        this.manufacturingProcedureId = manufacturingProcedureId;
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getVrNo() {
        return vrNo;
    }

    public void setVrNo(String vrNo) {
        this.vrNo = vrNo;
    }

    public Short getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Short shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Area getAreaId() {
        return areaId;
    }

    public void setAreaId(Area areaId) {
        this.areaId = areaId;
    }

    public Classification getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Classification classificationId) {
        this.classificationId = classificationId;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public PackSize getPackSizeId() {
        return packSizeId;
    }

    public void setPackSizeId(PackSize packSizeId) {
        this.packSizeId = packSizeId;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "server._main.entity.Product[ id=" + id + " ]";
    }

}
