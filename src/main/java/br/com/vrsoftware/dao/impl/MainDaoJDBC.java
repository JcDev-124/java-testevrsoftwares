/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vrsoftware.dao.impl;

import br.com.vrsoftware.dao.MainDao;
import br.com.vrsoftware.dao.conexao.DB;
import br.com.vrsoftware.exceptions.db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            st = conn.prepareStatement("CREATE DATABASE sistemapdv"
                    + " WITH OWNER = postgres"
                    + " ENCODING = 'WIN1252'" // 
                    + " TEMPLATE = template0;");
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
                    + "    status VARCHAR(10),"
                    + "    valorTotal DOUBLE PRECISION"
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
            // Drop the trigger and function if they exist
            st = conn.prepareStatement("DROP TRIGGER IF EXISTS atualiza_id_venda_trigger ON vendas");
            st.execute();

            st = conn.prepareStatement("DROP FUNCTION IF EXISTS insere_id_venda_na_ordem()");
            st.execute();

            // Create the function
            st = conn.prepareStatement(
                    "CREATE OR REPLACE FUNCTION insere_id_venda_na_ordem() "
                            + "RETURNS TRIGGER AS $$ "
                            + "BEGIN "
                            + "    UPDATE OrdemDeVenda "
                            + "    SET id_venda = NEW.id "
                            + "    WHERE id_venda IS NULL; "
                            + "    RETURN NEW; "
                            + "END; "
                            + "$$ LANGUAGE plpgsql;"
            );
            st.execute();

            // Create the trigger
            st = conn.prepareStatement(
                    "CREATE TRIGGER atualiza_id_venda_trigger "
                            + "AFTER INSERT ON vendas "
                            + "FOR EACH ROW "
                            + "EXECUTE FUNCTION insere_id_venda_na_ordem();"
            );
            st.execute();

        } catch (SQLException e) {
            throw new DbException("Erro ao criar o trigger no banco de dados: " + e.getMessage());
        } finally {
            // Close the PreparedStatement
            DB.CloseStatement(st);
        }
    }

}
