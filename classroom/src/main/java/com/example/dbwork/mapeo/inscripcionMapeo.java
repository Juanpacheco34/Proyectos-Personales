/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.mapeo;

import com.example.dbwork.modelos.Inscripcion;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jpach
 */
public class inscripcionMapeo implements RowMapper<Inscripcion> {

    @Override
    public Inscripcion mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inscripcion i = new Inscripcion();
        
        i.setId(rs.getInt("ID_PK"));
        i.setIdEstudiante(rs.getInt("ID_ESTUDIANTE_FK"));
        i.setIdAsignatura(rs.getInt("ID_ASIGNATURA_FK"));
        
        return i;
    }
    
}
