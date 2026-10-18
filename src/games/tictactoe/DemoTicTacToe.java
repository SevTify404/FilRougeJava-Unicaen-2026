package games.tictactoe;

import java.util.Scanner;

public class DemoTicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nom du j1 : ");

        String nameJ1 = scanner.next();

        System.out.println("Nom du j2 : ");

        String nameJ2 = scanner.next();


        TicTacToe ticTacToeInstance = new TicTacToe(nameJ1, nameJ2);

        while (!ticTacToeInstance.isOver()) {
            System.out.println(ticTacToeInstance.situationToString());
            System.out.println("C'est à "+ ticTacToeInstance.getCurrentPlayer() + " de jouer");

            boolean coupValide = false;

            // -1 pour que çà passe pas le isValid on sait jamais
            int ligneCoup = -1;
            int colonneCoup =-1;

            while (!coupValide) {
                System.out.println("Votre coup : ");
                System.out.println("- Rangée ? ");
                ligneCoup = Integer.parseInt(scanner.next());
                System.out.println("- Colonne ? ");
                colonneCoup = Integer.parseInt(scanner.next());

                if (ticTacToeInstance.isValid(ligneCoup, colonneCoup)) {
                    coupValide = true;
                } else {
                    System.out.println("Coup invalide, reprend");
                }
            }

            ticTacToeInstance.execute(ligneCoup, colonneCoup);
        }

        System.out.println("Jeu terminé");
        System.out.println("Grille finale : ");
        System.out.println(ticTacToeInstance.situationToString());
        String gagnant = ticTacToeInstance.getWinner();
        if (gagnant == null) {
            System.out.println("Pas de gagnanyt Match nullll");
        }else{
            System.out.println("Gagnant : " + ticTacToeInstance.getWinner());
            
        }

        scanner.close();

    }
}
