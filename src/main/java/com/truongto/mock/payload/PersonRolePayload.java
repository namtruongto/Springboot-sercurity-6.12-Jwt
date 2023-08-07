package com.truongto.mock.payload;

import java.util.Set;

public class PersonRolePayload {

    private Set<Integer> roles;

    public PersonRolePayload() {
    }

    public PersonRolePayload(Set<Integer> roles) {
        this.roles = roles;
    }

    public Set<Integer> getRoles() {
        return roles;
    }

    public void setRoles(Set<Integer> roles) {
        this.roles = roles;
    }

}
