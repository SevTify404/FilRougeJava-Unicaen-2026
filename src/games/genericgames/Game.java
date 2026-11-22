package games.genericgames;

import games.players.Player;
import java.util.List;

/**
 * Interface représentant un jeu abstrait jouable par deux joueurs.
 * Toute implémentation de cette interface doit fournir les méthodes
 * nécessaires pour gérer les coups, les joueurs et l'état du jeu.
 */
public interface Game {

    /**
     * Retourne le joueur dont c'est le tour de jouer.
     * @return Le joueur courant
     */
    Player getCurrentPlayer();

    /**
     * Retourne le joueur gagnant si le jeu est terminé.
     * @return Le joueur gagnant, ou null si le jeu n'est pas terminé ou s'il y a match nul
     */
    Player getWinner();

    /**
     * Vérifie si un coup est valide par rapport à l'état actuel du jeu.
     * @param entierBase3 La représentation du coup (en base 3 pour Morpion, nombre d'allumettes pour Nim)
     * @return True si le coup est valide, false sinon
     */
    boolean isValid(int entierBase3);

    /**
     * Exécute un coup sur le jeu.
     * Le coup doit être valide (vérifié par isValid) avant d'être exécuté.
     * @param entierBase3 La représentation du coup à exécuter
     */
    void execute(int entierBase3);

    /**
     * Retourne la liste des coups valides possibles dans l'état actuel du jeu.
     * @return Une liste d'entiers représentant les coups valides
     */
    List<Integer> validMoves();

    /**
     * Convertit un coup en une représentation textuelle lisible.
     * @param entierBase3 La représentation du coup
     * @return Une chaîne de caractères décrivant le coup
     */
    String moveToString(int entierBase3);

    /**
     * Retourne une représentation textuelle de la situation actuelle du jeu
     * (état du plateau, joueur courant, etc.).
     * @return Une chaîne de caractères décrivant la situation du jeu
     */
    String situationToString();

    /**
     * Crée une copie profonde de l'état actuel du jeu.
     * @return Une nouvelle instance de Game avec le même état que celui-ci
     */
    Game copy();

    /**
     * Vérifie si le jeu est terminé (victoire ou match nul).
     * @return True si le jeu est terminé, false sinon
     */
    boolean isOver();
}



