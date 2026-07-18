package implementacaorobofuzzy.ambiente;

public class ConsultaVizinhanca {

    public Vizinhanca consultar(Sala sala, Posicao posicao, int alcance) {
        Integer[][] matrizSala = sala.getMatriz();
        int tamanho = alcance * 2 + 1;
        Integer[][] matrizVizinhanca = new Integer[tamanho][tamanho];

        for (int y = 0; y < tamanho; y++) {
            for (int x = 0; x < tamanho; x++) {
                int salaX = posicao.getX() - alcance + x;
                int salaY = posicao.getY() - alcance + y;

                if (foraDaSala(matrizSala, salaX, salaY)) {
                    matrizVizinhanca[y][x] = 1;
                } else {
                    matrizVizinhanca[y][x] = matrizSala[salaY][salaX];
                }
            }
        }

        return new Vizinhanca(matrizVizinhanca);
    }

    private boolean foraDaSala(Integer[][] matrizSala, int x, int y) {
        if (y < 0 || y >= matrizSala.length) {
            return true;
        }
        return x < 0 || x >= matrizSala[y].length;
    }
}
