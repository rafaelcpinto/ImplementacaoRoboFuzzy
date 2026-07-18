package implementacaorobofuzzy.fuzzy.dominio;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataIn {

    private final Data perto = new Data("perto");
    private final Data medio = new Data("medio");
    private final Data longe = new Data("longe");

    public Data getPerto() {
        return perto;
    }

    public Data getMedio() {
        return medio;
    }

    public Data getLonge() {
        return longe;
    }

    public List<Data> getDados() {
        return Collections.unmodifiableList(Arrays.asList(perto, medio, longe));
    }
}
