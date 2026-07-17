package implementacaorobofuzzy.ambiente;

public class Memoria {
    public static Integer[][] sala;
    public String salaSt;
    public static int posPorta;
    public static int posicaoAtualX = 1;
    public static int posicaoAtualY = 1;

    public static int diferencaXp = 0;
    public static int diferencaXn = 0;
    public static int diferencaYp = 0;
    public static int diferencaYn = 0;
    public static int direcaoBuscaPorta = 1;

    public Memoria() {
        sala = CriaAmbiente.ambiente;
        salaSt = CriaAmbiente.amb;
        posPorta = CriaAmbiente.posicaoDaporta;
        posicaoAtualX = 1;
        posicaoAtualY = 1;
        direcaoBuscaPorta = 1;
    }
}
