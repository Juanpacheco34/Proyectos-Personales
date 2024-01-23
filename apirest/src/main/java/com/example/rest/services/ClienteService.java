/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.services;

import com.example.rest.models.Cliente;
import com.example.rest.repositorys.ClienteRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author psistemas1
 */
@Slf4j
@Service
public class ClienteService {

    /* Inyectamos la interfaz ClienteRepository ya que de aqui vamos a utilizar nuestro metodos CRUD */
    @Autowired
    private ClienteRepository cr;

    /*Insertamos nuestro registro cliente a la tabla*/
    @Transactional
    public Cliente saveService(Cliente c) throws Exception {

        Validar(c); //linea 78
        log.info("Registro Exitoso");
        return cr.save(c);


    }

    /*En este metodo devolveremos todos los Clientes que hay en la tabla*/
    @Transactional(readOnly = true) // readOnly nuestra transacion va a ser solo de lectura no va a modificar nada
    public List<Cliente> allClientesService() {

        return cr.findAll();
    }

    /*Busqueda por Id*/
    @Transactional(readOnly = true)
    public Cliente findByIdService(Long id) throws Exception {

        Optional<Cliente> c = cr.findById(id);
        
        return c.isPresent() ? c.get() : null; // isPresent booleano true devuelve el objeto , false nulo

    }

    /*Eliminaremos clientes por el id*/
    @Transactional
    public String deleteByIdService(Long id) {
        try {
            cr.deleteById(id);

            return "Eliminado con Exito";
        } catch (Exception e) {
            return "Error al eliminar :" + e.getMessage() + "---  ClienteService.class";

        }
    }

    
    @Transactional
    public Cliente updateByIdService(Long id , String nombreApellido, String correo, Long telefono) throws Exception{
        
        Cliente c = new Cliente();
        c.setId(id);
        c.setNombreApellido(nombreApellido);
        c.setEmail(correo);
        c.setTelefono(telefono);
        
        Validar(c);
        
        cr.updateByid(id, nombreApellido, correo, telefono);
        log.info("Actualizacion Exitosa");
        return c;
        
    }
    
    /*En este metodo validaremos que el usuario ingrese todos los datos requeridos
    -- para el correo se puede utilizar tambien una expresion regular para validar*/
    public void Validar(Cliente c) throws Exception {
        if (c.getNombreApellido().isEmpty() || c.getEmail().isEmpty() || String.valueOf(c.getTelefono()).length() < 10) {
            throw new Exception("Todos los campos son obligatorios || Debe diligenciarlos correctamente");
        }
    }

}
