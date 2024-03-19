package com.ezbudget.EzBudgetAPI.model;

import com.ezbudget.EzBudgetAPI.enums.EntryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
public class Entry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Error: Name should not be null")
    @NotEmpty(message = "Error: Name should not be empty")
    private String name;

    @NotNull(message = "Error: Value should not be null")
    private Double value;

    @NotNull(message = "Error: Date should not be null")
    private Date date;

    @NotNull(message = "Error: entryType should not be null")
    private EntryType entryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAuth user;

    public Entry(Long id, String name, Double value, Date date) {
        this.setName(name);
        this.setValue(value);
        this.setDate(date);
    }

    public Entry() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Error: Value should not be negative");
        }

        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(id, entry.id) && Objects.equals(name, entry.name) && Objects.equals(value, entry.value) && Objects.equals(date, entry.date) && entryType == entry.entryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, date, entryType);
    }
}
