package implementacaorobofuzzy.controle;

import implementacaorobofuzzy.fuzzy.ControleFuzzy;
import implementacaorobofuzzy.sensor.LeituraSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MovimentoTest {

    private static final int ALCANCE_SENSOR = 50;

    @Test
    void deveUsarMesmoModuloParaMesmaDistanciaEmTodasAsDirecoes() {
        int abaixo = modulo(novoMovimento().calcular(new LeituraSensor(50, 50, 50, 50)));
        int direita = modulo(novoMovimento().calcular(new LeituraSensor(50, 50, 0, 50)));
        int esquerda = modulo(novoMovimento().calcular(
                new LeituraSensor(50, 50, 0, 50, 1, 2)));
        int acima = modulo(novoMovimento().calcular(new LeituraSensor(0, 0, 0, 50)));

        assertEquals(abaixo, direita);
        assertEquals(abaixo, esquerda);
        assertEquals(abaixo, acima);
    }

    @Test
    void deveAplicarEixoESinalConformeDirecao() {
        Deslocamento abaixo = novoMovimento().calcular(new LeituraSensor(20, 20, 20, 20));
        Deslocamento direita = novoMovimento().calcular(new LeituraSensor(20, 20, 0, 20));
        Deslocamento esquerda = novoMovimento().calcular(
                new LeituraSensor(20, 20, 0, 20, 1, 2));
        Deslocamento acima = novoMovimento().calcular(new LeituraSensor(0, 0, 0, 20));

        assertTrue(abaixo.y() > 0);
        assertEquals(0, abaixo.x());
        assertTrue(direita.x() > 0);
        assertEquals(0, direita.y());
        assertTrue(esquerda.x() < 0);
        assertEquals(0, esquerda.y());
        assertTrue(acima.y() < 0);
        assertEquals(0, acima.x());
    }

    @Test
    void distanciaZeroDeveGerarDeslocamentoZero() {
        Deslocamento deslocamento = novoMovimento().calcular(new LeituraSensor(0, 0, 0, 0));

        assertEquals(0, deslocamento.x());
        assertEquals(0, deslocamento.y());
    }

    @Test
    void deslocamentoNuncaDeveUltrapassarEspacoDisponivel() {
        for (int distancia = 1; distancia <= ALCANCE_SENSOR; distancia++) {
            Deslocamento deslocamento = novoMovimento().calcular(
                    new LeituraSensor(distancia, distancia, 0, distancia)
            );
            assertTrue(modulo(deslocamento) <= distancia);
        }
    }

    @Test
    void direitaEEsquerdaDevemTerModulosIguais() {
        Deslocamento direita = novoMovimento().calcular(new LeituraSensor(17, 17, 0, 10));
        Deslocamento esquerda = novoMovimento().calcular(
                new LeituraSensor(17, 17, 0, 10, 1, 2));

        assertEquals(modulo(direita), modulo(esquerda));
    }

    @Test
    void acimaEAbaixoDevemTerModulosIguais() {
        Deslocamento abaixo = novoMovimento().calcular(new LeituraSensor(0, 0, 23, 23));
        Deslocamento acima = novoMovimento().calcular(new LeituraSensor(0, 0, 0, 23));

        assertEquals(modulo(abaixo), modulo(acima));
    }

    @Test
    void buscaLateralDeveDesacelerarAoDiminuirDistancia() {
        Movimento movimento = novoMovimento();
        Deslocamento distante = movimento.calcular(new LeituraSensor(40, 40, 0, 10));
        Deslocamento proximo = movimento.calcular(new LeituraSensor(5, 5, 0, 10));

        assertTrue(modulo(proximo) < modulo(distante));
        assertTrue(modulo(proximo) <= 5);
    }

    @Test
    void deveUsarSensorCorrespondenteADirecaoEscolhida() {
        Movimento movimento = novoMovimento();
        Deslocamento deslocamento = movimento.calcular(new LeituraSensor(7, 30, 0, 10));

        assertTrue(deslocamento.x() > 0);
        assertTrue(modulo(deslocamento) <= 7);
    }

    @Test
    void deveEscolherAberturaVisivelMaisProxima() {
        Movimento movimento = novoMovimento();

        Deslocamento deslocamento = movimento.calcular(
                new LeituraSensor(5, 5, 0, 3, 2, 4)
        );

        assertTrue(deslocamento.x() < 0);
        assertEquals(Movimento.DirecaoBusca.ESQUERDA, movimento.getDirecaoBusca());
    }

    @Test
    void deveManterDirecaoPreferencialQuandoAberturasEstaoEmpatadas() {
        Movimento movimento = novoMovimento();

        Deslocamento deslocamento = movimento.calcular(
                new LeituraSensor(5, 5, 0, 3, 2, 2)
        );

        assertTrue(deslocamento.x() > 0);
        assertEquals(Movimento.DirecaoBusca.DIREITA, movimento.getDirecaoBusca());
    }

    private Movimento novoMovimento() {
        return new Movimento(new ControleFuzzy(), ALCANCE_SENSOR);
    }

    private int modulo(Deslocamento deslocamento) {
        return Math.abs(deslocamento.x()) + Math.abs(deslocamento.y());
    }
}
