package br.com.vrsoftware.dao;

import br.com.vrsoftware.dao.impl.ClienteDaoJDBC;
import br.com.vrsoftware.dao.impl.ProdutoDaoJDBC;
import br.com.vrsoftware.exceptions.db.DB;

public class DaoFactory {
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
	public static ProdutoDao createProdutoDao(){
            return new ProdutoDaoJDBC(DB.getConnection());
        }
}
