package implementacaorobofuzzy.ambiente;

public final class Sala {

    private final Integer[][] matriz;

    public Sala() {
        this(50, 50);
    }

    public Sala(int linhas, int colunas) {
        this.matriz = new Integer[linhas][colunas];
        inicializaMatriz();
    }

    public Sala(Integer[][] matriz) {
        this.matriz = matriz;
    }

    private void inicializaMatriz() {
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                matriz[linha][coluna] = 0;
            }
        }
    }

    public Integer[][] getMatriz() {
        return matriz;
    }
}
