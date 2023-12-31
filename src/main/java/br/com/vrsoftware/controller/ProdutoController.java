package br.com.vrsoftware.controller;

import br.com.vrsoftware.model.Produto;
import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.ProdutoDao;

import javax.swing.JOptionPane;
import java.util.List;

public class ProdutoController {

    private ProdutoDao produtoDao;

    public ProdutoController() {
        produtoDao = DaoFactory.createProdutoDao();
    }

    public boolean inserirProduto(Produto obj) {
        try {
            if (obj == null) throw new ExceptionBussines("Argumento invalido.");
            Produto produtoExistente = produtoDao.findById(obj.getDescricao());

            if (produtoExistente != null && produtoExistente.getDescricao().equalsIgnoreCase(obj.getDescricao())) {
                JOptionPane.showMessageDialog(null, "Produto já cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
                throw new ExceptionBussines("Produto já cadastrado.");
            }
            produtoDao.insert(obj);
            return true;
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }

    public List<Produto> retornaTodosProdutos() {
        List<Produto> list = produtoDao.findAll();
        try {
            if (list == null) {
                throw new ExceptionBussines("Erro ao retornar a lista de produtos.");
            }
            return list;
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }

    public Produto retornaProdutoPorNome(String nome) {
        try {
            if(nome == null || nome.isEmpty()) {
                throw new ExceptionBussines("Argumento invalido.");
            }

            Produto produto = produtoDao.findById(nome);
            if(produto == null) {
                JOptionPane.showMessageDialog(null, "Produto nao cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);

                throw new ExceptionBussines("Produto nao existe");}
            return produto;
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }

    public Produto retornaProdutoPorId(Integer id) {
        try {
            if(id == null) throw new ExceptionBussines("Argumento invalido");
            Produto produto = produtoDao.findById(id);

            if(produto == null) throw new ExceptionBussines("Produto nao existe");
            return produto;
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: " + e.getMessage());
        }
    }

    public void atualizaProduto(Produto obj) {
        try {
            if (obj == null) throw new ExceptionBussines("Argumento invalido.");

            Produto produto = produtoDao.findById(obj.getDescricao());
            if (produto == null) {
                JOptionPane.showMessageDialog(null, "Produto não cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);
                throw new ExceptionBussines("Produto nao cadastrado.");

            }
            produtoDao.update(obj);
        } catch (Exception e) {
            throw new ExceptionBussines("Erro: "+ e.getMessage());
        }
    }
}
