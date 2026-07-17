package implementacaorobofuzzy.sensor;

import implementacaorobofuzzy.ambiente.Memoria;

public class Sensor {

    public void detecta(Integer posicaoAtualX, Integer posicaoAtualY) {
        Integer diferencaXp = 0;
        Integer diferencaXn = 0;
        Integer diferencaYp = 0;
        Integer diferencaYn = 0;

        Integer[][] sala = Memoria.sala;
        Integer distSensor = 6;

        int limiteDireita = Math.min(posicaoAtualX + distSensor, sala[0].length - 1);
        int limiteEsquerda = Math.max(posicaoAtualX - distSensor, 0);
        int limiteInferior = Math.min(posicaoAtualY + distSensor, sala.length - 1);
        int limiteSuperior = Math.max(posicaoAtualY - distSensor, 0);

        for (int i = posicaoAtualX; i <= limiteDireita; i++) {
            if (sala[posicaoAtualY][i] == 1) {
                diferencaXp = i - posicaoAtualX - 1;
                break;
            }
            if (i == limiteDireita) {
                diferencaXp = i - posicaoAtualX - 1;
            }
        }
        for (int i = posicaoAtualY; i <= limiteInferior; i++) {
            if (sala[i][posicaoAtualX] == 1) {
                diferencaYn = i - posicaoAtualY - 1;
                break;
            }
            if (i == limiteInferior) {
                diferencaYn = i - posicaoAtualY - 1;
            }
        }
        for (int i = posicaoAtualX; i >= limiteEsquerda; i--) {
            if (sala[posicaoAtualY][i] == 1) {
                diferencaXn = posicaoAtualX - i - 1;
                break;
            }
            if (i == limiteEsquerda) {
                diferencaXn = posicaoAtualX - i - 1;
            }
        }
        for (int i = posicaoAtualY; i >= limiteSuperior; i--) {
            if (sala[i][posicaoAtualX] == 1) {
                diferencaYp = posicaoAtualY - i - 1;
                break;
            }
            if (i == limiteSuperior) {
                diferencaYp = posicaoAtualY - i - 1;
            }
        }

        Memoria.diferencaXp = diferencaXp;
        Memoria.diferencaXn = diferencaXn;
        Memoria.diferencaYp = diferencaYp;
        Memoria.diferencaYn = diferencaYn;
    }
}
