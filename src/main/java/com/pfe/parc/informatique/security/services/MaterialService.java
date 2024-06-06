package com.pfe.parc.informatique.security.services;

import com.pfe.parc.informatique.entities.Material;
import com.pfe.parc.informatique.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    public Material save(Material material) {
        return materialRepository.save(material);
    }

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    public void deleteById(Long id) {
        materialRepository.deleteById(id);
    }
}
