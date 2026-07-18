package implementacaorobofuzzy.fuzzy;

public class Defuzzyficacao {

    public double calcular(InferenciaFuzzy inferencia) {
        return CalculaCentroDeGravidade.builder()
                .perto(inferencia.getVelocidadeBaixa())
                .medio(inferencia.getVelocidadeMedia())
                .longe(inferencia.getVelocidadeAlta())
                .build()
                .getValue();
    }
}
