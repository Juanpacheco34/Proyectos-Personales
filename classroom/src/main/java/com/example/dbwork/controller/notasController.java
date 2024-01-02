/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.controller;

import com.example.dbwork.modelos.NotaDefinitiva;
import com.example.dbwork.modelos.Notas;
import com.example.dbwork.repositorios.NotasRepositorio;
import com.example.dbwork.repositorios.notaDefinitivaRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
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
@RequestMapping("/notas")
public class notasController {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("beanNotas")
    private NotasRepositorio nr;

    @Autowired
    @Qualifier("beanNotaDefinitiva")
    private notaDefinitivaRepositorio ndr;

    NotaDefinitiva nd = new NotaDefinitiva();

    @GetMapping
    public String notas(HttpServletRequest r, Model m,
            @RequestParam(defaultValue = "all", required = false) String value, SpringDataWebProperties.Pageable page,
            @RequestParam(defaultValue = "0", required = false) int id) {

        Notas n = new Notas();

        m.addAttribute("r", r);

        switch (value) {
            case "new" -> {
                m.addAttribute("n", n);
                m.addAttribute("a", nr.asignaturasAll());
                m.addAttribute("e", nr.estudiantesAll());
                m.addAttribute("i", nr.inscripcionAll());
            }
            case "all" -> {
                m.addAttribute("n", nr.buscarTodos(page));
                m.addAttribute("a", nr.asignaturasAll());
                m.addAttribute("e", nr.estudiantesAll());
                m.addAttribute("i", nr.inscripcionAll());
            }
            case "update" -> {
                m.addAttribute("n", nr.buscarId(id));
                m.addAttribute("a", nr.asignaturasAll());
                m.addAttribute("e", nr.estudiantesAll());
                m.addAttribute("i", nr.inscripcionAll());
            }
        }
        return "notas";
    }

    @PostMapping
    public String newAndUpdate(@ModelAttribute Notas n) {
        if (n.getId() > 0) {
            //Nota Definitiva=(N1​×P1​)+(N2​×P2​)+(N3​×P3​) 30,40,30
            nd.setNotaDefinitiva((n.getPCorte() * 0.3) + (n.getSCorte() * 0.4) + (n.getTCorte() * 0.3));
            nd.setIdAsignatura(n.getIdAsignatura());
            nd.setIdEstudiante(n.getIdEstudiante());
            ndr.actualizar(nd);
            
            
            nr.actualizar(n);
        } else {
            nr.guardar(n);
        }

        return "redirect:/notas";
    }

}
