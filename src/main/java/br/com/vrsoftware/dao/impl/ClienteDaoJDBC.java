package br.com.vrsoftware.dao.impl;

import br.com.vrsoftware.model.Cliente;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.dao.conexao.DB;
import br.com.vrsoftware.exceptions.db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    "INSERT INTO clientes (nome) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS
            );
            st.setString(1, obj.getNome().toUpperCase());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                } else {
                    throw new DbException("Erro inesperado! Nenhuma linha afetada!");
                }
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
    public Cliente findById(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM clientes WHERE nome = ?");
            st.setString(1, nome.toUpperCase());
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

    @Override
    public Cliente findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM clientes WHERE id = ?");
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
        obj.setId(rs.getInt("id"));
        obj.setNome(rs.getString("nome"));
        return obj;
    }

    @Override
    public List<Cliente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM clientes ORDER BY id");
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
