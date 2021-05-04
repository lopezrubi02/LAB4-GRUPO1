package com.example.laboratorio4.repository;

import com.example.laboratorio4.entity.Departments;
import com.example.laboratorio4.entity.Employees;
import com.example.laboratorio4.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {

@Query(value = "SELECT job_title FROM hr.jobs",nativeQuery = true)
    List<Jobs> obtenerCargos();

    @Query(value = "SELECT department_name FROM hr.departments",nativeQuery = true)
    List<Departments> obtenerDepartamentos();


}
