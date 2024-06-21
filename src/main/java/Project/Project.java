/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.Project to edit this template
 */
package Project;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author pooja
 */
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "JNDI/IMS",
callerQuery = "select password from usertb where email=?",
groupsQuery = "(select roleName from role where roleID IN (select roleID from usertb where email=?))",
        
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
        
)

@Named(value = "java")
@ApplicationScoped
public class Project {

   
}
