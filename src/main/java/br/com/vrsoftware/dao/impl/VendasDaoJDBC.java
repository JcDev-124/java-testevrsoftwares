package br.com.vrsoftware.dao.impl;

import br.com.software.model.EnumStatus;
import br.com.software.model.Vendas;
import br.com.vrsoftware.dao.VendasDao;
import br.com.vrsoftware.exceptions.db.DB;
import br.com.vrsoftware.exceptions.db.DbException;

import java.sql.Connection;
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
                    "INSERT INTO vendas (data, cliente_id, status) VALUES (?, ?, ?) RETURNING ID"
            );
            st.setDate(1, obj.getData() != null ? new java.sql.Date(obj.getData().toEpochMilli()) : null);
            st.setInt(2, obj.getCliente());
            st.setString(3, obj.pegarStatus().name());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
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
        obj.setId(rs.getInt("ID"));
        Timestamp timestamp = rs.getTimestamp("data");
        obj.setData(timestamp != null ? timestamp.toInstant() : null);
        obj.setId(rs.getInt("ID_CLIENTE"));
        obj.setStatus(EnumStatus.valueOf(rs.getString("status")));
        return obj;
    }

    @Override
    public List<Vendas> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM vendas ORDER BY ID");
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
