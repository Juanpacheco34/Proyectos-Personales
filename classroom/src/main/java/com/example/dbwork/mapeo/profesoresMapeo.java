/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.mapeo;

import com.example.dbwork.modelos.Profesores;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author jpach
 */
public class profesoresMapeo implements RowMapper<Profesores> {
    
    Log log = LogFactory.getLog(getClass());

    @Override
    public Profesores mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profesores profe = new Profesores();
        profe.setId(rs.getInt("ID_PK"));
        profe.setNombreApellido(rs.getString("NOMBRE_APELLIDO"));
        return profe;
    }
    
}