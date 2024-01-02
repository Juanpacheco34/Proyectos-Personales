/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.controller;

import com.example.dbwork.modelos.Profesores;
import com.example.dbwork.repositorios.ProfesoresRepositorio;
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
 * @author psistemas1
 */
@Controller
@RequestMapping("/profesores")
public class profesoresController {

    @Autowired
    @Qualifier("beanProfesores")
    private ProfesoresRepositorio pr;

    @GetMapping
    public String profesorespage(HttpServletRequest r, Model m,
            @RequestParam(defaultValue = "all", required = false) String value, Pageable page,
            @RequestParam(defaultValue = "0", required = false) int id) {
        Profesores p = new Profesores();
        m.addAttribute("r", r);
        switch (value) {
            case "new" -> {
                m.addAttribute("p", p);
            }
            case "all" -> {
                m.addAttribute("p", pr.buscarTodos(page));
            }
            case "update" -> {
                m.addAttribute("p", pr.buscarId(id));
            }
        }
        return "profesores";
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Profesores p) {
        if (p.getId() > 0) {
            pr.actualizar(p);
        } else {
            pr.guardar(p);
        }

        return "redirect:/profesores";
    }

}
