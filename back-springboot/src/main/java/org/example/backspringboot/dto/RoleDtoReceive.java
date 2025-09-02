package org.example.backspringboot.dto;

/*
RoleDtoReceive : entrée → création/mise à jour
*/

import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class RoleDtoReceive {

    // ========== Propriétés ==========

    @NotBlank(message = "Le nom du rôle est obligatoire.")
    private String roleName;


    // ========== Constructeurs ==========

    public RoleDtoReceive() {
    }

    public RoleDtoReceive(String roleName) {
        this.roleName = roleName;
    }


    // ========== Getters/Setters ==========

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
