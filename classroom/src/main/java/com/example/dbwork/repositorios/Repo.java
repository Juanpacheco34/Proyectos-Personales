/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dbwork.repositorios;

import java.util.List;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

/**
 *
 * @author jpach
 * @param <T>
 */
public interface Repo<T> {

    public boolean guardar(T x);

    public boolean actualizar(T x);

    public List<T> buscarTodos(Pageable p);

    public T buscarId(int id);

}
