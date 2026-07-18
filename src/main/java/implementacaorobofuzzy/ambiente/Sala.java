package implementacaorobofuzzy.ambiente;

public final class Sala {
    public static final int TAMANHO_PADRAO = 1000;

    private final Integer[][] matriz;

    public Sala() {
        this(TAMANHO_PADRAO, TAMANHO_PADRAO);
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
