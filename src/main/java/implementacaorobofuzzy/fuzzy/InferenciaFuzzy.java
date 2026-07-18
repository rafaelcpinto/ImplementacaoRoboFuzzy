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

        double lento = 0.0;
        double velocidadeBaixa = 0.0;
        double velocidadeMedia = 0.0;
        double rapido = 0.0;

        // SE perto, ENTÃO lento
        if (perto > 0.0) {
            lento = perto;
        }

        // Centro de medio: medio E NAO perto E NAO longe
        double centroMedio = Math.min(
                medio,
                Math.min(1.0 - perto, 1.0 - longe)
        );

        // SE (perto E medio) OU centro de medio, ENTAO velocidade baixa
        if (medio > 0.0) {
            velocidadeBaixa = Math.max(
                    Math.min(perto, medio),
                    centroMedio
            );

            // SE (medio E longe) OU centro de medio, ENTAO velocidade media
            velocidadeMedia = Math.max(
                    Math.min(medio, longe),
                    centroMedio
            );
        }

        // SE longe, ENTÃO rápido
        if (longe > 0.0) {
            rapido = longe;
        }

        return new Double[][]{
                {lento},
                {velocidadeBaixa},
                {velocidadeMedia},
                {rapido}
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
