package br.com.vrsoftware.dao.impl;

import br.com.software.model.Cliente;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.exceptions.db.DB;
import br.com.vrsoftware.exceptions.db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC implements ClienteDao {

    private Connection conn;

    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Clientes (Nome) VALUES (?) RETURNING Id"
            );
            st.setString(1, obj.getNome());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Id");
                obj.setId(id);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
        }
    }

    @Override
    public Cliente findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Clientes WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateCliente(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
            DB.CloseResultSet(rs);
        }
    }

    private Cliente instantiateCliente(ResultSet rs) throws SQLException {
        Cliente obj = new Cliente();
        obj.setId(rs.getInt("Id"));
        obj.setNome(rs.getString("Nome"));
        return obj;
    }

    @Override
    public List<Cliente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM Clientes ORDER BY Nome");
            rs = st.executeQuery();

            List<Cliente> list = new ArrayList<>();
            while (rs.next()) {
                Cliente obj = instantiateCliente(rs);
                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
            DB.CloseResultSet(rs);
        }
    }
}
