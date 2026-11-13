package games.genericgames;

import java.util.InputMismatchException;
import java.util.Scanner;

import games.players.Player;

public abstract class AbstractGame implements Game{

    private Player nomJoueurUn;
    private Player nomJoueurDeux;
    protected  boolean joueurCourantEstJ1; // True si le coup suivant est celui du joueur 1, false sinon

    public AbstractGame(Player nomJoueurUn, Player nomJoueurDeux) {
        this.nomJoueurUn = nomJoueurUn;
        this.nomJoueurDeux = nomJoueurDeux;
        this.joueurCourantEstJ1 = true;
    }


    protected abstract void doExecute(int coup);

    @Override 
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

    public Player getJoueurDeux() {
        return nomJoueurDeux;
    }

    public Player getJoueurUn() {
        return nomJoueurUn;
    }


    /**
     * Change le joueur actif pour l'autre joueur
     */
    protected void changerJoueurCourant() {
        this.joueurCourantEstJ1 = !this.joueurCourantEstJ1;
    }


    /**
     * Récupère une valeur entière saisie au clavier en gérant les erreurs de saisie.
     * Redemande la saisie tant que l'utilisateur n'entre pas un nombre entier valide.
     * @param unScanner Le Scanner utilisé pour lire l'entrée standard
     * @return La valeur entière saisie par l'utilisateur
     */
    public static int recupererValeurEntierreAuClavier(Scanner unScanner) {
        int valeur = 0;
        boolean saisieValide = false;

        while (!saisieValide) {
            try {
                valeur = unScanner.nextInt();
                saisieValide = true;
            } catch (InputMismatchException e) {
                System.out.println("Erreur, il faut entrer un nombre entier valide.");
                unScanner.next(); // Consomme et ignore l'entrée incorrecte pour éviter une boucle infinie
            }
        }

        return valeur;
    }
}
