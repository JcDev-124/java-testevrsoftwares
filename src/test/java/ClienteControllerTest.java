import br.com.vrsoftware.controller.ClienteController;
import br.com.vrsoftware.controller.ExceptionBussines.ExceptionBussines;
import br.com.vrsoftware.dao.ClienteDao;
import br.com.vrsoftware.model.Cliente;
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
public class ClienteControllerTest {

    @InjectMocks
    ClienteController controller;

    @Mock
    ClienteDao clienteDao;


    @Test
    @DisplayName("Deve inserir um cliente com sucesso")
    public void deveSalvarClienteComSucesso(){

        //Arrange
        Cliente cliente = new Cliente("Julio");
        Mockito.when(clienteDao.findById(cliente.getNome())).thenReturn(null);

        //Action
        controller.inserirCliente(cliente);

        //Assertions
        Mockito.verify(clienteDao).findById(cliente.getNome());
        Mockito.verify(clienteDao).insert(cliente);
        org.junit.jupiter.api.Assertions.assertTrue(controller.inserirCliente(cliente));
    }

    @Test
    @DisplayName("Deve retornar erro ao salvar quando receber um parametro null")
    public void naoDeveSalvarClienteComErroDeArgumento(){
        //Arrange
        Cliente cliente = new Cliente();


        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.inserirCliente(cliente);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo
                ("Erro ao inserir cliente: Argumento invalido.");
    }

    @Test
    @DisplayName("Deve retornar erro de cliente ja cadastrado")
    public void naoDeveSalvarClienteComErroDeClienteJaExiste(){
        //Arrange
        Cliente cliente = new Cliente("Julio");
        Mockito.when(clienteDao.findById(cliente.getNome())).thenReturn(cliente);

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.inserirCliente(cliente);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo
                ("Erro ao inserir cliente: Cliente já cadastrado.");
    }

    @Test
    @DisplayName("Deve retornar cliente por nome com sucesso")
    public void deveRetornarClientePorNome(){
        //Arrange
        Cliente cliente = new Cliente("Julio");
        Mockito.when(clienteDao.findById(cliente.getNome())).thenReturn(cliente);

        //Action
        controller.pegarCliente(cliente.getNome());
        //Assertions
        Mockito.verify(clienteDao).findById(cliente.getNome());
    }

    @Test
    @DisplayName("Deve retornar erro de cliente nao encontrado por nome")
    public void naoDeveRetornarClientePorNomeInexistente(){
        //Arranges
        Cliente cliente = new Cliente("julio");
        Mockito.when(clienteDao.findById(cliente.getNome())).thenReturn(null);
        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.pegarCliente(cliente.getNome());
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
            "Erro ao obter cliente: Cliente não encontrado."
        );

    }

    @Test
    @DisplayName("Deve retornar erro de argumento invalido na busca por cliente")
    public void naoDeveRetornarClientePorNomeInvalido(){
        //Arrange
        String nome = null;

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.pegarCliente(nome);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
          "Erro ao obter cliente: Argumento invalido."
        );
    }



    @Test
    @DisplayName("Deve retornar cliente por ID com sucesso")
    public void deveRetornarClientePorId(){
        //Arrange
        Cliente cliente = new Cliente(1,"Julio");
        Mockito.when(clienteDao.findById(cliente.getId())).thenReturn(cliente);

        //Action
        controller.pegarNomeCliente(cliente.getId());
        //Assertions
        Mockito.verify(clienteDao).findById(cliente.getId());
    }

    @Test
    @DisplayName("Deve retornar erro de cliente nao encontrado por ID")
    public void naoDeveRetornarClientePorIdInexistente(){

        //Arranges
        Integer id = 1;
        Mockito.when(clienteDao.findById(1)).thenReturn(null);

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.pegarNomeCliente(id);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
          "Erro ao obter nome do cliente: Cliente não encontrado."
        );
    }

    @Test
    @DisplayName("Deve retornar erro ID invalido ao buscar por cliente, null")
    public void naoDeveRetornarClientePorIdInvalido(){
        //Arranges
        Integer id = null;

        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.pegarNomeCliente(id);
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro ao obter nome do cliente: Argumento invalido."
        );
    }

    @Test
    @DisplayName("Deve retornar a lista dos clientes com sucesso")
    public void deveRetornarListaClientes(){

        //Arranges
        Cliente cliente = new Cliente("Julio");
        ArrayList<Cliente> listClientes = new ArrayList<>();
        listClientes.add(cliente);
        Mockito.when(clienteDao.findAll()).thenReturn(listClientes);

        //Action
        List<Cliente> clientesEstornados = controller.retornaTodosClientes();

        //Assertions
        org.junit.jupiter.api.Assertions.assertNotNull(clientesEstornados);
        org.junit.jupiter.api.Assertions.assertEquals(listClientes.size(), clientesEstornados.size());
        org.junit.jupiter.api.Assertions.assertEquals(listClientes.get(0).getNome(), clientesEstornados.get(0).getNome());
    }


    @Test
    @DisplayName("Deve retornar erro ao retornar a lista de clientes")
    public void naoDeveRetornarListaClientes(){
        //Arranges
        Cliente cliente = new Cliente();
        Mockito.when(clienteDao.findAll()).thenReturn(null);
        //Action
        ExceptionBussines exceptionBussines = assertThrows(ExceptionBussines.class, () ->{
            controller.retornaTodosClientes();
        });

        //Assertions
        Assertions.assertThat(exceptionBussines.getMessage()).isEqualTo(
                "Erro: Não foi possivel obter a lista de clientes."
        );




    }

}