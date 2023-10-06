/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.software.model;

import java.util.Set;

/**
 *
 * @author Julio
 */
public class OrdemVenda {
    private Integer id;
    private Integer id_venda;
    private Integer id_produto;
    private Integer quantidade;
    private Double preco;

    public OrdemVenda(Integer id_venda, Integer id_produto, Integer quantidade, Double preco) {
        this.id_venda = id_venda;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public OrdemVenda(Integer id_produto, Integer quantidade, Double preco) {
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    public OrdemVenda(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public Integer getIdVenda() {
        return id_venda;
    }

    public void setIdVenda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public Integer getIdProduto() {
        return id_produto;
    }

    public void setIdProduto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    
    
    
    
    
    
    public double subTotal(){
        return preco * quantidade;
    }
}
