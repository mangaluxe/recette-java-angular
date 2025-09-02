package org.example.backspringboot.controller;

import org.example.backspringboot.dto.RoleDtoReceive;
import org.example.backspringboot.dto.RoleDtoSend;
import org.example.backspringboot.entity.Role;
import org.example.backspringboot.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role") // URL par défaut : http://localhost:8080/api/role
public class RoleController {

    // ========== Propriétés ==========

    private final RoleService roleService;


    // ========== Constructeur ==========

    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Récupérer tous les rôles
     */
    @GetMapping // GET http://localhost:8080/api/role
    public List<RoleDtoSend> getAllRoles() {
        return roleService.getAllRoles().stream()
                .map(this::convertToDtoSend)
                .toList();
    }

    /**
     * Récupérer un rôle par id
     */
    @GetMapping("/{id}") // GET http://localhost:8080/api/role/{id}
    public ResponseEntity<RoleDtoSend> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id)
                .map(role -> ResponseEntity.ok(convertToDtoSend(role)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ----- Create -----

    /**
     * Ajouter un rôle
     */
    @PostMapping // POST http://localhost:8080/api/role
    public ResponseEntity<RoleDtoSend> createRole(@RequestBody RoleDtoReceive dto) {
        Role role = new Role();
        role.setRoleName(dto.getRoleName());
        Role savedRole = roleService.createRole(role);
        return ResponseEntity.ok(convertToDtoSend(savedRole));
    }

    // ----- Update -----

    /**
     * Modifier un rôle
     */
    @PutMapping("/{id}") // PUT http://localhost:8080/api/role/{id}
    public ResponseEntity<RoleDtoSend> updateRole(@PathVariable Long id, @RequestBody RoleDtoReceive dto) {
        Role updatedRole = roleService.updateRole(id, dto.getRoleName());
        return ResponseEntity.ok(convertToDtoSend(updatedRole));
    }


    // ----- Delete -----

    /**
     * Supprimer un rôle
     */
    @DeleteMapping("/{id}") // DELETE http://localhost:8080/api/role/{id}
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }


    // ----- Utilitaires -----

    private RoleDtoSend convertToDtoSend(Role role) {
        RoleDtoSend dto = new RoleDtoSend();
        dto.setId(role.getId());
        dto.setRoleName(role.getRoleName());
        return dto;
    }

}
