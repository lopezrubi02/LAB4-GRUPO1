package com.example.laboratorio4.controller;


import com.example.laboratorio4.dto.Empleadosporsalario;
import com.example.laboratorio4.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/Search")
public class SearchController {
    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping(value = {"","/"})
    public String indice(){
        return "Search/indice";
    }

    @GetMapping(value = {"/Salario"})
    public String listaEmpleadosMayorSalrio (Model model){
        model.addAttribute("listaUsuariosSalario",employeesRepository.empleadosmayorsalario());
      //COMPLETAR Trabajo Emerson
        return "Search/lista2";
    }

    @PostMapping("/busquedaSalario")
    public String buscar (@RequestParam("searchField") Double searchField, Model model, RedirectAttributes attr){
        System.out.println("El valor de la busqueda es "+searchField);
        try{
            BigDecimal number= BigDecimal.valueOf(searchField);
            System.out.println("el valor de number es :"+ number);
            List<Empleadosporsalario>  listaUsuariosSalario = employeesRepository.empleadosBuscarSalario(number);
            if(listaUsuariosSalario.isEmpty()){
                attr.addFlashAttribute("msg","No se encontro resultados de la busqued realizada");
            }
            model.addAttribute("listaUsuariosSalario",listaUsuariosSalario);
            System.out.println("Una Entrada");
        }catch (Exception e){
            System.out.println(e.getMessage() + " " + e.getCause());
            attr.addFlashAttribute("msg","Debe ingresar un número");
            model.addAttribute("listaUsuariosSalario",employeesRepository.empleadosmayorsalario());
            System.out.println("Dos entradas");
        }
        //COMPLETAR
        return "Search/lista2";
    }

    @PostMapping("/busqueda")
    public String buscar (){

        //COMPLETAR
        return "/Search/salario";
    }

    @GetMapping(value = "/Filtro2")
    public String sueldoPromedioDepa (Model model){
        model.addAttribute("listaPromedioDepa", employeesRepository.depasueldopromedio());
        //COMPLETAR
        return "/Search/salario";
    }

    @GetMapping("/listar")
    public String listarEmpleadoDep() {
        //COMPLETAR
        return "/Search/lista3";

    }


}
