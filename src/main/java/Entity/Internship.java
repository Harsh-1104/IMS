/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pooja
 */
@Entity
@Table(name = "internship")
@NamedQueries({
    @NamedQuery(name = "Internship.findAll", query = "SELECT i FROM Internship i"),
    @NamedQuery(name = "Internship.findByIid", query = "SELECT i FROM Internship i WHERE i.iid = :iid"),
    @NamedQuery(name = "Internship.findByCid", query = "SELECT i FROM Internship i WHERE i.cid = :cid"),
    @NamedQuery(name = "Internship.findByTitle", query = "SELECT i FROM Internship i WHERE i.title = :title"),
    @NamedQuery(name = "Internship.findByDescription", query = "SELECT i FROM Internship i WHERE i.description = :description"),
    @NamedQuery(name = "Internship.findByStartDate", query = "SELECT i FROM Internship i WHERE i.startDate = :startDate"),
    @NamedQuery(name = "Internship.findByEndDate", query = "SELECT i FROM Internship i WHERE i.endDate = :endDate"),
    @NamedQuery(name = "Internship.findByStipend", query = "SELECT i FROM Internship i WHERE i.stipend = :stipend")})
public class Internship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "iid")
    private String iid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "StartDate")
    private String startDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EndDate")
    private String endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stipend")
    private boolean stipend;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iid")
    private Collection<Feedback> feedbackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iid")
    private Collection<Application> applicationCollection;
    @JoinColumn(name = "cid", referencedColumnName = "cid")
    @ManyToOne(optional = false)
    private Company cid;

    public Internship() {
    }

    public Internship(String iid) {
        this.iid = iid;
    }

    public Internship(String iid, String title, String description, String startDate, String endDate, boolean stipend) {
        this.iid = iid;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stipend = stipend;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean getStipend() {
        return stipend;
    }

    public void setStipend(boolean stipend) {
        this.stipend = stipend;
    }
    
    @JsonbTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }
    
    @JsonbTransient
    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }
    
    @JsonbTransient
    public Collection<Application> getApplicationCollection() {
        return applicationCollection;
    }
    
    @JsonbTransient
    public void setApplicationCollection(Collection<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }

    public Company getCid() {
        return cid;
    }

    public void setCid(Company cid) {
        this.cid = cid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iid != null ? iid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Internship)) {
            return false;
        }
        Internship other = (Internship) object;
        if ((this.iid == null && other.iid != null) || (this.iid != null && !this.iid.equals(other.iid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Internship[ iid=" + iid + " ]";
    }
    
}
