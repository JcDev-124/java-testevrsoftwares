package br.com.vrsoftware.dao;

import br.com.vrsoftware.dao.impl.ClienteDaoJDBC;
import br.com.vrsoftware.dao.impl.MainDaoJDBC;
import br.com.vrsoftware.dao.impl.OrdemVendasDaoJDBC;
import br.com.vrsoftware.dao.impl.ProdutoDaoJDBC;
import br.com.vrsoftware.dao.impl.VendasDaoJDBC;

import br.com.vrsoftware.exceptions.db.DB;

public class DaoFactory {

    public static ClienteDao createClienteDao() {
        return new ClienteDaoJDBC(DB.getConnection());
    }

    public static ProdutoDao createProdutoDao() {
        return new ProdutoDaoJDBC(DB.getConnection());
    }

    public static VendasDao createVendaDao() {
        return new VendasDaoJDBC(DB.getConnection());
    }

    public static OrdemVendasDao createOrdemVendaDao() {
        return new OrdemVendasDaoJDBC(DB.getConnection());
    }

    public static MainDao createMainDao(){
        return new MainDaoJDBC(DB.getConnection());
    }
}
