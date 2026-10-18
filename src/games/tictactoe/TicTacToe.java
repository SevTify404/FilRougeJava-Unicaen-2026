package games.tictactoe;

public class TicTacToe {

    private String[][] grille = null;
    private String nomJoueurUn;
    private String nomJoueurDeux;
    private boolean joueurCourantEstJ1; // True si le coup suivant est celui du joueur 1, false sinon

    public TicTacToe(String nomJoueurUn, String nomJoueurDeux) {
        this.nomJoueurDeux = nomJoueurDeux;
        this.nomJoueurUn = nomJoueurUn;
        this.joueurCourantEstJ1 = true;
        this.grille = new String[3][3];
    }

    private void changerJoueurActif() {
        this.joueurCourantEstJ1 = !this.joueurCourantEstJ1;
    }

    public String getCurrentPlayer() {
        return joueurCourantEstJ1 ? nomJoueurUn : nomJoueurDeux;
    }

    /*
    public void test() {
        System.out.println(situationToString());
    }
    */

    public void execute(int ligne, int colonne) {
        //X pour le joueur 1 et O pour le joueur 2
        this.grille[ligne][colonne] = this.bonCaractere(joueurCourantEstJ1);
        this.changerJoueurActif();
    }

    public boolean isValid(int ligne, int colonne) {
        if (ligne >= 3 || ligne < 0) {
            return false;
        }
        if (colonne >= 3 || colonne < 0) {
            return false;
        }

        return this.grille[ligne][colonne] == null;
    }

    private String bonCaractere(boolean estJ1) {
        return estJ1 ? "X" : "O";
    }

    private boolean retrouverBoolDepuisNomJoueur(String nomJoueur){
        return this.nomJoueurUn.equals(nomJoueur);
    }

    public boolean wins(String nomJoueur, int ligne, int colonne, int deltaLigne, int deltaColonne) {
        //System.out.println("estj1 : " + estJ1 + "\nligne : " + ligne + "\ncolonne : " + colonne + "\ndLigne :" + deltaLigne + "\ndCol : " + deltaColonne);
        String bonPattern = bonCaractere(retrouverBoolDepuisNomJoueur(nomJoueur)) + bonCaractere(retrouverBoolDepuisNomJoueur(nomJoueur)) + bonCaractere(retrouverBoolDepuisNomJoueur(nomJoueur));
        //System.out.println(bonPattern);
        // (row, column), (row + deltaRow, column + deltaColumn), (row + 2 × deltaRow, column +
        //2 × deltaColumn)
        return bonPattern.equals(
                this.grille[ligne][colonne]
                + this.grille[ligne + deltaLigne][colonne + deltaColonne]
                + this.grille[ligne + (2 * deltaLigne)][colonne + (2 * deltaColonne)]
        );

    }

    /*
    private String getPlayerWithBool(boolean estJ1) {
        return estJ1 ? nomJoueurUn : nomJoueurDeux;
    }
    */

    public String getWinner() {
        String[] tabJCourant = {nomJoueurUn, nomJoueurDeux};
        for (String joueur:  tabJCourant) {
            // les8 combos possibles pour win
            boolean victoire = wins(joueur, 0, 0, 0, 1)
                    || wins(joueur, 1, 0, 0, 1)
                    || wins(joueur, 2, 0, 0, 1)
                    || wins(joueur, 0, 0, 1, 0)
                    || wins(joueur, 0, 1, 1, 0)
                    || wins(joueur, 0, 2, 1, 0)
                    || wins(joueur, 0, 0, 1, 1)
                    || wins(joueur, 0, 2, 1, -1);

            if (victoire) {
                return joueur;
            }
        }
        /*
        for (boolean joueur : tabJCourant) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int deltaL = -1; deltaL <= 1; deltaL++) {
                        for (int deltaC = -1; deltaC <= 1; deltaC++) {
                            if (wins(joueur, i, j, deltaL, deltaC)) {
                                return getPlayerWithBool(joueur);
                            }
                        }
                    }
                }
            }
        }
         */
        return null;
    }

    public String situationToString() {

        String SEPARATION_HOR = " ";
        String situationString = "";
        situationString += SEPARATION_HOR + SEPARATION_HOR;

        // La ligne des nombres
        situationString += ("0" + SEPARATION_HOR + "1" + SEPARATION_HOR + "2");
        situationString += System.lineSeparator();

        // Les lignes du tableau
        int ligneCourante = 0;
        for (String[] ligne : this.grille) {
            situationString += String.valueOf(ligneCourante);
            situationString += SEPARATION_HOR;
            for (String elementCourant : ligne) {
                situationString += elementCourant == null ? "." : elementCourant;
                situationString += SEPARATION_HOR;
            }

            situationString += System.lineSeparator();
            ligneCourante++;
        }

        return situationString;

    }

    public boolean isOver() {
        if (getWinner() != null) {
            return true;
        }

        for (String[] ligne : this.grille) {
            for (String uneCase : ligne) {
                if (uneCase == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
