package com.mycompany.imsapp.resources;

import Entity.Application;
import Entity.Company;
import Entity.Department;
import Entity.Feedback;
import Entity.Internship;
import Entity.Student;
import Entity.Testtb;
import SB.CompanyBean;
import SB.StudentBean;
import SB.TestBean;
import java.text.ParseException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author
 */
@Path("rest")
public class JakartaEE8Resource {

    @EJB
    CompanyBean cb;

    @EJB
    StudentBean studB;

    @EJB
    TestBean tb;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Company/display")
    public Collection<Company> displayCompany() {
        return cb.displayCompany();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Company/delete/{cid}")
    public void deleteCompany(@PathParam("cid") String cid) {
        cb.deleteCompany(cid);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Company/insert/{cid}/{cname}/{email}/{password}/{address}/{contact_details}/{company_details}/{roleID}")
    public void insertCompany(@PathParam("cid") String cid, @PathParam("cname") String cname, @PathParam("email") String email, @PathParam("password") String password, @PathParam("address") String address, @PathParam("contact_details") String contact_details, @PathParam("company_details") String company_details, @PathParam("roleID") Integer roleID) {
        cb.insertCompany(cid, cname, email, password, address, contact_details, company_details, roleID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Internship/display")
    public Collection<Internship> displayInternship() {
        return cb.displayInternship();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Internship/delete/{iid}")
    public void deleteInternship(@PathParam("iid") String iid) {
        cb.deleteInternship(iid);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Feedback/display")
    public Collection<Feedback> displayFeedback() {
        return cb.displayFeedback();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Internship/insert/{cid}/{title}/{description}/{startDate}/{endDate}/{stipend}")
    public void insertInternship(@PathParam("iid") String iid, @PathParam("cid") String cid, @PathParam("title") String title, @PathParam("description") String description, @PathParam("startDate") String startDate, @PathParam("endDate") String endDate, @PathParam("stipend") Boolean stipend) {
        try {
            cb.insertInternship(cid, title, description, startDate, endDate, stipend);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/internship/displaybycid/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Internship> displayInternshipByCIDAPI(@PathParam("cid") String cid) {
        return cb.displayInternshipByCID(cid);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Usertb/insert/{uid}/{email}/{password}/{roleID}")
    public void insertUsertb(@PathParam("uid") String uid, @PathParam("email") String email, @PathParam("password") String password, @PathParam("roleID") Integer roleID) {
        cb.insertUsertb(uid, email, password, roleID);
    }
    
    @DELETE
    @Path("/user/delete/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteUserAPI(@PathParam("uid") String uid) {
        cb.deleteUser(uid);
    }

    //harsh API
    @GET
    @Path("/student/display")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Student> displayStudentAPI() {
        return studB.displayStudent();
    }

    @GET
    @Path("/student/display/{sid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student displayStudentByIDAPI(@PathParam("sid") String sid) {
        return studB.displayStudentByID(sid);
    }

    @DELETE
    @Path("/student/delete/{sid}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteStudentAPI(@PathParam("sid") String sid) {
        studB.deleteStudent(sid);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/student/insert/{sID}/{sname}/{semail}/{spwd}/{sphone}/{spref}")
    public void insertStudAPI(
            @PathParam("sID") String sid,
            @PathParam("sname") String sname,
            @PathParam("semail") String semail,
            @PathParam("spwd") String spwd,
            @PathParam("sphone") String sphone,
            @PathParam("spref") String spref
    ) {
        studB.insertStudent(sid, sname, semail, spwd, sphone, spref);
    }

    @GET
    @Path("/application/display")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Application> displayApplicationAPI() {
        return studB.displayApplication();
    }

    @GET
    @Path("/application/displaybyaid/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Application displayApplicationByIDAPI(@PathParam("aid") String aid) {
        return studB.displayApplicationByID(aid);
    }

    @GET
    @Path("/application/displaybyiid/{iid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Application displayApplicationByIIDAPI(@PathParam("iid") String iid) {
        return studB.displayApplicationByIID(iid);
    }

    @GET
    @Path("/application/displaybycid/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Application> displayApplicationByCIDAPI(@PathParam("cid") String cid) {
        return studB.displayApplicationByCID(cid);
    }

    @GET
    @Path("/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Application> getApplicationsByStatus(@PathParam("status") String status) {
        return studB.displayApplicationByStatus(status);
    }

    @DELETE
    @Path("/application/delete/{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteApplicationAPI(@PathParam("aid") String aid) {
        studB.deleteApplication(aid);
    }

    @GET
    @Path("/department/display")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Department> displayDeparementAPI() {
        return studB.displayDepartment();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/department/insert")
    public void insertDeptAPI(Department dept) {
        studB.insertDepartment(dept.getDName());
    }

    @GET
    @Path("/testtb/display")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Testtb> displayTest() {
        return tb.displayTest();
    }

    @DELETE
    @Path("/testtb/delete/{test_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteTest(@PathParam("test_id") Integer test_id) {
        tb.deleteTest(test_id);
    }

    @POST
    @Path("/testtb/insert/{test_name}/{test_type}")
    @Produces(MediaType.APPLICATION_JSON)
    public void insertTest(@PathParam("test_name") String test_name, @PathParam("test_type") String test_type) {
        tb.insertTest(test_name, test_type);
    }

    @POST
    @Path("/ApplicationStatus/{aid}/{status}")
    public void ChangeStatus(@PathParam("aid") String aid, @PathParam("status") String status) {
        cb.ChangeStatus(aid, status);
    }

    @POST
    @Path("/application/insert/{sid}/{iid}/{status}/{adate}/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public void insertApplication(@PathParam("sid") String sid, @PathParam("iid") String iid, @PathParam("status") String status, @PathParam("adate") String adate, @PathParam("cid") String cid) throws ParseException {
        studB.insertAppliction(sid, iid, status, adate, cid);
    }

    @POST
    @Path("/application/display/{sid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Application> displayApplicationBySid(@PathParam("sid") String sid) {
        return studB.displayApplicationBySID(sid);
    }    
}
