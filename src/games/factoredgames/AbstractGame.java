package games.factoredgames;

public abstract class AbstractGame {

    private String nomJoueurUn;
    private String nomJoueurDeux;
    protected  boolean joueurCourantEstJ1; // True si le coup suivant est celui du joueur 1, false sinon

    public AbstractGame(String nomJoueurUn, String nomJoueurDeux) {
        this.nomJoueurUn = nomJoueurUn;
        this.nomJoueurDeux = nomJoueurDeux;
        this.joueurCourantEstJ1 = true;
    }


    protected abstract void doExecute(int coup);

    public void execute(int coup){
        doExecute(coup);
        this.changerJoueurCourant();
    }

        /**
     * @return Le libellé du joueur courant ("Joueur 1" ou "Joueur 2")
     */
    public String getLibelleJoueurCourant() {
        return joueurCourantEstJ1 ? "Joueur 1" : "Joueur 2";
    }
    
    protected String nomJoueurParRapportAuBooleen(boolean estJ1){
        return estJ1 ? this.nomJoueurUn : this.nomJoueurDeux;
    }
    /**
     * @return Le joueur qui doit jouer le prochain coup
     */
    public String getCurrentPlayer() {
        return joueurCourantEstJ1 ? nomJoueurUn : nomJoueurDeux;
    }


    /**
     * Change le joueur actif pour l'autre joueur
     */
    protected  void changerJoueurCourant() {
        this.joueurCourantEstJ1 = !this.joueurCourantEstJ1;
    }
}
