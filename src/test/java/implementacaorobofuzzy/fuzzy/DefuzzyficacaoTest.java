package implementacaorobofuzzy.fuzzy;

import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoTrapezoidal;
import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoTriangular;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefuzzyficacaoTest {

    @Test
    void deveCalcularUsandoAsFuncoesDeSaida() {
        Defuzzyficacao defuzzyficacao = new Defuzzyficacao();
        defuzzyficacao.adicionar(
                "lento",
                new FuncaoTrapezoidal(0.0, 0.0, 0.10, 0.30)
        );
        defuzzyficacao.adicionar(
                "velocidadeBaixa",
                new FuncaoTriangular(0.10, 0.35, 0.60)
        );
        defuzzyficacao.adicionar(
                "velocidadeMedia",
                new FuncaoTriangular(0.40, 0.65, 0.90)
        );
        defuzzyficacao.adicionar(
                "rapido",
                new FuncaoTrapezoidal(0.70, 0.90, 1.0, 1.0)
        );

        double resultado = defuzzyficacao.calcular(Arrays.asList(0.3, 0.4, 0.0, 0.0));

        assertEquals(0.2464285714, resultado, 0.0001);
    }
}
