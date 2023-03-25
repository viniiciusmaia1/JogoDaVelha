import java.util.Scanner;

public class JogoDaVelha {
    public static void main(String[] args) {

        boolean terminou = false;
        String jogador = "";
        int cont = 0;
        Scanner position = new Scanner(System.in);
        boolean foiValido = true;

        Tabuleiro tabuleiro = Tabuleiro.criarNovo();

        tabuleiro.desenhaTabuleiro();
        
        while (cont < 9 && terminou == false) {

            do {
           
                if (cont % 2 == 0) {
                    jogador = Tabuleiro.XIS;
                    System.out.println("Jogador 1 (X), sua vez");
                    System.out.println("");

                    } else {
                    jogador = Tabuleiro.BOLA;
                    System.out.println("Jogador 2 (O), sua vez");
                    System.out.println("");
                }
                
                int numero = position.nextInt();

                while (numero > 9 || numero <= 0) {
                    
                    System.out.println("");
                    System.out.println("Posição inválida, jogue novamente!");
                    System.out.println("");

                    numero = position.nextInt(); 
                    
                }

                Posicao p = new Posicao(numero, jogador);
                foiValido = tabuleiro.marca(p);

                    if (!foiValido) {
                        System.out.println("");
                        System.out.println("Posição inválida, jogue novamente!");
                        System.out.println("");
                    }
                    
                
                } while (!foiValido);
                
            cont++;

            tabuleiro.desenhaTabuleiro();

            if(cont >= 4) {
                terminou = tabuleiro.validaGameOver();
            }

            if(terminou) {
                System.out.println("Parabéns! O jogador " + jogador + " venceu a partida!");
            }
        } 
        
    }
}