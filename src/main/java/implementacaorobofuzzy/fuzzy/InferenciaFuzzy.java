package implementacaorobofuzzy.fuzzy;

public class InferenciaFuzzy {

    private final double velocidadeBaixa;
    private final double velocidadeMedia;
    private final double velocidadeAlta;

    public InferenciaFuzzy(
            double distanciaPerto,
            double distanciaMedia,
            double distanciaLonge
    ) {
        distanciaPerto = validaGrau(distanciaPerto);
        distanciaMedia = validaGrau(distanciaMedia);
        distanciaLonge = validaGrau(distanciaLonge);

        double regra1 = regraDistanciaPerto(distanciaPerto);
        double regra2 = regraDistanciaMedia(distanciaMedia);
        double regra3 = regraDistanciaLonge(distanciaLonge);

        double regra4 = regraMediaTambemReduzVelocidade(distanciaMedia);
        double regra5 = regraLongeTambemPermiteVelocidadeMedia(distanciaLonge);

        this.velocidadeBaixa = Math.max(regra1, regra4);
        this.velocidadeMedia = Math.max(regra2, regra5);
        this.velocidadeAlta = regra3;
    }

    /*
     * R1:
     * SE distância é PERTO
     * ENTÃO velocidade é BAIXA
     */
    private double regraDistanciaPerto(double distanciaPerto) {
        return distanciaPerto;
    }

    /*
     * R2:
     * SE distância é MÉDIA
     * ENTÃO velocidade é MÉDIA
     */
    private double regraDistanciaMedia(double distanciaMedia) {
        return distanciaMedia;
    }

    /*
     * R3:
     * SE distância é LONGE
     * ENTÃO velocidade é ALTA
     */
    private double regraDistanciaLonge(double distanciaLonge) {
        return distanciaLonge;
    }

    /*
     * R4:
     * SE distância é MÉDIA
     * ENTÃO velocidade é BAIXA
     *
     * Essa regra tem peso 0.30.
     */
    private double regraMediaTambemReduzVelocidade(double distanciaMedia) {
        return Math.min(distanciaMedia, 0.30);
    }

    /*
     * R5:
     * SE distância é LONGE
     * ENTÃO velocidade é MÉDIA
     *
     * Essa regra tem peso 0.25.
     */
    private double regraLongeTambemPermiteVelocidadeMedia(double distanciaLonge) {
        return Math.min(distanciaLonge, 0.25);
    }

    private double validaGrau(double valor) {
        if (!Double.isFinite(valor) || valor < 0 || valor > 1) {
            throw new IllegalArgumentException("O grau de inferencia deve estar entre 0 e 1.");
        }
        return valor;
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
