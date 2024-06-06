package com.pfe.parc.informatique;

import com.pfe.parc.informatique.entities.Department;
import com.pfe.parc.informatique.entities.EDepartment;
import com.pfe.parc.informatique.entities.ERole;
import com.pfe.parc.informatique.entities.Role;
import com.pfe.parc.informatique.repository.DepartmentRepository;
import com.pfe.parc.informatique.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pfe.parc.informatique")
public class Application implements CommandLineRunner {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (departmentRepository.findAll().isEmpty()) {
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_GENERALE));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_TECHNIQUE));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DES_ETUDES_ET_DE_LA_PLANIFICATION));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DE_LA_REGLEMENTATION));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DE_LA_SURVEILLANCE));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DE_LA_GESTION_DES_FREQUENCES));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DES_RESSOURCES_HUMAINES));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DES_FINANCES));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DE_L_INFORMATIQUE));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DE_LA_COMMUNICATION));
			departmentRepository.save(new Department(EDepartment.DEPARTMENT_DIRECTION_DES_AFFAIRES_JURIDIQUES));
		}

		if (roleRepository.findAll().isEmpty()) {
			roleRepository.save(new Role(ERole.ROLE_EMPLOYEE));
			roleRepository.save(new Role(ERole.ROLE_TECHNICIAN));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
		}
	}
}
