/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.services;

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

    /*Insertamos nuestro registro orders a la tabla*/
    @Transactional
    public void saveService(Orders o) throws Exception {
        Validar(o);
        o.setFecha(new Date());
        
        or.save(o);

    }
    
    /*Buscaremos por id la orden que queremos ver en caso de que no se encuentre devolvera un null*/
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
        if (o.getCliente().getId() == 0 || o.getProduct().isEmpty()) {
            throw new Exception("La orden debe tener un cliente asignado || La orden debe tener productos digitados");
        }

    }
}
