/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.mapeo;

import com.example.dbwork.modelos.Asignaturas;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author psistemas1
 */
public class asignaturaMapeo implements RowMapper<Asignaturas>{

    @Override
    public Asignaturas mapRow(ResultSet rs, int rowNum) throws SQLException {
        Asignaturas a = new Asignaturas();
        
        a.setId(rs.getInt("ID_PK"));
        a.setNombreClase(rs.getString("NOMBRE_CLASE"));
        a.setIdProfesor(rs.getInt("ID_PROFESOR_FK"));
        
        return a;
    }
    
}
