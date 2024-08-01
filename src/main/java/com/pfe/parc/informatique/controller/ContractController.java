package com.pfe.parc.informatique.controller;

import com.pfe.parc.informatique.entities.Contract;
import com.pfe.parc.informatique.security.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    // Upload a contract with file
    @PostMapping("/upload")
    public ResponseEntity<Contract> uploadContract(
            @RequestParam("file") MultipartFile file,
            @RequestParam("company") String company,
            @RequestParam("subject") String subject,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        try {
            Contract contract = new Contract();
            contract.setCompany(company);
            contract.setSubject(subject);
            contract.setStartDate(java.sql.Date.valueOf(startDate)); // Convert to Date
            contract.setEndDate(java.sql.Date.valueOf(endDate)); // Convert to Date
            contract.setFileData(file.getBytes()); // Get byte[] from MultipartFile
            contract.setFileName(file.getOriginalFilename());

            Contract savedContract = contractService.save(contract);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedContract);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Retrieve all contracts
    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.findAll();
    }

    // Retrieve a contract by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        return contractService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Download the contract file
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadContractFile(@PathVariable Long id) {
        return contractService.findById(id).map(contract -> {
            // Set headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename(contract.getFileName())
                    .build());

            return new ResponseEntity<>(contract.getFileData(), headers, HttpStatus.OK);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Delete a contract by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        if (contractService.findById(id).isPresent()) {
            contractService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
