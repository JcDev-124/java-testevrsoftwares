/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.controller;

import br.com.software.model.Cliente;
import br.com.software.model.Produto;
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.ProdutoDao;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class ProdutoController {
    
    public ProdutoController() {
    }
    ProdutoDao produtoDao = DaoFactory.createProdutoDao();
    
    public boolean inserirProduto(Produto obj) {
        
        Produto produtoExistente = produtoDao.findById(obj.getDescricao());
        
        if (produtoExistente != null && produtoExistente.getDescricao().equals(obj.getDescricao())) {
            JOptionPane.showMessageDialog(null, "ERRO, PRODUTO JA CADASTRADO", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        produtoDao.insert(obj);
        
        return true;
        
    }
    
    public Integer pegarIdProduto(Produto obj) {
        return produtoDao.findById(obj.getDescricao()).getId();
    }
    
    public Integer pegarIdProduto(String descricao) {
        return produtoDao.findById(descricao).getId();
    }

    public List<Produto> retornaTodosProdutos() {
        return produtoDao.findAll();
    }
    
    public Double retornaPrecoProduto(String nome) {
        return produtoDao.findById(nome).getPreco();
    }
    
    public Produto retornaProdutoPorNome(String nome) {
        return produtoDao.findById(nome);
    }
    
    public void atualizaProduto(Produto obj) {
        produtoDao.update(obj);
    }
}
