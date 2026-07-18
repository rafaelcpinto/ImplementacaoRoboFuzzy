package implementacaorobofuzzy.fuzzy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Centroide {

    private final List<Double> centros;

    public Centroide(List<Double> centros) {
        Objects.requireNonNull(centros, "A lista de centros nao pode ser nula.");
        if (centros.isEmpty()) {
            throw new IllegalArgumentException("A lista de centros nao pode ser vazia.");
        }

        List<Double> copia = new ArrayList<>();
        for (Double centro : centros) {
            copia.add(validarValor(centro, "centro"));
        }
        this.centros = Collections.unmodifiableList(copia);
    }

    public double calcular(List<Double> valores) {
        Objects.requireNonNull(valores, "A lista de valores nao pode ser nula.");
        if (valores.size() != centros.size()) {
            throw new IllegalArgumentException(
                    "A quantidade de valores deve ser igual a quantidade de centros."
            );
        }

        double numerador = 0.0;
        double denominador = 0.0;

        for (int i = 0; i < valores.size(); i++) {
            double valor = validarValor(valores.get(i), "valor");
            numerador += valor * centros.get(i);
            denominador += valor;
        }

        return denominador == 0.0 ? 0.0 : numerador / denominador;
    }

    private double validarValor(Double valor, String tipo) {
        if (valor == null || !Double.isFinite(valor) || valor < 0.0 || valor > 1.0) {
            throw new IllegalArgumentException(
                    "O " + tipo + " deve estar entre 0 e 1."
            );
        }
        return valor;
    }
}
