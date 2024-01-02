/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.modelos;

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
public class Asignaturas {

    private int id;
    private String nombreClase;
    private int idProfesor;

}
