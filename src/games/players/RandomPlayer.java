package games.players;

import java.util.List;
import java.util.Random;

import games.genericgames.Game;

/**
 * Implémentation d'un joueur aléatoire qui choisit ses coups au hasard
 * parmi les coups valides disponibles.
 */
public class RandomPlayer implements Player{

    private final Random tireurRandom;

    /**
     * Instancie un nouveau joueur aléatoire.
     * @param unRandom Le générateur de nombres aléatoires à utiliser
     */
    public RandomPlayer(Random unRandom){
        this.tireurRandom = unRandom;
    }

    /**
     * Choisit un coup aléatoirement parmi les coups valides disponibles.
     * @param unGame L'instance du jeu en cours
     * @return L'entier représentant le coup choisi aléatoirement
     */
    @Override
    public int chooseMove(Game unGame) {

        List<Integer> coupsValides = unGame.validMoves();

        int indexRandom = tireurRandom.nextInt(coupsValides.size());

        return coupsValides.get(indexRandom);
    }

    /**
     * Retourne la représentation textuelle du joueur aléatoire.
     * @return Une chaîne contenant le nom et le hash code du joueur
     */
    @Override
    public String toString() {
        return  "Joueur aléatoire n° " + this.hashCode();
    }
}
