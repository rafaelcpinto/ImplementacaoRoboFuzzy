package implementacaorobofuzzy.ambiente;

public class Posicao {
    private int x;
    private int y;

    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }
    void addDesloc(int dx, int dy) {
        x=x+dx;
        y=y+dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
