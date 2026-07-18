package implementacaorobofuzzy.fuzzy;

public class CalculaCentroDeGravidade {

    private final double perto;
    private final double medio;
    private final double longe;
    private final double centro;

    private CalculaCentroDeGravidade(Builder builder) {
        this.perto = builder.perto;
        this.medio = builder.medio;
        this.longe = builder.longe;
        this.centro=calculaCentroDeGravidade();
    }

    private double calculaCentroDeGravidade() {
        double centroPerto = 0.1667;
        double centroMedio = 0.50;
        double centroLonge = 0.8333;

        double numerador = perto * centroPerto
                + medio * centroMedio
                + longe * centroLonge;

        double denominador = perto + medio + longe;

        if (denominador == 0) {
            return 0;
        }

        return numerador / denominador;
    }

    public static Builder builder() {
        return new Builder();
    }

    public double getValue() {
        return this.centro;
    }

    public static class Builder {
        private double perto;
        private double medio;
        private double longe;

        public Builder perto(double perto) {
            this.perto = validaGrau(perto);
            return this;
        }

        public Builder medio(double medio) {
            this.medio = validaGrau(medio);
            return this;
        }

        public Builder longe(double longe) {
            this.longe = validaGrau(longe);
            return this;
        }

        public double getCentroGravidade() {
            return build().calculaCentroDeGravidade();
        }

        public double calcular() {
            return getCentroGravidade();
        }

        public CalculaCentroDeGravidade build() {
            return new CalculaCentroDeGravidade(this);
        }

        private double validaGrau(double valor) {
            if (valor < 0 || valor > 1) {
                throw new IllegalArgumentException("O grau de pertinencia deve estar entre 0 e 1.");
            }
            return valor;
        }
    }
}
