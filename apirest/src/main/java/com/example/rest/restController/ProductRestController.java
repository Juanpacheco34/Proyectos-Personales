/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.restController;

import com.example.rest.models.Product;
import com.example.rest.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jpach
 */
@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    
    @Autowired
    private ProductService ps;
    
    @PostMapping
    public Product saveProducts(@RequestBody Product p) throws Exception{

        return ps.save(p) ;
    }
    
    @GetMapping
    public List<Product> allProduct(){
        return ps.allProductService();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> findByIdProduct(@PathVariable Long id){
        Product p = ps.findByIdService(id);
        
        return p == null ? new ResponseEntity("No se ha encontrado el producto con ID " + id, HttpStatus.NOT_FOUND) : ResponseEntity.ok(p);
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product p) throws Exception{
        
        return  ps.updateByIdProductService(id, p);
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        boolean res = ps.deleteByIdService(id);
        
        return res ? new ResponseEntity("Producto Eliminado", HttpStatus.OK) : new ResponseEntity("Error al eliminar el Producto " + id, HttpStatus.NOT_FOUND);
    }
    
}
