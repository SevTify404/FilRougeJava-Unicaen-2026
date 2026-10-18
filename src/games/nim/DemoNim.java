package games.nim;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DemoNim {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Nim instanceJeuNim = initialiserJeu(scanner);

        while (!instanceJeuNim.isOver()) {
            System.out.println(instanceJeuNim.situationToString());
            System.out.println("["+instanceJeuNim.getLibelleJoueur()+"] " + instanceJeuNim.getCurrentPlayer() + " C'est ton tour" );
            int coupValide = recupererCoupValide(instanceJeuNim, scanner);
            instanceJeuNim.removeMatches(coupValide);
        }

        System.out.println("Jeu terminé");
        System.out.println("Gagnant : " + instanceJeuNim.getWinner());
        scanner.close();
    }

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

    private static int recupererCoupValide(Nim instanceJeuNim,  Scanner unScanner) {

        // Initialisation à -1 pour rentrer dans la boucle au moins une fois

        int coup = -1;

        while (coup == -1) {
            System.out.println("Entre ton coup :");

            coup = recupererValeurEntierreAuClavier(unScanner);

            if (!instanceJeuNim.isValid(coup)) {
                System.out.println("Coup invalide, reprend");
                coup = -1;
            }
        }

        return coup;
    }

    private static Nim initialiserJeu(Scanner unScanner) {
        System.out.println("Nom du joueur 1 : ");
        String nameJ1 = unScanner.next();

        System.out.println("Nom du joueur 2 : ");
        String nameJ2 = unScanner.next();

        int k = recupererEntierPositif(unScanner, "le nombre maximal d'allumettes retirable en un coup");

        int n = recupererEntierPositif(unScanner, "le nombre initial d'allumettes dans le tas");

        return new Nim(n, k, nameJ1, nameJ2);
    }

    private static int recupererEntierPositif(Scanner unScanner, String message) {
        int valeur;
        do {
            System.out.println("Entre " + message + " :");
            valeur = recupererValeurEntierreAuClavier(unScanner);

            if (valeur <= 0) {
                System.out.println(message + " doit être strictement positif.");
            }
        } while (valeur <= 0);

        return valeur;

    }

}
