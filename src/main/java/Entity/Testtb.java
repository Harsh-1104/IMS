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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "testtb")
@NamedQueries({
    @NamedQuery(name = "Testtb.findAll", query = "SELECT t FROM Testtb t"),
    @NamedQuery(name = "Testtb.findByTestId", query = "SELECT t FROM Testtb t WHERE t.testId = :testId"),
    @NamedQuery(name = "Testtb.findByTestName", query = "SELECT t FROM Testtb t WHERE t.testName = :testName"),
    @NamedQuery(name = "Testtb.findByTestType", query = "SELECT t FROM Testtb t WHERE t.testType = :testType")})
public class Testtb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "test_id")
    private Integer testId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "test_name")
    private String testName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "test_type")
    private String testType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testId")
    private Collection<Qatb> qatbCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testId")
    private Collection<Score> scoreCollection;

    public Testtb() {
    }

    public Testtb(Integer testId) {
        this.testId = testId;
    }

    public Testtb(Integer testId, String testName, String testType) {
        this.testId = testId;
        this.testName = testName;
        this.testType = testType;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
    
    @JsonbTransient
    public Collection<Qatb> getQatbCollection() {
        return qatbCollection;
    }
    
    @JsonbTransient
    public void setQatbCollection(Collection<Qatb> qatbCollection) {
        this.qatbCollection = qatbCollection;
    }
    
    @JsonbTransient
    public Collection<Score> getScoreCollection() {
        return scoreCollection;
    }
    
    @JsonbTransient
    public void setScoreCollection(Collection<Score> scoreCollection) {
        this.scoreCollection = scoreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testId != null ? testId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Testtb)) {
            return false;
        }
        Testtb other = (Testtb) object;
        if ((this.testId == null && other.testId != null) || (this.testId != null && !this.testId.equals(other.testId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Testtb[ testId=" + testId + " ]";
    }
    
}
