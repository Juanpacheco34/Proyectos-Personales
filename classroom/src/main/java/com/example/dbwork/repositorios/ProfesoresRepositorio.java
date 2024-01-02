/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.repositorios;

import com.example.dbwork.mapeo.profesoresMapeo;
import com.example.dbwork.modelos.Profesores;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpach
 */
@Repository
public class ProfesoresRepositorio implements Repo<Profesores> {
    Log log = LogFactory.getLog(getClass());
    
    //Realizamos la inyeccion del bean conect de configuracion al datasource
    @Autowired
    @Qualifier("conect")
    private DataSource ds;
    
    private JdbcTemplate jt;

    //Indicamos que se ejecute depues que todos los bean esten cargados y asi inicialice el objeto de jt que es el jdbcTemplate con el bean de Datasource
    @PostConstruct
    public void postConstruct(){
        jt = new JdbcTemplate(ds);
    }
    
    //Realizamos la insercion a la base de datos utilizamos el jt.update por que es mas seguro
    @Override
    public boolean guardar(Profesores x) {
       
        try {
            String sql = "INSERT INTO PROFESORES(NOMBRE_APELLIDO) VALUES (?)";
            jt.update(sql, x.getNombreApellido());
            return true;
        } catch (DataAccessException e) {
            System.err.println("Error al Guardar el Registro\n" + e.getMessage());
            return false;
        }
    }

    
    @Override
    public boolean actualizar(Profesores x) {
        try {
            String sql = "UPDATE PROFESORES SET NOMBRE_APELLIDO=? WHERE ID_PK=?";
            jt.update(sql,x.getNombreApellido(),x.getId());
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar " + e.getMessage());
            return false;
        }
            
    }

    @Override
    public List<Profesores> buscarTodos(Pageable p) {
        return jt.query("SELECT * FROM PROFESORES", new profesoresMapeo());
    }

    @Override
    public Profesores buscarId(int id) {
        Object[] params = new Object[]{id};
        String sql = "SELECT * FROM PROFESORES WHERE ID_PK=?";
        return jt.queryForObject(sql,params,new profesoresMapeo());
    }
    


}
