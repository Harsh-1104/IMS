/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeans;

import Entity.Company;
import Entity.Student;
import SB.CompanyBean;
import SB.StudentBean;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author HARSH
 */
@Named(value = "dashboard")
@RequestScoped
public class dashboard {

    @EJB
    StudentBean sb;

    @EJB
    CompanyBean cb;

    Company company = new Company();
    Collection<Company> companylist;
    GenericType<Collection<Company>> gencompanylist;

    Student student = new Student();
    Collection<Student> studentlist;
    GenericType<Collection<Student>> genstudentlist;

    Integer totalInternship;
    Integer totalAppliedInternship;
    Integer totalApprovedInternship;
    Integer totalRejectdInternship;
    Integer totalPendingInternship;

    public dashboard() {
        companylist = new ArrayList<>();
        gencompanylist = new GenericType<Collection<Company>>() {
        };

        studentlist = new ArrayList<>();
        genstudentlist = new GenericType<Collection<Student>>() {
        };
    }
    
    private HttpSession getSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpSession) facesContext.getExternalContext().getSession(true);
    }

    public Integer getTotalInternship() {
        totalInternship = cb.displayInternship().size();
        return totalInternship;
    }

    public void setTotalInternship(Integer totalInternship) {
        this.totalInternship = totalInternship;
    }

    public Integer getTotalAppliedInternship() {
        String sid = getSession().getAttribute("uid").toString();
        totalAppliedInternship = sb.displayApplicationBySID(sid).size();
        return totalAppliedInternship;
    }

    public void setTotalAppliedInternship(Integer totalAppliedInternship) {
        this.totalAppliedInternship = totalAppliedInternship;
    }

    public Integer getTotalApprovedInternship() {
        String sid = getSession().getAttribute("uid").toString();
        totalApprovedInternship = sb.displayApprovedApplicationBySID(sid).size();
        return totalApprovedInternship;
    }

    public void setTotalApprovedInternship(Integer totalApprovedInternship) {
        this.totalApprovedInternship = totalApprovedInternship;
    }

    public Integer getTotalRejectdInternship() {
        String sid = getSession().getAttribute("uid").toString();
        totalRejectdInternship = sb.displayRejectedApplicationBySID(sid).size();
        return totalRejectdInternship;
    }

    public void setTotalRejectdInternship(Integer totalRejectdInternship) {
        this.totalRejectdInternship = totalRejectdInternship;
    }

    public Integer getTotalPendingInternship() {
        String sid = getSession().getAttribute("uid").toString();
        totalPendingInternship = sb.displayPendingApplicationBySID(sid).size();
        return totalPendingInternship;
    }

    public void setTotalPendingInternship(Integer totalPendingInternship) {
        this.totalPendingInternship = totalPendingInternship;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Collection<Company> getCompanylist() {
        return companylist;
    }

    public void setCompanylist(Collection<Company> companylist) {
        this.companylist = companylist;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Collection<Student> getStudentlist() {
        return studentlist;
    }

    public void setStudentlist(Collection<Student> studentlist) {
        this.studentlist = studentlist;
    }
}
