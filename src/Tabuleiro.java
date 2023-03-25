public class Tabuleiro {

    public Posicao [][] tabuleiro = new Posicao[3][3];

    public static final String XIS = "X";
    
    public static final String BOLA = "O";

    private Tabuleiro() {
        inicializa();
    }

    private void inicializa() {

        this.tabuleiro = new Posicao[][] {
                {
                    new Posicao(1, null),
                    Posicao.novaPosicao(2,null),
                    Posicao.novaPosicao(3,null)

                },
                {
                    Posicao.novaPosicao(4,null),
                    Posicao.novaPosicao(5,null),
                    Posicao.novaPosicao(6,null)
                },
                {
                    Posicao.novaPosicao(7,null),
                    Posicao.novaPosicao(8,null),
                    Posicao.novaPosicao(9,null)
                }
        };
    }

    public static Tabuleiro criarNovo() {
        return new Tabuleiro();
    }

    public void desenhaTabuleiro() {

        String tabuleiroStr = "";
        tabuleiroStr += "\n 1 | 2 | 3 \n";
        tabuleiroStr += " -   -   - \n";
        tabuleiroStr += " 4 | 5 | 6 \n";
        tabuleiroStr += " -   -   - \n";
        tabuleiroStr += " 7 | 8 | 9 \n";
        
        for(int i = 0; i < tabuleiro.length; i++) {
            for(int j = 0; j < tabuleiro[i].length; j++) {

                Posicao posicao = this.tabuleiro[i][j];
                if (posicao.getJogador() != null) {
                    tabuleiroStr = tabuleiroStr.replace(posicao.getPosicao() + "", posicao.getRepresentacaoGrafica());                
                }
            }
                
        }

        System.out.println(tabuleiroStr);
        
    }

    public Boolean resultado() {
            return false;
    }

    public boolean marca(Posicao p) {
        boolean valid = false;
        for(int i = 0; i < tabuleiro.length; i++) {
            for(int j = 0; j < tabuleiro[i].length; j++) {
                    
              if(tabuleiro[i][j].getPosicao() == p.getPosicao() && !tabuleiro[i][j].preenchido()) {
                  
                  tabuleiro[i][j].setJogador(p.getJogador());
                  
                  valid = true;
              }        
                
            }
                
        }
        return valid;
    }

    public boolean validaGameOver() {

        boolean ehGameOver = false;

        if(tabuleiro[1][1].preenchido()) {
            if (tabuleiro[0][0].getJogador() == tabuleiro[1][1].getJogador() && tabuleiro[1][1].getJogador() == tabuleiro[2][2].getJogador()) {
                ehGameOver = true;
            } 
            if (tabuleiro[0][2].getJogador() == tabuleiro[1][1].getJogador() && tabuleiro[1][1].getJogador() == tabuleiro[2][0].getJogador()) {
                ehGameOver = true;
            }
        }

        for (int linha = 0; linha < tabuleiro.length && !ehGameOver; linha++) {
            final var linhaAtual = tabuleiro[linha];

            Posicao anterior = null;
            for (int coluna = 0; coluna < linhaAtual.length; coluna++) {
                final var posicaoAtual = linhaAtual[coluna];
                if (anterior == null) {
                    anterior = posicaoAtual;
                    continue;
                }

                if (posicaoAtual.preenchido() &&
                anterior.preenchido() && 
                posicaoAtual.getJogador().equalsIgnoreCase(anterior.getJogador())) {
                    if (coluna + 1 == linhaAtual.length) {
                        ehGameOver = true;
                        break;
                    }
                    anterior = posicaoAtual;
                } else {
                    break;
                }
            }               
        }

        for (int coluna = 0; coluna < tabuleiro.length && !ehGameOver; coluna++) {
         final var linhaAtual = tabuleiro[coluna];

         final var cel1 = tabuleiro[0][coluna];
         final var cel2 = tabuleiro[1][coluna];
         final var cel3 = tabuleiro[2][coluna];

         boolean todosPreenchidos = cel1.preenchido() && cel2.preenchido() && cel3.preenchido();

            if (!todosPreenchidos) {
                continue;
            }

            if (cel1.getJogador().equalsIgnoreCase(cel2.getJogador()) && cel2.getJogador().equalsIgnoreCase(cel3.getJogador())) {
                ehGameOver = true;
                break;
            }
        }

        return ehGameOver;
    }

}

/*
Premissas do jogo

O jogo limita-se a 9 posições, uma matriz 3 x 3
Existem apenas 2 jogadores, com a sequência de 1 jogada por vez.
A jogada é realizada com a escolha da posição disponível.
A escolha de posição pode ser feita apenas com as disponíveis, caso contrário deve-se repetir a jogada.
Cada posição é identificada por um número único.
A matriz é apresentada no início do jogo e após cada jogada até o final do jogo.
Vence o jogo quem atingir uma sequência de 3 posições horizontais, verticais ou diagonais.
O jogo encerra quando não há mais posições disponíveis e não há sequências que estabelecem um vencedor.
A cada rodada deve-se validar os critérios de encerramento ou de vitória.
**/
