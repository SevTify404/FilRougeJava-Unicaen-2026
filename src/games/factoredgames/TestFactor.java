package games.factoredgames;

import gamestests.factoredgames.NimTests;
import gamestests.factoredgames.TicTacToeTests;


public class TestFactor {
    public static void main(String[] args) {

        boolean ok = true;
        NimTests nimTester = new NimTests();
        ok = ok && nimTester.testExtends();
        ok = ok && nimTester.testGetCurrentPlayer();
        ok = ok && nimTester.testExecute();
        ok = ok && nimTester.testIsValid();
        ok = ok && nimTester.testIsOver();
        ok = ok && nimTester.testGetWinner();
        TicTacToeTests ticTacToeTester = new TicTacToeTests();
        ok = ok && ticTacToeTester.testExtends();
        ok = ok && ticTacToeTester.testGetCurrentPlayer();
        ok = ok && ticTacToeTester.testExecuteAndIsValid();
        ok = ok && ticTacToeTester.testGetWinner();
        ok = ok && ticTacToeTester.testIsOver();
        System.out.println(ok ? "All tests OK" : "At least one test KO");
    }
}
