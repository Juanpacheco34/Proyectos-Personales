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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jpach
 */
@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    
    /*inyeccion de bean de la clase servicio*/
    @Autowired
    private ProductService ps;
    
    /*por peticion post realizaremos el ingreso del registro*/
    @PostMapping
    public Product saveProducts(@RequestBody Product p) throws Exception{

        return ps.save(p) ;
    }
    
    /*por peticion get Nos devuelve un array con los registros de la tabla */
    @GetMapping
    public List<Product> allProduct(){
        return ps.allProductService();
    }
    
    /*por peticion get recibimo el id el cual lo trae en la url
    en el responseEntity nos devolvera un el registro si es encontrado si no, nos devolvera un mensaje */
    @GetMapping("/{id}")
    public ResponseEntity<Product> findByIdProduct(@PathVariable Long id){
        Product p = ps.findByIdService(id);
        
        return p == null ? new ResponseEntity("No se ha encontrado el producto con ID " + id, HttpStatus.NOT_FOUND) : ResponseEntity.ok(p);
    }
   
    /*por la peticion put realizaremos la actualizacion del registro recibiendo el id por la url y los datos actualizar 
    en un objeto*/
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product p) throws Exception{
        
        return  ps.updateByIdProductService(id, p);
        
    }
    
    /*por la peticion delete realizaremos la eliminacion del registro por el id que recibiremos por la url*/
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        boolean res = ps.deleteByIdService(id);
        /*res es una variable booleana en caso de false devolvera un mensaje al sevidor postman*/
        return res ? new ResponseEntity("Producto Eliminado", HttpStatus.OK) : new ResponseEntity("Error al eliminar el Producto " + id, HttpStatus.NOT_FOUND);
    }
    
}
