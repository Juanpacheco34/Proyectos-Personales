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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Inscripcion {
    
    private int id;
    private int idEstudiante;
    private int idAsignatura;
    
}
