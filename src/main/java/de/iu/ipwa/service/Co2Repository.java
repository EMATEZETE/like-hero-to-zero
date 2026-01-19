package de.iu.ipwa.service;

import de.iu.ipwa.model.Co2Record;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class Co2Repository {

    @PersistenceContext(unitName = "lh2zPU")
    private EntityManager em;

    /** Alle Datensätze lesen */
    public List<Co2Record> findAll() {
        return em.createQuery(
                "SELECT r FROM Co2Record r ORDER BY r.date DESC, r.country ASC",
                Co2Record.class
        ).getResultList();
    }

    /** Datensatz anlegen */
    public void add(Co2Record record) {
        if (record == null) return;
        em.persist(record);
    }

    /** Datensatz entfernen */
    public void remove(Co2Record record) {
        if (record == null) return;
        Co2Record managed =
                em.contains(record) ? record : em.merge(record);
        em.remove(managed);
    }

    /** Datensatz aktualisieren (optional, aktuell nicht genutzt) */
    public Co2Record update(Co2Record record) {
        if (record == null) return null;
        return em.merge(record);
    }

    /** 
     * MUST 1:
     * Aktuellsten Datensatz eines Landes liefern
     */
    public Co2Record findLatestByCountry(String country) {
        if (country == null || country.isBlank()) return null;

        List<Co2Record> list = em.createQuery(
                "SELECT r FROM Co2Record r " +
                "WHERE r.country = :country " +
                "ORDER BY r.date DESC",
                Co2Record.class
        )
        .setParameter("country", country.trim())
        .setMaxResults(1)
        .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }
}