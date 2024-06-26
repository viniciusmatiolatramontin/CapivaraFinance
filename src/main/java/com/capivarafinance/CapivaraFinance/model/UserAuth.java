package com.capivarafinance.CapivaraFinance.model;

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
    private Long id;

    @NotNull(message = "Erro: Nome de usuário não pode ser nulo")
    @NotEmpty(message = "Erro: Nome de usuário não pode estar vazio")
    private String username;

    @NotNull(message = "Erro: Senha não pode ser nula")
    @NotEmpty(message = "Erro: Senha não pode estar vazia")
    private String password;

    @NotNull(message = "Erro: E-mail não pode ser nulo")
    @NotEmpty(message = "Erro: E-mail não pode estar vazio")
    private String email;

    @NotNull(message = "Erro: Saldo não pode ser nulo")
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
            throw new IllegalArgumentException("Erro: Saldo não pode ser negativo");
        }

        this.balance = balance;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        EmailValidator validator = EmailValidator.getInstance();

        if (!validator.isValid(email)) {
            throw new IllegalArgumentException("Erro: E-mail iválido");
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
