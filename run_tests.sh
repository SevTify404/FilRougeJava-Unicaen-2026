#!/bin/bash
# Script pour lancer tous les tests du projet un par un

# Compilation préalable
echo "=== Compilation ==="
javac -d build -cp lib/gamestests.jar:src/ $(find src -name "*.java")
if [ $? -ne 0 ]; then
    echo "Erreur lors de la compilation. Arrêt du script."
    exit 1
fi
echo "Compilation réussie."
echo ""

# 1. GenericGameTest (AbstractGame, Nim, TicTacToe, TicTacToeWithHints)
echo "=== 1. GenericGameTest ==="
java -cp build:lib/gamestests.jar games.genericgames.GenericGameTest
echo ""

# 2. TestFactor (Nim et TicTacToe factorisés)
echo "=== 2. TestFactor ==="
java -cp build:lib/gamestests.jar games.factoredgames.TestFactor
echo ""

# 3. TestPlayers (Human et RandomPlayer)
echo "=== 3. TestPlayers ==="
java -cp build:lib/gamestests.jar games.players.TestPlayers
echo ""

# 4. TestPlays (Orchestrator)
echo "=== 4. TestPlays ==="
java -cp build:lib/gamestests.jar games.plays.TestPlays
echo ""

# 5. Nim de Base
echo "=== 5. Nim de Base ==="
java -cp build:lib/gamestests.jar games.nim.Main
echo ""

# 6. TicTacToe de Base
echo "=== 6. TicTacToe de Base ==="
java -cp build:lib/gamestests.jar games.tictactoe.Main
echo ""

# 7. TicTacToeWithHints de Base
echo "=== 7. TicTacToeWithHints de Base ==="
java -cp build:lib/gamestests.jar games.tictactoe.TestTicTacToeWithHints
echo ""

echo "=== Tous les tests ont été exécutés ==="
