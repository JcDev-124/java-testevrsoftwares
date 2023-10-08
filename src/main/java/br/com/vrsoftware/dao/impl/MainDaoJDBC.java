/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.dao.impl;

import br.com.software.model.Cliente;
import br.com.vrsoftware.dao.MainDao;
import br.com.vrsoftware.exceptions.db.DB;
import br.com.vrsoftware.exceptions.db.DbException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public class MainDaoJDBC implements MainDao {

    private Connection conn;

    public MainDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void criarEstruturaBD() {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "CREATE DATABASE IF NOT EXISTS sistemapdv;"
            );

            // Execute a query to create the database structure
            st.execute();
        } catch (SQLException e) {
            throw new DbException("Erro ao criar a estrutura do banco de dados: " + e.getMessage());
        } finally {
            // Close the PreparedStatement
            DB.CloseStatement(st);
        }
    }

    @Override
    public void criarTabelasBD() {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS produtos ("
                    + "    id SERIAL PRIMARY KEY,"
                    + "    descricao VARCHAR(255) NOT NULL,"
                    + "    quantidade INTEGER,"
                    + "    preco DOUBLE PRECISION"
                    + ");"
                    + "CREATE TABLE IF NOT EXISTS clientes ("
                    + "    id SERIAL PRIMARY KEY,"
                    + "    nome VARCHAR(255) NOT NULL"
                    + ");"
                    + "CREATE TABLE IF NOT EXISTS vendas ("
                    + "    id SERIAL PRIMARY KEY,"
                    + "    data TIMESTAMPTZ,"
                    + "    cliente_id INTEGER REFERENCES clientes(id),"
                    + "    status INTEGER"
                    + ");"
                    + "CREATE TABLE IF NOT EXISTS OrdemDeVenda ("
                    + "    id_ordem_de_venda SERIAL PRIMARY KEY,"
                    + "    id_venda INTEGER REFERENCES vendas(id),"
                    + "    id_produto INTEGER,"
                    + "    quantidade INTEGER,"
                    + "    preco DOUBLE PRECISION"
                    + ");"
            );

            st.execute();
        } catch (SQLException e) {
            throw new DbException("Erro ao criar as tabelas " + e.getMessage());
        } finally {
            // Close the PreparedStatement
            DB.CloseStatement(st);
        }
    }

    @Override
    public void criarTriggerDB() {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "CREATE OR REPLACE FUNCTION IF NOT EXISTS insere_id_venda_na_ordem() "
                    + "RETURNS TRIGGER AS $$"
                    + "BEGIN"
                    + "    UPDATE OrdemDeVenda"
                    + "    SET id_venda = NEW.id"
                    + "    WHERE id_venda IS NULL;"
                    + "    RETURN NEW;"
                    + "END;"
                    + "$$ LANGUAGE plpgsql;"
                    + "CREATE TRIGGER IF NOT EXISTS atualiza_id_venda_trigger "
                    + "AFTER INSERT ON vendas "
                    + "FOR EACH ROW "
                    + "EXECUTE FUNCTION insere_id_venda_na_ordem();"
            );

            // Execute a query to create the database structure
            st.execute();
        } catch (SQLException e) {
            throw new DbException("Erro ao criar a estrutura do banco de dados: " + e.getMessage());
        } finally {
            // Close the PreparedStatement
            DB.CloseStatement(st);
        }
    }

}
