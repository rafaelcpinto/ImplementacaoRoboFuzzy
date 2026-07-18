package implementacaorobofuzzy.sensor;

import implementacaorobofuzzy.ambiente.Vizinhanca;

public class Sensor {

    public LeituraSensor ler(Vizinhanca vizinhanca) {
        Integer[][] matriz = vizinhanca.getMatriz();
        int centroY = matriz.length / 2;
        int centroX = matriz[centroY].length / 2;

        return new LeituraSensor(
                distanciaDireita(matriz, centroX, centroY),
                distanciaEsquerda(matriz, centroX, centroY),
                distanciaAbaixo(matriz, centroX, centroY),
                distanciaAcima(matriz, centroX, centroY)
        );
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
