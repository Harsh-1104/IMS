/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestClient;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:JakartaEE8Resource [rest]<br>
 * USAGE:
 * <pre>
 *        RC client = new RC();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HARSH
 */
public class RC {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/IMSAPP/resources";

    public RC() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest");
    }

    public <T> T displayApplicationBySid(Class<T> responseType, String sid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("application/display/{0}", new Object[]{sid})).request().post(null, responseType);
    }

    public <T> T getApplicationsByStatus(Class<T> responseType, String status) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("status/{0}", new Object[]{status}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteApplicationAPI(String sid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("application/delete/{0}", new Object[]{sid})).request().delete();
    }

    public void insertApplication(String sid, String iid, String status, String adate, String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("application/insert/{0}/{1}/{2}/{3}/{4}", new Object[]{sid, iid, status, adate, cid})).request().post(null);
    }

    public <T> T displayApplicationByIIDAPI(Class<T> responseType, String iid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("application/displaybyiid/{0}", new Object[]{iid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayInternship(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Internship/display");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void insertInternship(String cid, String title, String description, String startDate, String endDate, String stipend) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("Internship/insert/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{cid, title, description, startDate, endDate, stipend})).request().post(null);
    }

    public void insertStudAPI(String sID, String sname, String semail, String spwd, String sphone, String spref) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("student/insert/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{sID, sname, semail, spwd, sphone, spref})).request().post(null);
    }

    public void deleteInternship(String iid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("Internship/delete/{0}", new Object[]{iid})).request().delete();
    }

    public void insertUsertb(String uid, String email, String password, String roleID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("Usertb/insert/{0}/{1}/{2}/{3}", new Object[]{uid, email, password, roleID})).request().post(null);
    }

    public <T> T displayDeparementAPI(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("department/display");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayTest(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("testtb/display");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayInternshipByCIDAPI(Class<T> responseType, String cid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("internship/displaybycid/{0}", new Object[]{cid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteCompany(String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("Company/delete/{0}", new Object[]{cid})).request().delete();
    }

    public void ChangeStatus(String aid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("ApplicationStatus/{0}/{1}", new Object[]{aid, status})).request().post(null);
    }

    public void deleteUserAPI(String uid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("user/delete/{0}", new Object[]{uid})).request().delete();
    }

    public <T> T displayApplicationAPI(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("application/display");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteStudentAPI(String sid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("student/delete/{0}", new Object[]{sid})).request().delete();
    }

    public <T> T displayCompany(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Company/display");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void insertCompany(String cid, String cname, String email, String password, String address, String contact_details, String company_details, String roleID) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("Company/insert/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{cid, cname, email, password, address, contact_details, company_details, roleID})).request().post(null);
    }

    public void deleteTest(String test_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("testtb/delete/{0}", new Object[]{test_id})).request().delete();
    }

    public <T> T displayApplicationByCIDAPI(Class<T> responseType, String cid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("application/displaybycid/{0}", new Object[]{cid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void insertDeptAPI(Object requestEntity) throws ClientErrorException {
        webTarget.path("department/insert").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T displayStudentByIDAPI(Class<T> responseType, String sid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("student/display/{0}", new Object[]{sid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void insertTest(String test_name, String test_type) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("testtb/insert/{0}/{1}", new Object[]{test_name, test_type})).request().post(null);
    }

    public <T> T displayFeedback(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Feedback/display");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayStudentAPI(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("student/display");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayApplicationByIDAPI(Class<T> responseType, String aid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("application/displaybyaid/{0}", new Object[]{aid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
