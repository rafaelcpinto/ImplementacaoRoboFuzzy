package implementacaorobofuzzy.controle;

import implementacaorobofuzzy.fuzzy.ControleFuzzy;
import implementacaorobofuzzy.sensor.LeituraSensor;

import java.util.Objects;

public class Movimento {

    private final ControleFuzzy controleFuzzy;
    private final int alcanceSensor;
    private DirecaoBusca direcaoBusca = DirecaoBusca.DIREITA;
    private double ultimoValorFuzzy;

    public Movimento(ControleFuzzy controleFuzzy, int alcanceSensor) {
        this.controleFuzzy = Objects.requireNonNull(
                controleFuzzy,
                "O controlador fuzzy nao pode ser nulo."
        );
        if (alcanceSensor <= 0) {
            throw new IllegalArgumentException("O alcance do sensor deve ser positivo.");
        }
        this.alcanceSensor = alcanceSensor;
    }

    public Deslocamento calcular(LeituraSensor leitura) {
        Objects.requireNonNull(leitura, "A leitura do sensor nao pode ser nula.");

        if (leitura.abaixo() > 0) {
            return calcularDeslocamento(DirecaoMovimento.ABAIXO, leitura);
        }

        escolheDirecaoDaAbertura(leitura);

        if (direcaoBusca == DirecaoBusca.DIREITA && leitura.direita() == 0) {
            direcaoBusca = DirecaoBusca.ESQUERDA;
        } else if (direcaoBusca == DirecaoBusca.ESQUERDA && leitura.esquerda() == 0) {
            direcaoBusca = DirecaoBusca.DIREITA;
        }

        if (direcaoBusca == DirecaoBusca.DIREITA && leitura.direita() > 0) {
            return calcularDeslocamento(DirecaoMovimento.DIREITA, leitura);
        } else if (direcaoBusca == DirecaoBusca.ESQUERDA && leitura.esquerda() > 0) {
            return calcularDeslocamento(DirecaoMovimento.ESQUERDA, leitura);
        } else if (leitura.acima() > 0) {
            return calcularDeslocamento(DirecaoMovimento.ACIMA, leitura);
        }

        ultimoValorFuzzy = 0.0;
        return new Deslocamento(0, 0);
    }

    private Deslocamento calcularDeslocamento(
            DirecaoMovimento direcao,
            LeituraSensor leitura
    ) {
        int distanciaLivre = obterDistancia(direcao, leitura);
        double valorNormalizado = Math.min(
                1.0,
                Math.max(0.0, distanciaLivre / (double) alcanceSensor)
        );
        ultimoValorFuzzy = controleFuzzy.calcular(valorNormalizado);
        double deslocamento = ultimoValorFuzzy * alcanceSensor;

        // A leitura ja conta apenas as celulas livres e exclui o obstaculo.
        int deslocamentoAplicado = (int) Math.round(Math.min(
                deslocamento,
                distanciaLivre
        ));

        if (distanciaLivre > 0 && deslocamentoAplicado == 0) {
            deslocamentoAplicado = 1;
        }

        return switch (direcao) {
            case DIREITA -> new Deslocamento(deslocamentoAplicado, 0);
            case ESQUERDA -> new Deslocamento(-deslocamentoAplicado, 0);
            case ABAIXO -> new Deslocamento(0, deslocamentoAplicado);
            case ACIMA -> new Deslocamento(0, -deslocamentoAplicado);
        };
    }

    private int obterDistancia(DirecaoMovimento direcao, LeituraSensor leitura) {
        return switch (direcao) {
            case DIREITA -> leitura.direita();
            case ESQUERDA -> leitura.esquerda();
            case ABAIXO -> leitura.abaixo();
            case ACIMA -> leitura.acima();
        };
    }

    private void escolheDirecaoDaAbertura(LeituraSensor leitura) {
        int esquerda = leitura.aberturaEsquerda();
        int direita = leitura.aberturaDireita();

        if (esquerda >= 0 && (direita < 0 || esquerda < direita)) {
            direcaoBusca = DirecaoBusca.ESQUERDA;
        } else if (direita >= 0 && (esquerda < 0 || direita < esquerda)) {
            direcaoBusca = DirecaoBusca.DIREITA;
        }
    }

    public DirecaoBusca getDirecaoBusca() {
        return direcaoBusca;
    }

    public double getUltimoValorFuzzy() {
        return ultimoValorFuzzy;
    }

    public enum DirecaoBusca {
        DIREITA,
        ESQUERDA
    }

    private enum DirecaoMovimento {
        DIREITA,
        ESQUERDA,
        ABAIXO,
        ACIMA
    }

}
