/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.main;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import mbrinstant.entity.mbr.ManufacturingProcedure;
import mbrinstant.entity.mbr.Udf;

/**
 *
 * @author maine
 */
@XmlRootElement
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Expose
    private Integer id;
    @Expose
    private String code;
    @Expose
    private String brandName;
    @Expose
    private String genericName;
    @Expose
    private String vrNo;
    @Expose
    private Short shelfLife;
    @Expose
    private Area areaId;
    @Expose
    private Classification classificationId;
    @Expose
    private Company clientId;
    @Expose
    private PackSize packSizeId;
    @Expose
    private Udf udfId;
    @Expose
    private ManufacturingProcedure manufacturingProcedureId;

    public Product(String code, String brandName, String genericName, String vrNo, Short shelfLife, Area areaId, Classification classificationId, Company clientId, PackSize packSizeId) {
        this.code = code;
        this.brandName = brandName;
        this.genericName = genericName;
        this.vrNo = vrNo;
        this.shelfLife = shelfLife;
        this.areaId = areaId;
        this.classificationId = classificationId;
        this.clientId = clientId;
        this.packSizeId = packSizeId;
    }

    public Product() {
    }

    public ManufacturingProcedure getManufacturingProcedureId() {
        return manufacturingProcedureId;
    }

    public void setManufacturingProcedureId(ManufacturingProcedure manufacturingProcedureId) {
        this.manufacturingProcedureId = manufacturingProcedureId;
    }

    public Udf getUdfId() {
        return udfId;
    }

    public void setUdfId(Udf udfId) {
        this.udfId = udfId;
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

    public Company getClientId() {
        return clientId;
    }

    public void setClientId(Company clientId) {
        this.clientId = clientId;
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
        return code + " : " + brandName + " - " + packSizeId;
    }

}
