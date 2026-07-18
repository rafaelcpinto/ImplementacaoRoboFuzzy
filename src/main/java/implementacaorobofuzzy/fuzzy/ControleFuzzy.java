package implementacaorobofuzzy.fuzzy;

import implementacaorobofuzzy.fuzzy.functions.FuncaoPertinencia;
import implementacaorobofuzzy.fuzzy.functions.FuncaoTrapezoidal;
import implementacaorobofuzzy.fuzzy.functions.FuncaoTriangular;

public class ControleFuzzy {

    private final FuncaoPertinencia entrada1 = new FuncaoTriangular(0.0, 0.0, 0.50);
    private final FuncaoPertinencia entrada2 = new FuncaoTriangular(0.25, 0.50, 0.75);
    private final FuncaoPertinencia entrada3 = new FuncaoTriangular(0.50, 1.0, 1.0);

    private final FuncaoPertinencia saida1 = new FuncaoTrapezoidal(0.0, 0.0, 0.05, 0.25);
    private final FuncaoPertinencia saida2 = new FuncaoTriangular(0.05, 0.28, 0.52);
    private final FuncaoPertinencia saida3 = new FuncaoTriangular(0.38, 0.58, 0.78);
    private final FuncaoPertinencia saida4 = new FuncaoTrapezoidal(0.65, 0.82, 1.0, 1.0);

    public double calcular(double entrada) {
        validarEntrada(entrada);

        double grau1 = entrada1.calcular(entrada);
        double grau2 = entrada2.calcular(entrada);
        double grau3 = entrada3.calcular(entrada);
        double grau2Isolado = Math.min(
                grau2,
                Math.min(1.0 - grau1, 1.0 - grau3)
        );

        double grauSaida1 = grau1;
        double grauSaida2 = Math.max(Math.min(grau1, grau2), grau2Isolado);
        double grauSaida3 = Math.max(Math.min(grau2, grau3), grau2Isolado);
        double grauSaida4 = grau3;

        double somaGraus = grauSaida1 + grauSaida2 + grauSaida3 + grauSaida4;
        if (somaGraus == 0.0) {
            return 0.0;
        }

        double somaPonderada = grauSaida1 * saida1.getCentroide()
                + grauSaida2 * saida2.getCentroide()
                + grauSaida3 * saida3.getCentroide()
                + grauSaida4 * saida4.getCentroide();

        return somaPonderada / somaGraus;
    }

    private void validarEntrada(double entrada) {
        if (!Double.isFinite(entrada) || entrada < 0.0 || entrada > 1.0) {
            throw new IllegalArgumentException("A entrada deve estar entre 0 e 1.");
        }
    }
}
