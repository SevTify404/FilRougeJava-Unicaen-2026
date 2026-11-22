package games.players;

import java.util.List;
import java.util.Scanner;

import games.genericgames.AbstractGame;
import games.genericgames.Game;
import games.genericgames.Nim;
import games.genericgames.TicTacToe;

public class Human implements Player{

    private final Scanner scannerInterne;
    private final String nom;

    public Human(String nom, Scanner scanner){
        this.nom = nom;
        this.scannerInterne = scanner;
    }

    private String stringCoupsValides(List<Integer> lesCoups){

        String resultats = "";
        for(int coupBase3: lesCoups){
            resultats += coupBase3 + ",";
        }

        return resultats;
    }

    public String getNom() {
        return nom;
    }

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
                int ligne = AbstractGame.recupererValeurEntierreAuClavier(scannerInterne);
                System.out.println("- Colonne ?");
                int colonne = AbstractGame.recupererValeurEntierreAuClavier(scannerInterne);
                coup = TicTacToe.positionVersEntierBase3(ligne, colonne);
            } else if (uneGame instanceof Nim) {
                System.out.println("Entrez le nombre d'allumettes à retirer :");

                coup = AbstractGame.recupererValeurEntierreAuClavier(scannerInterne);

            }else {
                System.out.println("Veuillez entrer votre coup (En base 8 si nécessaire)");
                coup = AbstractGame.recupererValeurEntierreAuClavier(scannerInterne);
            }

            if (uneGame.isValid(coup)) coupEstValide = true;
            else System.out.println("Coup invalide, reprenez");
        };

        return coup;
    }

    @Override
    public String toString() {
        return getNom();
    }
}
