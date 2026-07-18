package implementacaorobofuzzy.sensor;

public record LeituraSensor(
        int direita,
        int esquerda,
        int abaixo,
        int acima,
        int aberturaEsquerda,
        int aberturaDireita
) {

    public LeituraSensor(int direita, int esquerda, int abaixo, int acima) {
        this(direita, esquerda, abaixo, acima, -1, -1);
    }

}
