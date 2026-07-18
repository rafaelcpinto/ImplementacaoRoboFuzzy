package implementacaorobofuzzy.ambiente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsultaVizinhancaTest {

    @Test
    void deveRetornarMatrizDeVizinhancaAoRedorDaPosicao() {
        Sala sala = new Sala(new Integer[][] {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        });

        Integer[][] vizinhanca = new ConsultaVizinhanca().consultar(sala, new Posicao(2, 2), 1);

        assertArrayEquals(new Integer[] {7, 8, 9}, vizinhanca[0]);
        assertArrayEquals(new Integer[] {12, 13, 14}, vizinhanca[1]);
        assertArrayEquals(new Integer[] {17, 18, 19}, vizinhanca[2]);
    }

    @Test
    void devePreencherForaDaSalaComoObstaculo() {
        Sala sala = new Sala(new Integer[][] {
                {1, 2},
                {3, 4}
        });

        Integer[][] vizinhanca = new ConsultaVizinhanca().consultar(sala, new Posicao(0, 0), 1);

        assertArrayEquals(new Integer[] {1, 1, 1}, vizinhanca[0]);
        assertArrayEquals(new Integer[] {1, 1, 2}, vizinhanca[1]);
        assertArrayEquals(new Integer[] {1, 3, 4}, vizinhanca[2]);
    }
}
