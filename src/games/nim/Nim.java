package games.nim;

public class Nim {

    private int tailleInitialeDuTas;
    private int nombreMaxAlumetteRetirableEnUnCoup;
    private String nomJoueurUn;
    private String nomJoueurDeux;
    private int nombreCourantAlumettes;

    private boolean joueurCourantJ1; // True si le coup suivant est celui du joueur 1, false sinon


    /**
     * Instancie un nouvel objet de type Nim representant une partie
     * @param tailleInitialeDuTas Le nombre d'allumettes initial pour le jeu
     * @param nombreMaxAlumetteRetirableEnUnCoup Le nombre maximal d'allumettes retirable en un coup
     * @param nomJoueurUn Le nom du premier joueur
     * @param nomJoueurDeux Le nom du deuxieme joueur
     */
    public Nim(int tailleInitialeDuTas, int nombreMaxAlumetteRetirableEnUnCoup,
            String nomJoueurUn, String nomJoueurDeux
    ) {
        this.tailleInitialeDuTas = tailleInitialeDuTas;
        this.nombreMaxAlumetteRetirableEnUnCoup = nombreMaxAlumetteRetirableEnUnCoup;
        this.nomJoueurUn = nomJoueurUn;
        this.nomJoueurDeux = nomJoueurDeux;
        this.nombreCourantAlumettes = tailleInitialeDuTas;
        this.joueurCourantJ1 = true;
    }

    /**
     * @return Le nombre d'allumettes initial
     */
    public int getInitialNbMatches() {
        return this.tailleInitialeDuTas;
    }

    /**
     * @return Le libellé du joueur courant ("Joueur 1" ou "Joueur 2")
     */
    public String getLibelleJoueur() {
        return joueurCourantJ1 ? "Joueur 1" : "Joueur 2";
    }

    /**
     * @return Le nombre d'allumettes restant actuel dans le jeu
     */
    public int getCurrentNbMatches() {
        return this.nombreCourantAlumettes;
    }


    /**
     * @return Le joueur qui doit jouer le prochain coup
     */
    public String getCurrentPlayer() {
        return joueurCourantJ1 ? nomJoueurUn : nomJoueurDeux;
    }


    /**
     * Change le joueur actif pour l'autre joueur
     */
    private void changerJoueurCourant() {
        this.joueurCourantJ1 = !this.joueurCourantJ1;
    }

    /**
     * @return La situation actuelle du jeu
     */
    public String situationToString() {
        return "Il reste : " + getCurrentNbMatches() + " allumetes" + System.lineSeparator() + "Le joueur suivant est : " + getCurrentPlayer();
    }

    /**
     * Retire les allumettes en un coup et change le joueur actif
     * @param nombreAllumettesARetirer Le nombre d'allumettes à retirer dans le coup
     */
    public void removeMatches(int nombreAllumettesARetirer) {

        if (isValid(nombreAllumettesARetirer)) {
            this.nombreCourantAlumettes = this.nombreCourantAlumettes - nombreAllumettesARetirer;
            this.changerJoueurCourant();
        }


    }


    /**
     * Vérifie si un coup est jouable par rapport à la situation courante du jeu
     * @param nombreAllumettesARetirer Le nombre d'allumettes à retirer dans le coup
     * @return Un booléen indiquant si le coup est valide ou pas, true si le coup est valide, false sinon
     */
    public boolean isValid(int nombreAllumettesARetirer) {
        return !(nombreAllumettesARetirer <= 0
                || nombreAllumettesARetirer > this.nombreMaxAlumetteRetirableEnUnCoup
                || nombreAllumettesARetirer > this.getCurrentNbMatches());
    }


    /**
     * Vérifie si le jeu est terminé ou pas
     * @return Un booléen indiquant si le jeu est terminé, true si le jeu est terminé, false sinon
     */
    public boolean isOver() {
        return this.getCurrentNbMatches() == 0;
    }


    /**
     * Retourne le nom du gagnant si le jeu est terminé et que quelqu'un a gagné
     * @return Le nom du gagnant si le jeu est terminé et qu'il y'a un gagnant, sinon la méthode renvoie null
     */
    public String getWinner() {
        if (!this.isOver()) {
            return null;
        }

        // Si le dernier coup a retirer la derniere allumette alors celui qui l'a fait est le perdant
        //Vu que la méthode removeMatches se charge de changer le jouer courant apres chaque coup, donc le joueur courant actuel
        // devrait etre celui qui a gagné

        return this.getCurrentPlayer();
    }


}
