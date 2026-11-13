package games.plays;

import games.genericgames.Game;
import games.players.Player;

public class Orchestrator {
    Game internalGame;

    public Orchestrator(Game uneGame){
        this.internalGame = uneGame;
    }

    public void play(){
        while (!internalGame.isOver()) {
            Player joueurActuel = internalGame.getCurrentPlayer();
            System.out.println("C'est à " + joueurActuel.toString() + " de jouer");
            System.out.println(internalGame.situationToString());
            
            int coup = joueurActuel.chooseMove(internalGame);

            internalGame.execute(coup);
            
            System.out.println("Coup joué : " + internalGame.moveToString(coup));
        }

        System.out.println("Jeu terminé");
        Player gagnant = internalGame.getWinner();
        if (gagnant == null) {
            System.out.println("Pas de gagnant, match nul");
        } else {
            System.out.println("Gagnant : " + gagnant.toString());
        }
    }
}
