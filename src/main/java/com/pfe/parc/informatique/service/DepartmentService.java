package com.pfe.parc.informatique.service;

import com.pfe.parc.informatique.entities.Department;
import com.pfe.parc.informatique.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}
