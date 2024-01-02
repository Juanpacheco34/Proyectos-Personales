/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.repositorios;

import com.example.dbwork.mapeo.asignaturaMapeo;
import com.example.dbwork.mapeo.estudianteMapeo;
import com.example.dbwork.mapeo.inscripcionMapeo;
import com.example.dbwork.modelos.Asignaturas;
import com.example.dbwork.modelos.Estudiantes;
import com.example.dbwork.modelos.Inscripcion;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author jpach
 */
public class InscripcionRepositorio implements Repo<Inscripcion> {

    @Autowired
    @Qualifier("conect")
    private DataSource ds;

    private JdbcTemplate jt;

    @PostConstruct
    public void postconstuct() {
        this.jt = new JdbcTemplate(ds);
    }

    @Override
    public boolean guardar(Inscripcion x) {
        try {
            String sql = "INSERT INTO INSCRIPCION(ID_PK,ID_ESTUDIANTE_FK,ID_ASIGNATURA_FK)VALUES(?,?,?)";
            jt.update(sql, x.getId(), x.getIdEstudiante(), x.getIdAsignatura());
            return true;
        } catch (DataAccessException e) {

            System.out.println("Error al guardar \n" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Inscripcion x) {
        try {
            String sql = "UPDATE INSCRIPCION SET ID_ASIGNATURA_FK WHERE ID_PK=?";
            jt.update(sql, x.getIdAsignatura(), x.getId());
            return true;
        } catch (DataAccessException e) {

            System.out.println("Error al guardar \n" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Inscripcion> buscarTodos(Pageable p) {
        return jt.query("SELECT * FROM INSCRIPCION", new inscripcionMapeo());
    }

    @Override
    public Inscripcion buscarId(int id) {
        Object[] params = new Object[]{id};
        return jt.queryForObject("SELECT * FROM INSCRIPCION WHERE ID_PK=?", params, new inscripcionMapeo());
    }

    public List<Estudiantes> estudiantesAll() {
        return jt.query("SELECT * FROM ESTUDIANTES", new estudianteMapeo());
    }
    
    public List<Asignaturas> asignaturasAll() {
        return jt.query("SELECT * FROM ASIGNATURAS ORDER BY NOMBRE_CLASE ASC", new asignaturaMapeo());
    }

}
