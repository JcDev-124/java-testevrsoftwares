package br.com.vrsoftware.controller;

import br.com.software.model.Produto;
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
            Produto produtoExistente = produtoDao.findById(obj.getDescricao());

            if (produtoExistente != null && produtoExistente.getDescricao().equalsIgnoreCase(obj.getDescricao())) {
                JOptionPane.showMessageDialog(null, "Produto já cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);

                throw new IllegalArgumentException("Produto já cadastrado.");

            }

            produtoDao.insert(obj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer pegarIdProduto(Produto obj) {
        try {
            return produtoDao.findById(obj.getDescricao()).getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer pegarIdProduto(String descricao) {
        try {
            return produtoDao.findById(descricao).getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Produto> retornaTodosProdutos() {
        try {
            return produtoDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Double retornaPrecoProduto(String nome) {
        try {
            return produtoDao.findById(nome).getPreco();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Produto retornaProdutoPorNome(String nome) {
        try {
            return produtoDao.findById(nome);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Produto retornaProdutoPorId(Integer id) {
        try {
            return produtoDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void atualizaProduto(Produto obj) {
        try {
            Produto produto = produtoDao.findById(obj.getDescricao());
            if (produto == null) {
                JOptionPane.showMessageDialog(null, "Produto não cadastrado", "Aviso", JOptionPane.WARNING_MESSAGE);

                throw new IllegalArgumentException("Produto não cadastrado.");

            }
            produtoDao.update(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
