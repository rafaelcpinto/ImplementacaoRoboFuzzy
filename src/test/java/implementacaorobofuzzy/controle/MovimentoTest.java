package implementacaorobofuzzy.controle;

import implementacaorobofuzzy.sensor.LeituraSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovimentoTest {

    @Test
    void deveAvancarQuandoCaminhoAbaixoEstaLivre() {
        Movimento movimento = new Movimento();
        Deslocamento deslocamento = movimento.calcular(
                new LeituraSensor(2, 2, 1, 0),
                new DeslocamentosPossiveis(1, 1, 1, 0));

        assertEquals(0, deslocamento.getX());
        assertEquals(1, deslocamento.getY());
        assertEquals(Movimento.DirecaoBusca.DIREITA, movimento.getDirecaoBusca());
    }

    @Test
    void deveManterAUltimaDirecaoDeBuscaEnquantoEstiverLivre() {
        Movimento movimento = new Movimento();

        DeslocamentosPossiveis possiveis = new DeslocamentosPossiveis(1, 1, 0, 1);
        movimento.calcular(new LeituraSensor(2, 2, 0, 1), possiveis);
        Deslocamento deslocamento = movimento.calcular(new LeituraSensor(2, 2, 0, 1), possiveis);

        assertEquals(1, deslocamento.getX());
        assertEquals(0, deslocamento.getY());
        assertEquals(Movimento.DirecaoBusca.DIREITA, movimento.getDirecaoBusca());
    }

    @Test
    void deveTrocarABuscaParaEsquerdaAoEncontrarObstaculoNaDireita() {
        Movimento movimento = new Movimento();
        Deslocamento deslocamento = movimento.calcular(
                new LeituraSensor(0, 3, 0, 1),
                new DeslocamentosPossiveis(0, 1, 0, 1));

        assertEquals(-1, deslocamento.getX());
        assertEquals(0, deslocamento.getY());
        assertEquals(Movimento.DirecaoBusca.ESQUERDA, movimento.getDirecaoBusca());
    }

    @Test
    void deveUsarOsDeslocamentosCalculadosPeloOrquestrador() {
        Movimento movimento = new Movimento();

        Deslocamento deslocamento = movimento.calcular(
                new LeituraSensor(0, 0, 10, 0),
                new DeslocamentosPossiveis(0, 0, 8, 0));

        assertEquals(0, deslocamento.getX());
        assertEquals(8, deslocamento.getY());
    }

    @Test
    void deveEscolherAberturaVisivelMaisProxima() {
        Movimento movimento = new Movimento();
        LeituraSensor leitura = new LeituraSensor(5, 5, 0, 3, 2, 4);

        Deslocamento deslocamento = movimento.calcular(
                leitura, new DeslocamentosPossiveis(3, 2, 0, 1));

        assertEquals(-2, deslocamento.getX());
        assertEquals(0, deslocamento.getY());
        assertEquals(Movimento.DirecaoBusca.ESQUERDA, movimento.getDirecaoBusca());
    }

    @Test
    void deveManterDirecaoPreferencialQuandoAberturasEstaoEmpatadas() {
        Movimento movimento = new Movimento();
        LeituraSensor leitura = new LeituraSensor(5, 5, 0, 3, 2, 2);

        Deslocamento deslocamento = movimento.calcular(
                leitura, new DeslocamentosPossiveis(3, 2, 0, 1));

        assertEquals(3, deslocamento.getX());
        assertEquals(Movimento.DirecaoBusca.DIREITA, movimento.getDirecaoBusca());
    }
}
