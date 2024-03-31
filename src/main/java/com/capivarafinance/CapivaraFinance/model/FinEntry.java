package com.capivarafinance.CapivaraFinance.model;

import com.capivarafinance.CapivaraFinance.enums.EntryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotNull(message = "Erro: Nome não pode ser nulo")
    @NotEmpty(message = "Erro: Nome não pode estar vazio")
    private String name;

    @NotNull(message = "Erro: Valor não pode ser nulo")
    private Double entryValue;

    @NotNull(message = "Erro: Data não pode ser nula")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "Erro: Tipo não pode ser nulo")
    private EntryType entryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
            throw new IllegalArgumentException("Erro: Valor não pode ser negativo");
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
