package implementacaorobofuzzy.sensor;

public class LeituraSensor {
    private final int direita;
    private final int esquerda;
    private final int abaixo;
    private final int acima;
    private final int aberturaEsquerda;
    private final int aberturaDireita;

    public LeituraSensor(int direita, int esquerda, int abaixo, int acima) {
        this(direita, esquerda, abaixo, acima, -1, -1);
    }

    public LeituraSensor(int direita, int esquerda, int abaixo, int acima,
                         int aberturaEsquerda, int aberturaDireita) {
        this.direita = direita;
        this.esquerda = esquerda;
        this.abaixo = abaixo;
        this.acima = acima;
        this.aberturaEsquerda = aberturaEsquerda;
        this.aberturaDireita = aberturaDireita;
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

    public int getAberturaEsquerda() {
        return aberturaEsquerda;
    }

    public int getAberturaDireita() {
        return aberturaDireita;
    }
}
