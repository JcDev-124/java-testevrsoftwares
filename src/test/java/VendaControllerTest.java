import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.controller.VendasController;
import br.com.vrsoftware.dao.VendasDao;
import br.com.vrsoftware.model.EnumStatus;
import br.com.vrsoftware.model.Vendas;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VendaControllerTest {

    @InjectMocks
    VendasController controller;

    @Mock
    VendasDao vendasDao;

    @Test
    @DisplayName("Deve inserir venda com sucesso")
    public void deveInserirVenda(){

        //Arranges
        LocalDate date = LocalDate.now();
        EnumStatus status = EnumStatus.FINALIZADO;
        Vendas venda = new Vendas(date,1,status,200.0);

        //Action
        controller.inserirVenda(venda);
        //Assertions
        Mockito.verify(vendasDao).insert(venda);


    }

    @Test
    @DisplayName("nao deve inserir venda por erro de argumento invalido")
    public void naoDeveInserirVenda(){
        //Arranges
        Vendas venda = null;

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.inserirVenda(venda);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Argumento invalido"
        );
    }

    @Test
    @DisplayName("deve retornar lista de vendas com sucesso")
    public void deveRetornarListaDeVendas(){
        //Arranges
        LocalDate date = LocalDate.now();
        EnumStatus status = EnumStatus.FINALIZADO;
        Vendas venda = new Vendas(date,1,status,200.0);
        List<Vendas> listVendas = new ArrayList<>();
        List<Vendas> listVendasRetornadas = new ArrayList<>();

        listVendas.add(venda);

        Mockito.when(vendasDao.findAll()).thenReturn(listVendas);

        //Action
        listVendasRetornadas = controller.retornaTodasVendas();

        //Assertions
        assertNotNull(listVendasRetornadas);
        assertEquals(listVendas,listVendasRetornadas);

    }

    @Test
    @DisplayName("nao deve retornar lista de vendas")
    public void naoDeveRetornarListaVendas(){
        //Arranges
        Mockito.when(vendasDao.findAll()).thenReturn(null);

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.retornaTodasVendas();
        });
        //Assertions
        Assertions.assertThat("Erro: Não foi possível obter a lista de vendas.");
    }

    @Test
    @DisplayName("Nao deve atualizar status da venda por venda nao existir")
    public void naoDeveAtualizarVenda(){
        //Arranges
        Integer id = null;

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.atualizaStatusVenda(id);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Venda nao existe"
        );
    }

    @Test
    @DisplayName("deve atualizar venda com sucesso")
    public void deveAtualizarVenda(){
        //Arranges
        LocalDate date = LocalDate.now();
        EnumStatus status = EnumStatus.FINALIZADO;
        Vendas venda = new Vendas(1, date,1,status,200.0);


        //Action
        controller.atualizaStatusVenda(venda.getId());

        //Assertions
        Mockito.verify(vendasDao).findByVenda(venda.getId());

    }

}
