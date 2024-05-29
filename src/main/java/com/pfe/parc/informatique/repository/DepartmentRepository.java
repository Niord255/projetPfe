package com.pfe.parc.informatique.repository;

import com.pfe.parc.informatique.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
