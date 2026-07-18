package implementacaorobofuzzy.fuzzy.functions;

/** Funcao de pertinencia triangular definida pelos pontos a, b e c. */
public final class FuncaoTriangular implements FuncaoPertinencia {

    private final double a;
    private final double b;
    private final double c;

    public FuncaoTriangular(double a, double b, double c) {
        if (!Double.isFinite(a) || !Double.isFinite(b) || !Double.isFinite(c)
                || a > b || b > c || a == c) {
            throw new IllegalArgumentException(
                    "Os pontos devem ser finitos e obedecer a <= b <= c, com a < c."
            );
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calcular(double entrada) {
        if (!Double.isFinite(entrada) || entrada < a || entrada > c) {
            return 0.0;
        }
        if (entrada == b) {
            return 1.0;
        }
        if (entrada == a || entrada == c) {
            return 0.0;
        }
        if (entrada < b) {
            return (entrada - a) / (b - a);
        }
        return (c - entrada) / (c - b);
    }
    @Override
    public double getCentroide() {
        return (a + b + c) / 3.0;
    }
}
