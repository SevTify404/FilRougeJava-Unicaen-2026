package games.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe de démonstration pour le jeu de Morpion (Tic Tac Toe).
 * Permet de lancer une partie interactive en ligne de commande
 * où deux joueurs placent tour à tour leur symbole sur une grille 3x3.
 */
public class DemoTicTacToe {

    /**
     * Point d'entrée de la démonstration du jeu de Morpion.
     * Initialise une partie, affiche la grille à chaque tour,
     * récupère les coups des joueurs et annonce le gagnant à la fin.
     * @param args Les arguments en ligne de commande (non utilisés)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TicTacToe ticTacToeInstance = initialiserJeu(scanner);

        while (!ticTacToeInstance.isOver()) {
            System.out.println(ticTacToeInstance.situationToString());

            System.out.println("["+ticTacToeInstance.getLibelleJoueurCourant()+"] "+"C'est à " + ticTacToeInstance.getCurrentPlayer() + " de jouer");
            int[] coupValide = recupererCoupValide(ticTacToeInstance, scanner);
            ticTacToeInstance.execute(coupValide[0], coupValide[1]);
        }

        System.out.println("Jeu terminé");
        System.out.println("Grille finale : ");
        System.out.println(ticTacToeInstance.situationToString());
        String gagnant = ticTacToeInstance.getWinner();
        if (gagnant == null) {
            System.out.println("Pas de gagnant, match nul");
        } else {
            System.out.println("Gagnant : " + gagnant);
        }
        scanner.close();
    }

    /**
     * Récupère une valeur entière saisie au clavier en gérant les erreurs de saisie.
     * Redemande la saisie tant que l'utilisateur n'entre pas un nombre entier valide.
     * @param unScanner Le Scanner utilisé pour lire l'entrée standard
     * @return La valeur entière saisie par l'utilisateur
     */
    private static int recupererValeurEntierreAuClavier(Scanner unScanner) {
        int valeur = 0;
        boolean saisieValide = false;

        while (!saisieValide) {
            try {
                valeur = unScanner.nextInt();
                saisieValide = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur, il faut entrer un nombre entier valide.");
                unScanner.next(); // Consomme et ignore l'entrée incorrecte pour éviter une boucle infinie
            }
        }

        return valeur;
    }

    /**
     * Récupère un coup valide pour le jeu de Morpion en demandant à l'utilisateur.
     * Redemande le coup tant qu'il n'est pas valide selon les règles du jeu.
     * @param ticTacToeInstance L'instance du jeu en cours pour vérifier la validité du coup
     * @param unScanner Le Scanner utilisé pour lire l'entrée standard
     * @return Un tableau de deux entiers représentant la ligne et la colonne du coup valide
     */
    private static int[] recupererCoupValide(TicTacToe ticTacToeInstance, Scanner unScanner) {

        int ligne = -1;
        int colonne = -1;
        boolean coupValide = false;

        while (!coupValide) {
            System.out.println("Votre coup :");
            System.out.println("- Rangée ?");
            ligne = recupererValeurEntierreAuClavier(unScanner);
            System.out.println("- Colonne ?");
            colonne = recupererValeurEntierreAuClavier(unScanner);

            if (ticTacToeInstance.isValid(ligne, colonne)) {
                coupValide = true;
            } else {
                System.out.println("Coup invalide, reprend");
            }
        }

        return new int[]{ligne, colonne};
    }

    /**
     * Initialise une nouvelle partie de Morpion en demandant les paramètres à l'utilisateur.
     * Récupère les noms des deux joueurs et demande s'ils souhaitent jouer avec des indices.
     * Retourne une instance de TicTacToeWithHints si les indices sont activés, sinon une instance de TicTacToe classique.
     * @param unScanner Le Scanner utilisé pour lire l'entrée standard
     * @return Une nouvelle instance de TicTacToe configurée avec les paramètres saisis
     */
    private static TicTacToe initialiserJeu(Scanner unScanner) {

        TicTacToe game;
        System.out.println("Nom du joueur 1 : ");
        String nameJ1 = unScanner.next();

        System.out.println("Nom du joueur 2 : ");
        String nameJ2 = unScanner.next();

        System.out.println("Voulez vous jouer avec des indices ? oui ou non : ?");

        String answer = unScanner.next();
        if (answer.equals("oui")) {
            System.out.println("hints");
            game = new TicTacToeWithHints(nameJ1, nameJ2);
        } else {
            game = new TicTacToe(nameJ1, nameJ2);
        }

        return game;
    }

}
