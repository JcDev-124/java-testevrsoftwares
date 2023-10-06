/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.controller;

import br.com.software.model.OrdemVenda;
import br.com.software.model.Produto;
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.OrdemVendasDao;
import br.com.vrsoftware.dao.ProdutoDao;

/**
 *
 * @author Julio
 */
public class OrdemVendasController {

    OrdemVendasDao ordem = DaoFactory.createOrdemVendaDao();
    ProdutoDao produtoDao = DaoFactory.createProdutoDao();

    public void inserirOrdemVendas(OrdemVenda obj) {
        ordem.insert(obj);
    }

   
}
