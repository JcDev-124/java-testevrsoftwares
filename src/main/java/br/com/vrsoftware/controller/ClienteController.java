/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.controller;

import br.com.software.model.Cliente;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.dao.DaoFactory;
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
    
  
public boolean inserirCliente(Cliente obj) {

    // Verifica se o cliente já está cadastrado
    Cliente clienteExistente = clienteDao.findById(obj.getNome());

    if (clienteExistente != null && clienteExistente.getNome().equals(obj.getNome())) {
       JOptionPane.showMessageDialog(null, "ERRO, CLIENTE JA CADASTRADO", "Erro", JOptionPane.ERROR_MESSAGE);
       return false;
    }
    clienteDao.insert(obj);
    return true;
    
}

public Integer pegarIdCliente(Cliente obj){
    return clienteDao.findById(obj.getNome()).getId();
}

public Integer pegarIdCliente(String nome){
    return clienteDao.findById(nome).getId();
}
public Cliente pegarCliente(String nome){
    return clienteDao.findById(nome);
}

public String pegarNomeCliente(Integer id){
    
    String cliente = clienteDao.findById(id).getNome();
    if(cliente!=null) return cliente;
    else return "nao existe";
}

public List<Cliente> retornaTodosClientes(){
    return clienteDao.findAll();
}

}
