import br.com.vrsoftware.controller.ClienteController;
import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.controller.ProdutoController;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.dao.ProdutoDao;
import br.com.vrsoftware.model.Produto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {


    @InjectMocks
    ProdutoController controller;

    @Mock
    ProdutoDao produtoDao;

    @Test
    @DisplayName("Deve inserir produto com sucesso")
    public void deveInserirProduto(){

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
    public void naoDeveInserirProdutoPorErroArgumento(){
        //Arranges
        Produto produto = null;

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.inserirProduto(produto);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Argumento invalido."
        );
    }

    @Test
    @DisplayName("Nao deve inserir produto por erro de produto ja cadastrado")
    public void naoDeveInserirProdutoPorProdutoCadastrado(){

        //Arranges
        Produto produto = new Produto("Miojo", 200.0, 10);
        Mockito.when(produtoDao.findById(produto.getDescricao())).thenReturn(produto);

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.inserirProduto(produto);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Produto já cadastrado."
        );
    }
}
