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
import javax.ws.rs.core.Response;

/**
 *
 * @author HARSH
 */
@Named(value = "profile")
@RequestScoped
public class profile {
   
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

    public profile() {

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

    public Company getCompany() {
        String cid = getSession().getAttribute("uid").toString();
        this.company = cb.displayCompanybycid(cid);
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
        String sid = getSession().getAttribute("uid").toString();
        this.student = sb.displayStudentByID(sid);
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Collection<Student> getStudentlist() {
        studentlist = sb.displayStudent();
        return studentlist;
    }

    public void setStudentlist(Collection<Student> studentlist) {
        this.studentlist = studentlist;
    }
}
