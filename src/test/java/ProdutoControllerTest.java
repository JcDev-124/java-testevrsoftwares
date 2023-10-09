import br.com.vrsoftware.controller.ClienteController;
import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.controller.ProdutoController;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.dao.ProdutoDao;
import br.com.vrsoftware.model.Cliente;
import br.com.vrsoftware.model.Produto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {


    @InjectMocks
    ProdutoController controller;

    @Mock
    ProdutoDao produtoDao;

    @Test
    @DisplayName("Deve inserir produto com sucesso")
    public void deveInserirProduto() {

        //Arranges
        Produto produto = new Produto("Miojo", 200.0, 10);
        Mockito.when(produtoDao.findById(produto.getDescricao())).thenReturn(null);

        //Action
        controller.inserirProduto(produto);

        //Assertions
        Mockito.verify(produtoDao).findById(produto.getDescricao());
        Mockito.verify(produtoDao).insert(produto);
        org.junit.jupiter.api.Assertions.assertTrue(controller.inserirProduto(produto));
    }

    @Test
    @DisplayName("Nao deve inserir produto por erro de um argumento invalido")
    public void naoDeveInserirProdutoPorErroArgumento() {
        //Arranges
        Produto produto = null;

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.inserirProduto(produto);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Argumento invalido."
        );
    }

    @Test
    @DisplayName("Nao deve inserir produto por erro de produto ja cadastrado")
    public void naoDeveInserirProdutoPorProdutoCadastrado() {

        //Arranges
        Produto produto = new Produto("Miojo", 200.0, 10);
        Mockito.when(produtoDao.findById(produto.getDescricao())).thenReturn(produto);

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.inserirProduto(produto);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Produto já cadastrado."
        );
    }

    @Test
    @DisplayName("Deve retornar lista de produtos com sucesso")
    public void deveRetornarListaProdutos() {
        //Arranges
        Produto produto = new Produto("Miojo", 200.0, 10);
        ArrayList<Produto> listProdutos = new ArrayList<>();
        listProdutos.add(produto);
        Mockito.when(produtoDao.findAll()).thenReturn(listProdutos);

        //Action
        List<Produto> produtosEstornados = controller.retornaTodosProdutos();

        //Assertions
        org.junit.jupiter.api.Assertions.assertNotNull(produtosEstornados);
        org.junit.jupiter.api.Assertions.assertEquals(listProdutos.size(), produtosEstornados.size());
        org.junit.jupiter.api.Assertions.assertEquals(listProdutos, produtosEstornados);
    }

    @Test
    @DisplayName("nao deve retornar lista produtos por erro")
    public void naoDeveRetornarListaProdutos() {

        //Arranges
        Produto produto = new Produto();
        Mockito.when(produtoDao.findAll()).thenReturn(null);
        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.retornaTodosProdutos();
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Erro ao retornar a lista de produtos."
        );
    }

    @Test
    @DisplayName("Deve retornar produto por nome com sucesso")
    public void deveRetornarProdutoPorNome() {

        //Arranges
        Produto produto = new Produto("Miojo", 200.0, 10);
        Mockito.when(produtoDao.findById(produto.getDescricao())).thenReturn(produto);

        //Action
        Produto produtoRetornado = controller.retornaProdutoPorNome(produto.getDescricao());

        //Assertions
        Mockito.verify(produtoDao).findById(produto.getDescricao());
        org.junit.jupiter.api.Assertions.assertEquals(produto, produtoRetornado);
    }

    @Test
    @DisplayName("nao deve retoranr o produto por nome por erro de parametro vazio ou null")
    public void naoDeveRetornarProdutoPorErroArgumento() {
        //Arranges
        String nome = "";

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.retornaProdutoPorNome(nome);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Argumento invalido."
        );


    }

    @Test
    @DisplayName("nao deve retornar produto por nome porque produto nao existe")
    public void naoDeveRetornarProdutoPorNaoExistir() {

        //Arranges
        String nomeTeste = "teste";
        Mockito.when(produtoDao.findById(nomeTeste)).thenReturn(null);

        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.retornaProdutoPorNome(nomeTeste);
        });
        //Action

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Produto nao existe"
        );
    }

    @Test
    @DisplayName("deve retornar produto com sucesso por id")
    public void deveRetornarProdutoPorId(){
        //Arranges
        Produto produto = new Produto(1,"Miojo", 200.0, 10);
        Mockito.when(produtoDao.findById(produto.getId())).thenReturn(produto);

        //Action
        Produto produtoRetornado = controller.retornaProdutoPorId(produto.getId());

        //Assertions
        Mockito.verify(produtoDao).findById(produto.getId());
        org.junit.jupiter.api.Assertions.assertEquals(produto, produtoRetornado);
    }
    @Test
    @DisplayName("nao deve retornar produto por id null")
    public void naoDeveRetornarProdutoPorIdNull(){
        //Arranges
        Integer id = null;
        //Actions
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.retornaProdutoPorId(id);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Argumento invalido"
        );

    }

    @Test
    @DisplayName("nao deve retornar produto por ID por produto inexistente")
    public void naoDeveRetornarProdutoPorIdPorNaoExistir(){
        //Arranges
        Integer id = 1;
        Mockito.when(produtoDao.findById(id)).thenReturn(null);

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.retornaProdutoPorId(id);
        });

        //Assertions
        Assertions.assertThat("Erro: Produto nao existe");
    }

    @Test
    @DisplayName("Deve atualizar produto com sucesso")
    public void deveAtualizarProdutoComSuceso(){
        //Arranges
        Produto produto = new Produto("Miojo", 200.0, 10);
        Mockito.when(produtoDao.findById(produto.getDescricao())).thenReturn(produto);

        //Action
        controller.atualizaProduto(produto);

        //Assertions
        Mockito.verify(produtoDao).findById(produto.getDescricao());
        Mockito.verify(produtoDao).update(produto);

    }

    @Test
    @DisplayName("nao deve atualizar produto por erro de argumento invalido")
    public void naoDeveAtualizarProdutoPorErroArgumento(){
        //Arranges
        Produto produto = null;

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.atualizaProduto(produto);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo("" +
                "Erro: Argumento invalido.");
    }

    @Test
    @DisplayName("nao deve atualizar um produto porque ele nao existe")
    public void naoDeveAtualizarPorNaoExistir(){

        //Arranges
        Produto produto = new Produto("Descricao", 200.0, 10);
        Mockito.when(produtoDao.findById(produto.getDescricao())).thenReturn(null);
        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () -> {
            controller.atualizaProduto(produto);

        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Produto nao cadastrado."
        );
    }
}

