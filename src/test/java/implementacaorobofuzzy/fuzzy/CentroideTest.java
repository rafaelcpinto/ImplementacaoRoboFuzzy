package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CentroideTest {

    @Test
    void deveCalcularMediaPonderada() {
        Centroide centroide = new Centroide(Arrays.asList(0.1944, 0.50, 0.8056));

        assertEquals(
                0.3690285714,
                centroide.calcular(Arrays.asList(0.3, 0.4, 0.0)),
                0.0001
        );
    }
}
