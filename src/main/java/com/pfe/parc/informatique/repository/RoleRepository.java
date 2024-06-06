package com.pfe.parc.informatique.repository;

import com.pfe.parc.informatique.entities.ERole;
import com.pfe.parc.informatique.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
