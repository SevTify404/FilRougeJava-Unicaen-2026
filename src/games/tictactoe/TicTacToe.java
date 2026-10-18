package games.tictactoe;

public class TicTacToe {

    private String[][] grille = null;
    private String nomJoueurUn;
    private String nomJoueurDeux;
    private boolean joueurCourantEstJ1; // True si le coup suivant est celui du joueur 1, false sinon

    /**
     * Instancie un nouvel objet de type TicTacToe representant une partie
     * @param nomJoueurUn Le nom du premier joueur
     * @param nomJoueurDeux Le nom du deuxieme joueur
     */
    public TicTacToe(String nomJoueurUn, String nomJoueurDeux) {
        this.nomJoueurDeux = nomJoueurDeux;
        this.nomJoueurUn = nomJoueurUn;
        this.joueurCourantEstJ1 = true;
        this.grille = new String[3][3];
    }

    /**
     * Change le joueur actif pour l'autre joueur
     */
    private void changerJoueurActif() {
        this.joueurCourantEstJ1 = !this.joueurCourantEstJ1;
    }

    /**
     * @return Le joueur qui doit jouer le prochain coup
     */
    public String getCurrentPlayer() {
        return joueurCourantEstJ1 ? nomJoueurUn : nomJoueurDeux;
    }

    /*
    public void test() {
        System.out.println(situationToString());
    }
    */

    /**
     * Execute un coup en plaçant le symbole du joueur courant sur la grille et change le joueur actif
     * @param ligne Le numéro de la ligne où placer le symbole
     * @param colonne Le numéro de la colonne où placer le symbole
     */
    public void execute(int ligne, int colonne) {
        //X pour le joueur 1 et O pour le joueur 2
        this.grille[ligne][colonne] = this.retrouverCaractereDeGrille(joueurCourantEstJ1);
        this.changerJoueurActif();
    }

    /**
     * Vérifie si un coup est jouable par rapport à la situation courante du jeu
     * @param ligne Le numéro de la ligne où placer le symbole
     * @param colonne Le numéro de la colonne où placer le symbole
     * @return Un booléen indiquant si le coup est valide ou pas, true si le coup est valide, false sinon
     */
    public boolean isValid(int ligne, int colonne) {
        if (ligne >= 3 || ligne < 0) {
            return false;
        }
        if (colonne >= 3 || colonne < 0) {
            return false;
        }

        return this.grille[ligne][colonne] == null;
    }

    /**
     * Retourne le symbole correspondant au joueur
     * @param estJ1 True si le joueur est le joueur 1, false sinon
     * @return Le symbole "X" pour le joueur 1, "O" pour le joueur 2
     */
    private String retrouverCaractereDeGrille(boolean estJ1) {
        return estJ1 ? "X" : "O";
    }

    /**
     * Retourne un booléen indiquant si le joueur donné est le joueur 1
     * @param nomJoueur Le nom du joueur
     * @return True si le joueur est le joueur 1, false sinon
     */
    private boolean retrouverBoolDepuisNomJoueur(String nomJoueur){
        return this.nomJoueurUn.equals(nomJoueur);
    }

    /**
     * Vérifie si un joueur a gagné en formant une ligne de 3 symboles alignés
     * @param nomJoueur Le nom du joueur à vérifier
     * @param ligne La ligne de départ
     * @param colonne La colonne de départ
     * @param deltaLigne L'incrément pour les lignes
     * @param deltaColonne L'incrément pour les colonnes
     * @return True si le joueur a gagné avec cette combinaison, false sinon
     */
    public boolean wins(String nomJoueur, int ligne, int colonne, int deltaLigne, int deltaColonne) {

        // On crée une String avec l'allignement gagnant afin de comparer avec l'alignement calculé
        String patternGagnant = retrouverCaractereDeGrille(retrouverBoolDepuisNomJoueur(nomJoueur)).repeat(3);

        // (row, column), (row + deltaRow, column + deltaColumn), (row + 2 × deltaRow, column +
        //2 × deltaColumn)
        String patternGrilleObtenu = this.grille[ligne][colonne]
                + this.grille[ligne + deltaLigne][colonne + deltaColonne]
                + this.grille[ligne + (2 * deltaLigne)][colonne + (2 * deltaColonne)];

        return patternGagnant.equals(patternGrilleObtenu);

    }

    /**
     * Retourne le nom du gagnant si le jeu est terminé et que quelqu'un a gagné
     * @return Le nom du gagnant si le jeu est terminé et qu'il y'a un gagnant, sinon la méthode renvoie null
     */
    public String getWinner() {
        String[] tabJCourant = {this.nomJoueurUn, this.nomJoueurDeux};
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

    /**
     * @return La situation actuelle du jeu sous forme de chaîne de caractères
     */
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

    /**
     * Vérifie si le jeu est terminé ou pas
     * @return Un booléen indiquant si le jeu est terminé, true si le jeu est terminé, false sinon
     */
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
