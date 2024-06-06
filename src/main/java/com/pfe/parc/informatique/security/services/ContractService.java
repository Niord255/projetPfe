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

    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }

    public void deleteById(Long id) {
        contractRepository.deleteById(id);
    }
}
