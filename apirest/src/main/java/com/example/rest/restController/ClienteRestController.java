/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.restController;

import com.example.rest.models.Cliente;
import com.example.rest.services.ClienteService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author psistemas1
 */
@Slf4j
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService cs;

    /*Devuelve los registros de la tabla*/
    @GetMapping
    @ResponseBody
    public List<Cliente> allCliente() {
        return cs.allClientesService();
    }

    /*Realizamos una insercion de datos a la tabla*/
    @PostMapping
    public Cliente postCliente(@RequestBody Cliente c) throws Exception {
        return cs.saveService(c);
    }

    /*Realizamos una busqueda por id el id lo enviamos por la url pathVariable lo recibe y lo inyecta en la variable id*/
    @GetMapping("/{id}") //Esperamos el id por la url 
    public Cliente cliente(@PathVariable Long id) throws Exception {
        return cs.findByIdService(id);
    }

    /*Realizamos una eliminacion por id el id lo enviamos por la url pathVariable lo recibe y lo inyecta en la variable id*/
    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable Long id) {
        return cs.deleteByIdService(id);
    }

    /*Realizamos una actualizacion a un x registro de la tabla por el id, recibimos el id por url con pathvariable y con requestparam
    recibimos los datos del los demas por parametros*/
    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestParam String nombreApellido, @RequestParam String email, @RequestParam Long telefono) throws Exception {
        return cs.updateByIdService(id, nombreApellido, email, telefono);

    }
}
