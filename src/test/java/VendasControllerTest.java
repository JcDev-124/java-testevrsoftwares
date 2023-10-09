import br.com.vrsoftware.controller.OrdemVendasController;
import br.com.vrsoftware.dao.OrdemVendasDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VendasControllerTest {

    @Mock
    OrdemVendasDao ordemVendasDao;

    @InjectMocks
    OrdemVendasController ordemVendasController;

    @Test
    @DisplayName("nao deve inserir uma ordem de venda porque argumento é null")
    public void naoDeveInserirOrdemVenda(){
        
    }
}
