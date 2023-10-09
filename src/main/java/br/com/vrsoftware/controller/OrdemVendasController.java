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
            if(obj == null ) throw new ExceptionBussines("Argumento invalido");
            ordem.insert(obj);
        } catch (Exception e) {

            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }

    public List<OrdemVenda> retornaVendasPorId(Integer id) {
        try {
            if(id == null) throw new ExceptionBussines("Argumento invalido");

            List<OrdemVenda> vendas = ordem.findAll(id);
            return vendas;

        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }
}
