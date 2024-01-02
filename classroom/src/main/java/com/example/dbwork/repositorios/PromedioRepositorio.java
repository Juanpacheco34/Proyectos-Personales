/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.repositorios;

import com.example.dbwork.mapeo.promedioMapeo;
import com.example.dbwork.modelos.Promedio;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author psistemas1
 */
public class PromedioRepositorio implements Repo<Promedio> {

    @Autowired
    @Qualifier("conect")
    private DataSource ds;

    private JdbcTemplate jt;

    @PostConstruct
    public void postConstruct() {
        jt = new JdbcTemplate(ds);
    }

    @Override
    public boolean guardar(Promedio x) {
        try {
            String sql = "INSERT INTO PROMEDIO(PROMEDIO,ID_ESTUDIANTE_FK)VALUES(?,?)";
            jt.update(sql, x.getPromedio(), x.getIdEstudiante());
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Promedio x) {
        try {
            String sql = "UPDATE PROMEDIO SET PROMEDIO=?";
            jt.update(sql, x.getPromedio());
            return true;
        } catch (Exception e) {
            System.out.println("Error al Actualizar " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Promedio> buscarTodos(Pageable p) {
        return jt.query("SELECT * FROM PROMEDIO", new promedioMapeo());
    }

    @Override
    public Promedio buscarId(int id) {
        Object[] params = new Object[]{id};
        return jt.queryForObject("SELECT * FROM PROMEDIO WHERE ID_PK = ?", params, new promedioMapeo());
    }

}
