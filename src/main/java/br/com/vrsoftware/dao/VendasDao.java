/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.vrsoftware.dao;

import br.com.vrsoftware.model.Vendas;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface VendasDao {

    void insert(Vendas obj);

    Vendas findById(Integer id);
    
    void findByVenda(Integer id);
    
    List<Vendas> findAll();

}
