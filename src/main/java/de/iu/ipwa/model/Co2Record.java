package de.iu.ipwa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "co2_record")
public class Co2Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false)
    private Double co2;

    // Pflicht: Default-Konstruktor für JPA
    public Co2Record() {
    }

    public Co2Record(LocalDate date, String country, Double co2) {
        this.date = date;
        this.country = country;
        this.co2 = co2;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = (country == null) ? null : country.trim();
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    // Hilfreich für JSF-Tabellen/Aktionen (Vergleich über ID)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Co2Record)) return false;
        Co2Record other = (Co2Record) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}