package implementacaorobofuzzy.fuzzy.dominio;

import java.util.Arrays;
import java.util.List;

public class DataOut {

    private final Data lento = new Data("lento");
    private final Data velocidadeBaixa = new Data("velocidadeBaixa");
    private final Data velocidadeMedia = new Data("velocidadeMedia");
    private final Data rapido = new Data("rapido");

    public Data getLento() {
        return lento;
    }

    public Data getRapido() {
        return rapido;
    }

    public Data getVelocidadeBaixa() {
        return velocidadeBaixa;
    }

    public Data getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public List<Double> getValores() {
        return Arrays.asList(
                lento.getValor(),
                velocidadeBaixa.getValor(),
                velocidadeMedia.getValor(),
                rapido.getValor()
        );
    }

    public List<Double> getCentros() {
        return Arrays.asList(
                lento.getCentro(),
                velocidadeBaixa.getCentro(),
                velocidadeMedia.getCentro(),
                rapido.getCentro()
        );
    }
}
