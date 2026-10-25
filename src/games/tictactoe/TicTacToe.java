package games.tictactoe;

public class TicTacToe {

    protected String[][] grille;
    private String nomJoueurUn;
    private String nomJoueurDeux;
    protected boolean joueurCourantEstJ1; // True si le coup suivant est celui du joueur 1, false sinon
    protected String SEPARATEUR = " ";
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
    protected void changerJoueurActif() {
        this.joueurCourantEstJ1 = !this.joueurCourantEstJ1;
//        System.out.println("Joueur actif changé, new value : " + this.joueurCourantEstJ1);
    }

    /**
     * @return Le joueur qui doit jouer le prochain coup
     */
    public String getCurrentPlayer() {
        return joueurCourantEstJ1 ? nomJoueurUn : nomJoueurDeux;
    }

    /**
     * @return Le libellé du joueur courant ("Joueur 1" ou "Joueur 2")
     */
    public String getLibelleJoueurCourant() {
        return joueurCourantEstJ1 ? "Joueur 1" : "Joueur 2";
    }


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
     * Retourne le pattern gagnant par rapport au joueur
     * @param estJ1 True si le joueur est le joueur 1, false sinon
     * @return Le pattern gagnant, XXX ou OOO selon le joueur
     */
    protected String retrouverPatternGagnant(boolean estJ1){
        return retrouverCaractereDeGrille(estJ1).repeat(3);
    }

    /**
     * Retourne le symbole correspondant au joueur
     * @param estJ1 True si le joueur est le joueur 1, false sinon
     * @return Le symbole "X" pour le joueur 1, "O" pour le joueur 2
     */
    protected String retrouverCaractereDeGrille(boolean estJ1) {
        return estJ1 ? "X" : "O";
    }

    /**
     * Vérifie si un joueur a gagné en formant une ligne de 3 symboles alignés
     * @param estJ1 Le joueur à vérifier
     * @param ligne La ligne de départ
     * @param colonne La colonne de départ
     * @param deltaLigne L'incrément pour les lignes
     * @param deltaColonne L'incrément pour les colonnes
     * @return True si le joueur a gagné avec cette combinaison, false sinon
     */
    public boolean wins(boolean estJ1, int ligne, int colonne, int deltaLigne, int deltaColonne) {

        // On crée une String avec l'allignement gagnant afin de comparer avec l'alignement calculé
        String patternGagnant = retrouverPatternGagnant(estJ1);

        // (row, column), (row + deltaRow, column + deltaColumn), (row + 2 × deltaRow, column +
        //2 × deltaColumn)
        String patternGrilleObtenu = this.grille[ligne][colonne]
                + this.grille[ligne + deltaLigne][colonne + deltaColonne]
                + this.grille[ligne + (2 * deltaLigne)][colonne + (2 * deltaColonne)];

        return patternGagnant.equals(patternGrilleObtenu);

    }

    protected boolean verifierLignesGagnantes(boolean estJ1) {
        return wins(estJ1, 0, 0, 0, 1)
                || wins(estJ1, 1, 0, 0, 1)
                || wins(estJ1, 2, 0, 0, 1);
    }

    protected boolean verifierColonnesGagnantes(boolean estJ1) {
        return wins(estJ1, 0, 0, 1, 0)
                || wins(estJ1, 0, 1, 1, 0)
                || wins(estJ1, 0, 2, 1, 0);
    }

    protected boolean verifierDiagonalesGagnantes(boolean estJ1) {
        return wins(estJ1, 0, 0, 1, 1)
                || wins(estJ1, 2, 0, -1, 1);
    }


    /**
     * Retourne le nom du gagnant si le jeu est terminé et que quelqu'un a gagné
     * @return Le nom du gagnant si le jeu est terminé et qu'il y'a un gagnant, sinon la méthode renvoie null
     */
    public String getWinner() {

        // Tableau pour representer les deux joueurs
        // True represente J1
        // False represente J2
        boolean[] tabJoueurs = {true, false};

        for (boolean joueurBool:  tabJoueurs) {

            // Les combos possibles pour win
            boolean victoire = verifierColonnesGagnantes(joueurBool) ||
                    verifierLignesGagnantes(joueurBool) ||
                    verifierDiagonalesGagnantes(joueurBool);

            if (victoire) {
                return joueurBool ? this.nomJoueurUn : this.nomJoueurDeux;
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

    protected String dessinnerEnteteSituation() {
        String situationString = "";
        situationString += SEPARATEUR + SEPARATEUR;

        // La ligne des nombres
        situationString += ("0" + SEPARATEUR + "1" + SEPARATEUR + "2");
        situationString += System.lineSeparator();

        return situationString;
    }

    protected String dessinerTableauSituation(String situationString) {
        int ligneCourante = 0;
        for (String[] ligne : this.grille) {
            situationString += String.valueOf(ligneCourante);
            situationString += SEPARATEUR;
            for (String elementCourant : ligne) {
                situationString += elementCourant == null ? "." : elementCourant;
                situationString += SEPARATEUR;
            }

            situationString += System.lineSeparator();
            ligneCourante++;
        }

        return situationString;
    }


    /**
     * @return La situation actuelle du jeu sous forme de chaîne de caractères
     */
    public String situationToString() {
        return dessinerTableauSituation(this.dessinnerEnteteSituation());
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
