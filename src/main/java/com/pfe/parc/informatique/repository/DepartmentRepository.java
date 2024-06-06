package com.pfe.parc.informatique.repository;

import com.pfe.parc.informatique.entities.Department;
import com.pfe.parc.informatique.entities.EDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(EDepartment name);
}
