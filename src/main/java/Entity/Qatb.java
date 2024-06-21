/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pooja
 */
@Entity
@Table(name = "qatb")
@NamedQueries({
    @NamedQuery(name = "Qatb.findAll", query = "SELECT q FROM Qatb q"),
    @NamedQuery(name = "Qatb.findByQaId", query = "SELECT q FROM Qatb q WHERE q.qaId = :qaId"),
    @NamedQuery(name = "Qatb.findByQuestion", query = "SELECT q FROM Qatb q WHERE q.question = :question"),
    @NamedQuery(name = "Qatb.findByOpt1", query = "SELECT q FROM Qatb q WHERE q.opt1 = :opt1"),
    @NamedQuery(name = "Qatb.findByOpt2", query = "SELECT q FROM Qatb q WHERE q.opt2 = :opt2"),
    @NamedQuery(name = "Qatb.findByOpt3", query = "SELECT q FROM Qatb q WHERE q.opt3 = :opt3"),
    @NamedQuery(name = "Qatb.findByOpt4", query = "SELECT q FROM Qatb q WHERE q.opt4 = :opt4")})
public class Qatb implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qa_id")
    private Integer qaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "question")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "opt1")
    private String opt1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "opt2")
    private String opt2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "opt3")
    private String opt3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "opt4")
    private String opt4;
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    @ManyToOne(optional = false)
    private Testtb testId;

    public Qatb() {
    }

    public Qatb(Integer qaId) {
        this.qaId = qaId;
    }

    public Qatb(Integer qaId, String question, String opt1, String opt2, String opt3, String opt4) {
        this.qaId = qaId;
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
    }

    public Integer getQaId() {
        return qaId;
    }

    public void setQaId(Integer qaId) {
        this.qaId = qaId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public Testtb getTestId() {
        return testId;
    }

    public void setTestId(Testtb testId) {
        this.testId = testId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qaId != null ? qaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qatb)) {
            return false;
        }
        Qatb other = (Qatb) object;
        if ((this.qaId == null && other.qaId != null) || (this.qaId != null && !this.qaId.equals(other.qaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Qatb[ qaId=" + qaId + " ]";
    }
    
}
