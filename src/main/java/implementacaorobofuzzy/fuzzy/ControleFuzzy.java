package implementacaorobofuzzy.fuzzy;

public class ControleFuzzy {

    private final Fuzzyficacao fuzzyficacao;
    private final Defuzzyficacao defuzzyficacao;

    public ControleFuzzy() {
        this.fuzzyficacao = new Fuzzyficacao();
        this.defuzzyficacao = new Defuzzyficacao();
    }

    public double calcular(double input) {

        double perto = fuzzyficacao.calcPerto(input);
        double medio = fuzzyficacao.calcMedio(input);
        double longe = fuzzyficacao.calcLonge(input);

        InferenciaFuzzy inferencia = new InferenciaFuzzy(perto, medio, longe);

        return defuzzyficacao.calcular(inferencia);
    }
}
