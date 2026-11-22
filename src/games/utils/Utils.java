package games.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/// Classe utilitaire pour des fonctions générales qui ne dépendent pas forcément des jeux
public class Utils {


    /**
     * Récupère une valeur entière saisie au clavier en gérant les erreurs de saisie.
     * Redemande la saisie tant que l'utilisateur n'entre pas un nombre entier valide.
     * @param unScanner Le Scanner utilisé pour lire l'entrée standard
     * @return La valeur entière saisie par l'utilisateur
     */
    public static int recupererValeurEntierreAuClavier(Scanner unScanner) {
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
     * Récupère un entier strictement positif en demandant à l'utilisateur.
     * Redemande la saisie tant que la valeur n'est pas strictement positive.
     * @param unScanner Le Scanner utilisé pour lire l'entrée standard
     * @param message Le message affiché à l'utilisateur pour décrire la valeur attendue
     * @return Une valeur entière strictement positive
     */
    public static int recupererEntierPositif(Scanner unScanner, String message) {
        int valeur;
        do {
            System.out.println("Entrez " + message + " :");
            valeur = Utils.recupererValeurEntierreAuClavier(unScanner);

            if (valeur <= 0) {
                System.out.println(message + " doit être strictement positif.");
            }
        } while (valeur <= 0);

        return valeur;

    }

    /**
     * Convertit les coordonnées d'une case (ligne, colonne) en un entier en base 3.
     * La formule utilisée est : entier = 3 × ligne + colonne.
     * @param ligne Le numéro de la ligne (0-2)
     * @param colonne Le numéro de la colonne (0-2)
     * @return L'entier en base 3 correspondant à la position (0-8)
     */
    public static int positionVersEntierBase3(int ligne, int colonne) {
        // Formule i = 3r + c de labase 3
        return (3 * ligne) + colonne;
    }

    /**
     * Récupère le numéro de ligne à partir d'un entier en base 3.
     * @param entierBase3 L'entier en base 3 (0-8)
     * @return Le numéro de ligne (0-2)
     */
    public static int trouverLigneDepuisEntierBase3(int entierBase3) {
        return entierBase3 / 3;
    }

    /**
     * Récupère le numéro de colonne à partir d'un entier en base 3.
     * @param entierBase3 L'entier en base 3 (0-8)
     * @return Le numéro de colonne (0-2)
     */
    public static int trouverColonneDepuisEntierBase3(int entierBase3) {
        return entierBase3 % 3;
    }
}
