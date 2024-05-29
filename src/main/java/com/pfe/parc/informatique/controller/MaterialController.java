package com.pfe.parc.informatique.controller;

import com.pfe.parc.informatique.entities.Material;
import com.pfe.parc.informatique.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materials")
@CrossOrigin("*")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public Material createMaterial(@RequestBody Material material) {
        return materialService.save(material);
    }

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Material> getMaterialById(@PathVariable Long id) {
        return materialService.findById(id);
    }

    @PutMapping("/{id}")
    public Material updateMaterial(@PathVariable Long id, @RequestBody Material materialDetails) {
        Material material = materialService.findById(id).orElseThrow(() -> new RuntimeException("Material not found"));
        material.setUser(materialDetails.getUser());
        material.setBarcode(materialDetails.getBarcode());
        material.setSerialNumber(materialDetails.getSerialNumber());
        material.setBrand(materialDetails.getBrand());
        material.setType(materialDetails.getType());
        return materialService.save(material);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteById(id);
    }
}
