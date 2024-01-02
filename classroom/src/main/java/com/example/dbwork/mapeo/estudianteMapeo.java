/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.mapeo;

import com.example.dbwork.modelos.Estudiantes;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jpach
 */
public class estudianteMapeo implements RowMapper<Estudiantes> {

    @Override
    public Estudiantes mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Estudiantes est = new Estudiantes();
        
        est.setId(rs.getInt("ID_PK"));
        est.setNombreApellido(rs.getString("NOMBRE_APELLIDO"));
        
        return est;
    }
    
}
