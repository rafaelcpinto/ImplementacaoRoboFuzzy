package implementacaorobofuzzy.sensor;

import implementacaorobofuzzy.ambiente.Vizinhanca;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensorTest {

    @Test
    void deveMedirDistanciasAPartirDaVizinhanca() {
        Vizinhanca vizinhanca = new Vizinhanca(new Integer[][] {
                {0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 9, 2, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        });

        LeituraSensor leitura = new Sensor().ler(vizinhanca);

        assertEquals(2, leitura.getDireita());
        assertEquals(2, leitura.getEsquerda());
        assertEquals(1, leitura.getAbaixo());
        assertEquals(2, leitura.getAcima());
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

        LeituraSensor leitura = new Sensor().ler(new Vizinhanca(sala));

        assertEquals(1, leitura.getDireita());
        assertEquals(1, leitura.getEsquerda());
        assertEquals(1, leitura.getAbaixo());
        assertEquals(1, leitura.getAcima());
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

        LeituraSensor leitura = new Sensor().ler(new Vizinhanca(vizinhanca));

        assertEquals(2, leitura.getAbaixo());
    }
}
