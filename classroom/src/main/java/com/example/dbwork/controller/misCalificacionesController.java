/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.controller;

import com.example.dbwork.modelos.Estudiantes;
import com.example.dbwork.modelos.NotaDefinitiva;
import com.example.dbwork.repositorios.AsignaturaRepositorio;
import com.example.dbwork.repositorios.EstudiantesRepositorio;
import com.example.dbwork.repositorios.NotasRepositorio;
import com.example.dbwork.repositorios.PromedioRepositorio;
import com.example.dbwork.repositorios.notaDefinitivaRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jpach
 */
@Slf4j
@Controller
public class misCalificacionesController {

    @Autowired
    @Qualifier("beanNotaDefinitiva")
    private notaDefinitivaRepositorio ndr;

    @Autowired
    @Qualifier("beanPromedio")
    private PromedioRepositorio pr;

    @Autowired
    @Qualifier("beanAsignaturas")
    private AsignaturaRepositorio ar;

    @Autowired
    @Qualifier("beanEstudiantes")
    private EstudiantesRepositorio er;

    @Autowired
    @Qualifier("beanNotas")
    private NotasRepositorio nr;

    @GetMapping("/misCalificaciones")
    public String calificaciones(HttpServletRequest r, Model m,
            @RequestParam(defaultValue = "search", required = false) String value,
            @RequestParam(defaultValue = "0", required = false) int id, Pageable pa) {

        log.info("value " + value + " " + id);
        m.addAttribute("r", r);

        switch (value) {
            case "search" -> {

            }
            case "view" -> {
                if (id > 0) {
                    log.info("Entro " + id);
                    m.addAttribute("n", nr.findStudentById(id));
                    m.addAttribute("nd", ndr.buscarTodos(pa));
                    m.addAttribute("e", er.buscarTodos(pa));
                    m.addAttribute("a", ar.buscarTodos(pa));
                }
            }
        }

        return "misCalificaciones";
    }

    @GetMapping("/misCalificaciones/search")
    public String notasStudent(@RequestParam("id") int id, HttpServletRequest r, Model m) {

        log.info("Id" + id);
//        m.addAttribute("r", r);
//        m.addAttribute("student", ndr.findStudentById(id));

        return "redirect:/misCalificaciones?value=view&id=" + id;
    }
}
