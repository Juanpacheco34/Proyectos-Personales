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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> allOrders() {
        return os.allOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Orders postOrders(@RequestBody Orders o) throws Exception{
       return os.saveService(o);
    }
    
    @PutMapping("/{id}")
    public Orders updateOrders(@PathVariable Long id, @RequestParam int cantidad) throws Exception{
        return os.updateOrdersByIdService(id, cantidad);
        
    }

}
