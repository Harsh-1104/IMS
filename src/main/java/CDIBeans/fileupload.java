/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import javax.faces.component.UIComponent;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author HARSH
 */
@Named(value = "fileupload")
@RequestScoped
public class fileupload {

    private Part file;

    public void upload() throws IOException {
        if (file != null) {
            // Read file content
            Scanner scanner = new Scanner(file.getInputStream());
            String fileContent = scanner.useDelimiter("\\A").next();
            scanner.close();

            // Get the path to the uploads directory in the root of the application
//            String uploadsDir = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "src\\main\\webapp\\uploads\\";

            // Ensure the directory exists
//            Files.createDirectories(Paths.get(uploadsDir));

            // Write the file content to the uploads directory
            Files.write(Paths.get("D:\\Java\\IMSAPP\\src\\main\\webapp\\uploads\\"+ file.getSubmittedFileName()), fileContent.getBytes(), StandardOpenOption.CREATE);

            // Add success message
            System.out.println("Success : " + file.getSubmittedFileName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", file.getSubmittedFileName() + " is uploaded."));
            
        
        } else {
            // Add error message if file is not selected
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "File is not selected."));
        }
    }

    public void validate(FacesContext context, UIComponent component, Object value) {
        Part afile = (Part) value;
        System.out.println("file size : " + afile.getSize());
        if (afile.getSize() > 9999999) {
            throw new ValidatorException(new FacesMessage("File is too large"));
        }
//        if (!file.getContentType().equals("text/plain")) {
//            throw new ValidatorException(new FacesMessage("File is not a text file"));
//        }
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
