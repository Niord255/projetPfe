package com.pfe.parc.informatique.repository;

import com.pfe.parc.informatique.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
