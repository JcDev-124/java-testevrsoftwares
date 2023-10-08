/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.software.model;

/**
 *
 * @author Julio
 */
public class Produto {
    
    private Integer id;
    private String descricao;
    private Double preco;
    private Integer quantidade;

    public Produto(){}
    
    public Produto(String descricao, Double preco, Integer quantidade) {
        
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    
    
}
