package games.plays;

import games.genericgames.Game;
import games.players.Player;

/**
 * Orchestrateur de partie qui gère le déroulement d'un jeu entre deux joueurs.
 * Il affiche la situation du jeu, demande les coups aux joueurs et annonce
 * le résultat final (victoire ou match nul).
 */
public class Orchestrator {
    /** Le jeu en cours, géré par cet orchestrateur. */
    Game internalGame;

    /**
     * Instancie un nouvel orchestrateur pour le jeu donné.
     * @param uneGame Le jeu à orchestrer
     */
    public Orchestrator(Game uneGame){
        this.internalGame = uneGame;
    }

    /**
     * Lance et gère une partie complète jusqu'à la fin du jeu.
     * À chaque tour, la situation est affichée, le joueur courant choisit son coup,
     * et le coup est exécuté. À la fin, le gagnant ou le match nul est annoncé.
     */
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
            System.out.println("Gagnant : " + gagnant);
        }
    }
}
