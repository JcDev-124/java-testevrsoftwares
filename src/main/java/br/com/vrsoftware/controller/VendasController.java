package br.com.vrsoftware.controller;

import br.com.vrsoftware.model.Vendas;
import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.VendasDao;

import java.util.List;

public class VendasController {

    private VendasDao vendasDao;

    public VendasController() {
        vendasDao = DaoFactory.createVendaDao();
    }

    public void inserirVenda(Vendas venda) {

        try {
            if (venda == null) {
                throw new ExceptionBussines("Erro ao retornar a lista");
            }
            vendasDao.insert(venda);
        } catch (Exception e) {
            // Trate a exceção conforme necessário (ex: log, mensagem de erro, etc.)
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir venda: " + e.getMessage());
        }
    }

    public List<Vendas> retornaTodasVendas() {
        try {
            List<Vendas> vendas = vendasDao.findAll();
            if (vendas != null) {
                return vendas;
            } else {
                throw new RuntimeException("Não foi possível obter a lista de vendas.");
            }
        } catch (Exception e) {
            // Trate a exceção conforme necessário (ex: log, mensagem de erro, etc.)
            e.printStackTrace();
            throw new RuntimeException("Erro ao obter a lista de vendas: " + e.getMessage());
        }
    }

    public void atualizaStatusVenda(Integer id) {

        try {
            if(id == null ) throw new ExceptionBussines("VENDA NAO EXISTE");

            vendasDao.findByVenda(id);
        } catch (Exception e) {
            // Trate a exceção conforme necessário (ex: log, mensagem de erro, etc.)
            e.printStackTrace();
            throw new ExceptionBussines("Venda nao existe! " + e.getMessage());
        }
    }
}
