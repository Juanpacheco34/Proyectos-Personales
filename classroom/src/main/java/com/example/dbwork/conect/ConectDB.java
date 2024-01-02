/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.conect;

import com.example.dbwork.repositorios.AsignaturaRepositorio;
import com.example.dbwork.repositorios.EstudiantesRepositorio;
import com.example.dbwork.repositorios.InscripcionRepositorio;
import com.example.dbwork.repositorios.NotasRepositorio;
import com.example.dbwork.repositorios.ProfesoresRepositorio;
import com.example.dbwork.repositorios.PromedioRepositorio;
import com.example.dbwork.repositorios.notaDefinitivaRepositorio;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author jpach
 */
@Configuration
//@ComponentScan("com.example.dbwork.conect")
public class ConectDB {

    @Bean(name = "conect")
    public DataSource conect() {
        DriverManagerDataSource dmd = new DriverManagerDataSource();
        
        dmd.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dmd.setUrl("jdbc:mysql://localhost:3306/classroom?zeroDateTimeBehavior=CONVERT_TO_NULL");
        dmd.setUsername("root");
        dmd.setPassword("Juan34241.");
        
        return dmd;
    }
    
    @Bean(name = "beanProfesores")
    public ProfesoresRepositorio beanProfesores(){
        return new ProfesoresRepositorio();
    }
    @Bean(name = "beanAsignaturas")
    public AsignaturaRepositorio beanAsignaturas(){
        return new AsignaturaRepositorio();
    }
    @Bean(name = "beanEstudiantes")
    public EstudiantesRepositorio beanEstudiantes(){
        return new EstudiantesRepositorio();
    }
    @Bean(name = "beanInscripcion")
    public InscripcionRepositorio beanInscripcion(){
        return new InscripcionRepositorio();
    }
    @Bean(name = "beanNotas")
    public NotasRepositorio beanNotas(){
        return new NotasRepositorio();
    }
    @Bean(name = "beanNotaDefinitiva")
    public notaDefinitivaRepositorio beanNotaDefinitiva(){
        return new notaDefinitivaRepositorio();
    }
    @Bean(name = "beanPromedio")
    public PromedioRepositorio beanPromedio(){
        return new PromedioRepositorio();
    }
}
