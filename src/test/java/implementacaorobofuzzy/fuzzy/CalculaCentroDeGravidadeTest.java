package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculaCentroDeGravidadeTest {

    @Test
    void deveCalcularCentroideComListas() {
        Centroide centroide = new Centroide(Arrays.asList(0.1944, 0.5));

        assertEquals(
                0.3690285714,
                centroide.calcular(Arrays.asList(0.3, 0.4)),
                0.0001
        );
    }

    @Test
    void deveExigirListasDoMesmoTamanho() {
        Centroide centroide = new Centroide(Arrays.asList(0.1944, 0.5));

        assertThrows(
                IllegalArgumentException.class,
                () -> centroide.calcular(Arrays.asList(0.3))
        );
    }
}
