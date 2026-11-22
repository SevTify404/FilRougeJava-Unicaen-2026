package games.genericgames;

import games.players.Player;

/**
 * Classe abstraite implémentant l'interface Game et fournissant les fonctionnalités
 * communes à tous les jeux : gestion des joueurs, changement de joueur courant,
 * et lecture d'entrée au clavier.
 */
public abstract class AbstractGame implements Game{

    private final Player nomJoueurUn;
    private final Player nomJoueurDeux;
    protected  boolean joueurCourantEstJ1; // True si le coup suivant est celui du joueur 1, false sinon

    /**
     * Instancie un nouvel objet de type AbstractGame représentant une partie entre deux joueurs.
     * @param nomJoueurUn Le premier joueur
     * @param nomJoueurDeux Le deuxième joueur
     */
    public AbstractGame(Player nomJoueurUn, Player nomJoueurDeux) {
        this.nomJoueurUn = nomJoueurUn;
        this.nomJoueurDeux = nomJoueurDeux;
        this.joueurCourantEstJ1 = true;
    }


    /**
     * Implémente la logique spécifique d'exécution d'un coup pour un jeu donné.
     * Cette méthode est appelée par execute() après la validation du coup.
     * Elle ne doit PAS changer le joueur courant, car execute() s'en charge.
     * @param coup Le coup à exécuter
     */
    protected abstract void doExecute(int coup);

    /**
     * Exécute un coup valide en déléguant à doExecute() puis en changeant le joueur courant.
     * Si le coup n'est pas valide, rien ne se passe.
     * @param coup Le coup à exécuter
     */
    @Override 
    public void execute(int coup){
        if (isValid(coup)) {
            doExecute(coup);
            this.changerJoueurCourant();
        }
    }

        /**
     * @return Le libellé du joueur courant ("Joueur 1" ou "Joueur 2")
     */
    public String getLibelleJoueurCourant() {
        return joueurCourantEstJ1 ? "Joueur 1" : "Joueur 2";
    }
    

    /**
     * Retourne le joueur correspondant au booléen fourni.
     * @param estJ1 True pour le joueur 1, false pour le joueur 2
     * @return Le joueur correspondant
     */
    protected Player nomJoueurParRapportAuBooleen(boolean estJ1){
        return estJ1 ? this.nomJoueurUn : this.nomJoueurDeux;
    }

    /**
     * @return Le joueur qui doit jouer le prochain coup
     */
    @Override 
    public Player getCurrentPlayer() {
        return joueurCourantEstJ1 ? nomJoueurUn : nomJoueurDeux;
    }

    /**
     * Retourne le deuxième joueur de la partie.
     * @return Le joueur 2
     */
    public Player getJoueurDeux() {
        return nomJoueurDeux;
    }

    /**
     * Retourne le premier joueur de la partie.
     * @return Le joueur 1
     */
    public Player getJoueurUn() {
        return nomJoueurUn;
    }


    /**
     * Change le joueur actif pour l'autre joueur
     */
    protected void changerJoueurCourant() {
        this.joueurCourantEstJ1 = !this.joueurCourantEstJ1;
    }

}
