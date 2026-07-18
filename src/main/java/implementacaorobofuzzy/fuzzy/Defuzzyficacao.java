package implementacaorobofuzzy.fuzzy;

import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoPertinencia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Defuzzyficacao {

    private final Map<String, FuncaoPertinencia> funcoes = new LinkedHashMap<>();

    public void adicionar(String nome, FuncaoPertinencia funcao) {
        validarNome(nome);
        Objects.requireNonNull(funcao, "A funcao nao pode ser nula.");

        if (funcoes.putIfAbsent(nome, funcao) != null) {
            throw new IllegalArgumentException("Funcao duplicada: " + nome);
        }
    }

    public double calcular(List<Double> valores) {
        Objects.requireNonNull(valores, "A lista de valores nao pode ser nula.");

        List<Double> centros = new ArrayList<>();
        for (FuncaoPertinencia funcao : funcoes.values()) {
            centros.add(funcao.getCentroide());
        }

        return new Centroide(centros).calcular(valores);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da funcao nao pode ser vazio.");
        }
    }
}
