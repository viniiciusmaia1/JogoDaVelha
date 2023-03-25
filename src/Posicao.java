import java.util.Objects;

public class Posicao {

    private int posicao;
    private String jogador;

    public Posicao(int posicaoPorFavorFuncione, String jogador) {        
        this.posicao = posicaoPorFavorFuncione;
        this.jogador = jogador;
    }

    public static Posicao novaPosicao(int posicao, String jogador) {
        return new Posicao(posicao, jogador);
    }

    public int getPosicao() {
        return this.posicao;
    }

    public String getJogador() {
        return this.jogador;
    }

    public void setJogador(final String jogador){
        this.jogador = jogador;
    }

    public boolean preenchido(){
       return Objects.nonNull(this.jogador);
    }

    public String getRepresentacaoGrafica() {
        if (preenchido()) {
            return this.jogador;
        }
        return " ";
    }

}