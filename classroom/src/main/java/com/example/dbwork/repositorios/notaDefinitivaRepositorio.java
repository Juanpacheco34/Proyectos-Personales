/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.repositorios;

import com.example.dbwork.mapeo.notaDefinitivaMapeo;
import com.example.dbwork.modelos.NotaDefinitiva;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author psistemas1
 */
public class notaDefinitivaRepositorio implements Repo<NotaDefinitiva> {

    @Autowired
    @Qualifier("conect")
    private DataSource ds;

    private JdbcTemplate jt;

    @PostConstruct
    public void postConstruct() {
        jt = new JdbcTemplate(ds);
    }

    @Override
    public boolean guardar(NotaDefinitiva x) {
        try {
            String sql = "INSERT INTO NOTA_DEFINITIVA (NOTA_DEFINITIVA,ID_ASIGNATURA_FK,ID_ESTUDIANTE_FK) VALUES(?,?,?)";
            jt.update(sql, x.getNotaDefinitiva(), x.getIdAsignatura(),x.getIdEstudiante());
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(NotaDefinitiva x) {
        try {
            String sql = "UPDATE NOTA_DEFINITIVA SET NOTA_DEFINITIVA=? WHERE ID_ASIGNATURA_FK=? AND ID_ESTUDIANTE_FK=? ";
            jt.update(sql, x.getNotaDefinitiva(),x.getIdAsignatura(),x.getIdEstudiante());
            return true;
        } catch (Exception e) {
            System.out.println("Error al Actualizar " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<NotaDefinitiva> buscarTodos(SpringDataWebProperties.Pageable p) {
        return jt.query("SELECT * FROM NOTA_DEFINITIVA", new notaDefinitivaMapeo());
    }

    @Override
    public NotaDefinitiva buscarId(int id) {
        Object[] params = new Object[]{id};
       return jt.queryForObject("SELECT * FROM NOTA_DEFINITIVA WHERE ID_PK = ?", params, new notaDefinitivaMapeo());
    }

}
