/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.repositorios;

import com.example.dbwork.mapeo.estudianteMapeo;
import com.example.dbwork.modelos.Estudiantes;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author psistemas1
 */
public class EstudiantesRepositorio  implements Repo<Estudiantes>{
    
    @Autowired
    @Qualifier("conect")
    private DataSource ds;
    
    private JdbcTemplate jt;
    
    @PostConstruct
    public void postContruct(){
        jt = new JdbcTemplate(ds);
    }
    

    @Override
    public boolean guardar(Estudiantes x) {
        try {
            String sql = "INSERT INTO ESTUDIANTES(NOMBRE_APELLIDO) VALUES(?)";
            jt.update(sql,x.getNombreApellido());
            
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Estudiantes x) {
        
        try {
            String sql = "UPDATE ESTUDIANTES SET NOMBRE_APELLIDO =? WHERE ID_PK=?";
            jt.update(sql,x.getNombreApellido(),x.getId());
            return true;
        } catch (Exception e) {
            System.err.println("Error al Actualizar " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Estudiantes> buscarTodos(Pageable p) {
        
        return jt.query("SELECT * FROM ESTUDIANTES",new estudianteMapeo());
        
        }

    @Override
    public Estudiantes buscarId(int id) {
        Object[] params = new Object[]{id};
        String sql = "SELECT * FROM ESTUDIANTES WHERE ID_PK=?";
        return jt.queryForObject(sql, params,new estudianteMapeo());
    }
    
}
