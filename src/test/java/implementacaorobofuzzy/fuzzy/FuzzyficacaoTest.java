package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FuzzyficacaoTest {

    private final Fuzzyficacao fuzzyficacao = new Fuzzyficacao();

    @Test
    void deveCalcularPerto() {
        assertEquals(1.0, fuzzyficacao.calcPerto(0), 0.0001);
        assertEquals(0.5, fuzzyficacao.calcPerto(0.25), 0.0001);
        assertEquals(0.0, fuzzyficacao.calcPerto(0.50), 0.0001);
    }

    @Test
    void deveCalcularMedio() {
        assertEquals(0.0, fuzzyficacao.calcMedio(0.25), 0.0001);
        assertEquals(1.0, fuzzyficacao.calcMedio(0.50), 0.0001);
        assertEquals(0.0, fuzzyficacao.calcMedio(0.75), 0.0001);
    }

    @Test
    void deveCalcularLonge() {
        assertEquals(0.0, fuzzyficacao.calcLonge(0.50), 0.0001);
        assertEquals(0.5, fuzzyficacao.calcLonge(0.75), 0.0001);
        assertEquals(1.0, fuzzyficacao.calcLonge(1.0), 0.0001);
    }
}
