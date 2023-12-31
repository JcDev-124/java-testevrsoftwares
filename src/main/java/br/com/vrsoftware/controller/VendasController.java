package br.com.vrsoftware.controller;

import br.com.vrsoftware.model.Vendas;
import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.VendasDao;

import javax.management.RuntimeMBeanException;
import java.util.List;

public class VendasController {

    private VendasDao vendasDao;

    public VendasController() {
        vendasDao = DaoFactory.createVendaDao();
    }

    public void inserirVenda(Vendas venda) {

        try {
            if (venda == null) {
                throw new ExceptionBussines("Argumento invalido");
            }
            vendasDao.insert(venda);
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }

    public List<Vendas> retornaTodasVendas() {
        try {
            List<Vendas> vendas = vendasDao.findAll();
            if (vendas != null) {
                return vendas;
            } else {
                throw new ExceptionBussines("Não foi possível obter a lista de vendas.");
            }
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }

    public void atualizaStatusVenda(Integer id) {
        try {
            if(id == null ) throw new ExceptionBussines("Venda nao existe");

            vendasDao.findByVenda(id);
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }
}
