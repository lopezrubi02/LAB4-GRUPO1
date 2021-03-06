package com.example.laboratorio4.controller;
import com.example.laboratorio4.entity.Departments;
import com.example.laboratorio4.entity.Employees;
import com.example.laboratorio4.entity.Jobs;
import com.example.laboratorio4.repository.DepartmentsRepository;
import com.example.laboratorio4.repository.EmployeesRepository;
import com.example.laboratorio4.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    DepartmentsRepository departmentsRepository;

    @GetMapping(value = {"","/"})
    public String listaEmployee(Model model){
        model.addAttribute("listaEmployee", employeesRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("/new")
    public String nuevoEmployeeForm(@ModelAttribute("employee") Employees employee,Model model) {
        model.addAttribute("listaEmployee", employeesRepository.findAll());
        model.addAttribute("listaJobs", jobsRepository.findAll());
        model.addAttribute("listaDepartments", departmentsRepository.findAll());
        return "employee/Frm";
    }

    @PostMapping("/save")
    public String guardarEmployee(@ModelAttribute("employee") @Valid Employees employees, BindingResult bindingResult,
                                  RedirectAttributes attr,
                                  @RequestParam(name="fechaContrato", required=false) String fechaContrato, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaEmployee", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/Frm";
        }else {

            if (employees.getEmployeeId() == 0) {
                attr.addFlashAttribute("msg", "Empleado creado exitosamente");
                employees.setHireDate(new Date());
                employeesRepository.save(employees);
                return "redirect:/employee";
            } else {

                try {
                    employees.setHireDate(new SimpleDateFormat("yyyy-MM-dd").parse(fechaContrato));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                employeesRepository.save(employees);
                attr.addFlashAttribute("msg", "Empleado actualizado exitosamente");
                return "redirect:/employee";
            }
        }
    }

    @GetMapping("/edit")
    public String editarEmployee(Model model, @RequestParam("id") int id,
                                 @ModelAttribute("employee") Employees employee) {


        Optional<Employees> optEmployee = employeesRepository.findById(id);

        if (optEmployee.isPresent()) {
            employee = optEmployee.get();
            model.addAttribute("employee", employee);
            model.addAttribute("listaJobs", jobsRepository.findAll());
            model.addAttribute("listaEmployee", employeesRepository.findAll());
            model.addAttribute("listaDepartments", departmentsRepository.findAll());
            return "employee/Frm";
        } else {
            return "redirect:/employee/list";
        }
    }

    @GetMapping("/delete")
    public String borrarEmpleado(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Employees> optEmployees = employeesRepository.findById(id);

        if (optEmployees.isPresent()) {
            employeesRepository.deleteById(id);
            attr.addFlashAttribute("msg","Empleado borrado exitosamente");
        }
        return "redirect:/employee";

    }

    @InitBinder("employee")
    public void validador(WebDataBinder binder) {

        PropertyEditorSupport validacionEntero = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    int entero = Integer.parseInt(text);
                    this.setValue(entero > 10 && entero < 51 ? entero : 10);
                } catch (NumberFormatException e) {
                    this.setValue(0);
                }
            }
        };
        binder.registerCustomEditor(Integer.class, "salary", validacionEntero);
    }



    //@PostMapping("/search")
    //public String buscar (){

        //COMPLETAR
    //}

}
