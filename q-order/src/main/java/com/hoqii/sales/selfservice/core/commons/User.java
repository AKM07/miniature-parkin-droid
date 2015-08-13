package com.hoqii.sales.selfservice.core.commons;

import com.hoqii.sales.selfservice.core.DefaultPersistence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianw on 11/6/14.
 */
public class User extends DefaultPersistence {
    private String id;
    private String username;
    private String password;
    private String email;
    private Name name = new Name();
    private Address address = new Address();
    private List<Role> roles = new ArrayList<Role>();
    private String reference;
//    private User upline = new User();
    private Bank bank = new Bank();
    private String agentCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}