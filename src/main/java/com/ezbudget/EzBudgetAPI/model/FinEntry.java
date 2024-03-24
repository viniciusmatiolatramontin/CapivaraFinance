package com.ezbudget.EzBudgetAPI.model;

import com.ezbudget.EzBudgetAPI.enums.EntryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class FinEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Error: Name should not be null")
    @NotEmpty(message = "Error: Name should not be empty")
    private String name;

    @NotNull(message = "Error: Value should not be null")
    private Double entryValue;

    @NotNull(message = "Error: Date should not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "Error: Entry Type should not be null")
    private EntryType entryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserAuth owner;

    public FinEntry(String name, Double entryValue, EntryType entryType, Date date) {
        this.setName(name);
        this.setEntryValue(entryValue);
        this.setEntryType(entryType);
        this.setDate(date);
    }

    public FinEntry() {

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

    public Double getEntryValue() {
        return entryValue;
    }

    public void setEntryValue(Double entryValue) {
        if (entryValue < 0) {
            throw new IllegalArgumentException("Error: Value should not be negative");
        }

        this.entryValue = entryValue;
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

    public UserAuth getOwner() {
        return owner;
    }

    public void setOwner(UserAuth owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinEntry entry = (FinEntry) o;
        return Objects.equals(id, entry.id) && Objects.equals(name, entry.name) && Objects.equals(entryValue, entry.entryValue) && Objects.equals(date, entry.date) && entryType == entry.entryType && Objects.equals(owner, entry.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, entryValue, date, entryType, owner);
    }
}
