/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.mapeo;

import com.example.dbwork.modelos.Notas;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author psistemas1
 */
public class notasMapeo implements RowMapper<Notas> {

    @Override
    public Notas mapRow(ResultSet rs, int rowNum) throws SQLException {
        Notas notas = new Notas();
        
        notas.setId(rs.getInt("ID_PK"));
        notas.setPCorte(rs.getDouble("P_CORTE"));
        notas.setSCorte(rs.getDouble("S_CORTE"));
        notas.setTCorte(rs.getDouble("T_CORTE"));
        notas.setIdAsignatura(rs.getInt("ID_ASIGNATURA_FK"));
        notas.setIdEstudiante(rs.getInt("ID_ESTUDIANTE_FK"));
        
        return notas;
    }
    
    
    
}
