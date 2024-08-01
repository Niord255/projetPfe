package com.pfe.parc.informatique.security.services;

import com.pfe.parc.informatique.entities.Contract;
import com.pfe.parc.informatique.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    // Save a new contract or update an existing one
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    // Find all contracts
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    // Find a contract by ID
    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }

    // Delete a contract by ID
    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }
}
