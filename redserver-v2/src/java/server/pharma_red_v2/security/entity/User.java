/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.security.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author maine
 */
@Entity
@Table(name = "user", schema = "security")
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "email_ad", unique = true)
    private String emailAd;
    @Size(max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 100)
    @Column(name = "position")
    private String position;
    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "user_group", schema = "security",
            joinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "group_id", referencedColumnName = "id")})
    private List<Group1> groupList;

    @ManyToMany
    @JoinTable(name = "user_role", schema = "security",
            joinColumns = {
                @JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roleList;

    public User() {
    }

    public User(String emailAd, String password) {
        this.emailAd = emailAd;
        this.password = password;
    }

    public User(Integer id) {
        this.id = id;
    }

    public List<Group1> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group1> groupList) {
        this.groupList = groupList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailAd() {
        return emailAd;
    }

    public void setEmailAd(String emailAd) {
        this.emailAd = emailAd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public boolean isAdmin() {
        for (Group1 g : getGroupList()) {
            if (g.getGroupName().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "server.pharma_red_v2.security.entity.User[ id=" + id + " ]";
    }

}
