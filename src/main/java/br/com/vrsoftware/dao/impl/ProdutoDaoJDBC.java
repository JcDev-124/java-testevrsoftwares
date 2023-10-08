package br.com.vrsoftware.dao.impl;

import br.com.software.model.Produto;
import br.com.vrsoftware.dao.ProdutoDao;
import br.com.vrsoftware.exceptions.db.DB;
import br.com.vrsoftware.exceptions.db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO produtos (descricao, preco, quantidade) VALUES (?, ?, ?) RETURNING id"
            );
            st.setString(1, obj.getDescricao().toUpperCase());
            st.setDouble(2, obj.getPreco());
            st.setInt(3, obj.getQuantidade());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
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

    public void update(Produto obj) {
        PreparedStatement st = null;

        try {
            // Atualiza a quantidade do produto somando o valor acrescentado
            st = conn.prepareStatement(
                    "UPDATE produtos SET quantidade = quantidade + ?, preco = ? WHERE descricao = ?"
            );
            st.setInt(1, obj.getQuantidade());
            st.setDouble(2, obj.getPreco());
            st.setString(3, obj.getDescricao());

            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                throw new DbException("Produto não encontrado: " + obj.getDescricao());
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar a quantidade do produto: " + e.getMessage());
        } finally {
            DB.CloseStatement(st);
        }
    }

    @Override
    public Produto findById(String descricao) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM produtos WHERE descricao = ?");
            st.setString(1, descricao.toUpperCase());
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateProduto(rs);
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
    public Produto findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM produtos WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateProduto(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
            DB.CloseResultSet(rs);
        }
    }

    private Produto instantiateProduto(ResultSet rs) throws SQLException {
        Produto obj = new Produto();
        obj.setId(rs.getInt("id"));
        obj.setDescricao(rs.getString("descricao"));
        obj.setPreco(rs.getDouble("preco"));
        obj.setQuantidade(rs.getInt("quantidade"));
        return obj;
    }

    @Override
    public List<Produto> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM produtos ORDER BY id");
            rs = st.executeQuery();

            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                Produto obj = instantiateProduto(rs);
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
