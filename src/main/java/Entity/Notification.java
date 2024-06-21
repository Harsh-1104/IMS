/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pooja
 */
@Entity
@Table(name = "notification")
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findByNId", query = "SELECT n FROM Notification n WHERE n.nId = :nId"),
    @NamedQuery(name = "Notification.findByNHeader", query = "SELECT n FROM Notification n WHERE n.nHeader = :nHeader"),
    @NamedQuery(name = "Notification.findByNBody", query = "SELECT n FROM Notification n WHERE n.nBody = :nBody"),
    @NamedQuery(name = "Notification.findByNTime", query = "SELECT n FROM Notification n WHERE n.nTime = :nTime"),
    @NamedQuery(name = "Notification.findByStatus", query = "SELECT n FROM Notification n WHERE n.status = :status"),
    @NamedQuery(name = "Notification.findBySid", query = "SELECT n FROM Notification n WHERE n.sid = :sid")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "n_id")
    private Integer nId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "n_header")
    private String nHeader;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "n_body")
    private String nBody;
    @Basic(optional = false)
    @NotNull
    @Column(name = "n_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "sid")
    private String sid;

    public Notification() {
    }

    public Notification(Integer nId) {
        this.nId = nId;
    }

    public Notification(Integer nId, String nHeader, String nBody, Date nTime, boolean status, String sid) {
        this.nId = nId;
        this.nHeader = nHeader;
        this.nBody = nBody;
        this.nTime = nTime;
        this.status = status;
        this.sid = sid;
    }

    public Integer getNId() {
        return nId;
    }

    public void setNId(Integer nId) {
        this.nId = nId;
    }

    public String getNHeader() {
        return nHeader;
    }

    public void setNHeader(String nHeader) {
        this.nHeader = nHeader;
    }

    public String getNBody() {
        return nBody;
    }

    public void setNBody(String nBody) {
        this.nBody = nBody;
    }

    public Date getNTime() {
        return nTime;
    }

    public void setNTime(Date nTime) {
        this.nTime = nTime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nId != null ? nId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.nId == null && other.nId != null) || (this.nId != null && !this.nId.equals(other.nId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Notification[ nId=" + nId + " ]";
    }
    
}
