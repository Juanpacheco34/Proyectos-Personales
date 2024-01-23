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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;
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
public class Orders  { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /*Aqui indicamos una relacion de muchos a uno*/
    @ManyToOne
    private Cliente cliente;
    
    @OneToMany
    private List<Product> product;

    /*Indicamos a la tabla que solo vamos almacenar la fecha*/
    @Temporal(TemporalType.DATE)
    private Date fecha;
}
