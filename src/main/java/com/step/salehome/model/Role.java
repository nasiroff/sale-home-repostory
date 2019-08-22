package com.step.salehome.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {
    private int idRole;
    private String roleType;

    public Role(int idRole){
        this.idRole = idRole;
    }
}
