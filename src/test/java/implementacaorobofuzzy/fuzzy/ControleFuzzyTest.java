package implementacaorobofuzzy.fuzzy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ControleFuzzyTest {

    private static final double TOLERANCIA = 0.0001;

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0, 0.249, 0.25, 0.251, 0.499, 0.50,
            0.501, 0.749, 0.75, 0.751, 1.0
    })
    void deveCalcularSaidaNosLimitesDasFuncoes(double entrada) {
        double saida = new ControleFuzzy().calcular(entrada);

        assertTrue(Double.isFinite(saida));
        assertTrue(saida >= 0.0 && saida <= 1.0);
    }

    @Test
    void saidaNaoDeveDiminuirQuandoEntradaAumenta() {
        ControleFuzzy controle = new ControleFuzzy();

        double resultado1 = controle.calcular(0.2);
        double resultado2 = controle.calcular(0.5);
        double resultado3 = controle.calcular(0.8);

        assertTrue(resultado1 <= resultado2 + TOLERANCIA);
        assertTrue(resultado2 <= resultado3 + TOLERANCIA);
    }
}
