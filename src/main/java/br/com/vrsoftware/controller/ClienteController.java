package br.com.vrsoftware.controller;

import br.com.vrsoftware.model.Cliente;
import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.dao.DaoFactory;

import java.util.List;
import javax.swing.JOptionPane;

public class ClienteController {

    private ClienteDao clienteDao;

    public ClienteController() {
        clienteDao = DaoFactory.createClienteDao();
    }

    public boolean inserirCliente(Cliente obj) {


        try {
            if(obj.getNome() == null){
                throw new ExceptionBussines("Argumento invalido.");
            }

            Cliente clienteExistente = clienteDao.findById(obj.getNome());

            if (clienteExistente != null && clienteExistente.getNome().equalsIgnoreCase(obj.getNome())) {
                JOptionPane.showMessageDialog(null, "Cliente já cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
                throw new ExceptionBussines("Cliente já cadastrado.");
            }
            clienteDao.insert(obj);
            return true;
        } catch (Exception e) {
            throw new ExceptionBussines("Erro ao inserir cliente: " + e.getMessage());
        }
    }



    public Cliente pegarCliente(String nome) {
        try {
            if(nome == null || nome.isEmpty() ) throw new ExceptionBussines("Argumento invalido.");
            Cliente cliente = clienteDao.findById(nome);
            if (cliente != null) {
                return cliente;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);

                throw new ExceptionBussines("Cliente não encontrado.");
            }
        } catch (Exception e) {
            throw new ExceptionBussines("Erro ao obter cliente: " + e.getMessage());
        }
    }

    public Cliente pegarNomeCliente(Integer id) {
        try {
            if(id == null) throw new ExceptionBussines("Argumento invalido.");
            Cliente cliente = clienteDao.findById(id);
            if (cliente != null) {
                return cliente;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);

                throw new ExceptionBussines("Cliente não encontrado.");
            }
        } catch (Exception e) {
            throw new ExceptionBussines("Erro ao obter nome do cliente: " + e.getMessage());
        }
    }

    public List<Cliente> retornaTodosClientes() {
        try {
            List<Cliente> clientes = clienteDao.findAll();
            if (clientes != null) {
                return clientes;
            } else {
                throw new ExceptionBussines("Não foi possivel obter a lista de clientes.");
            }
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }
}
