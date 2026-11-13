package games.players;

import java.util.List;
import java.util.Scanner;

import games.genericgames.Game;

public class Human implements Player{

    private Scanner scannerInterne;
    private String nom;

    public Human(String nom, Scanner scanner){
        this.nom = nom;
        this.scannerInterne = scanner;
    }

    private String stringCoupsValides(List<Integer> lesCoups){

        String resultats = "";
        for(int coupBase3: lesCoups){
            resultats += coupBase3;
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
            coup = uneGame.recupererCoup(scannerInterne);

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
