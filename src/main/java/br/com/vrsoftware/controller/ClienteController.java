/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.controller;

import br.com.software.model.Cliente;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.dao.DaoFactory;

/**
 *
 * @author Julio
 */
public class ClienteController {

    public ClienteController() {
    }
    
    
    public void inserirCliente(Cliente obj){
            ClienteDao clienteDao = DaoFactory.createClienteDao();
            clienteDao.insert(obj);
    }
    
}
