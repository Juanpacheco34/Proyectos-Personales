/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.services;

import com.example.rest.models.Product;
import com.example.rest.repositorys.ProductRepository;
import java.math.BigDecimal;
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
public class ProductService {

    //Inyecion de bean para manejar los metodos que tenemos en la clase productRepository
    @Autowired
    ProductRepository pr;

    @Transactional
    public Product save(Product p) throws Exception {
        Validar(p); //Realizamos validacion de los campos requeridos en caso de que venga vacio lanzara un error
        return pr.save(p);

    }

    // Nos devuelve todos los registros que tenemos en la tabla
    @Transactional(readOnly = true)
    public List<Product> allProductService() {
        return pr.findAll();
    }

    //Nos devuelve el registro de la tabla con el id que hemos proporcionado
    @Transactional(readOnly = true)
    public Product findByIdService(Long id) {
        /* el metodo buscar por el id nos pide realizar una validacion de si se ha encontrado el registro 
        lo que realizamos es que guardamos la busqueda en un optional y de hay verificamos con isPresent que es un booleano
        no dice si se ha encontrado el registro si es si nos devuelve el objeto si no nos devuelve un null*/
        Optional<Product> p = pr.findById(id);

        return p.isPresent() ? p.get() : null;
    }

    /*Actulizamos el registro dependiendo del id que nos indiquen*/
    @Transactional
    public Product updateByIdProductService(Long id, Product p) throws Exception {
        Product up = findByIdService(id);
        up.setCantidad(p.getCantidad());
        up.setPrecio(p.getPrecio());

        Validar(up);

        /*pasamos los parametros correspondientes que son el id de la condicion la nueva cantidasd y el nuevo precio*/
        pr.updateById(up.getId(), up.getCantidad(), up.getPrecio());

        return up;
    }

    /*Eliminamos el registro por id indicado*/
    @Transactional
    public boolean deleteByIdService(Long id) {
        try {
            /*En caso de que el registro por el id no exista nos devolvera el siguiente mensaje*/
            if (findByIdService(id) == null) {
                throw new Exception("No existe el registro con el id " + id);
            }
            
            pr.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /*En este metodo validaremos que el usuario ingrese todos los datos requeridos*/
    public void Validar(Product p) throws Exception {
        log.info("Objeto: " + p.toString());
        if (p.getNombreProducto().isEmpty() || p.getCantidad() <= 0 || p.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Todos los campos son obligatorios || Debe diligenciarlos correctamente");
        }

    }

}
