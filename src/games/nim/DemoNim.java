package games.nim;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DemoNim {

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

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nom du j1 : ");

        String nameJ1 = scanner.next();

        System.out.println("Nom du j2 : ");

        String nameJ2 = scanner.next();

        System.out.println("Entre la vaeleur de k poto : ");
        int k = recupererValeurEntierreAuClavier(scanner);

        System.out.println("Entre la vaeleur de n poto : ");
        int n = recupererValeurEntierreAuClavier(scanner);

        Nim instanceNim = new Nim(n, k, nameJ1, nameJ2);

        while (!instanceNim.isOver()) {
            System.out.println("Situation actuelle : ");
            System.out.println(instanceNim.situationToString());
            System.out.println("Tour de " + instanceNim.getCurrentPlayer() + " : ");
            boolean coupValide = false;
            int coup = 0;
            while (!coupValide) {
                System.out.println("Entre ton coup :");
                coup = recupererValeurEntierreAuClavier(scanner);
                if (instanceNim.isValid(coup)) {
                    coupValide = true;
                } else {
                    System.out.println("Coup invalide, reprend");
                }
            }
            instanceNim.removeMatches(coup);
        }

        System.out.println("Jeu terminé");
        System.out.println("Gagnant : " + instanceNim.getWinner());
        scanner.close();

    }

}
