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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
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

/*Serializable: pueden ser convertidos en un formato que puede ser guardado en un archivo,
transmitido a través de la red, o almacenado de alguna manera persistente.*/
public class Orders implements Serializable { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false) //No puede ser nulo
    private String nombreProducto;
    
    @Column(nullable = false)
    private int cantidad;

    /*Aqui indicamos una relacion de muchos a uno*/
    @ManyToOne
    private Cliente cliente;


    /*Indicamos a la tabla que solo vamos almacenar la fecha*/
    @Temporal(TemporalType.DATE)
    private Date fecha;
}
