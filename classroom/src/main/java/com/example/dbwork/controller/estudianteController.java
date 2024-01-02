/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.controller;

import com.example.dbwork.modelos.Estudiantes;
import com.example.dbwork.repositorios.EstudiantesRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jpach
 */
@Controller
@RequestMapping("/estudiantes")
public class estudianteController {

    @Autowired
    @Qualifier("beanEstudiantes")
    private EstudiantesRepositorio er;

    @GetMapping
    public String estudiantes(Model m, HttpServletRequest r, @RequestParam(defaultValue = "all", required = false) String value, Pageable p,
            @RequestParam(defaultValue = "0", required = false) int id) {
        Estudiantes e = new Estudiantes();
        m.addAttribute("r", r);

        switch (value) {
            case "new" -> {
                m.addAttribute("e", e);
            }
            case "all" -> {
                m.addAttribute("e", er.buscarTodos(p));

            }
            case "update" -> {
                m.addAttribute("e", er.buscarId(id));

            }

        }

        return "estudiantes";
    }
    
    @PostMapping
    public String newAndUpdate(@ModelAttribute Estudiantes e){
        if (e.getId() > 0) {
            er.actualizar(e);
        }else{
            er.guardar(e);
        }
        
        
        return "redirect:/estudiantes";
        
    }

}
