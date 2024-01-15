/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.repositorys;

import com.example.rest.models.Product;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpach
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
        //Se crea una interfaz que herede de jpaRepository para poder obtener los metodos de consulta
    
    @Modifying
    @Query("UPDATE Product p SET p.cantidad=:cantidad, p.precio=:precio WHERE p.id=:id")
    public void updateById(@Param("id") Long id, @Param("cantidad") int cantidad, @Param("precio") BigDecimal precio);

}
