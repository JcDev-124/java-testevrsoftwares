package br.com.vrsoftware.controller;

import br.com.software.model.Cliente;
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
            // Verifica se o cliente já está cadastrado
            Cliente clienteExistente = clienteDao.findById(obj.getNome());

            if (clienteExistente != null && clienteExistente.getNome().equalsIgnoreCase(obj.getNome())) {
                JOptionPane.showMessageDialog(null, "Cliente já cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
                throw new IllegalArgumentException("Cliente já cadastrado.");
            }

            clienteDao.insert(obj);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public Integer pegarIdCliente(Cliente obj) {
        try {
            Cliente cliente = clienteDao.findById(obj.getNome());
            if (cliente != null) {
                return cliente.getId();
            } else {
                throw new RuntimeException("Cliente não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter ID do cliente: " + e.getMessage());
        }
    }

    public Integer pegarIdCliente(String nome) {
        try {
            Cliente cliente = clienteDao.findById(nome);
            if (cliente != null) {
                return cliente.getId();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);

                throw new RuntimeException("Cliente não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter ID do cliente: " + e.getMessage());
        }
    }

    public Cliente pegarCliente(String nome) {
        try {
            Cliente cliente = clienteDao.findById(nome);
            if (cliente != null) {
                return cliente;
            } else {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);

                throw new RuntimeException("Cliente não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter cliente: " + e.getMessage());
        }
    }

    public String pegarNomeCliente(Integer id) {
        try {
            Cliente cliente = clienteDao.findById(id);
            if (cliente != null) {
                return cliente.getNome();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);

                throw new RuntimeException("Cliente não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter nome do cliente: " + e.getMessage());
        }
    }

    public List<Cliente> retornaTodosClientes() {
        try {
            List<Cliente> clientes = clienteDao.findAll();
            if (clientes != null) {
                return clientes;
            } else {
                throw new RuntimeException("Não foi possível obter a lista de clientes.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter a lista de clientes: " + e.getMessage());
        }
    }
}
