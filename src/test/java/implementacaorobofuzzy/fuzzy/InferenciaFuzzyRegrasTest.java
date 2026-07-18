package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InferenciaFuzzyRegrasTest {

    @Test
    void deveAplicarRegrasDeVelocidade() {
        InferenciaFuzzy inferencia = InferenciaFuzzy.builder()
                .entradaBaixa(0.8)
                .entradaMedia(0.3)
                .entradaAlta(0.0)
                .build();

        assertEquals(0.8, inferencia.getBaixa(), 0.0001);
        assertEquals(0.3, inferencia.getMedia(), 0.0001);
        assertEquals(0.0, inferencia.getAlta(), 0.0001);
    }
}
