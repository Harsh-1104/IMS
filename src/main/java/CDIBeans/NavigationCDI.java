/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
@Named(value = "navigationCDI")
@RequestScoped
public class NavigationCDI {

    String company;
    String user;
    String admin;

    public NavigationCDI() {
    }

    public String checkCompany() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        company = (String) session.getAttribute("company");
        if (company == null) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/pages/login.jsf");
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return "/pages/login.jsf?faces-redirect=true";
        }
        return "successOutcome";
    }

    public String checkStudent() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        user = (String) session.getAttribute("student");
        if (user == null) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/pages/login.jsf");
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return "/pages/login.jsf?faces-redirect=true";
        }
        return "successOutcome";
    }

    public String checkAdmin() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        admin = (String) session.getAttribute("admin");
        if (admin == null) {
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/pages/login.jsf");
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return "/pages/login.jsf?faces-redirect=true";
        }
        return "successOutcome";
    }
}
