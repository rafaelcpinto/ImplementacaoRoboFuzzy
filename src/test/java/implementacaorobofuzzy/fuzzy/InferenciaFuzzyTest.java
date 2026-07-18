package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InferenciaFuzzyTest {

    @Test
    void deveCriarInferenciaComBuilder() {
        InferenciaFuzzy inferencia = InferenciaFuzzy.builder()
                .entradaBaixa(0.2)
                .entradaMedia(0.7)
                .entradaAlta(0.1)
                .build();

        assertEquals(0.2, inferencia.getBaixa(), 0.0001);
        assertEquals(0.7, inferencia.getMedia(), 0.0001);
        assertEquals(0.1, inferencia.getAlta(), 0.0001);
    }

    @Test
    void naoDeveAceitarGrauForaDoIntervaloFuzzy() {
        assertThrows(IllegalArgumentException.class, () -> InferenciaFuzzy.builder().entradaAlta(1.2));
        assertThrows(IllegalArgumentException.class, () -> InferenciaFuzzy.builder().entradaBaixa(-0.1));
    }
}
