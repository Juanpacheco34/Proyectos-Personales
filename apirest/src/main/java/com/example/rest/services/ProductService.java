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

    @Autowired
    ProductRepository pr;

    @Transactional
    public Product save(Product p) throws Exception {
        Validar(p);
        return pr.save(p);

    }

    @Transactional(readOnly = true)
    public List<Product> allProductService() {
        return pr.findAll();
    }

    @Transactional(readOnly = true)
    public Product findByIdService(Long id) {
        Optional<Product> p = pr.findById(id);

        return p.isPresent() ? p.get() : null;
    }

    @Transactional
    public Product updateByIdProductService(Long id, Product p) throws Exception {
        Product up = findByIdService(id);
        up.setCantidad(p.getCantidad());
        up.setPrecio(p.getPrecio());

        Validar(up);

        pr.updateById(up.getId(), up.getCantidad(), up.getPrecio());

        return up;
    }

    @Transactional
    public boolean deleteByIdService(Long id) {
        try {
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
