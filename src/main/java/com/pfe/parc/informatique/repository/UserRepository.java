package com.pfe.parc.informatique.repository;

import com.pfe.parc.informatique.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
