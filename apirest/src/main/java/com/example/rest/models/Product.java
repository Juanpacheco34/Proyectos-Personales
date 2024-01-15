/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jpach
 */
//Mapeo de la clase como tabla de la base de datos
@Entity
//Metodos Getter, Setter, toString
@Data
//Constructores
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nombreProducto;
    
    @Column(nullable = false)
    private int cantidad;
    
    /*Indicamos a la tabla que la columna va a manejar 7 enteros y 2 decimales*/
    @Column(name = "precio", precision = 9, scale = 2)
    private BigDecimal precio;
    
    
}
