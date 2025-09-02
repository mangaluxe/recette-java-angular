package org.example.backspringboot.dto;

/*
RoleDtoSend : sortie → réponse API
*/

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class RoleDtoSend {

    // ========== Propriétés ==========

    private Long id;
    private String roleName;


    // ========== Constructeurs ==========

    public RoleDtoSend() {
    }

    public RoleDtoSend(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }


    // ========== Getters/Setters ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
