package de.iu.ipwa.controller;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void login() {
        loggedIn = true;
    }

    public void logout() {
        loggedIn = false;
    }
}