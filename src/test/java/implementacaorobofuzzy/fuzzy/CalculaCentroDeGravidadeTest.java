package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculaCentroDeGravidadeTest {

    @Test
    void deveCalcularCentroDeGravidadeComBuilder() {
        double centro = CalculaCentroDeGravidade.builder()
                .perto(0.3)
                .medio(0.4)
                .longe(0.0)
                .getCentroGravidade();

        assertEquals(0.3690285714, centro, 0.0001);
    }

    @Test
    void deveCalcularCentroDeGravidadeAposBuild() {
        double centro = CalculaCentroDeGravidade.builder()
                .perto(0.3)
                .medio(0.4)
                .longe(0.0)
                .build()
                .getValue();

        assertEquals(0.3690285714, centro, 0.0001);
    }

    @Test
    void naoDeveAceitarGrauForaDoIntervaloFuzzy() {
        assertThrows(IllegalArgumentException.class, () -> CalculaCentroDeGravidade.builder().perto(-0.1));
        assertThrows(IllegalArgumentException.class, () -> CalculaCentroDeGravidade.builder().longe(1.1));
    }

    @Test
    void deveRetornarZeroQuandoNaoExistePeso() {
        double centro = CalculaCentroDeGravidade.builder()
                .perto(0.0)
                .medio(0.0)
                .longe(0.0)
                .calcular();

        assertEquals(0.0, centro, 0.0001);
    }
}
