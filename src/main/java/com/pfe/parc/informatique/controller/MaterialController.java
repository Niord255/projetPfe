package com.pfe.parc.informatique.controller;

import com.pfe.parc.informatique.entities.Material;
import com.pfe.parc.informatique.security.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Long id) {
        Optional<Material> material = materialService.findById(id);
        return material.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Material createMaterial(@RequestBody Material material) {
        return materialService.save(material);
    }

    @PutMapping("/{id}") // Ensure this annotation is present
    public ResponseEntity<Material> updateMaterial(@PathVariable Long id, @RequestBody Material updatedMaterial) {
        Optional<Material> material = materialService.findById(id);
        if (material.isPresent()) {
            updatedMaterial.setId(id); // Set the ID for the update
            return ResponseEntity.ok(materialService.save(updatedMaterial));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        Optional<Material> material = materialService.findById(id);
        if (material.isPresent()) {
            materialService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
