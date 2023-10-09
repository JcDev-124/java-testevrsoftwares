package br.com.vrsoftware.controller;

import br.com.vrsoftware.model.OrdemVenda;
import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.OrdemVendasDao;

import java.util.List;

public class OrdemVendasController {

    private OrdemVendasDao ordem;

    public OrdemVendasController() {
        ordem = DaoFactory.createOrdemVendaDao();
    }

    public void inserirOrdemVendas(OrdemVenda obj) {
        try {
            if(obj == null ) throw new ExceptionBussines("Erro ao inserir a venda");
            ordem.insert(obj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionBussines("Erro ao inserir ordem de vendas: " + e.getMessage());
        }
    }

    public List<OrdemVenda> retornaVendasPorId(Integer id) {
        try {
            List<OrdemVenda> vendas = ordem.findAll(id);
            if (vendas != null) {
                return vendas;
            } else {
                throw new ExceptionBussines("Não foi possível obter a lista de vendas para o ID: " + id);
            }
        } catch (Exception e) {
            // Trate a exceção conforme necessário (ex: log, mensagem de erro, etc.)
            e.printStackTrace();
            throw new ExceptionBussines("Erro ao obter vendas por ID: " + e.getMessage());
        }
    }
}
