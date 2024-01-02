/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.controller;

import com.example.dbwork.modelos.Inscripcion;
import com.example.dbwork.modelos.NotaDefinitiva;
import com.example.dbwork.modelos.Notas;
import com.example.dbwork.repositorios.InscripcionRepositorio;
import com.example.dbwork.repositorios.NotasRepositorio;
import com.example.dbwork.repositorios.notaDefinitivaRepositorio;
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
@RequestMapping("/inscripcion")
public class inscripcionController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("beanInscripcion")
    private InscripcionRepositorio ir;

    @Autowired
    @Qualifier("beanNotas")
    private NotasRepositorio nr;
    
    @Autowired
    @Qualifier("beanNotaDefinitiva")
    private notaDefinitivaRepositorio ndr;

    Notas n = new Notas();
    NotaDefinitiva nd = new NotaDefinitiva();

    @GetMapping
    public String inscripcion(Model m, HttpServletRequest r, @RequestParam(defaultValue = "all", required = false) String value, Pageable p,
            @RequestParam(defaultValue = "0", required = false) int id) {
        Inscripcion i = new Inscripcion();
        m.addAttribute("r", r);

        switch (value) {
            case "new" -> {
                m.addAttribute("i", i);
                m.addAttribute("e", ir.estudiantesAll());
                m.addAttribute("a", ir.asignaturasAll());
            }
            case "all" -> {
                m.addAttribute("i", ir.buscarTodos(p));
                m.addAttribute("e", ir.estudiantesAll());
                m.addAttribute("a", ir.asignaturasAll());

            }
            case "update" -> {
                m.addAttribute("i", ir.buscarId(id));
                m.addAttribute("e", ir.estudiantesAll());
                m.addAttribute("a", ir.asignaturasAll());

            }

        }

        return "inscripcion";
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Inscripcion i) {
        if (i.getId() > 0) {
            ir.actualizar(i);
        } else {

            ir.guardar(i);
            n.setIdEstudiante(i.getIdEstudiante());
            n.setIdAsignatura(i.getIdAsignatura());
            n.setPCorte(0.0);
            n.setSCorte(0.0);
            n.setTCorte(0.0);
            
            //Nota Definitiva=(N1​×P1​)+(N2​×P2​)+(N3​×P3​) 30,40,30
            nd.setNotaDefinitiva((n.getPCorte() * 0.3) + (n.getSCorte() * 0.4) + (n.getTCorte() * 0.3));
            nd.setIdAsignatura(n.getIdAsignatura());
            nd.setIdEstudiante(n.getIdEstudiante());
            ndr.guardar(nd);
            nr.guardar(n);

        }

        return "redirect:/inscripcion";

    }

}
