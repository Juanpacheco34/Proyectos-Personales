/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jpach
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderDTO implements Serializable {
    
    private Long id;
    private String nombreApellido;
    private String nombreProducto;
    private int cantidad;
    private BigDecimal precio;
    private Date fecha;
    
}
