/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeans;

import Entity.Application;
import Entity.Company;
import Entity.Internship;
import Entity.Student;
import Entity.Usertb;
import RestClient.RC;
import SB.StudentBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pooja
 */
@Named(value = "cdb")
@RequestScoped
public class cdb {

    RC client;
    Usertb u = new Usertb();
    Collection<Usertb> cu;
    GenericType<Collection<Usertb>> gcu;

    Response rs;

    Company company = new Company();
    Collection<Company> companylist;
    GenericType<Collection<Company>> gencompanylist;

    Student student = new Student();
    Collection<Student> studentlist;
    GenericType<Collection<Student>> genstudentlist;

    Internship internship = new Internship();
    Collection<Internship> internshiplist;
    GenericType<Collection<Internship>> geninternshiplist;

    Collection<Internship> internshiplistbycid;
    GenericType<Collection<Internship>> geninternshiplistbycid;

    Application application = new Application();
    Collection<Application> applicationlist;
    GenericType<Collection<Application>> genapplicationlist;

    Collection<Application> applicationlistbysid;
    GenericType<Collection<Application>> genapplicationlistbysid;

    Collection<Application> filterapplicationlistbysid;
    GenericType<Collection<Application>> filtergenapplicationlistbysid;

    Collection<Application> applicationlistbystatus;
    GenericType<Collection<Application>> genapplicationlistbystatus;

    Collection<Application> adminapplicationlist;
    GenericType<Collection<Application>> admingenapplicationlist;

    String roleID;
    String cid;
    String status;
    String aid;
    String iid;

    Collection<NavItem> UsernavItems;
    Collection<NavItem> AdminnavItems;
    Collection<NavItem> CompanynavItems;

    @EJB
    StudentBean studB;

    public class NavItem {

        private String name;
        private String link;
        private String icon;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public NavItem(String name, String link, String icon) {
            this.name = name;
            this.link = link;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public cdb() {
        UsernavItems = new ArrayList<>();
        AdminnavItems = new ArrayList<>();
        CompanynavItems = new ArrayList<>();
        client = new RC();
        cu = new ArrayList();
        gcu = new GenericType<Collection<Usertb>>() {
        };

        companylist = new ArrayList<>();
        gencompanylist = new GenericType<Collection<Company>>() {
        };

        studentlist = new ArrayList<>();
        genstudentlist = new GenericType<Collection<Student>>() {
        };

        internshiplist = new ArrayList<>();
        geninternshiplist = new GenericType<Collection<Internship>>() {
        };

        internshiplistbycid = new ArrayList<>();
        geninternshiplistbycid = new GenericType<Collection<Internship>>() {
        };

        applicationlist = new ArrayList<>();
        genapplicationlist = new GenericType<Collection<Application>>() {
        };

        applicationlistbysid = new ArrayList<>();
        genapplicationlistbysid = new GenericType<Collection<Application>>() {
        };

        filterapplicationlistbysid = new ArrayList<>();
        filtergenapplicationlistbysid = new GenericType<Collection<Application>>() {
        };

        applicationlistbystatus = new ArrayList<>();
        genapplicationlistbystatus = new GenericType<Collection<Application>>() {
        };

        adminapplicationlist = new ArrayList<>();
        admingenapplicationlist = new GenericType<Collection<Application>>() {
        };

        this.roleID = "0";
        this.status = "";
    }

    public Collection<NavItem> getUsernavItems() {
        UsernavItems.add(new NavItem("Dashboard", "/IMSAPP/pages/studentDashboard.jsf", "ri-dashboard-fill"));
        UsernavItems.add(new NavItem("Company", "/IMSAPP/pages/company_list.jsf", "ri-building-2-fill"));
        UsernavItems.add(new NavItem("Internship", "/IMSAPP/pages/internshipList.jsf", "bx bxs-graduation"));
        UsernavItems.add(new NavItem("Application", "/IMSAPP/pages/display_Application.jsf", "mdi mdi-application"));
        return UsernavItems;
    }

    public void setUsernavItems(Collection<NavItem> UsernavItems) {
        this.UsernavItems = UsernavItems;
    }

    public Collection<NavItem> getAdminnavItems() {
        AdminnavItems.add(new NavItem("Dashboard", "/IMSAPP/pages/AdminDashboard.jsf", "ri-dashboard-fill"));
        AdminnavItems.add(new NavItem("Student", "/IMSAPP/pages/AdminStudent.jsf", "bx bxs-user-account"));
        AdminnavItems.add(new NavItem("Company", "/IMSAPP/pages/AdminCompany.jsf", "ri-building-2-fill"));
        AdminnavItems.add(new NavItem("Internship", "/IMSAPP/pages/AdminInternship.jsf", "bx bxs-graduation"));
        AdminnavItems.add(new NavItem("Application", "/IMSAPP/pages/AdminApplication.jsf", "mdi mdi-application"));
        return AdminnavItems;
    }

    public void setAdminnavItems(Collection<NavItem> AdminnavItems) {
        this.AdminnavItems = AdminnavItems;
    }

    public Collection<NavItem> getCompanynavItems() {
        CompanynavItems.add(new NavItem("Dashboard", "/IMSAPP/pages/companyDashboard.jsf", "ri-dashboard-fill"));
        CompanynavItems.add(new NavItem("Internship", "/IMSAPP/pages/display_Internship.jsf", "ri-dashboard-2-line"));
        CompanynavItems.add(new NavItem("Application", "/IMSAPP/pages/Application_by_company.jsf", "mdi mdi-application"));
        return CompanynavItems;
    }

    public void setCompanynavItems(Collection<NavItem> CompanynavItems) {
        this.CompanynavItems = CompanynavItems;
    }

    public static String GetUniqueID(int length) {
        String uuidString = UUID.randomUUID().toString().replace("-", "");
        return uuidString.substring(0, Math.min(uuidString.length(), length));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public Usertb getU() {
        return u;
    }

    public void setU(Usertb u) {
        this.u = u;
    }

    public Collection<Usertb> getCu() {
        return cu;
    }

    public void setCu(Collection<Usertb> cu) {
        this.cu = cu;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Collection<Company> getCompanylist() {
        rs = client.displayCompany(Response.class);
        companylist = rs.readEntity(gencompanylist);
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
        rs = client.displayStudentAPI(Response.class);
        studentlist = rs.readEntity(genstudentlist);
        return studentlist;
    }

    public void setStudentlist(Collection<Student> studentlist) {
        this.studentlist = studentlist;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }

    public Collection<Internship> getInternshiplist() {
        rs = client.displayInternship(Response.class);
        internshiplist = rs.readEntity(geninternshiplist);
        return internshiplist;
    }

    public void setInternshiplist(Collection<Internship> internshiplist) {
        this.internshiplist = internshiplist;
    }

    public Collection<Internship> getInternshiplistbycid() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String companyid = (String) session.getAttribute("uid");
        rs = client.displayInternshipByCIDAPI(Response.class, companyid);
        internshiplistbycid = rs.readEntity(geninternshiplistbycid);
        return internshiplistbycid;
    }

    public void setInternshiplistbycid(Collection<Internship> internshiplistbycid) {
        this.internshiplistbycid = internshiplistbycid;
    }

    public Collection<Application> getAdminapplicationlist() {
        rs = client.displayApplicationAPI(Response.class);
        adminapplicationlist = rs.readEntity(admingenapplicationlist);
        return adminapplicationlist;
    }

    public void setAdminapplicationlist(Collection<Application> adminapplicationlist) {
        this.adminapplicationlist = adminapplicationlist;
    }

    public Collection<Application> getApplicationlist() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String companyid = (String) session.getAttribute("uid");
        rs = client.displayApplicationByCIDAPI(Response.class, companyid);
        applicationlist = rs.readEntity(genapplicationlist);
        return applicationlist;
    }

    public void setApplicationlist(Collection<Application> applicationlist) {
        this.applicationlist = applicationlist;
    }

    public Collection<Application> getApplicationlistbystatus() {
        return applicationlistbystatus;
    }

    public void setApplicationlistbystatus(Collection<Application> applicationlistbystatus) {
        this.applicationlistbystatus = applicationlistbystatus;
    }

    public String toLogin() {
        return "/pages/login.jsf";
    }

    public String toRegister() {
        return "/pages/register.jsf";
    }

    public String insertUsertb() {
        String uniqueid = GetUniqueID(12);
        client.insertUsertb(uniqueid, u.getEmail(), u.getPassword(), roleID);
        if (roleID.equals("1")) {
            client.insertStudAPI(uniqueid, student.getSname(), u.getEmail(), u.getPassword(), student.getPhone(), student.getPreference());
        }
        if (roleID.equals("3")) {
            client.insertCompany(uniqueid, company.getCname(), u.getEmail(), u.getPassword(), company.getAddress(), company.getContactDetails(), company.getCompanyDetails(), roleID);
        }
        return "/pages/login.jsf";
    }

    public void SearchStatus() {
        if (status != "All") {
            rs = client.getApplicationsByStatus(Response.class, status);
            applicationlistbystatus = rs.readEntity(genapplicationlistbystatus);
        }
    }

    public String insertInternship() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        String companyid = (String) session.getAttribute("uid");

        client.insertInternship(companyid, internship.getTitle(), internship.getDescription(), internship.getStartDate(), internship.getEndDate(), String.valueOf(internship.getStipend()));
        try {
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/display_Internship.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getApplicationdata(Application a) {
        this.application = a;
        this.status = application.getStatus();
        return "Application_by_company.jsf";
    }

    public String deleteInternship(String iid) {
        client.deleteInternship(iid);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/display_Internship.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String AdmindeleteApplication(String aid) throws IOException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            client.deleteApplicationAPI(aid);
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/AdminApplication.jsf");
            return null;
        } catch (ClientErrorException e) {
            return null;
        }
    }

    public String AdmindeleteInternship(String iid) throws IOException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            client.deleteInternship(iid);
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/AdminInternship.jsf");
            return null;
        } catch (ClientErrorException e) {
            return null;
        }
    }

    public String AdmindeleteUser(String sid) throws IOException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            client.deleteUserAPI(sid);
            client.deleteStudentAPI(sid);
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/AdminStudent.jsf");
            return null;
        } catch (ClientErrorException e) {
            return null;
        }
    }

    public String AdmindeleteCompany(String cid) throws IOException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            client.deleteUserAPI(cid);
            client.deleteCompany(cid);
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/AdminCompany.jsf");
            return null;
        } catch (ClientErrorException e) {
            return null;
        }
    }

    public String ChangeStatus(String aid, String status) {
        client.ChangeStatus(aid, status);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/Application_by_company.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to get the current session
    private HttpSession getSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (HttpSession) facesContext.getExternalContext().getSession(true);
    }

    public String toApplication(String iid, String cid) {
        getSession().setAttribute("cid", cid);
        getSession().setAttribute("iid", iid);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/Add_Application.jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String addApplication() throws IOException {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            String sid = getSession().getAttribute("uid").toString();
            String instance_id = getSession().getAttribute("iid").toString();
            String compid = getSession().getAttribute("cid").toString();
            String applicationstatus = "Pending";
            String adate = new Date().toString();

            client.insertApplication(sid, instance_id, applicationstatus, adate, compid);
            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/display_Application.jsf");
            return null;
        } catch (ClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Collection<Application> getFilterapplicationlistbysid() {
        return filterapplicationlistbysid;
    }

    public void setFilterapplicationlistbysid(Collection<Application> filterapplicationlistbysid) {
        this.filterapplicationlistbysid = filterapplicationlistbysid;
    }

    public Collection<Application> getApplicationlistbysid() {
        String sid = getSession().getAttribute("uid").toString();
        rs = client.displayApplicationBySid(Response.class, sid);
        applicationlistbysid = rs.readEntity(genapplicationlistbysid);
        return applicationlistbysid;
    }

    public void setApplicationlistbysid(Collection<Application> applicationlistbysid) {
        this.applicationlistbysid = applicationlistbysid;
    }

//    public void filterAppByStatus() {
////        System.out.println("A : " + studB.displayApplicationByStatus(status));
//
//        System.out.println("Status:" + status);
//        if (status != "All") {
//            System.out.println("x : " + studB.displayApplicationByStatus(status));
//            filterapplicationlistbysid = studB.displayApplicationByStatus(status);
//        }
//        else {
//            String sid = getSession().getAttribute("uid").toString();
//            rs = client.displayApplicationBySid(Response.class, sid);
//            applicationlistbysid = rs.readEntity(genapplicationlistbysid);
//        }
//
//////        return applicationlistbysid;
////        try {
////            FacesContext facesContext = FacesContext.getCurrentInstance();
////
////            this.applicationlistbysid = studB.displayApplicationByStatus(status);
////            facesContext.getExternalContext().redirect(facesContext.getExternalContext().getRequestContextPath() + "/pages/display_Application.jsf");
////            return null;
////        } catch (ClientErrorException e) {
////            e.printStackTrace();
////            return null;
////        }
//    }
    
}
