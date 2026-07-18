package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InferenciaFuzzyTest {

    @Test
    void deveReceberDoublesERetornarMatriz() {
        InferenciaFuzzy inferencia = InferenciaFuzzy.builder()
                .perto(0.2)
                .medio(0.7)
                .longe(0.1)
                .build();

        Double[][] resultado = inferencia.executar();

        assertEquals(0.2, resultado[0][0], 0.0001);
        assertEquals(0.7, resultado[1][0], 0.0001);
        assertEquals(0.7, resultado[2][0], 0.0001);
        assertEquals(0.1, resultado[3][0], 0.0001);
    }

    @Test
    void deveSepararVelocidadeMediaERapida() {
        InferenciaFuzzy inferencia = InferenciaFuzzy.builder()
                .perto(0.0)
                .medio(0.3)
                .longe(0.8)
                .build();

        Double[][] resultado = inferencia.executar();

        assertEquals(0.0, resultado[0][0], 0.0001);
        assertEquals(0.2, resultado[1][0], 0.0001);
        assertEquals(0.3, resultado[2][0], 0.0001);
        assertEquals(0.8, resultado[3][0], 0.0001);
    }

    @Test
    void deveRejeitarGrauInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> InferenciaFuzzy.builder().perto(-0.1)
        );
    }
}
