package de.iu.ipwa.controller;

import java.io.IOException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class LoginBean {

    private String email;
    private String password;

    @Inject
    private UserSession userSession;

    // Login: prüft E-Mail + Passwort
    public String login() {

        if ("wissenschaftler@demo.de".equalsIgnoreCase(email) && "zugang".equals(password)) {
            userSession.login();
            return "scientist.xhtml?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Login fehlgeschlagen",
                "E-Mail oder Passwort falsch.")
        );
        return null;
    }

    // Logout + Redirect
    public String logout() {
        userSession.logout();
        return "public.xhtml?faces-redirect=true";
    }

    // Guard für scientist.xhtml (wird per f:viewAction aufgerufen)
    public void requireLogin() {
        if (!userSession.isLoggedIn()) {
            try {
                FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("login.xhtml");
            } catch (IOException e) {
                // Anfänger-minimal: ignorieren
            }
        }
    }

    // Getter / Setter
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}