/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.vrsoftware.dao;

import br.com.software.model.Cliente;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface ClienteDao {

    void insert(Cliente obj);

    Cliente findById(String nome);

    Cliente findById(Integer id);

    List<Cliente> findAll();

}
