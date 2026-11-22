package games.tictactoe;

import java.util.ArrayList;

public class TicTacToeWithHints extends TicTacToe {

    private ArrayList<Integer> hints;

    /**
     * Instancie un nouvel objet de type TicTacToe representant une partie
     *
     * @param nomJoueurUn   Le nom du premier joueur
     * @param nomJoueurDeux Le nom du deuxieme joueur
     */
    public TicTacToeWithHints(String nomJoueurUn, String nomJoueurDeux) {
        super(nomJoueurUn, nomJoueurDeux);
    }

    /**
     * Met à jour le cache des indices avec la liste fournie.
     * @param hints La liste des indices à stocker
     */
    private void setHints(ArrayList<Integer> hints) {
        this.hints = hints;
    }

    /**
     * Retourne le cache des indices courants.
     * @return La liste des indices mis en cache, peut être null si non encore calculé
     */
    public ArrayList<Integer> getHints() {
        return hints;
    }

    /**
     * Vide le cache des indices en le mettant à null.
     * Utilisé lorsque la partie est terminée pour libérer la mémoire.
     */
    private void cleanHints() {
        hints = null;
    }

    /**
     * Vérifie si un coup donné (en base 3) est présent dans le cache des indices.
     * Gère le cas où le cache n'a pas encore été initialisé.
     * @param coupBase3 Le coup à vérifier, représenté en base 3 (0-8)
     * @return True si le coup est dans la liste des indices, false sinon
     */
    private boolean hintsContains(int coupBase3) {
        if (hints == null) return false;
        return hints.contains(coupBase3);
    }

    /**
     * Convertit les coordonnées d'une case (ligne, colonne) en un entier en base 3.
     * La formule utilisée est : entier = 3 × ligne + colonne.
     * @param ligne Le numéro de la ligne (0-2)
     * @param colonne Le numéro de la colonne (0-2)
     * @return L'entier en base 3 correspondant à la position (0-8)
     */
    private int positionVersEntierBase3(int ligne, int colonne) {
        // Formule i = 3r + c de labase 3
        return (3 * ligne) + colonne;
    }

    /**
     * Récupère le numéro de colonne à partir d'un entier en base 3.
     * @param entierBase3 L'entier en base 3 (0-8)
     * @return Le numéro de colonne (0-2)
     */
    private int trouverColonneDepuisEntierBase3(int entierBase3) {
        return entierBase3 % 3;
    }

    /**
     * Récupère le numéro de ligne à partir d'un entier en base 3.
     * @param entierBase3 L'entier en base 3 (0-8)
     * @return Le numéro de ligne (0-2)
     */
    private int trouverLigneDepuisEntierBase3(int entierBase3) {
        return entierBase3 / 3;
    }

    /**
     * Trouve toutes les cases vides de la grille et retourne leurs positions
     * sous forme d'entiers en base 3.
     * @return Une liste d'entiers représentant les positions des cases vides (0-8)
     */
    private ArrayList<Integer> trouverCasesVides() {

        ArrayList<Integer> listeCasesVides = new ArrayList<>();

        // On parcoure la grille et si une case est empty on l'ajoute à notre liste
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grille[i][j] == null) {
                    listeCasesVides.add(this.positionVersEntierBase3(i, j));
                }
            }
        }

        return listeCasesVides;
    }

    /**
     * Annule un coup en vidant la case spécifiée de la grille.
     * @param ligne Le numéro de la ligne de la case à vider
     * @param colonne Le numéro de la colonne de la case à vider
     */
    private void annulerCoup(int ligne, int colonne) {
        this.grille[ligne][colonne] = null;
    }

    /**
     * Vérifie si jouer sur une ligne donnée constitue un coup gagnant
     * pour le joueur courant.
     * @param ligne Le numéro de la ligne à vérifier (0-2)
     * @return True si cette ligne forme un alignement gagnant, false sinon
     */
    private boolean verifierLigneCoupGagnant(int ligne) {
        return wins(this.joueurCourantEstJ1, ligne, 0, 0,1);
    }

    /**
     * Vérifie si jouer sur une colonne donnée constitue un coup gagnant
     * pour le joueur courant.
     * @param colonne Le numéro de la colonne à vérifier (0-2)
     * @return True si cette colonne forme un alignement gagnant, false sinon
     */
    private boolean verifierColonneCoupGagnant(int colonne) {
        return wins(this.joueurCourantEstJ1, 0, colonne, 1, 0);
    }

    /**
     * Vérifie si un coup donné (ligne, colonne) est un coup gagnant pour le joueur courant.
     * La méthode simule le coup, vérifie s'il est gagnant, puis l'annule pour restaurer l'état.
     * @param ligne Le numéro de la ligne du coup à tester
     * @param colonne Le numéro de la colonne du coup à tester
     * @return True si le coup est gagnant, false sinon
     */
    private boolean coupGagnant(int ligne, int colonne) {

        // Jouer le coup
        this.mockExecute(ligne, colonne);

        // Voir si il est gagnant
        boolean coupGagnant = verifierColonneCoupGagnant(colonne) ||
                verifierLigneCoupGagnant(ligne) ||
                verifierDiagonalesGagnantes(this.joueurCourantEstJ1);

//        boolean coupGagnant = getWinner() != null;


        // Annuler le coup
        this.annulerCoup(ligne, colonne);

        return coupGagnant;
    }

    /**
     * Retourne la liste des coups gagnants disponibles pour l'adversaire.
     * Chaque coup est représenté par un entier en base 3 correspondant à une case de la grille.
     * Si plus de 6 cases sont vides, la méthode retourne une liste vide car aucun coup
     * gagnant n'est possible avant au moins 2 tours complets.
     * @return Une liste d'entiers représentant les positions des coups gagnants (0-8)
     */
    public ArrayList<Integer> hints() {

        ArrayList<Integer> hints = new ArrayList<>();

        // On recupere lees cases vides pour pouvoir simuler des coups
        ArrayList<Integer> listeCasesVides = this.trouverCasesVides();

        // Petite optimisation, si on a plus de 6 cases vides c'est impossible d'avoir un coup gagnant
        // Autrement dit il est possible de gagner apres minimum 2 coups de chaque joueur
        if (listeCasesVides.size() > 6) {
            return hints;       // On retourne le tableau vide
        }

        this.changerJoueurActif();      // Pour agir en tant que l'adversairz

        // Pour chaque case vide on simule un coup
        for (int caseBase3 : listeCasesVides) {
            boolean coupPotentiellementGagnant = this.coupGagnant(
                    this.trouverLigneDepuisEntierBase3(caseBase3),
                    this.trouverColonneDepuisEntierBase3(caseBase3)
            );

            // Si le coup est gagnant on l'ajoute au tableau
            if (coupPotentiellementGagnant) {
                hints.add(caseBase3);
            }

        }

        this.changerJoueurActif();      // Pour remettre en l'ordre

        return hints;
    }

    /**
     * Vérifie si le jeu est terminé.
     * Si la partie est terminée, le cache des indices est vidé.
     * @return True si le jeu est terminé (victoire ou match nul), false sinon
     */
    @Override
    public boolean isOver() {
        if (super.isOver()){
            cleanHints();
            return true;
        }
        return false;
    }

    /**
     * Exécute un coup en plaçant le symbole du joueur courant sur la grille,
     * change le joueur actif, puis met à jour le cache des indices.
     * @param ligne Le numéro de la ligne où placer le symbole (0-2)
     * @param colonne Le numéro de la colonne où placer le symbole (0-2)
     */
    @Override
    public void execute(int ligne, int colonne) {
        super.execute(ligne, colonne);
        setHints(hints());
    }

    /**
     * Simule un coup en plaçant uniquement le symbole du joueur courant sur la grille,
     * sans changer le joueur actif ni mettre à jour le cache.
     * Utilisé pour tester des coups potentiels sans effet de bord sur l'état du jeu.
     * @param ligne Le numéro de la ligne où placer le symbole (0-2)
     * @param colonne Le numéro de la colonne où placer le symbole (0-2)
     */
    private void mockExecute(int ligne, int colonne) {
        this.grille[ligne][colonne] = this.retrouverCaractereDeGrille(joueurCourantEstJ1);
    }


    /**
     * Construit la représentation textuelle du tableau de jeu avec les indices.
     * Les cases vides correspondant à un coup gagnant sont marquées par "!"
     * au lieu de "." pour indiquer au joueur où il pourrait perdre.
     * @param situationString La chaîne de base contenant l'en-tête de la situation
     * @return La chaîne complète représentant le tableau de jeu avec les indices
     */
    @Override
    protected String dessinerTableauSituation(String situationString) {
        for (int l = 0; l < 3; l++) {
            situationString += String.valueOf(l);
            situationString += SEPARATEUR;
            for (int c = 0; c < 3; c++) {
                String elementCourant = this.grille[l][c];
                // Un peu brutal mais bon....
                situationString +=
                        elementCourant == null ?
                        this.hintsContains(this.positionVersEntierBase3(l, c)) ?
                                "!" : "."
                        :
                        elementCourant;
                situationString += SEPARATEUR;
            }

            situationString += System.lineSeparator();
        }

        return situationString;
    }
}
