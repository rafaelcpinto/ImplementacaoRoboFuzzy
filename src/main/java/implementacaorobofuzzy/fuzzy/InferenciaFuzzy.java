package implementacaorobofuzzy.fuzzy;

public class InferenciaFuzzy {

    private static final double PESO_REGRA_MEDIA_PARA_BAIXA = 0.30;
    private static final double PESO_REGRA_LONGE_PARA_MEDIA = 0.25;

    private final double velocidadeBaixa;
    private final double velocidadeMedia;
    private final double velocidadeAlta;

    public InferenciaFuzzy(
            double distanciaPerto,
            double distanciaMedia,
            double distanciaLonge
    ) {
        validarGrau(distanciaPerto);
        validarGrau(distanciaMedia);
        validarGrau(distanciaLonge);

        double regraPertoParaBaixa =
                aplicarRegra(distanciaPerto);

        double regraMediaParaMedia =
                aplicarRegra(distanciaMedia);

        double regraLongeParaAlta =
                aplicarRegra(distanciaLonge);

        double regraMediaParaBaixa =
                aplicarRegraComPeso(
                        distanciaMedia,
                        PESO_REGRA_MEDIA_PARA_BAIXA
                );

        double regraLongeParaMedia =
                aplicarRegraComPeso(
                        distanciaLonge,
                        PESO_REGRA_LONGE_PARA_MEDIA
                );

        this.velocidadeBaixa = agregar(
                regraPertoParaBaixa,
                regraMediaParaBaixa
        );

        this.velocidadeMedia = agregar(
                regraMediaParaMedia,
                regraLongeParaMedia
        );

        this.velocidadeAlta = regraLongeParaAlta;
    }

    private double aplicarRegra(double grauEntrada) {
        return grauEntrada;
    }

    private double aplicarRegraComPeso(
            double grauEntrada,
            double peso
    ) {
        validarGrau(peso);

        return grauEntrada * peso;
    }

    private double agregar(double... graus) {
        double maiorGrau = 0.0;

        for (double grau : graus) {
            validarGrau(grau);
            maiorGrau = Math.max(maiorGrau, grau);
        }

        return maiorGrau;
    }

    private void validarGrau(double valor) {
        if (!Double.isFinite(valor) || valor < 0.0 || valor > 1.0) {
            throw new IllegalArgumentException(
                    "O grau de inferência deve estar entre 0 e 1."
            );
        }
    }

    public double getVelocidadeBaixa() {
        return velocidadeBaixa;
    }

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public double getVelocidadeAlta() {
        return velocidadeAlta;
    }
}