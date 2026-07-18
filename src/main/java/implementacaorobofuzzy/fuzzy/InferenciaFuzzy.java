package implementacaorobofuzzy.fuzzy;

public class InferenciaFuzzy {
    private final double baixa;
    private final double media;
    private final double alta;

    private InferenciaFuzzy(Builder builder) {
        this.baixa = builder.baixa;
        this.media = builder.media;
        this.alta = builder.alta;
    }

    public double getBaixa() {
        return baixa;
    }

    public double getMedia() {
        return media;
    }

    public double getAlta() {
        return alta;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private double entradaBaixa;
        private double entradaMedia;
        private double entradaAlta;
        private double baixa;
        private double media;
        private double alta;

        public Builder entradaBaixa(double entradaBaixa) {
            this.entradaBaixa = validaGrau(entradaBaixa);
            return this;
        }

        public Builder entradaMedia(double entradaMedia) {
            this.entradaMedia = validaGrau(entradaMedia);
            return this;
        }

        public Builder entradaAlta(double entradaAlta) {
            this.entradaAlta = validaGrau(entradaAlta);
            return this;
        }

        public InferenciaFuzzy build() {
            aplicaRegras();
            return new InferenciaFuzzy(this);
        }

        private void aplicaRegras() {
            baixa = entradaBaixa;
            media = entradaMedia;
            alta = entradaAlta;
        }

        private double validaGrau(double valor) {
            if (valor < 0 || valor > 1) {
                throw new IllegalArgumentException("O grau de inferencia deve estar entre 0 e 1.");
            }
            return valor;
        }
    }
}
