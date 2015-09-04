/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrinstant.entity.line_clearance;

import java.io.Serializable;

/**
 *
 * @author maine
 */
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;
    private Short id;
    private String code;
    private String description;

    public Type() {
    }

    public Type(Short id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.line_clearance.Type[ id=" + id + " ]";
    }

}
