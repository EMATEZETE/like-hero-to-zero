package de.iu.ipwa.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import de.iu.ipwa.model.Co2Record;
import de.iu.ipwa.service.Co2Repository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class Co2Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    // Add-Form
    private LocalDate date = LocalDate.now();
    private String country;
    private Double co2;

    // Daten
    private List<Co2Record> records;

    // Edit (minimal)
    private Co2Record editing;
    private LocalDate editDate;
    private String editCountry;
    private Double editCo2;

    @Inject
    private Co2Repository repository;

    @PostConstruct
    public void init() {
        reload();
    }

    public List<Co2Record> getRecords() {
        return records;
    }

    // ADD
    public void add() {
        if (date == null) return;
        if (country == null || country.isBlank()) return;
        if (co2 == null) return;

        repository.add(new Co2Record(date, country.trim(), co2));

        date = LocalDate.now();
        country = null;
        co2 = null;

        reload();
    }

    // DELETE
    public void delete(Co2Record record) {
        if (record == null) return;

        repository.remove(record);

        if (isEditing(record)) {
            cancelEdit();
        }

        reload();
    }

    // EDIT START
    public void startEdit(Co2Record record) {
        if (record == null) return;

        editing = record;
        editDate = record.getDate();
        editCountry = record.getCountry();
        editCo2 = record.getCo2();
    }

    // EDIT SAVE
    public void saveEdit() {
        if (editing == null) return;
        if (editDate == null) return;
        if (editCountry == null || editCountry.isBlank()) return;
        if (editCo2 == null) return;

        editing.setDate(editDate);
        editing.setCountry(editCountry.trim());
        editing.setCo2(editCo2);

        repository.update(editing);

        cancelEdit();
        reload();
    }

    public void cancelEdit() {
        editing = null;
        editDate = null;
        editCountry = null;
        editCo2 = null;
    }
    
    public boolean isEditing(Co2Record record) {
        return editing != null
            && record != null
            && editing.getId() != null
            && record.getId() != null
            && editing.getId().equals(record.getId());
    }

    private void reload() {
        records = repository.findAll();
    }

    // Getter/Setter Add-Form
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Double getCo2() { return co2; }
    public void setCo2(Double co2) { this.co2 = co2; }

    // Getter/Setter Edit-Form
    public LocalDate getEditDate() { return editDate; }
    public void setEditDate(LocalDate editDate) { this.editDate = editDate; }

    public String getEditCountry() { return editCountry; }
    public void setEditCountry(String editCountry) { this.editCountry = editCountry; }

    public Double getEditCo2() { return editCo2; }
    public void setEditCo2(Double editCo2) { this.editCo2 = editCo2; }
    
    private String countryFilter;
    public String getCountryFilter() { return countryFilter; }
    public void setCountryFilter(String countryFilter) { this.countryFilter = countryFilter; }
}