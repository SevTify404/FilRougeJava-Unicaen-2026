package games.nim;

import games.utils.Utils;

import java.util.Scanner;

/**
 * Classe de démonstration pour le jeu de Nim.
 * Permet de lancer une partie interactive en ligne de commande
 * où deux joueurs retirent des allumettes tour à tour.
 */
public class DemoNim {

    /**
     * Point d'entrée de la démonstration du jeu de Nim.
     * Initialise une partie, affiche la situation du jeu à chaque tour,
     * récupère les coups des joueurs et annonce le gagnant à la fin.
     * @param args Les arguments en ligne de commande (non utilisés)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Nim instanceJeuNim = initialiserJeu(scanner);

        while (!instanceJeuNim.isOver()) {
            System.out.println(instanceJeuNim.situationToString());
            System.out.println("["+instanceJeuNim.getLibelleJoueurCourant()+"] " + instanceJeuNim.getCurrentPlayer() + " C'est ton tour" );
            int coupValide = recupererCoupValide(instanceJeuNim, scanner);
            instanceJeuNim.removeMatches(coupValide);
        }

        System.out.println("Jeu terminé");
        System.out.println("Gagnant : " + instanceJeuNim.getWinner());
        scanner.close();
    }


    /**
     * Récupère un coup valide pour le jeu de Nim en demandant à l'utilisateur.
     * Redemande le coup tant qu'il n'est pas valide selon les règles du jeu.
     * @param instanceJeuNim L'instance du jeu en cours pour vérifier la validité du coup
     * @param unScanner Le Scanner utilisé pour lire l'entrée standard
     * @return Un coup valide sous forme d'entier positif
     */
    private static int recupererCoupValide(Nim instanceJeuNim,  Scanner unScanner) {

        // Initialisation à -1 pour rentrer dans la boucle au moins une fois

        int coup = -1;

        while (coup == -1) {
            System.out.println("Entre ton coup :");

            coup = Utils.recupererValeurEntierreAuClavier(unScanner);

            if (!instanceJeuNim.isValid(coup)) {
                System.out.println("Coup invalide, reprend");
                coup = -1;
            }
        }

        return coup;
    }

    /**
     * Initialise une nouvelle partie de Nim en demandant les paramètres à l'utilisateur.
     * Récupère les noms des deux joueurs ainsi que les paramètres du jeu.
     * @param unScanner Le Scanner utilisé pour lire l'entrée standard
     * @return Une nouvelle instance de Nim configurée avec les paramètres saisis
     */
    private static Nim initialiserJeu(Scanner unScanner) {
        System.out.println("Nom du joueur 1 : ");
        String nameJ1 = unScanner.next();

        System.out.println("Nom du joueur 2 : ");
        String nameJ2 = unScanner.next();

        while (true) {
            int k = Utils.recupererEntierPositif(unScanner, "le nombre maximal d'allumettes retirable en un coup");

            int n = Utils.recupererEntierPositif(unScanner, "le nombre initial d'allumettes dans le tas");

            if (k > n) {
                System.out.println("Le nombre maximal d'allumettes retirable en un coup ne peut pas être supérieur au nombre initial d'allumettes dans le tas, Reprenez.");
            } else {
                return new Nim(n, k, nameJ1, nameJ2);
            }
        }

    }



}
