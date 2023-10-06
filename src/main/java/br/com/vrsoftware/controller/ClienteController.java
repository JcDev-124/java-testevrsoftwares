/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.controller;

import br.com.software.model.Cliente;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.dao.DaoFactory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio
 */
public class ClienteController {
    ClienteDao clienteDao = DaoFactory.createClienteDao();

    public ClienteController() {
    }
    
    
public void inserirCliente(Cliente obj) {

    // Verifica se o cliente já está cadastrado
    Cliente clienteExistente = clienteDao.findById(obj.getNome());

    if (clienteExistente != null) {
        JOptionPane.showMessageDialog(null, "ERRO, CLIENTE JA CADASTRADO", "Erro", JOptionPane.ERROR_MESSAGE);
    } else {
        // O cliente não existe, então podemos inserir
        clienteDao.insert(obj);
    }
}

public Integer pegarIdCliente(Cliente obj){
    return clienteDao.findById(obj.getNome()).getId();
}

public List<Cliente> retornaTodosClientes(){
    return clienteDao.findAll();
}

}
