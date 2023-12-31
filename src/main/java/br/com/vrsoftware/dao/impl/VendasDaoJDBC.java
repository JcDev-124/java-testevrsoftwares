package br.com.vrsoftware.dao.impl;

import br.com.vrsoftware.model.EnumStatus;
import br.com.vrsoftware.model.Vendas;
import br.com.vrsoftware.dao.VendasDao;
import br.com.vrsoftware.dao.conexao.DB;
import br.com.vrsoftware.exceptions.db.DbException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VendasDaoJDBC implements VendasDao {

    private Connection conn;

    public VendasDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Vendas obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO vendas (data, cliente_id, status, valorTotal) VALUES (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            st.setDate(1, obj.getData() != null ? Date.valueOf(obj.getData()) : null);
            st.setInt(2, obj.getCliente());
            st.setString(3, obj.pegarStatus().name());
            st.setDouble(4, obj.getValorTotal());

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
    public void findByVenda(Integer id) {
        PreparedStatement updateStatement = null;

        try {
            // Tenta atualizar o status para "DIGITANDO" diretamente para o ID
            updateStatement = conn.prepareStatement("UPDATE vendas SET status = ? WHERE id = ?");
            updateStatement.setString(1, "DIGITANDO");
            updateStatement.setInt(2, id);

            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Venda não encontrada para o ID: " + id);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(updateStatement);
        }
    }

    @Override
    public Vendas findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM vendas WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateVenda(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
            DB.CloseResultSet(rs);
        }
    }

    private Vendas instantiateVenda(ResultSet rs) throws SQLException {
        Vendas obj = new Vendas();
        obj.setId(rs.getInt("id"));
        obj.setCliente(rs.getInt("cliente_id"));  // Corrigido para setar o cliente_id
        Timestamp timestamp = rs.getTimestamp("data");
        obj.setData(timestamp != null ? timestamp.toLocalDateTime().toLocalDate() : null);
        obj.setStatus(EnumStatus.valueOf(rs.getString("status")));
        obj.setValorTotal(rs.getDouble("valorTotal"));
        return obj;
    }

    @Override
    public List<Vendas> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM vendas ORDER BY id DESC;");
            rs = st.executeQuery();

            List<Vendas> list = new ArrayList<>();
            while (rs.next()) {
                Vendas obj = instantiateVenda(rs);
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
