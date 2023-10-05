package br.com.vrsoftware.dao;

import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.dao.impl.ClienteDaoJDBC;
import br.com.vrsoftware.exceptions.db.DB;

public class DaoFactory {
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoJDBC(DB.getConnection());
	}
	
	
}
