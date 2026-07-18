package implementacaorobofuzzy.controle;

import implementacaorobofuzzy.sensor.LeituraSensor;

public class Movimento {
    private DirecaoBusca direcaoBusca = DirecaoBusca.DIREITA;

    public Deslocamento calcular(LeituraSensor leitura, DeslocamentosPossiveis possiveis) {
        if (leitura.getAbaixo() > 0) {
            return new Deslocamento(0, possiveis.getAbaixo());
        }

        escolheDirecaoDaAbertura(leitura);

        if (direcaoBusca == DirecaoBusca.DIREITA && leitura.getDireita() == 0) {
            direcaoBusca = DirecaoBusca.ESQUERDA;
        } else if (direcaoBusca == DirecaoBusca.ESQUERDA && leitura.getEsquerda() == 0) {
            direcaoBusca = DirecaoBusca.DIREITA;
        }

        if (direcaoBusca == DirecaoBusca.DIREITA && leitura.getDireita() > 0) {
            return new Deslocamento(possiveis.getDireita(), 0);
        } else if (direcaoBusca == DirecaoBusca.ESQUERDA && leitura.getEsquerda() > 0) {
            return new Deslocamento(-possiveis.getEsquerda(), 0);
        } else if (leitura.getAcima() > 0) {
            return new Deslocamento(0, -possiveis.getAcima());
        }

        return new Deslocamento(0, 0);
    }

    private void escolheDirecaoDaAbertura(LeituraSensor leitura) {
        int esquerda = leitura.getAberturaEsquerda();
        int direita = leitura.getAberturaDireita();

        if (esquerda >= 0 && (direita < 0 || esquerda < direita)) {
            direcaoBusca = DirecaoBusca.ESQUERDA;
        } else if (direita >= 0 && (esquerda < 0 || direita < esquerda)) {
            direcaoBusca = DirecaoBusca.DIREITA;
        }
    }

    public DirecaoBusca getDirecaoBusca() {
        return direcaoBusca;
    }

    public enum DirecaoBusca {
        DIREITA,
        ESQUERDA
    }
}
