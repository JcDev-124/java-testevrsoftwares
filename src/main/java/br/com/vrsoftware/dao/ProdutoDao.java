/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.vrsoftware.dao;

import br.com.software.model.Produto;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface ProdutoDao {
    
        void insert(Produto obj);
        void update(Produto obj);
	Produto findById(String nome);
        Produto findById(Integer id);
	List<Produto> findAll();
        
}
