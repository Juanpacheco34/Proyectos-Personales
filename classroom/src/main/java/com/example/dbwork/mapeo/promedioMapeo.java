/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.mapeo;

import com.example.dbwork.modelos.Promedio;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author psistemas1
 */
public class promedioMapeo implements RowMapper<Promedio> {

    @Override
    public Promedio mapRow(ResultSet rs, int rowNum) throws SQLException {

        Promedio pro = new Promedio();
        
        pro.setId(rs.getInt("ID_PK"));
        pro.setPromedio(rs.getDouble("PROMEDIO"));
        pro.setIdEstudiante(rs.getInt("ID_ESTUDIANTE_FK"));
        
        return pro;
    }
    
}
