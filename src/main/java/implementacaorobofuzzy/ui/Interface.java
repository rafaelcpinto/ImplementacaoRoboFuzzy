package implementacaorobofuzzy.ui;

import implementacaorobofuzzy.ambiente.Posicao;
import implementacaorobofuzzy.controle.Deslocamento;
import implementacaorobofuzzy.controle.Movimento;
import implementacaorobofuzzy.sensor.LeituraSensor;

public class Interface {
    public void imprimePorta(Posicao porta) {
        System.out.println("Porta: (" + porta.getX() + ", " + porta.getY() + ")");
    }

    public void resultado(boolean chegou, int valorX, int valorY) {
        if (chegou) {
            System.out.println("O ROBO CHEGOU NA PORTA.");
        } else {
            System.out.println("O ROBO NAO CHEGOU NA PORTA.");
        }
        System.out.println("posicaoFinalX: " + valorX);
        System.out.println("posicaoFinalY: " + valorY);
    }

    public void inicio(Posicao posicao) {
        System.out.println("TRAJETO DO ROBO");
        System.out.println("Inicio: (" + posicao.getX() + ", " + posicao.getY() + ")");
    }

    public void passo(int numero, Deslocamento deslocamento, Posicao posicao,
                      Movimento.DirecaoBusca direcaoBusca, double valorFuzzy) {
        System.out.println("Passo " + numero
                + " | deslocamento=(" + deslocamento.x() + ", " + deslocamento.y() + ")"
                + " | posicao=(" + posicao.getX() + ", " + posicao.getY() + ")"
                + " | busca=" + direcaoBusca
                + " | fuzzy=" + valorFuzzy);
    }

    public void encontrouBarreira(int passo, Posicao posicao, LeituraSensor leitura,
                                  Movimento.DirecaoBusca direcaoBusca) {
        System.out.println("BARREIRA ENCONTRADA NO PASSO " + passo);
        System.out.println("  Posicao do robo: (" + posicao.getX() + ", " + posicao.getY() + ")");
        System.out.println("  Sensor: abaixo=" + leitura.abaixo()
                + ", direita=" + leitura.direita()
                + ", esquerda=" + leitura.esquerda()
                + ", acima=" + leitura.acima());
        System.out.println("  Aberturas visiveis: esquerda="
                + formataAbertura(leitura.aberturaEsquerda())
                + ", direita=" + formataAbertura(leitura.aberturaDireita()));
        System.out.println("  Direcao escolhida para buscar abertura: " + direcaoBusca);
    }

    private String formataAbertura(int distancia) {
        return distancia < 0 ? "nao encontrada" : String.valueOf(distancia);
    }
}
