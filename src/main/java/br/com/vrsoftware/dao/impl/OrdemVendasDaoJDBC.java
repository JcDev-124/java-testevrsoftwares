/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.dao.impl;

// Importações omitidas para brevidade
import br.com.vrsoftware.model.OrdemVenda;
import br.com.vrsoftware.dao.OrdemVendasDao;
import br.com.vrsoftware.exceptions.db.DB;
import br.com.vrsoftware.exceptions.db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdemVendasDaoJDBC implements OrdemVendasDao {

    private Connection conn;

    public OrdemVendasDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public OrdemVenda findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM OrdemDeVenda WHERE id_ordem_de_venda = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateOrdemDeVenda(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
            DB.CloseResultSet(rs);
        }
    }

    public void insert(OrdemVenda ordemDeVenda) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO OrdemDeVenda (id_venda, id_produto, quantidade, preco) VALUES (?, ?, ?, ?)"
            );
            st.setNull(1, java.sql.Types.INTEGER);  // Deixa id_venda como nulo inicialmente
            st.setInt(2, ordemDeVenda.getIdProduto());
            st.setInt(3, ordemDeVenda.getQuantidade());
            st.setDouble(4, ordemDeVenda.getPreco());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
        }
    }

    public List<OrdemVenda> findAll(Integer idVenda) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM OrdemDeVenda WHERE id_venda = ?");
            st.setInt(1, idVenda);
            rs = st.executeQuery();

            List<OrdemVenda> list = new ArrayList<>();
            while (rs.next()) {
                OrdemVenda ordemDeVenda = instantiateOrdemDeVenda(rs);
                list.add(ordemDeVenda);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.CloseStatement(st);
            DB.CloseResultSet(rs);
        }
    }

    private OrdemVenda instantiateOrdemDeVenda(ResultSet rs) throws SQLException {
        OrdemVenda ordemDeVenda = new OrdemVenda();
        ordemDeVenda.setId(rs.getInt("id_ordem_de_venda"));
        ordemDeVenda.setIdVenda(rs.getInt("id_venda"));
        ordemDeVenda.setIdProduto(rs.getInt("id_produto"));
        ordemDeVenda.setQuantidade(rs.getInt("quantidade"));
        ordemDeVenda.setPreco(rs.getDouble("preco"));
        return ordemDeVenda;
    }
      

}
