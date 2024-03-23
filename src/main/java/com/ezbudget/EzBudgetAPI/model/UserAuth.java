package com.ezbudget.EzBudgetAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Entity
public class UserAuth implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(message = "Error: Id should not be null")
    private Long id;

    @NotNull(message = "Error: Username should not be null")
    @NotEmpty(message = "Error: Username should not be empty")
    private String username;

    @NotNull(message = "Error: Password should not be null")
    @NotEmpty(message = "Error: Password should not be empty")
    private String password;

    @NotNull(message = "Error: Email should not be null")
    @NotEmpty(message = "Error: Email should not be empty")
    private String email;

    @NotNull(message = "Error: Balance should not be null")
    private Double balance;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<FinEntry> finEntries;

    public UserAuth(String username, String password, String email, Double balance) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setBalance(balance);
        finEntries = new ArrayList<>();
    }

    public UserAuth() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) throws IllegalArgumentException {
        if (balance < 0) {
            throw new IllegalArgumentException("Error: Balance should not be negative");
        }

        this.balance = balance;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        EmailValidator validator = EmailValidator.getInstance();

        if (!validator.isValid(email)) {
            throw new IllegalArgumentException("Error: Email should be valid");
        }

        this.email = email;
    }

    public List<FinEntry> getFinEntries() {
        return finEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuth userAuth = (UserAuth) o;
        return Objects.equals(id, userAuth.id) && Objects.equals(username, userAuth.username) && Objects.equals(password, userAuth.password) && Objects.equals(email, userAuth.email) && Objects.equals(balance, userAuth.balance) && Objects.equals(finEntries, userAuth.finEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, balance, finEntries);
    }
}
