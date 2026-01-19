package de.iu.ipwa.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HomeBean {

    public String getTitle() {
        return "Like Hero To Zero 🌍";
    }
}
