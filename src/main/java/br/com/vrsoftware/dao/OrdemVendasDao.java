/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.vrsoftware.dao;

import br.com.vrsoftware.model.OrdemVenda;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface OrdemVendasDao {

    void insert(OrdemVenda obj);

    List<OrdemVenda> findAll(Integer id_venda);
    
}
