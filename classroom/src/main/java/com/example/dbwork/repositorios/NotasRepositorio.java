/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.repositorios;

import com.example.dbwork.mapeo.asignaturaMapeo;
import com.example.dbwork.mapeo.estudianteMapeo;
import com.example.dbwork.mapeo.inscripcionMapeo;
import com.example.dbwork.mapeo.notasMapeo;
import com.example.dbwork.modelos.Asignaturas;
import com.example.dbwork.modelos.Estudiantes;
import com.example.dbwork.modelos.Inscripcion;
import com.example.dbwork.modelos.Notas;
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
public class NotasRepositorio implements Repo<Notas> {

    @Autowired
    @Qualifier("conect")
    private DataSource ds;

    private JdbcTemplate jt;

    @PostConstruct
    public void postContruct() {
        jt = new JdbcTemplate(ds);
    }

    @Override
    public boolean guardar(Notas x) {
        try {
            String sql = "INSERT INTO NOTAS(P_CORTE, S_CORTE, T_CORTE, ID_ASIGNATURA_FK, ID_ESTUDIANTE_FK)VALUES(?,?,?,?,?)";
            jt.update(sql, x.getPCorte(), x.getSCorte(), x.getTCorte(), x.getIdAsignatura(), x.getIdEstudiante());
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Notas x) {
        try {
            String sql = "UPDATE NOTAS SET P_CORTE=?, S_CORTE=?, T_CORTE=? WHERE ID_PK = ?";
            jt.update(sql, x.getPCorte(), x.getSCorte(), x.getTCorte(), x.getId());
            return true;

        } catch (Exception e) {
            System.err.println("Error al Actualizar " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Notas> buscarTodos(Pageable p) {

        return jt.query("SELECT * FROM NOTAS", new notasMapeo());
    }

    @Override
    public Notas buscarId(int id) {
        Object[] params = new Object[]{id};
        String sql = "SELECT * FROM NOTAS WHERE ID_PK=?";
        return jt.queryForObject(sql, params, new notasMapeo());

    }
    
    public List<Asignaturas> asignaturasAll(){
        return jt.query("SELECT * FROM ASIGNATURAS ORDER BY NOMBRE_CLASE ASC ", new asignaturaMapeo());
    }
    
    public List<Estudiantes> estudiantesAll(){
        return jt.query("SELECT * FROM ESTUDIANTES ORDER BY NOMBRE_APELLIDO ASC ", new estudianteMapeo());
    }
    public List<Inscripcion> inscripcionAll(){
        return jt.query("SELECT * FROM INSCRIPCION ORDER BY ID_PK ASC ", new inscripcionMapeo());
    }
    
        public List<Notas> findStudentById(int id){
        return jt.query("SELECT * FROM Notas WHERE ID_ESTUDIANTE_FK  = ?",new Object[]{id}, new notasMapeo());
    }
}
