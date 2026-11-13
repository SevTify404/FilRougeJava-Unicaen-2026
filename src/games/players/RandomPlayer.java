package games.players;

import java.util.List;
import java.util.Random;

import games.genericgames.Game;

public class RandomPlayer implements Player{

    private Random tireurRandom;

    public RandomPlayer(Random unRandom){
        this.tireurRandom = unRandom;
    }

    @Override
    public int chooseMove(Game unGame) {

        List<Integer> coupsValides = unGame.validMoves();
        
        System.out.println(coupsValides.size());
        int indexRandom = tireurRandom.nextInt(coupsValides.size());

        return coupsValides.indexOf(indexRandom);
    }

    @Override
    public String toString() {
        return  "Joueur aléatoire n° " + this.hashCode();
    }
}
