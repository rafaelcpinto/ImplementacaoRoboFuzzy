package implementacaorobofuzzy.ambiente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmbienteTest {

    @Test
    void deveCriarPortaEmPosicaoAleatoriaNaBordaInferior() {
        Ambiente ambiente = new Ambiente();
        Posicao porta = ambiente.getPorta();

        org.junit.jupiter.api.Assertions.assertTrue(porta.getX() >= 0 && porta.getX() < 50);
        assertEquals(49, porta.getY());
        assertEquals(2, ambiente.sala.getMatriz()[porta.getY()][porta.getX()]);
    }

    @Test
    void deveReconhecerQuandoRoboChegaExatamenteNaPorta() {
        Ambiente ambiente = new Ambiente();
        Posicao porta = ambiente.getPorta();

        ambiente.addDeslocamento(porta.getX(), porta.getY());

        assertEquals(true, ambiente.chegouNaPorta());
    }

    @Test
    void devePararNaPortaQuandoDeslocamentoPassaPorEla() {
        Ambiente ambiente = new Ambiente();
        ambiente.addDeslocamento(0, 49);

        ambiente.addDeslocamento(49, 0);

        assertEquals(true, ambiente.chegouNaPorta());
        assertEquals(ambiente.getPorta().getX(), ambiente.getPosicao().getX());
        assertEquals(49, ambiente.getPosicao().getY());
    }

    @Test
    void deveCriarBarreirasComAberturasAlternadas() {
        Ambiente ambiente = new Ambiente();
        Integer[][] sala = ambiente.sala.getMatriz();

        int quantidadeBarreiras = 0;
        for (Integer[] linha : sala) {
            int obstaculos = 0;
            int espacosLivres = 0;
            for (Integer celula : linha) {
                if (celula == 1) {
                    obstaculos++;
                } else if (celula == 0) {
                    espacosLivres++;
                }
            }

            if (obstaculos > 0) {
                quantidadeBarreiras++;
                assertEquals(39, obstaculos);
                assertEquals(11, espacosLivres);
            }
        }

        assertEquals(5, quantidadeBarreiras);
    }
}
