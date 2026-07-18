package implementacaorobofuzzy.fuzzy;

import implementacaorobofuzzy.fuzzy.fuzzificacao.functions.FuncaoPertinencia;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Fuzzyficacao {

    private final Map<String, FuncaoPertinencia> funcoes = new LinkedHashMap<>();

    public void adicionar(String nome, FuncaoPertinencia funcao) {
        validarNome(nome);
        Objects.requireNonNull(funcao, "A funcao nao pode ser nula.");

        if (funcoes.putIfAbsent(nome, funcao) != null) {
            throw new IllegalArgumentException("Funcao duplicada: " + nome);
        }
    }

    public Map<String, Double> calcular(double entrada) {
        validarEntrada(entrada);
        Map<String, Double> resultados = new LinkedHashMap<>();

        for (Map.Entry<String, FuncaoPertinencia> item : funcoes.entrySet()) {
            resultados.put(item.getKey(), item.getValue().calcular(entrada));
        }

        return Collections.unmodifiableMap(resultados);
    }

    public Map<String, Double> obterCentroides() {
        Map<String, Double> centroides = new LinkedHashMap<>();

        for (Map.Entry<String, FuncaoPertinencia> item : funcoes.entrySet()) {
            centroides.put(item.getKey(), item.getValue().getCentroide());
        }

        return Collections.unmodifiableMap(centroides);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da funcao nao pode ser vazio.");
        }
    }

    private void validarEntrada(double entrada) {
        if (!Double.isFinite(entrada) || entrada < 0.0 || entrada > 1.0) {
            throw new IllegalArgumentException("A entrada deve estar entre 0 e 1.");
        }
    }
}
