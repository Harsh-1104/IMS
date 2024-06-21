/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auth;

//import beans.LoginBean;
import SB.CompanyBean;
import io.jsonwebtoken.ExpiredJwtException;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.ws.rs.core.SecurityContext;
import static jwtrest.Constants.AUTHORIZATION_HEADER;
import static jwtrest.Constants.BEARER;
import jwtrest.JWTCredential;
import jwtrest.TokenProvider;
import keeprecord.KeepRecord;

/**
 *
 * @author root
 */
@Named
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism, Serializable {

    @Inject
    IdentityStoreHandler handler;
    CredentialValidationResult result;
    AuthenticationStatus status;
    SecurityContext ct;
    @Inject
    TokenProvider tokenProvider;

    public String logout() throws ServletException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        request.logout();
        KeepRecord.reset();

        return "/login.jsf?faces-redirect=true";
    }
// @Inject LoginBean lbean;
    @EJB
    CompanyBean cb;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext ctx) throws AuthenticationException {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        HttpSession session = request.getSession();
        try {
            if (request.getRequestURI().contains("logout")) {
                request.logout();
                session.invalidate();
                KeepRecord.reset();
                response.sendRedirect("login.jsf");
                return ctx.doNothing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String token = extractToken(ctx);
        try {

            if (token == null && request.getParameter("email") != null) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
//          String username = lbean.getUsername();
//          String password = lbean.getPassword();
                Credential credential = new UsernamePasswordCredential(email, new Password(password));
//                AuthenticationStatus au = ct.authenticate(request, response, AuthenticationParameters.withParams().credential(credential));
                System.out.println(email + password);
                result = handler.validate(credential);

                if (result.getStatus() == Status.VALID) {
                    KeepRecord.setErrorStatus("");
                    AuthenticationStatus status = createToken(result, ctx);

                    status = ctx.notifyContainerAboutLogin(result);

//                    KeepRecord.setUsername(username);
//                    KeepRecord.setPassword(password);
                    KeepRecord.setPrincipal(result.getCallerPrincipal());
                    KeepRecord.setRoles(result.getCallerGroups());
                    KeepRecord.setCredential(credential);

                    if (result.getCallerGroups().contains("user")) {
                        session.setAttribute("user", email);
                        String uid = cb.getUserIDByEmail(email);
                        String uname = cb.getUserDetailByUID(email).getSname();
                        session.setAttribute("uid", uid);
                        session.setAttribute("student", email);
                        session.setAttribute("uname", uname);
                        response.sendRedirect("studentDashboard.jsf");
                    }
                    if (result.getCallerGroups().contains("admin")) {
                        session.setAttribute("user", email);
                        String uid = cb.getUserIDByEmail(email);
                        session.setAttribute("uid", uid);
                        session.setAttribute("admin", email);
                        session.setAttribute("aname", "Admin");
                        response.sendRedirect("AdminDashboard.jsf");
                    }
                    if (result.getCallerGroups().contains("company")) {
                        String uid = cb.getUserIDByEmail(email);
                        session.setAttribute("uid", uid);
                        session.setAttribute("company", email);
                        String cname = cb.getCompanyDetailByUID(email).getCname();
                        session.setAttribute("cname", cname);
                        response.sendRedirect("companyDashboard.jsf");
                    }

                    return status;
                } else {
                    KeepRecord.setErrorStatus("Either Username or Password is wrong !");
                    response.sendRedirect("login.jsf");

                    return ctx.doNothing();
                }

            }

            if (KeepRecord.getToken() != null) {

                ctx.notifyContainerAboutLogin(KeepRecord.getPrincipal(), KeepRecord.getRoles());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(token);
        token = KeepRecord.getToken();
        if (token != null) {
            // validation of the jwt credential

            return validateToken(token, ctx);
        } else if (ctx.isProtected()) {
//            // A protected resource is a resource for which a constraint has been defined.
//            // if there are no credentials and the resource is protected, we response with unauthorized status

            return ctx.responseUnauthorized();
        }
        return ctx.doNothing();
    }

    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
//        try {
//            if (tokenProvider.validateToken(token)) {
//                JWTCredential credential = tokenProvider.getCredential(token);
//                System.out.println("JWTAuthenticationMechanism-Token Validated");
//                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
//
//            }
//            // if token invalid, response with unauthorized status
//            return context.responseUnauthorized();
//        } catch (ExpiredJwtException eje) {
//            //LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
//            return context.responseUnauthorized();
//        }
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
                System.out.println("JWTAuthenticationMechanism - Token Validated");
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());

            }
            System.out.println("JWTAuthenticationMechanism - Invalid Token");
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
            System.out.println("JWTAuthenticationMechanism - Token Expired");
            return context.responseUnauthorized();
        }
    }

    /**
     * Create the JWT using CredentialValidationResult received from
     * IdentityStoreHandler
     *
     * @param result the result from validation of UsernamePasswordCredential
     * @param context
     * @return the AuthenticationStatus to notify the container
     */
    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
//        if (!isRememberMe(context)) {
        // if (true) {
        String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
        //context.getRequest().getSession().setAttribute("token", jwt);
        KeepRecord.setToken(jwt);
        context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
        System.out.println("Token Value " + jwt);

        //     }
        System.out.println("JWTAuthenticationMechanism - Token Created");

        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    /**
     * To extract the JWT from Authorization HTTP header
     *
     * @param context
     * @return The JWT access tokens
     */
    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
            //  System.out.println("JWTAuthenticationMechanism - Extract Tokens");
            return token;
        }
        return null;
    }

    /**
     * this function invoked using RememberMe.isRememberMeExpression EL
     * expression
     *
     * @param context
     * @return The remember me flag
     */
//    public Boolean isRememberMe(HttpMessageContext context) {
//        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
//    }
}
