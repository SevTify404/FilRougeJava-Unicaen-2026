package games.genericgames;

import games.players.Player;
import java.util.List;
import java.util.Scanner;

public interface Game {

    public Player getCurrentPlayer();

    public Player getWinner();

    public boolean isValid(int entierBase3);

    public void execute(int entierBase3);

    public List<Integer> validMoves();

    public String moveToString(int entierBase3);
    
    public String situationToString();

    public Game copy();

    // Petits ajouts pour simplifier le code
    public int recupererCoup(Scanner unScanner);

    public boolean isOver();
}



