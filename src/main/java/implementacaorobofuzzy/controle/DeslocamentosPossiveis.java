package implementacaorobofuzzy.controle;

public class DeslocamentosPossiveis {
    private final int direita;
    private final int esquerda;
    private final int abaixo;
    private final int acima;

    public DeslocamentosPossiveis(int direita, int esquerda, int abaixo, int acima) {
        this.direita = direita;
        this.esquerda = esquerda;
        this.abaixo = abaixo;
        this.acima = acima;
    }

    public int getDireita() {
        return direita;
    }

    public int getEsquerda() {
        return esquerda;
    }

    public int getAbaixo() {
        return abaixo;
    }

    public int getAcima() {
        return acima;
    }
}
