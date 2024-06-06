package com.pfe.parc.informatique.controller;

import com.pfe.parc.informatique.entities.Contract;
import com.pfe.parc.informatique.security.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contracts")
@CrossOrigin("*")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping
    public Contract createContract(@RequestBody Contract contract) {
        return contractService.save(contract);
    }

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Contract> getContractById(@PathVariable Long id) {
        return contractService.findById(id);
    }

    @PutMapping("/{id}")
    public Contract updateContract(@PathVariable Long id, @RequestBody Contract contractDetails) {
        Contract contract = contractService.findById(id).orElseThrow(() -> new RuntimeException("Contract not found"));
        contract.setCompany(contractDetails.getCompany());
        contract.setSubject(contractDetails.getSubject());
        contract.setStartDate(contractDetails.getStartDate());
        contract.setEndDate(contractDetails.getEndDate());
        contract.setFile(contractDetails.getFile());
        return contractService.save(contract);
    }

    @DeleteMapping("/{id}")
    public void deleteContract(@PathVariable Long id) {
        contractService.deleteById(id);
    }
}
