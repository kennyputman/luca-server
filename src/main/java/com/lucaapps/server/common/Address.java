package com.lucaapps.server.common;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "postcode", nullable = false)
    private String postcode;
    @Column(name = "country", nullable = false)
    private String country;

    @ManyToOne(optional = true)
    @JoinColumn(name = "client_id")
    private Client client;

    public Address() {}

    public Address(String street, String city, String state, String postcode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
