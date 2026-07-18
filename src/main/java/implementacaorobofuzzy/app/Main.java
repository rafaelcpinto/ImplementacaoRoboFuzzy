package implementacaorobofuzzy.app;

import implementacaorobofuzzy.ambiente.Ambiente;
import implementacaorobofuzzy.ambiente.Posicao;
import implementacaorobofuzzy.ambiente.Sala;
import implementacaorobofuzzy.controle.Deslocamento;
import implementacaorobofuzzy.controle.Movimento;
import implementacaorobofuzzy.fuzzy.ControleFuzzy;
import implementacaorobofuzzy.sensor.LeituraSensor;
import implementacaorobofuzzy.sensor.Sensor;
import implementacaorobofuzzy.ui.Interface;

public class Main {
    private static final int MAXIMO_DE_PASSOS = Sala.TAMANHO_PADRAO * 5;

    public static void main(String[] args) {
        var sensor = new Sensor();
        var controleFuzzy = new ControleFuzzy();
        var ambiente = new Ambiente();
        var movimento = new Movimento(controleFuzzy, ambiente.getAlcanceSensor());
        var interfaceUsuario = new Interface();

        interfaceUsuario.inicio(ambiente.getPosicao());
        interfaceUsuario.imprimePorta(ambiente.getPorta());
        boolean desviandoBarreira = false;

        for (int passo = 1; passo <= MAXIMO_DE_PASSOS; passo++) {
            LeituraSensor leitura = sensor.ler(ambiente.getVizinhanca());

            int baixo = leitura.abaixo();
            var deslocamento = movimento.calcular(leitura);

            if (baixo == 0 && !desviandoBarreira) {
                interfaceUsuario.encontrouBarreira(passo, ambiente.getPosicao(), leitura,
                        movimento.getDirecaoBusca());
                desviandoBarreira = true;
            } else if (baixo > 0) {
                desviandoBarreira = false;
            }

            if (deslocamento.x() == 0 && deslocamento.y() == 0) {
                System.out.println("Robo sem caminho livre.");
                break;
            }

            Posicao posicaoAnterior = ambiente.getPosicao();
            ambiente.addDeslocamento(deslocamento.x(), deslocamento.y());
            Posicao posicaoAtual = ambiente.getPosicao();
            var deslocamentoRealizado = new Deslocamento(
                    posicaoAtual.getX() - posicaoAnterior.getX(),
                    posicaoAtual.getY() - posicaoAnterior.getY());

            interfaceUsuario.passo(passo, deslocamentoRealizado, posicaoAtual,
                    movimento.getDirecaoBusca(),
                    movimento.getUltimoValorFuzzy());

            if (ambiente.chegouNaPorta()) {
                interfaceUsuario.resultado(true,
                        ambiente.getPosicao().getX(), ambiente.getPosicao().getY());
                break;
            }
        }
    }

}
