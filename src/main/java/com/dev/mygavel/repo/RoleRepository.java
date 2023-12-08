package com.dev.mygavel.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.mygavel.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {


//    Role findByLibelle(String libelle);
    Optional<Role> findById(Long id);
    Role findByLibelle(String libelle);
}
