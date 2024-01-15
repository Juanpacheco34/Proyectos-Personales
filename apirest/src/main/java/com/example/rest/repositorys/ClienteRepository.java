/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rest.models.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jpach
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /*JpaRepository no maneja metodo para update asi que creamos uno propio con param indicamos en la consulta cuales son los parametro que pasaremos*/
    @Modifying
    @Query("UPDATE Cliente c SET c.nombreApellido=:nombreApellido,c.email=:email,c.telefono=:telefono WHERE c.id =:id")
    public void updateByid(
            @Param("id") Long id,
            @Param("nombreApellido") String nombreApellido,
            @Param("email") String email,
            @Param("telefono") Long telefono);
}
