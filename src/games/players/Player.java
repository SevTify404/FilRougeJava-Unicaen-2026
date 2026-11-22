package games.players;

import games.genericgames.Game;

/**
 * Interface représentant un joueur dans un jeu.
 * Toute implémentation doit fournir une stratégie pour choisir un coup.
 */
public interface Player {
    /**
     * Choisit un coup à jouer en fonction de l'état actuel du jeu.
     * @param unGame L'instance du jeu en cours, fournissant l'état actuel et les coups valides
     * @return L'entier représentant le coup choisi
     */
    int chooseMove(Game unGame);
}
