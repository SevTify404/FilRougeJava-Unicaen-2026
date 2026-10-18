package games.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DemoTicTacToe {

    private static int recupererValeurEntierreAuClavier(Scanner unScanner) {
        int valeur = 0;
        boolean saisieValide = false;

        while (!saisieValide) {
            try {
                valeur = unScanner.nextInt();
                saisieValide = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur, il faut entrer un nombre entier valide.");
                unScanner.next();
            }
        }

        return valeur;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nom du joueur 1 : ");

        String nameJ1 = scanner.next();

        System.out.println("Nom du joueur 2 : ");

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
                ligneCoup = recupererValeurEntierreAuClavier(scanner);
                System.out.println("- Colonne ? ");
                colonneCoup = recupererValeurEntierreAuClavier(scanner);

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
            System.out.println("Pas de gagnant Match nul");
        }else{
            System.out.println("Gagnant : " + ticTacToeInstance.getWinner());
        }

        scanner.close();

    }
}
