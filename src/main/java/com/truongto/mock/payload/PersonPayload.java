package com.truongto.mock.payload;

public class PersonPayload {
    String name;
    String address;
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonPayload() {
    }

    public PersonPayload(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

}
