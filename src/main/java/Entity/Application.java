/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pooja
 */
@Entity
@Table(name = "application")
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a"),
    @NamedQuery(name = "Application.findByAid", query = "SELECT a FROM Application a WHERE a.aid = :aid"),
    @NamedQuery(name = "Application.findBySid", query = "SELECT a FROM Application a WHERE a.sid = :sid"),
    @NamedQuery(name = "Application.findBycid", query = "SELECT a FROM Application a WHERE a.cid = :cid"),
    @NamedQuery(name = "Application.findByStatus", query = "SELECT a FROM Application a WHERE a.status = :status"),
    @NamedQuery(name = "Application.findByApprovedandsid", query = "SELECT a FROM Application a WHERE a.status = 'Approved' AND a.sid = :sid"),
    @NamedQuery(name = "Application.findByRejectedandsid", query = "SELECT a FROM Application a WHERE a.status = 'Rejected' AND a.sid = :sid"),
    @NamedQuery(name = "Application.findByPendingandsid", query = "SELECT a FROM Application a WHERE a.status = 'Pending' AND a.sid = :sid"),
    @NamedQuery(name = "Application.findByApplicationDate", query = "SELECT a FROM Application a WHERE a.applicationDate = :applicationDate")})
public class Application implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "aid")
    private String aid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "application_date")
    private String applicationDate;
    @JoinColumn(name = "cid", referencedColumnName = "cid")
    @ManyToOne(optional = false)
    private Company cid;
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    @ManyToOne(optional = false)
    private Department deptId;
    @JoinColumn(name = "iid", referencedColumnName = "iid")
    @ManyToOne(optional = false)
    private Internship iid;
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @ManyToOne(optional = false)
    private Student sid;

    public Application() {
    }

    public Application(String aid) {
        this.aid = aid;
    }

    public Application(String aid, String status, String applicationDate) {
        this.aid = aid;
        this.status = status;
        this.applicationDate = applicationDate;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Company getCid() {
        return cid;
    }

    public void setCid(Company cid) {
        this.cid = cid;
    }

    public Department getDeptId() {
        return deptId;
    }

    public void setDeptId(Department deptId) {
        this.deptId = deptId;
    }

    public Internship getIid() {
        return iid;
    }

    public void setIid(Internship iid) {
        this.iid = iid;
    }

    public Student getSid() {
        return sid;
    }

    public void setSid(Student sid) {
        this.sid = sid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Application[ aid=" + aid + " ]";
    }

}
