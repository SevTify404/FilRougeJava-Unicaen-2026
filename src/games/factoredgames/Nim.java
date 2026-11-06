package games.factoredgames;

public class Nim extends AbstractGame{

    private int tailleInitialeDuTas;
    private int nombreMaxAlumetteRetirableEnUnCoup;
    private int nombreCourantAlumettes;



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
        super(nomJoueurUn, nomJoueurDeux);
        this.tailleInitialeDuTas = tailleInitialeDuTas;
        this.nombreMaxAlumetteRetirableEnUnCoup = nombreMaxAlumetteRetirableEnUnCoup;
        this.nombreCourantAlumettes = tailleInitialeDuTas;
    }

    /**
     * @return Le nombre d'allumettes initial
     */
    public int getInitialNbMatches() {
        return this.tailleInitialeDuTas;
    }


    /**
     * @return Le nombre d'allumettes restant actuel dans le jeu
     */
    public int getCurrentNbMatches() {
        return this.nombreCourantAlumettes;
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
    @Override 
    public void doExecute(int nombreAllumettesARetirer) {

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
