package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InferenciaFuzzyRegrasTest {

    @Test
    void deveAplicarRegrasDeVelocidade() {
        InferenciaFuzzy inferencia = new InferenciaFuzzy(0.8, 0.3, 0.0);

        assertEquals(0.8, inferencia.getVelocidadeBaixa(), 0.0001);
        assertEquals(0.3, inferencia.getVelocidadeMedia(), 0.0001);
        assertEquals(0.0, inferencia.getVelocidadeAlta(), 0.0001);
    }
}
