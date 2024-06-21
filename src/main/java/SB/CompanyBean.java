/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SB;

import Entity.Application;
import java.util.UUID;
import Entity.Company;
import Entity.Feedback;
import Entity.Internship;
import Entity.Role;
import Entity.Student;
import Entity.Usertb;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author pooja
 */
@Stateless
public class CompanyBean {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public static String GetUniqueID(int length) {
        String uuidString = UUID.randomUUID().toString().replace("-", "");
        return uuidString.substring(0, Math.min(uuidString.length(), length));
    }

    public Collection<Company> displayCompany() {
        return em.createNamedQuery("Company.findAll").getResultList();
    }
    
    public Company displayCompanybycid(String cid) {
        List<Company> resultList = em.createNamedQuery("Company.findByCid").setParameter("cid", cid).getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public void deleteCompany(String cid) {
        Company c = em.find(Company.class, cid);
        em.remove(c);
    }

    Pbkdf2PasswordHashImpl pb;

    public void insertCompany(String cid, String cname, String email, String password, String address, String contact_details, String company_details, Integer roleID) {
        pb = new Pbkdf2PasswordHashImpl();
        String enc = pb.generate(password.toCharArray());

        Role r = em.find(Role.class, 3);
        Collection<Company> cc = r.getCompanyCollection();
        Company c = new Company();

        c.setCid(cid);
        c.setCname(cname);
        c.setEmail(email);
        c.setPassword(enc);
        c.setAddress(address);
        c.setContactDetails(contact_details);
        c.setCompanyDetails(company_details);
        c.setRoleID(r);

        em.persist(c);
        em.merge(r);
    }

    public Collection<Internship> displayInternship() {
        return em.createNamedQuery("Internship.findAll").getResultList();
    }

    public Collection<Internship> displayInternshipByCID(String cid) {
        Company c = em.find(Company.class, cid);
        return em.createNamedQuery("Internship.findByCid").setParameter("cid", c).getResultList();
    }

    public void deleteInternship(String iid) {
        Internship i = em.find(Internship.class, iid);
        em.remove(i);
    }

    public Collection<Feedback> displayFeedback() {
        return em.createNamedQuery("Feedback.findAll").getResultList();
    }

    public void insertInternship(String cid, String title, String description, String startDate, String endDate, Boolean stipend){
        String iid = GetUniqueID(12);
        Company c = em.find(Company.class, cid);

        Internship i = new Internship();

        i.setIid(iid);
        i.setCid(c);
        i.setTitle(title);
        i.setDescription(description);
        i.setStartDate(startDate);
        i.setEndDate(endDate);
        i.setStipend(stipend);

        em.persist(i);
        em.merge(c);
    }

    public void insertUsertb(String uid, String email, String password, Integer roleID) {
        pb = new Pbkdf2PasswordHashImpl();
        String enc = pb.generate(password.toCharArray());

        Role r = em.find(Role.class, roleID);
        Usertb u = new Usertb();

        u.setUid(uid);
        u.setEmail(email);
        u.setPassword(enc);
        u.setRoleID(r);

        em.persist(u);
        em.merge(r);
    }
    
    public void deleteUser(String uid) {
       Usertb u = em.find(Usertb.class, uid);
       em.remove(u);
    }
    
    public String getUserIDByEmail(String email) {
        try {
            Usertb u = em.createQuery("SELECT u FROM Usertb u WHERE u.email = :email", Usertb.class).setParameter("email", email).getSingleResult();
            return u.getUid();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ChangeStatus(String aid, String status) {
        Application a = em.find(Application.class, aid);
        a.setStatus(status);
        em.merge(a);
    }
    
    public Student getUserDetailByUID(String email) {
        try {
            Student stud = em.createNamedQuery("Student.findByEmail", Student.class).setParameter("email", email).getSingleResult();
            return stud;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Company getCompanyDetailByUID(String email) {
        try {
            Company comp = em.createNamedQuery("Company.findByEmail", Company.class).setParameter("email", email).getSingleResult();
            return comp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
