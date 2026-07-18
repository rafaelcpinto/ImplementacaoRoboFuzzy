package implementacaorobofuzzy.app;

import implementacaorobofuzzy.ambiente.Ambiente;
import implementacaorobofuzzy.ambiente.Posicao;
import implementacaorobofuzzy.ambiente.Sala;
import implementacaorobofuzzy.controle.Deslocamento;
import implementacaorobofuzzy.controle.DeslocamentosPossiveis;
import implementacaorobofuzzy.controle.Movimento;
import implementacaorobofuzzy.fuzzy.ControleFuzzy;
import implementacaorobofuzzy.sensor.LeituraSensor;
import implementacaorobofuzzy.sensor.Sensor;
import implementacaorobofuzzy.ui.Interface;

public class Main {
    private static final int MAXIMO_DE_PASSOS = Sala.TAMANHO_PADRAO * 5;

    public static void main(String[] args) {
        Sensor sensor = new Sensor();
        ControleFuzzy controleFuzzy = new ControleFuzzy();
        Ambiente ambiente = new Ambiente();
        Movimento movimento = new Movimento();
        Interface interfaceUsuario = new Interface();

        interfaceUsuario.inicio(ambiente.getPosicao());
        interfaceUsuario.imprimePorta(ambiente.getPorta());
        boolean desviandoBarreira = false;

        for (int passo = 1; passo <= MAXIMO_DE_PASSOS; passo++) {
            LeituraSensor leitura = sensor.ler(ambiente.getVizinhanca());

            int direita =  0;
            int esquerda = 0;
            int baixo = leitura.getAbaixo();
            int cima = leitura.getAcima();
            if(baixo==0){
                direita =  leitura.getDireita();
                esquerda = leitura.getEsquerda();
            }
            DeslocamentosPossiveis possiveis = new DeslocamentosPossiveis(
            calculaDeslocamento(controleFuzzy, direita, ambiente.getAlcanceSensor()),
            calculaDeslocamento(controleFuzzy, esquerda, ambiente.getAlcanceSensor()),
            calculaDeslocamento(controleFuzzy, baixo, ambiente.getAlcanceSensor()),
            calculaDeslocamento(controleFuzzy,cima, ambiente.getAlcanceSensor())
            );
            Deslocamento deslocamento = movimento.calcular(leitura, possiveis);

            if (baixo == 0 && !desviandoBarreira) {
                interfaceUsuario.encontrouBarreira(passo, ambiente.getPosicao(), leitura,
                        movimento.getDirecaoBusca());
                desviandoBarreira = true;
            } else if (baixo > 0) {
                desviandoBarreira = false;
            }

            if (deslocamento.getX() == 0 && deslocamento.getY() == 0) {
                System.out.println("Robo sem caminho livre.");
                break;
            }

            Posicao posicaoAnterior = ambiente.getPosicao();
            ambiente.addDeslocamento(deslocamento.getX(), deslocamento.getY());
            Posicao posicaoAtual = ambiente.getPosicao();
            Deslocamento deslocamentoRealizado = new Deslocamento(
                    posicaoAtual.getX() - posicaoAnterior.getX(),
                    posicaoAtual.getY() - posicaoAnterior.getY());

            interfaceUsuario.passo(passo, deslocamentoRealizado, posicaoAtual,
                    movimento.getDirecaoBusca());

            if (ambiente.chegouNaPorta()) {
                interfaceUsuario.resultado(true,
                        ambiente.getPosicao().getX(), ambiente.getPosicao().getY());
                break;
            }
        }
    }

    private static int calculaDeslocamento(ControleFuzzy controleFuzzy, int distanciaLivre,
                                           int alcanceSensor) {
        if (distanciaLivre <= 0) {
            return 0;
        }

        double entradaNormalizada = Math.min(distanciaLivre / (double) alcanceSensor, 1.0);
        double intensidade = controleFuzzy.calcular(entradaNormalizada);
        int deslocamento = (int) Math.round(intensidade * distanciaLivre);

        return Math.max(1, Math.min(deslocamento, distanciaLivre));
    }
}
