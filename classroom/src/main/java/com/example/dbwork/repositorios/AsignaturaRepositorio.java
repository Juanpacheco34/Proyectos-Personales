/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.repositorios;

import com.example.dbwork.mapeo.asignaturaMapeo;
import com.example.dbwork.mapeo.profesoresMapeo;
import com.example.dbwork.modelos.Asignaturas;
import com.example.dbwork.modelos.Profesores;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author psistemas1
 */
public class AsignaturaRepositorio implements Repo<Asignaturas> {

    Log log = LogFactory.getLog(getClass());

    @Autowired
    @Qualifier("conect")
    private DataSource ds;

    private JdbcTemplate jt;

    @PostConstruct
    public void postContruct() {
        jt = new JdbcTemplate(ds);
    }

    @Override
    public boolean guardar(Asignaturas x) {
        try {
            String sql = "INSERT INTO ASIGNATURAS(NOMBRE_CLASE, ID_PROFESOR_FK)VALUES(?,?)";
            jt.update(sql, x.getNombreClase(), x.getIdProfesor());
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Asignaturas x) {
        try {
            String sql = "UPDATE ASIGNATURAS SET NOMBRE_CLASE=?,ID_PROFESOR_FK=? WHERE ID_PK=?";
            jt.update(sql, x.getNombreClase(), x.getIdProfesor(), x.getId());
            return true;

        } catch (Exception e) {
            System.err.println("Error al Actualizar " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Asignaturas> buscarTodos(Pageable p) {

        return jt.query("SELECT * FROM ASIGNATURAS", new asignaturaMapeo());
    }

    @Override
    public Asignaturas buscarId(int id) {
        Object[] params = new Object[]{id};
        String sql = "SELECT * FROM ASIGNATURAS WHERE ID_PK=?";
        return jt.queryForObject(sql, params, new asignaturaMapeo());
    }

    public List<Profesores> profesores() {
        return jt.query("SELECT * FROM PROFESORES ORDER BY NOMBRE_APELLIDO ASC", new profesoresMapeo());
    }

}
