package org.example.backspringboot.service;

import org.example.backspringboot.entity.Role;
import org.example.backspringboot.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    // ========== Propriétés ==========

    private final RoleRepository roleRepository;


    // ========== Constructeur ==========

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Récupérer tous les rôles
     */
    public List<Role> getAllRoles() {
        // return roleRepository.findAll(); // Dans le désordre
        return roleRepository.findAllByOrderByIdAsc(); // Dans l'ordre
    }

    /**
     * Récupérer un rôle par id
     */
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    /**
     * Récupérer un rôle par nom de rôle
     */
    public Optional<Role> getRoleByName(String roleName) {
        return Optional.ofNullable(roleRepository.findByRoleName(roleName));
    }


    // ----- Create -----

    /**
     * Ajouter un rôle
     */
    public Role createRole(Role role) {
        if (roleRepository.findByRoleName(role.getRoleName()) != null) { // Vérifie si le nom de rôle existe déjà
            throw new IllegalArgumentException("Ce nom de rôle existe déjà : " + role.getRoleName());
        }
        return roleRepository.save(role);
    }


    // ----- Update -----

    /**
     * Modifier un rôle
     */
    public Role updateRole(Long id, String newRoleName) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé avec l'id : " + id));
        existingRole.setRoleName(newRoleName);
        return roleRepository.save(existingRole);
    }


    // ----- Delete -----

    /**
     * Supprimer un rôle
     */
    public void deleteRole(Long id) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé avec l'id : " + id));
        roleRepository.delete(existingRole);
    }

}
