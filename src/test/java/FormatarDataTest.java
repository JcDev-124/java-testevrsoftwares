import br.com.vrsoftware.utils.FormatarData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class FormatarDataTest {

    @Test
    @DisplayName("Formata a data")
    public void testFormataData() {
        LocalDate dataEntrada = LocalDate.of(2023, 10, 9);
        String dataFormatadaEsperada = "09/10/2023";

        String dataFormatada = FormatarData.formataData(dataEntrada);

        assertEquals(dataFormatadaEsperada, dataFormatada);
    }

    @Test
    @DisplayName("transforma para um localdate")
    public void testParseData() {
        String dataFormatada = "25/12/2022";
        LocalDate dataEsperada = LocalDate.of(2022, 12, 25);

        LocalDate dataParse = FormatarData.parseData(dataFormatada);

        assertEquals(dataEsperada, dataParse);
    }
}