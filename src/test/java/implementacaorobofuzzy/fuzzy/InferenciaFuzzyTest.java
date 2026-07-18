package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InferenciaFuzzyTest {

    @Test
    void deveCriarInferenciaEAgregarAsRegras() {
        InferenciaFuzzy inferencia = new InferenciaFuzzy(0.2, 0.7, 0.1);

        assertEquals(0.21, inferencia.getVelocidadeBaixa(), 0.0001);
        assertEquals(0.7, inferencia.getVelocidadeMedia(), 0.0001);
        assertEquals(0.1, inferencia.getVelocidadeAlta(), 0.0001);
    }

    @Test
    void naoDeveAceitarGrauForaDoIntervaloFuzzy() {
        assertThrows(IllegalArgumentException.class, () -> new InferenciaFuzzy(0, 0, 1.2));
        assertThrows(IllegalArgumentException.class, () -> new InferenciaFuzzy(-0.1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new InferenciaFuzzy(Double.NaN, 0, 0));
    }
}
