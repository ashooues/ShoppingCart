package com.ShoppingCart.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Embeddable
public class Address {
    @Column(name = "State")
    private String state;
    @Column(name = "City")
    private String city;
    @Column(name = "Line1")
    private String line1;
    @Column(name = "Line2")
    private String line2;
    @Column(name = "Pin_Code")
    private long pinCode;


    public Address() {
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public long getPinCode() {
        return pinCode;
    }

    public void setPinCode(long pinCode) {
        this.pinCode = pinCode;
    }
}
