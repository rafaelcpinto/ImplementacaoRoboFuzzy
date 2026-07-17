package implementacaorobofuzzy.controle;

import implementacaorobofuzzy.ambiente.CriaAmbiente;
import implementacaorobofuzzy.ambiente.Memoria;
import implementacaorobofuzzy.fuzzy.CalculaCentroDeGravidade;
import implementacaorobofuzzy.fuzzy.Defuzzyficacao;
import implementacaorobofuzzy.fuzzy.RegrasNebulosas;
import implementacaorobofuzzy.sensor.Sensor;
import implementacaorobofuzzy.ui.Interface;

public class Executa {

    public void executa() {
        CriaAmbiente criaAmbiente = new CriaAmbiente();
        criaAmbiente.criaAmbiente();
        Memoria memoria = new Memoria();
        Sensor sensor = new Sensor();
        RegrasNebulosas regrasNebulosas = new RegrasNebulosas();
        Interface inter = new Interface();
        CalculaCentroDeGravidade calculaCG = new CalculaCentroDeGravidade();

        System.out.println(memoria.salaSt);
        inter.imprimePorta(memoria.posPorta);

        int passos = 0;
        int limitePassos = memoria.sala.length * memoria.sala[0].length;

        while (memoria.posicaoAtualY < memoria.sala.length - 1 && passos < limitePassos) {
            passos++;
            sensor.detecta(memoria.posicaoAtualX, memoria.posicaoAtualY);
            regrasNebulosas.executaRegras(memoria);

            Defuzzyficacao defuzzyficacao = new Defuzzyficacao();

            double deslocamentoXp = 0;
            double deslocamentoXn = 0;
            double defuzzyY1 = 0.0;
            double defuzzyY2 = 0.0;

            if (regrasNebulosas.logicYperto) {
                defuzzyY1 = defuzzyficacao.moveCurtoY(regrasNebulosas.valorFuzzyY);
            }
            if (regrasNebulosas.logicYlonge) {
                defuzzyY2 = defuzzyficacao.moveLongoY(regrasNebulosas.valorFuzzyY);
            }

            if (regrasNebulosas.logicXpPerto) {
                deslocamentoXn = defuzzyficacao.moveCurtoX(regrasNebulosas.valorFuzzyXp);
            }
            if (regrasNebulosas.logicXpLonge) {
                deslocamentoXp = defuzzyficacao.moveLongoX(regrasNebulosas.valorFuzzyXp);
            }

            if (regrasNebulosas.logicXnPerto) {
                deslocamentoXp = defuzzyficacao.moveCurtoX(regrasNebulosas.valorFuzzyXn);
            }
            if (regrasNebulosas.logicXnLonge) {
                deslocamentoXn = defuzzyficacao.moveLongoX(regrasNebulosas.valorFuzzyXn);
            }

            double deslocamentoY = calculaCG.calculaCentroDeGravidade(
                    regrasNebulosas.valorFuzzyY,
                    regrasNebulosas.valorFuzzyY2,
                    defuzzyY1,
                    defuzzyY2);
            int movimentoX = (int) deslocamentoXp - (int) deslocamentoXn;
            int movimentoY = (int) deslocamentoY;

            if (memoria.sala[memoria.posicaoAtualY + 1][memoria.posicaoAtualX] != 1) {
                movimentoX = 0;
                movimentoY = 1;
            } else {
                movimentoY = 0;
                int proximoX = memoria.posicaoAtualX + memoria.direcaoBuscaPorta;
                if (memoria.sala[memoria.posicaoAtualY][proximoX] == 1) {
                    memoria.direcaoBuscaPorta = memoria.direcaoBuscaPorta * -1;
                    if (memoria.sala[memoria.posicaoAtualY - 1][memoria.posicaoAtualX] != 1) {
                        movimentoX = 0;
                        movimentoY = -1;
                    } else {
                        proximoX = memoria.posicaoAtualX + memoria.direcaoBuscaPorta;
                        if (memoria.sala[memoria.posicaoAtualY][proximoX] != 1) {
                            movimentoX = memoria.direcaoBuscaPorta;
                        }
                    }
                }
                if (movimentoY == 0 && memoria.sala[memoria.posicaoAtualY][proximoX] != 1) {
                    movimentoX = memoria.direcaoBuscaPorta;
                }
            }

            memoria.posicaoAtualX = memoria.posicaoAtualX + movimentoX;
            memoria.posicaoAtualY = memoria.posicaoAtualY + movimentoY;

            inter.coordenadas(memoria.posicaoAtualX, memoria.posicaoAtualY);
        }

        inter.resultado(memoria.sala[memoria.posicaoAtualY][memoria.posicaoAtualX] == 2,
                memoria.posicaoAtualX, memoria.posicaoAtualY);
    }
}
