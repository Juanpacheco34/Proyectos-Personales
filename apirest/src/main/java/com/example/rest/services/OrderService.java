/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.services;

import com.example.rest.models.Cliente;
import com.example.rest.models.Orders;
import com.example.rest.repositorys.ClienteRepository;
import com.example.rest.repositorys.OrderRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author psistemas1
 */
@Service
public class OrderService {

    /*Inyectamos los bean para manejar sus metodos en el service */
    @Autowired
    OrderRepository or;
    @Autowired
    ClienteRepository cr;

    /*Insertamos nuestro registro orders a la tabla*/
    @Transactional
    public Orders saveService(Orders o) throws Exception {

        Validar(o);

        Orders OSave = new Orders();

        OSave.setNombreProducto(o.getNombreProducto());
        OSave.setCantidad(o.getCantidad());
        /*en este opcional vamos a guardar nuestro objeto cliente para después en la línea
        siguiente hacer una condición que diga que sí está presente nos va a mandar el objeto
        en particular y si no nos manda un null isPresent es un tipo booleano*/
        Optional<Cliente> c = cr.findById(o.getCliente().getId());
        OSave.setCliente(c.isPresent() ? c.get() : null);
        OSave.setFecha(new Date());

        or.save(OSave);
        return OSave;

    }

    @Transactional
    public Orders updateOrdersByIdService(Long id, int cantidad) throws Exception {
        if (id <= 0 || cantidad <= 0) {
            throw new Exception("No es posible Actualizar el registro, problema al actualizar");
        }
        Orders o = new Orders();
        Optional<Orders> Op = or.findById(id);

        return o;
    }

    @Transactional
    public Orders findByIdService(Long id) {
        Optional<Orders> op = or.findById(id);
        
        return op.isPresent() ? op.get() : null;
    }

    //Indicamos a spring que vamos hacer modificaciones en la base datos en este case vamos a leer la transaction va ser solo de lectura
    @Transactional(readOnly = true)
    public List<Orders> allOrders() {
        return or.findAll();
    }

    /*En este metodo validaremos que el usuario ingrese todos los datos requeridos*/
    public void Validar(Orders o) throws Exception {
        if (o.getNombreProducto().isEmpty() || o.getCantidad() <= 0 || o.getCliente().getId() <= 0) {
            throw new Exception("Todos los campos son obligatorios || Debe diligenciarlos correctamente");
        }

    }
}
