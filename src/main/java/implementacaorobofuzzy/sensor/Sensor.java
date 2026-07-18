package implementacaorobofuzzy.sensor;

public class Sensor {

    public LeituraSensor ler(Integer[][] matriz) {
        int centroY = matriz.length / 2;
        int centroX = matriz[centroY].length / 2;
        int[] aberturas = localizaAberturasNaBarreira(matriz, centroX, centroY);

        return new LeituraSensor(
                distanciaDireita(matriz, centroX, centroY),
                distanciaEsquerda(matriz, centroX, centroY),
                distanciaAbaixo(matriz, centroX, centroY),
                distanciaAcima(matriz, centroX, centroY),
                aberturas[0],
                aberturas[1]
        );
    }

    private int[] localizaAberturasNaBarreira(Integer[][] matriz, int centroX, int centroY) {
        int[] aberturas = {-1, -1};
        int linhaAbaixo = centroY + 1;

        if (linhaAbaixo >= matriz.length || matriz[linhaAbaixo][centroX] != 1) {
            return aberturas;
        }

        for (int distancia = 1; distancia <= centroX; distancia++) {
            if (matriz[linhaAbaixo][centroX - distancia] != 1) {
                aberturas[0] = distancia;
                break;
            }
        }

        for (int distancia = 1; centroX + distancia < matriz[linhaAbaixo].length; distancia++) {
            if (matriz[linhaAbaixo][centroX + distancia] != 1) {
                aberturas[1] = distancia;
                break;
            }
        }

        return aberturas;
    }

    private int distanciaDireita(Integer[][] matriz, int centroX, int centroY) {
        int distancia = 0;
        for (int x = centroX + 1; x < matriz[centroY].length; x++) {
            if (matriz[centroY][x] == 1) {
                break;
            }
            distancia++;
        }
        return distancia;
    }

    private int distanciaEsquerda(Integer[][] matriz, int centroX, int centroY) {
        int distancia = 0;
        for (int x = centroX - 1; x >= 0; x--) {
            if (matriz[centroY][x] == 1) {
                break;
            }
            distancia++;
        }
        return distancia;
    }

    private int distanciaAbaixo(Integer[][] matriz, int centroX, int centroY) {
        int distancia = 0;
        for (int y = centroY + 1; y < matriz.length; y++) {
            if (matriz[y][centroX] == 1) {
                break;
            }
            distancia++;
        }
        return distancia;
    }

    private int distanciaAcima(Integer[][] matriz, int centroX, int centroY) {
        int distancia = 0;
        for (int y = centroY - 1; y >= 0; y--) {
            if (matriz[y][centroX] == 1) {
                break;
            }
            distancia++;
        }
        return distancia;
    }
}
