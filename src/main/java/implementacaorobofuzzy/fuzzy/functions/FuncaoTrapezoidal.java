package implementacaorobofuzzy.fuzzy.functions;

/**
 * Funcao de pertinencia trapezoidal definida pelos pontos a, b, c e d.
 * Pontos iguais nas extremidades permitem criar ombros esquerdo e direito.
 */
public final class FuncaoTrapezoidal implements FuncaoPertinencia {

    private final double a;
    private final double b;
    private final double c;
    private final double d;

     public FuncaoTrapezoidal(double a, double b, double c, double d) {
        if (!Double.isFinite(a) || !Double.isFinite(b)
                || !Double.isFinite(c) || !Double.isFinite(d)
                || a > b || b > c || c > d || a == d) {
            throw new IllegalArgumentException(
                    "Os pontos devem ser finitos e obedecer a <= b <= c <= d, com a < d."
            );
        }

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }


    @Override
    public double calcular(double entrada) {
        if (!Double.isFinite(entrada) || entrada < a || entrada > d) {
            return 0.0;
        }
        if (entrada >= b && entrada <= c) {
            return 1.0;
        }
        if (entrada < b) {
            return (entrada - a) / (b - a);
        }
        return (d - entrada) / (d - c);
    }
    @Override
    public double getCentroide() {
        double areaTrianguloEsquerdo = (b - a) / 2.0;
        double areaRetangulo = c - b;
        double areaTrianguloDireito = (d - c) / 2.0;

        double momentoTrianguloEsquerdo =
                areaTrianguloEsquerdo * (a + 2.0 * b) / 3.0;
        double momentoRetangulo = areaRetangulo * (b + c) / 2.0;
        double momentoTrianguloDireito =
                areaTrianguloDireito * (2.0 * c + d) / 3.0;

        double areaTotal =
                areaTrianguloEsquerdo + areaRetangulo + areaTrianguloDireito;

        return (momentoTrianguloEsquerdo + momentoRetangulo + momentoTrianguloDireito)
                / areaTotal;
    }
}
