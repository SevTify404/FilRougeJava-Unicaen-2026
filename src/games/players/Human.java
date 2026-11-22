package games.players;

import java.util.List;
import java.util.Scanner;

import games.genericgames.Game;
import games.genericgames.Nim;
import games.genericgames.TicTacToe;
import games.utils.Utils;

/**
 * Implémentation d'un joueur humain qui choisit ses coups via l'entrée clavier.
 * Le joueur lit la situation du jeu et saisit manuellement son coup.
 */
public class Human implements Player{

    private final Scanner scannerInterne;
    private final String nom;

    /**
     * Instancie un nouveau joueur humain.
     * @param nom Le nom du joueur
     * @param scanner Le Scanner utilisé pour lire les entrées clavier
     */
    public Human(String nom, Scanner scanner){
        this.nom = nom;
        this.scannerInterne = scanner;
    }

    /**
     * Convertit la liste des coups valides en une chaîne de caractères lisible.
     * @param lesCoups La liste des coups valides
     * @return Une chaîne contenant les coups séparés par des virgules
     */
    private String stringCoupsValides(List<Integer> lesCoups){

        String resultats = "";
        for(int coupBase3: lesCoups){
            resultats += coupBase3 + ",";
        }

        return resultats;
    }

    /**
     * Retourne le nom du joueur.
     * @return Le nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Choisit un coup en demandant au joueur humain de saisir son choix au clavier.
     * L'interface d'entrée s'adapte au type de jeu (Morpion ou Nim).
     * Le coup est revérifié jusqu'à ce qu'il soit valide.
     * @param uneGame L'instance du jeu en cours
     * @return L'entier représentant le coup choisi par le joueur
     */
    @Override 
    public int chooseMove(Game uneGame){

        // TODO : Faire en sorte que ce trucs ai un bon affichage par rapport au jeu
        System.out.println("Liste des coups valides : " + this.stringCoupsValides(uneGame.validMoves()));
        
        int coup = 0;
        boolean coupEstValide = false;


        while (!coupEstValide) {
            if (uneGame instanceof TicTacToe){
                System.out.println("Votre coup :");
                System.out.println("- Rangée ?");
                int ligne = Utils.recupererValeurEntierreAuClavier(scannerInterne);
                System.out.println("- Colonne ?");
                int colonne = Utils.recupererValeurEntierreAuClavier(scannerInterne);
                coup = Utils.positionVersEntierBase3(ligne, colonne);
            } else if (uneGame instanceof Nim) {
                System.out.println("Entrez le nombre d'allumettes à retirer :");

                coup = Utils.recupererValeurEntierreAuClavier(scannerInterne);

            }else {
                System.out.println("Veuillez entrer votre coup (En base 8 si nécessaire)");
                coup = Utils.recupererValeurEntierreAuClavier(scannerInterne);
            }

            if (uneGame.isValid(coup)) coupEstValide = true;
            else System.out.println("Coup invalide, reprenez");
        }

        return coup;
    }

    /**
     * Retourne la représentation textuelle du joueur (son nom).
     * @return Le nom du joueur
     */
    @Override
    public String toString() {
        return getNom();
    }
}
