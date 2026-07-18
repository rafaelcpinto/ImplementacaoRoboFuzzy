package implementacaorobofuzzy.fuzzy;

import implementacaorobofuzzy.fuzzy.dominio.DataIn;
import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoTrapezoidal;
import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoTriangular;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuzzyficacaoTest {

    @Test
    void deveCalcularValoresSemConhecerDataIn() {
        Fuzzyficacao fuzzyficacao = new Fuzzyficacao();
        DataIn entrada = new DataIn();
        fuzzyficacao.adicionar(
                entrada.getPerto().getNome(),
                new FuncaoTrapezoidal(0.0, 0.0, 0.25, 0.5)
        );
        fuzzyficacao.adicionar(
                entrada.getMedio().getNome(),
                new FuncaoTriangular(0.25, 0.5, 0.75)
        );
        fuzzyficacao.adicionar(
                entrada.getLonge().getNome(),
                new FuncaoTrapezoidal(0.5, 0.75, 1.0, 1.0)
        );

        Map<String, Double> valores = fuzzyficacao.calcular(0.5);
        Map<String, Double> centroides = fuzzyficacao.obterCentroides();

        assertEquals(0.0, valores.get("perto"), 0.0001);
        assertEquals(1.0, valores.get("medio"), 0.0001);
        assertEquals(0.0, valores.get("longe"), 0.0001);
        assertEquals(0.5, centroides.get("medio"), 0.0001);
        assertThrows(IllegalArgumentException.class, () -> fuzzyficacao.calcular(-0.1));
    }
}
