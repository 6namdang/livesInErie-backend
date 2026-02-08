package com.pa.livesinerie.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    // Added the 'type' field here
    @Column(name = "type", nullable = false)
    private String type; 

    @Column(name = "website_url")
    private String websiteUrl; // Fixed camelCase naming convention

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    protected Community() {}

    // Updated constructor to include 'type' and fixed duplicate assignments
    public Community(String name, String type, String websiteUrl, String email, String phoneNumber, String address) {
        this.name = name;
        this.type = type;
        this.websiteUrl = websiteUrl;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}