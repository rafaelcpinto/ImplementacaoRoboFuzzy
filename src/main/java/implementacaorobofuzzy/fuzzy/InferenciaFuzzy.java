package implementacaorobofuzzy.fuzzy;

public class InferenciaFuzzy {

    private final double perto;
    private final double medio;
    private final double longe;

    private InferenciaFuzzy(Builder builder) {
        this.perto = builder.perto;
        this.medio = builder.medio;
        this.longe = builder.longe;
    }

    public Double[][] executar() {
        double lento = perto;
        double centroMedio = Math.min(
                medio,
                Math.min(1.0 - perto, 1.0 - longe)
        );

        double velocidadeBaixa = Math.max(
                Math.min(perto, medio),
                centroMedio
        );
        double velocidadeMedia = Math.max(
                Math.min(medio, longe),
                centroMedio
        );

        return new Double[][]{
                {lento},
                {velocidadeBaixa},
                {velocidadeMedia},
                {longe}
        };
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private double perto;
        private double medio;
        private double longe;

        private Builder() {
        }

        public Builder perto(double perto) {
            validarValor(perto);
            this.perto = perto;
            return this;
        }

        public Builder medio(double medio) {
            validarValor(medio);
            this.medio = medio;
            return this;
        }

        public Builder longe(double longe) {
            validarValor(longe);
            this.longe = longe;
            return this;
        }

        public InferenciaFuzzy build() {
            return new InferenciaFuzzy(this);
        }

        private void validarValor(double valor) {
            if (!Double.isFinite(valor) || valor < 0.0 || valor > 1.0) {
                throw new IllegalArgumentException(
                        "O grau de pertinencia deve estar entre 0 e 1."
                );
            }
        }
    }
}
