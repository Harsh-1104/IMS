/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SB;

import Entity.Application;
import Entity.Company;
import Entity.Department;
import Entity.Internship;
import Entity.Role;
import Entity.Student;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author HARSH
 */
@Stateless
public class StudentBean {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    public static String GetUniqueID(int length) {
        String uuidString = UUID.randomUUID().toString().replace("-", "");
        return uuidString.substring(0, Math.min(uuidString.length(), length));
    }

    // Student Display
    public Collection<Student> displayStudent() {
        return em.createNamedQuery("Student.findAll").getResultList();
    }

    // Student Display by ID
    public Student displayStudentByID(String sid) {
        List<Student> resultList = em.createNamedQuery("Student.findBySid").setParameter("sid", sid).getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    Pbkdf2PasswordHashImpl pb;

    // Insert Student
    public void insertStudent(String sid, String sname, String semail, String spwd, String sphone, String spref) {
        pb = new Pbkdf2PasswordHashImpl();
        String enc = pb.generate(spwd.toCharArray());

        Role role = em.find(Role.class, 1);
        Student stud = new Student();

        stud.setSid(sid);
        stud.setSname(sname);
        stud.setEmail(semail);
        stud.setPassword(enc);
        stud.setPhone(sphone);
        stud.setPreference(spref);
        stud.setRoleID(role);

        em.persist(stud);
        em.merge(role);
    }

    // Delete Student
    public void deleteStudent(String id) {
        Student stud = em.find(Student.class, id);
        em.remove(stud);
    }

    // Department Display
    public Collection<Department> displayDepartment() {
        return em.createNamedQuery("Department.findAll").getResultList();
    }

    // Insert Department
    public void insertDepartment(String dname) {
        Department dept = new Department();
        dept.setDName(dname);
        em.persist(dept);
    }

    // Application Display
    public Collection<Application> displayApplication() {
        return em.createNamedQuery("Application.findAll").getResultList();
    }

    // Application Display by ID
    public Application displayApplicationByID(String aid) {
        List<Application> resultList = em.createNamedQuery("Application.findByAid").setParameter("aid", aid).getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public Application displayApplicationByIID(String iid) {
        List<Application> resultList = em.createNamedQuery("Application.findByiid").setParameter("iid", iid).getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public Collection<Application> displayApprovedApplicationBySID(String sid) {
        Student s = em.find(Student.class, sid);
        return em.createNamedQuery("Application.findByApprovedandsid").setParameter("sid", s).getResultList();
    }

    public Collection<Application> displayRejectedApplicationBySID(String sid) {
        Student s = em.find(Student.class, sid);
        return em.createNamedQuery("Application.findByRejectedandsid").setParameter("sid", s).getResultList();
    }

    public Collection<Application> displayPendingApplicationBySID(String sid) {
        Student s = em.find(Student.class, sid);
        return em.createNamedQuery("Application.findByPendingandsid").setParameter("sid", s).getResultList();
    }

    public Collection<Application> displayApplicationBySID(String sid) {
        Student s = em.find(Student.class, sid);
        return em.createNamedQuery("Application.findBySid").setParameter("sid", s).getResultList();
    }

    public Collection<Application> displayApplicationByCID(String cid) {
        Company c = em.find(Company.class, cid);
        return em.createNamedQuery("Application.findBycid").setParameter("cid", c).getResultList();
    }

    // Delete Application
    public void deleteApplication(String id) {
        Application stud = em.find(Application.class, id);
        em.remove(stud);
    }

    public Collection<Application> displayApplicationByStatus(String status) {
        return em.createNamedQuery("Application.findByStatus", Application.class).setParameter("status", status).getResultList();
    }

    public void insertAppliction(String sid, String iid, String status, String adate, String cid) {
        Student s = em.find(Student.class, sid);
        Internship i = em.find(Internship.class, iid);
        Company c = em.find(Company.class, cid);
        Department d = em.find(Department.class, 1);
        Application a = new Application();
        String numericUUID = GetUniqueID(12);

        a.setAid(numericUUID);
        a.setSid(s);
        a.setIid(i);
        a.setStatus(status);
        a.setApplicationDate(adate);
        a.setCid(c);
        a.setDeptId(d);

        em.persist(a);
        em.merge(s);
        em.merge(i);
        em.merge(c);
        em.merge(d);
    }
}
