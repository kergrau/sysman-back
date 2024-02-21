package com.sysman.tecnica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sysman.tecnica.entities.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
