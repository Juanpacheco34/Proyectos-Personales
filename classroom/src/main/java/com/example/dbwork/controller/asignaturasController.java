/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.controller;

import com.example.dbwork.modelos.Asignaturas;
import com.example.dbwork.repositorios.AsignaturaRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@RequestMapping("/asignaturas")
public class asignaturasController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("beanAsignaturas")
    private AsignaturaRepositorio ar;

    @GetMapping
    public String asignaturas(Model m, HttpServletRequest r, @RequestParam(defaultValue = "all", required = false) String value, Pageable p,
            @RequestParam(defaultValue = "0", required = false) int id) {
        Asignaturas a = new Asignaturas();
        m.addAttribute("r", r);

        switch (value) {
            case "new" -> {
                m.addAttribute("a", a);
                m.addAttribute("p", ar.profesores());
            }
            case "all" -> {
                m.addAttribute("a", ar.buscarTodos(p));
                m.addAttribute("p", ar.profesores());

            }
            case "update" -> {
                m.addAttribute("a", ar.buscarId(id));
                m.addAttribute("p", ar.profesores());

            }

        }

        return "asignaturas";
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Asignaturas a) {
        if (a.getId() > 0) {
            ar.actualizar(a);
        } else {
            ar.guardar(a);
        }

        return "redirect:/asignaturas";

    }

}
