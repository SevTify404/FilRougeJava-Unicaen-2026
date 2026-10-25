package games.tictactoe;

import java.util.ArrayList;

public class TicTacToeWithHints extends TicTacToe {

    /**
     * Instancie un nouvel objet de type TicTacToe representant une partie
     *
     * @param nomJoueurUn   Le nom du premier joueur
     * @param nomJoueurDeux Le nom du deuxieme joueur
     */
    public TicTacToeWithHints(String nomJoueurUn, String nomJoueurDeux) {
        super(nomJoueurUn, nomJoueurDeux);
    }


    private int positionVersEntierBase3(int ligne, int colonne) {
        // Formule i = 3r + c de labase 3
        return (3 * ligne) + colonne;
    }

    private int trouverColonneDepuisEntierBase3(int entierBase3) {
        return entierBase3 % 3;
    }

    private int trouverLigneDepuisEntierBase3(int entierBase3) {
        return entierBase3 / 3;
    }

    private ArrayList<Integer> trouverCasesVides() {

        ArrayList<Integer> listeCasesVides = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grille[i][j] == null) {
                    listeCasesVides.add(this.positionVersEntierBase3(i, j));
                }
            }
        }

        return listeCasesVides;
    }

    private void annulerCoup(int ligne, int colonne) {
        this.grille[ligne][colonne] = null;
    }

    private boolean verifierLigneCoupGagnant(int ligne) {
        return wins(this.joueurCourantEstJ1, ligne, 0, 0,1);
    }

    private boolean verifierColonneCoupGagnant(int colonne) {
        return wins(this.joueurCourantEstJ1, 0, colonne, 1, 0);
    }

    private boolean coupGagnant(int ligne, int colonne) {

        // Jouer le coup
        this.execute(ligne, colonne);

        this.changerJoueurActif();      // Pour équilibrer execut(), il change implicitement le joueur actif

        // Voir si il est gagnant
        boolean coupGagnant = verifierColonneCoupGagnant(colonne) ||
                verifierLigneCoupGagnant(ligne) ||
                verifierDiagonalesGagnantes(this.joueurCourantEstJ1);

//        boolean coupGagnant = getWinner() != null;


        // Annuler le coup
        this.annulerCoup(ligne, colonne);

        return coupGagnant;
    }

    public ArrayList<Integer> hints() {

        ArrayList<Integer> hints = new ArrayList<>();

        ArrayList<Integer> listeCasesVides = this.trouverCasesVides();

        // Petite optimisation, si on a plus de 6 cases vides c'est impossible d'avoir un coup gagnant
        // Autrement dit il est possible de gagner apres minimum 2 coups de chaque joueur
        if (listeCasesVides.size() > 6) {
            // On retourne le tableau vide
            return hints;
        }

        this.changerJoueurActif();      // Pour agir en tant que l'adversairz

        for (int caseBase3 : listeCasesVides) {
            boolean coupPotentiellementGagnant = this.coupGagnant(
                    this.trouverLigneDepuisEntierBase3(caseBase3),
                    this.trouverColonneDepuisEntierBase3(caseBase3)
            );

            if (coupPotentiellementGagnant) {
                hints.add(caseBase3);
            }

        }

        this.changerJoueurActif();      // Pour remettre en l'ordre

        return hints;
    }

    @Override
    protected String dessinerTableauSituation(String situationString) {
        ArrayList<Integer> hints = this.hints();
        for (int l = 0; l < 3; l++) {
            situationString += String.valueOf(l);
            situationString += SEPARATEUR;
            for (int c = 0; c < 3; c++) {
                String elementCourant = this.grille[l][c];
                // Un peu brutal mais bon....
                situationString += elementCourant == null ? hints.contains(this.positionVersEntierBase3(l, c)) ? "!" : "." : elementCourant;
                situationString += SEPARATEUR;
            }

            situationString += System.lineSeparator();
        }

        return situationString;
    }
}
