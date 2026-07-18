package implementacaorobofuzzy.fuzzy;

import implementacaorobofuzzy.fuzzy.dominio.DataIn;
import implementacaorobofuzzy.fuzzy.dominio.DataOut;
import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoTrapezoidal;
import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoTriangular;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControleFuzzy {

    private final Fuzzyficacao fuzzyficacao = new Fuzzyficacao();
    private final Defuzzyficacao defuzzyficacao = new Defuzzyficacao();
    private final DataIn dataIn = new DataIn();
    private final DataOut dataOut = new DataOut();

    public ControleFuzzy() {
        fuzzyficacao.adicionar(
                dataIn.getPerto().getNome(),
                new FuncaoTrapezoidal(0.0, 0.0, 0.25, 0.50)
        );
        fuzzyficacao.adicionar(
                dataIn.getMedio().getNome(),
                new FuncaoTriangular(0.25, 0.50, 0.75)
        );
        fuzzyficacao.adicionar(
                dataIn.getLonge().getNome(),
                new FuncaoTrapezoidal(0.50, 0.75, 1.0, 1.0)
        );

        defuzzyficacao.adicionar(
                dataOut.getLento().getNome(),
                new FuncaoTrapezoidal(0.0, 0.0, 0.10, 0.30)
        );
        defuzzyficacao.adicionar(
                dataOut.getVelocidadeBaixa().getNome(),
                new FuncaoTriangular(0.10, 0.35, 0.60)
        );
        defuzzyficacao.adicionar(
                dataOut.getVelocidadeMedia().getNome(),
                new FuncaoTriangular(0.40, 0.65, 0.90)
        );
        defuzzyficacao.adicionar(
                dataOut.getRapido().getNome(),
                new FuncaoTrapezoidal(0.70, 0.90, 1.0, 1.0)
        );
    }

    public double calcular(double input) {
        Map<String, Double> valoresFuzzy = fuzzyficacao.calcular(input);

        InferenciaFuzzy inferencia = InferenciaFuzzy.builder()
                .perto(obterValor(valoresFuzzy, dataIn.getPerto().getNome()))
                .medio(obterValor(valoresFuzzy, dataIn.getMedio().getNome()))
                .longe(obterValor(valoresFuzzy, dataIn.getLonge().getNome()))
                .build();

        Double[][] matrizInferencia = inferencia.executar();
        List<Double> valoresInferidos = new ArrayList<>();
        for (Double[] linha : matrizInferencia) {
            valoresInferidos.add(linha[0]);
        }

        return defuzzyficacao.calcular(valoresInferidos);
    }

    private double obterValor(Map<String, Double> valores, String nome) {
        Double valor = valores.get(nome);
        if (valor == null) {
            throw new IllegalArgumentException("Valor fuzzy nao encontrado: " + nome);
        }
        return valor;
    }
}
