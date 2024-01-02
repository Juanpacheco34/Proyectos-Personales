/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.mapeo;

import com.example.dbwork.modelos.NotaDefinitiva;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author psistemas1
 */
public class notaDefinitivaMapeo implements RowMapper<NotaDefinitiva> {

    @Override
    public NotaDefinitiva mapRow(ResultSet rs, int rowNum) throws SQLException {

        NotaDefinitiva nd = new NotaDefinitiva();
        
        nd.setId(rs.getInt("ID_PK"));
        nd.setNotaDefinitiva(rs.getDouble("NOTA_DEFINITIVA"));
        nd.setIdAsignatura(rs.getInt("ID_ASIGNATURA_FK"));
        nd.setIdEstudiante(rs.getInt("ID_ESTUDIANTE_FK"));
        
       return nd;
    }
    
}
