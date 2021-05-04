package com.example.laboratorio4.repository;

import com.example.laboratorio4.dto.Empleadosportiempo;
import com.example.laboratorio4.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {


    @Query(value = "select e.first_name, e.last_name, j.job_title, date(jh.start_date) as fecha_inicio, \n" +
            "date(jh.end_date) as fecha_fin,\n" +
            "FLOOR(( DATE_FORMAT(jh.end_date,\"%Y%m%d\") - DATE_FORMAT(jh.start_date,\"%Y%m%d\"))/10000) AS anios_trabajados,\n" +
            "FLOOR((1200 + DATE_FORMAT(jh.end_date,\"%m%d\") - DATE_FORMAT(jh.start_date,\"%m%d\"))/100) %12 AS meses_trabajados\n" +
            "from hr.job_history jh \n" +
            "inner join hr.employees e \n" +
            "on e.employee_id = jh.employee_id\n" +
            "inner join hr.jobs j\n" +
            "on j.job_id = e.job_id\n" +
            "order by datediff(jh.end_date,jh.start_date) desc", nativeQuery = true)
    List<Empleadosportiempo> empleadosmastiempotrabajando();

}
