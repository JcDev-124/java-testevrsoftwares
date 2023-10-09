import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.controller.OrdemVendasController;
import br.com.vrsoftware.dao.OrdemVendasDao;
import br.com.vrsoftware.model.OrdemVenda;
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
public class OrdemVendasControllerTest {

    @Mock
    OrdemVendasDao ordemVendasDao;

    @InjectMocks
    OrdemVendasController controller;

    @Test
    @DisplayName("nao deve inserir uma ordem de venda porque argumento é null")
    public void naoDeveInserirOrdemVenda(){

        //Arranges
        OrdemVenda ordemVenda = null;

        //Actions
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.inserirOrdemVendas(ordemVenda);
        });

        //Assertions
        org.assertj.core.api.Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Argumento invalido"
        );
    }

    @Test
    @DisplayName("Deve inserir uma ordem venda com sucesso")
    public void deveInserirOrdemVenda(){

        //Arranges
        OrdemVenda ordemVenda = new OrdemVenda(1,1,1,200.0);

        //Action
        controller.inserirOrdemVendas(ordemVenda);

        //Assertions
        Mockito.verify(ordemVendasDao).insert(ordemVenda);

    }

    @Test
    @DisplayName("Deve retornar lista de vendas com sucesso")
    public void deveRetornarLista(){
        //Arranges
        Integer id = 1;
        OrdemVenda ordemVenda = new OrdemVenda(1,1,1,200.0);
        List<OrdemVenda> listOrdemVendas = new ArrayList<>();
        List<OrdemVenda> listOrdemVendasRetornadas = new ArrayList<>();

        listOrdemVendas.add(ordemVenda);
        Mockito.when(ordemVendasDao.findAll(id)).thenReturn(listOrdemVendas);

        //Action
        listOrdemVendasRetornadas = controller.retornaVendasPorId(id);

        //Assertions
        org.junit.jupiter.api.Assertions.assertNotNull(listOrdemVendasRetornadas);
        org.junit.jupiter.api.Assertions.assertEquals(listOrdemVendas,listOrdemVendasRetornadas);

    }

    @Test
    @DisplayName("Nao deve retornar lista por erro de argumento")
    public void naoDeveRetornarLista(){
        //Arranges
        Integer id = null;
    
        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.retornaVendasPorId(id);
        });

        //Assertions
        org.assertj.core.api.Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Argumento invalido"
        );

    }
}


