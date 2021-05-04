package com.example.laboratorio4.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name="employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "first_name")
    @NotBlank(message = "No puede estar vacío")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "No puede estar vacío")
    private String lastName;
    @Email(message = "Debe tener el formato: nombre@correo.com")
    private String email;
    @NotBlank(message = "No puede estar vacío")
    @Size(min=8,message = "Debe tener un mínimo de 8 caracteres")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Column(name = "hire_date")
    private Date hireDate;

    //private String jobId;

    @ManyToOne
    @JoinColumn(name ="job_id")
    private Jobs jobs;
    @Min(value = 1,message = "Tiene que ser mayor que 0")
    @Digits(integer = 10,fraction = 0)
    private String salary;
    @Column(name = "commission_pct")
    private Double commissionPct;
    //@Column(name = "manager_id")
    //private int managerId;
    @ManyToOne
    @JoinColumn(name="manager_id")
    private Employees manager;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;


    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public Jobs getJobs() {
        return jobs;
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public Double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Double commissionPct) {
        this.commissionPct = commissionPct;
    }

    public Employees getManager() {
        return manager;
    }

    public void setManager(Employees manager) {
        this.manager = manager;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    @ManyToOne
    @JoinColumn(name="department_id")
    private Departments departments;
}
