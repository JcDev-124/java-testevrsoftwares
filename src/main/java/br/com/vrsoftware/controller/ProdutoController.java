/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.controller;

import br.com.software.model.Produto;
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.ProdutoDao;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class ProdutoController {
    
    public ProdutoController(){}
    
 public void inserirProduto(Produto obj){
    
    ProdutoDao produtoDao = DaoFactory.createProdutoDao();

    Produto produtoExistente = produtoDao.findById(obj.getDescricao());

    if (produtoExistente != null) {
        JOptionPane.showMessageDialog(null, "ERRO, PRODUTO JA CADASTRADO", "Erro", JOptionPane.ERROR_MESSAGE);
    } else {
        produtoDao.insert(obj);
    }
        
    }
    
}
