                /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.controller;

import com.example.dbwork.modelos.Asignaturas;
import com.example.dbwork.modelos.Estudiantes;
import com.example.dbwork.modelos.NotaDefinitiva;
import com.example.dbwork.modelos.Notas;
import com.example.dbwork.modelos.Promedio;
import com.example.dbwork.repositorios.AsignaturaRepositorio;
import com.example.dbwork.repositorios.EstudiantesRepositorio;
import com.example.dbwork.repositorios.NotasRepositorio;
import com.example.dbwork.repositorios.PromedioRepositorio;
import com.example.dbwork.repositorios.notaDefinitivaRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jpach
 */
@Controller
@RequestMapping("/misCalificaciones")
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
    
    @GetMapping
    public String misCalificaciones(HttpServletRequest r, Model m, @RequestParam(defaultValue = "all", required = false) String value,
            @RequestParam(defaultValue = "0", required = false) int id, Pageable pa) {
        m.addAttribute("r", r);


        switch (value) {
            case "all" -> {
                m.addAttribute("e", er.buscarTodos(pa));
                m.addAttribute("a", ar.buscarTodos(pa));
                m.addAttribute("n", nr.buscarTodos(pa));
                m.addAttribute("nd", ndr.buscarTodos(pa));

            }

        }

        return "misCalificaciones";
    }

}
