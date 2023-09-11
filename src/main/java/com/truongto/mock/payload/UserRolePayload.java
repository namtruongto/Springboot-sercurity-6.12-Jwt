package com.truongto.mock.payload;

import java.util.Set;

public class UserRolePayload {

    private Set<Integer> roles;

    public UserRolePayload() {
    }

    public UserRolePayload(Set<Integer> roles) {
        this.roles = roles;
    }

    public Set<Integer> getRoles() {
        return roles;
    }

    public void setRoles(Set<Integer> roles) {
        this.roles = roles;
    }

}
