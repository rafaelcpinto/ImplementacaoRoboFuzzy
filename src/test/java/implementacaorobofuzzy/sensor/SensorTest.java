package implementacaorobofuzzy.sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensorTest {

    @Test
    void deveMedirDistanciasAPartirDaVizinhanca() {
        Integer[][] vizinhanca = {
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 9, 2, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        };

        LeituraSensor leitura = new Sensor().ler(vizinhanca);

        assertEquals(2, leitura.direita());
        assertEquals(2, leitura.esquerda());
        assertEquals(1, leitura.abaixo());
        assertEquals(2, leitura.acima());
    }

    @Test
    void deveMedirDistanciasAteObstaculos() {
        Integer[][] sala = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };

        LeituraSensor leitura = new Sensor().ler(sala);

        assertEquals(1, leitura.direita());
        assertEquals(1, leitura.esquerda());
        assertEquals(1, leitura.abaixo());
        assertEquals(1, leitura.acima());
    }

    @Test
    void deveTratarPortaComoCaminhoLivre() {
        Integer[][] vizinhanca = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 2, 1, 1}
        };

        LeituraSensor leitura = new Sensor().ler(vizinhanca);

        assertEquals(2, leitura.abaixo());
    }

    @Test
    void deveLocalizarAberturasVisiveisNaBarreiraAbaixo() {
        Integer[][] vizinhanca = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        LeituraSensor leitura = new Sensor().ler(vizinhanca);

        assertEquals(2, leitura.aberturaEsquerda());
        assertEquals(2, leitura.aberturaDireita());
    }

    @Test
    void naoDeveProcurarAberturaQuandoCaminhoAbaixoEstaLivre() {
        Integer[][] vizinhanca = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        LeituraSensor leitura = new Sensor().ler(vizinhanca);

        assertEquals(-1, leitura.aberturaEsquerda());
        assertEquals(-1, leitura.aberturaDireita());
    }
}
