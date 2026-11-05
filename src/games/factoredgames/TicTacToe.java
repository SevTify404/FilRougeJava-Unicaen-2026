package games.factoredgames;

public class TicTacToe extends AbstractGame {

    protected String[][] grille;
    protected String SEPARATEUR = " ";
    /**
     * Instancie un nouvel objet de type TicTacToe representant une partie
     * @param nomJoueurUn Le nom du premier joueur
     * @param nomJoueurDeux Le nom du deuxieme joueur
     */
    public TicTacToe(String nomJoueurUn, String nomJoueurDeux) {
        super(nomJoueurUn, nomJoueurDeux);
        this.grille = new String[3][3];
    }
    
    /**
     * Convertit les coordonnées d'une case (ligne, colonne) en un entier en base 3.
     * La formule utilisée est : entier = 3 × ligne + colonne.
     * @param ligne Le numéro de la ligne (0-2)
     * @param colonne Le numéro de la colonne (0-2)
     * @return L'entier en base 3 correspondant à la position (0-8)
     */
    public static int positionVersEntierBase3(int ligne, int colonne) {
        // Formule i = 3r + c de labase 3
        return (3 * ligne) + colonne;
    }

        /**
     * Récupère le numéro de colonne à partir d'un entier en base 3.
     * @param entierBase3 L'entier en base 3 (0-8)
     * @return Le numéro de colonne (0-2)
     */
    protected  int trouverColonneDepuisEntierBase3(int entierBase3) {
        return entierBase3 % 3;
    }

    /**
     * Récupère le numéro de ligne à partir d'un entier en base 3.
     * @param entierBase3 L'entier en base 3 (0-8)
     * @return Le numéro de ligne (0-2)
     */
    protected  int trouverLigneDepuisEntierBase3(int entierBase3) {
        return entierBase3 / 3;
    }

    /**
     * Execute un coup en plaçant le symbole du joueur courant sur la grille et change le joueur actif
     * @param entierBase3 L'entier en base 3 (0-8)
     */
    @Override 
    public void doExecute(int entierBase3) {
        //X pour le joueur 1 et O pour le joueur 2
        int ligne = trouverLigneDepuisEntierBase3(entierBase3);
        int colonne = trouverColonneDepuisEntierBase3(entierBase3);
        this.grille[ligne][colonne] = this.retrouverCaractereDeGrille(joueurCourantEstJ1);
    }

    /**
     * Vérifie si un coup est jouable par rapport à la situation courante du jeu
     * @param entierBase3 L'entier en base 3 (0-8)
     * @return Un booléen indiquant si le coup est valide ou pas, true si le coup est valide, false sinon
     */
    public boolean isValid(int entierBase3) {

        int ligne = trouverLigneDepuisEntierBase3(entierBase3);
        int colonne = trouverColonneDepuisEntierBase3(entierBase3);

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

    /**
     * Vérifie si un joueur a gagné en formant une ligne horizontale de 3 symboles alignés.
     * @param estJ1 True si le joueur à vérifier est le joueur 1, false sinon
     * @return True si le joueur a gagné avec une ligne horizontale, false sinon
     */
    protected boolean verifierLignesGagnantes(boolean estJ1) {
        return wins(estJ1, 0, 0, 0, 1)
                || wins(estJ1, 1, 0, 0, 1)
                || wins(estJ1, 2, 0, 0, 1);
    }

    /**
     * Vérifie si un joueur a gagné en formant une colonne verticale de 3 symboles alignés.
     * @param estJ1 True si le joueur à vérifier est le joueur 1, false sinon
     * @return True si le joueur a gagné avec une colonne verticale, false sinon
     */
    protected boolean verifierColonnesGagnantes(boolean estJ1) {
        return wins(estJ1, 0, 0, 1, 0)
                || wins(estJ1, 0, 1, 1, 0)
                || wins(estJ1, 0, 2, 1, 0);
    }

    /**
     * Vérifie si un joueur a gagné en formant une diagonale de 3 symboles alignés.
     * @param estJ1 True si le joueur à vérifier est le joueur 1, false sinon
     * @return True si le joueur a gagné avec une diagonale, false sinon
     */
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
                return nomJoueurParRapportAuBooleen(joueurBool);
            }
        }
        return null;
    }

    /**
     * Construit l'en-tête de la représentation textuelle de la situation du jeu,
     * contenant les numéros de colonnes.
     * @return La chaîne représentant l'en-tête de la situation
     */
    protected String dessinnerEnteteSituation() {
        String situationString = "";
        situationString += SEPARATEUR + SEPARATEUR;

        // La ligne des nombres
        situationString += ("0" + SEPARATEUR + "1" + SEPARATEUR + "2");
        situationString += System.lineSeparator();

        return situationString;
    }

    /**
     * Construit la représentation textuelle du tableau de jeu en ajoutant le contenu
     * de chaque case de la grille à la chaîne fournie.
     * Les cases vides sont représentées par ".".
     * @param situationString La chaîne de base contenant l'en-tête de la situation
     * @return La chaîne complète représentant le tableau de jeu
     */
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
