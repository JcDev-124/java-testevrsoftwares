/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.software.model;

import java.time.Instant;
import java.time.LocalDate;

/**
 *
 * @author Julio
 */
public class Vendas {

    private Integer id;
    private LocalDate data;
    private Integer id_cliente;
    private Integer status;

    public Vendas(){}
    
    public Vendas(LocalDate data, Integer id_cliente, EnumStatus status) {
        this.data = data;
        this.id_cliente = id_cliente;
        setStatus(status);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getCliente() {
        return id_cliente;
    }

    public void setCliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    
    
    
    public EnumStatus pegarStatus() {
        return EnumStatus.valueOf(status);
    }

    public void setStatus(EnumStatus status) {
        if (status != null) {
            this.status = status.getCode();
        }
    }

}
