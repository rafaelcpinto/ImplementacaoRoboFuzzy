package implementacaorobofuzzy.fuzzy;

public class ControleFuzzy {

    private final Fuzzyficacao fuzzy = new Fuzzyficacao();

    public double Calcula(Double input) {
        InferenciaFuzzy inferencia = InferenciaFuzzy.builder()
                .entradaBaixa(fuzzy.calcPerto(input))
                .entradaMedia(fuzzy.calcMedio(input))
                .entradaAlta(fuzzy.calcLonge(input))
                .build();

        CalculaCentroDeGravidade centroDeGravidade = CalculaCentroDeGravidade.builder()
                .perto(inferencia.getBaixa())
                .medio(inferencia.getMedia())
                .longe(inferencia.getAlta())
                .build();

        double velocidade = centroDeGravidade.getValue();

        return velocidade;
    }
}
