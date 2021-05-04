package com.example.laboratorio4.repository;

import com.example.laboratorio4.dto.Departamentosueldopromedio;
import com.example.laboratorio4.dto.EmpleadoDepartamento;
import com.example.laboratorio4.dto.Empleadosporsalario;
import com.example.laboratorio4.dto.Empleadosportiempo;
import com.example.laboratorio4.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {



    @Query(value = "select first_name, last_name, jh.start_date, jh.end_date, j.job_title ,salary from employees e\n" +
            "inner join jobs j on (e.job_id = j.job_id)\n" +
            "inner join  job_history jh on (e.employee_id=jh.employee_id)\n" +
            "order by salary DESC;", nativeQuery = true)
    List<Empleadosporsalario> empleadosmayorsalario();

    @Query(value ="select first_name, last_name, jh.start_date, jh.end_date, j.job_title ,salary from employees e\n" +
            "inner join jobs j on (e.job_id = j.job_id)\n" +
            "inner join  job_history jh on (e.employee_id=jh.employee_id)\n" +
            "where salary = ?1; ",nativeQuery = true)
    List<Empleadosporsalario> empleadosBuscarSalario(BigDecimal salary);

    @Query(value = "select d.department_id, department_name, AVG(e.salary) as 'promedio' from departments d\n" +
            "inner join employees e on (d.department_id = e.department_id)\n" +
            "group by department_id " +
            "order by promedio DESC;",nativeQuery = true)
    List<Departamentosueldopromedio> depasueldopromedio();

    @Query(value = "select employee_id,first_name,last_name,j.job_title,salary from employees e\n" +
            "inner join jobs j on (e.job_id=j.job_id)\n" +
            "where department_id = ?1\n" +
            "order by salary DESC;", nativeQuery = true)
    List<EmpleadoDepartamento> empleadosPorDepartamento(int id);

}
