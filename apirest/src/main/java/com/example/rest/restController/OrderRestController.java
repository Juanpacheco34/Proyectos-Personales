/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.restController;

import com.example.rest.models.Orders;
import com.example.rest.services.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author psistemas1
 */
@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    @Autowired
    private OrderService os;

    /*por el mentodo post insertaremos un registro que nos llega desde el postman en forma de objeto*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postOrders(@RequestBody Orders o) throws Exception {
        os.saveService(o);
    }

    /*Nos devuelve una lista con los registros en que tenemos en dicha orden*/
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> allOrders() {
        return os.allOrders();
    }

    /*por la url mandaremos el id para buscar la orden en caso de no encontrar nada nos devuelve un aviso al postman*/
    @GetMapping("/{id}")
    public ResponseEntity<Orders> findProduct(@PathVariable("id") Long id) {
        Orders o = os.findByIdService(id);
        return o != null ? ResponseEntity.ok(o)
                : new ResponseEntity("No se encontro resultado para el id " + id, HttpStatus.NOT_FOUND);
    }

}
