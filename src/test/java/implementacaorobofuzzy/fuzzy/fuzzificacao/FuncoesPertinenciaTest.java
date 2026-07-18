package implementacaorobofuzzy.fuzzy.fuzzificacao;

import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoPertinencia;
import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoTrapezoidal;
import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoTriangular;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncoesPertinenciaTest {

    @Test
    void deveCalcularFuncaoTriangular() {
        FuncaoPertinencia funcao = new FuncaoTriangular(0.0, 0.5, 1.0);

        assertEquals(0.0, funcao.calcular(0.0), 0.0001);
        assertEquals(0.5, funcao.calcular(0.25), 0.0001);
        assertEquals(1.0, funcao.calcular(0.5), 0.0001);
        assertEquals(0.5, funcao.calcular(0.75), 0.0001);
        assertEquals(0.0, funcao.calcular(1.0), 0.0001);
        assertEquals(0.5, funcao.getCentroide(), 0.0001);
    }

    @Test
    void deveCalcularFuncaoTrapezoidalComOmbros() {
        FuncaoPertinencia ombroEsquerdo = new FuncaoTrapezoidal(0.0, 0.0, 0.25, 0.5);
        FuncaoPertinencia ombroDireito = new FuncaoTrapezoidal(0.5, 0.75, 1.0, 1.0);

        assertEquals(1.0, ombroEsquerdo.calcular(0.0), 0.0001);
        assertEquals(0.5, ombroEsquerdo.calcular(0.375), 0.0001);
        assertEquals(0.5, ombroDireito.calcular(0.625), 0.0001);
        assertEquals(1.0, ombroDireito.calcular(1.0), 0.0001);
        assertEquals(0.1944, ombroEsquerdo.getCentroide(), 0.0001);
        assertEquals(0.8056, ombroDireito.getCentroide(), 0.0001);
    }

    @Test
    void deveRejeitarPontosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> new FuncaoTriangular(0.5, 0.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> new FuncaoTrapezoidal(0.0, 0.8, 0.2, 1.0));
    }
}
