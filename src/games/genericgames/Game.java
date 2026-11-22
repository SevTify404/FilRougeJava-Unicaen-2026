package games.genericgames;

import games.players.Player;
import java.util.List;

public interface Game {

    Player getCurrentPlayer();

    Player getWinner();

    boolean isValid(int entierBase3);

    void execute(int entierBase3);

    List<Integer> validMoves();

    String moveToString(int entierBase3);
    
    String situationToString();

    Game copy();

    boolean isOver();
}



