/**
 * Created by maaj on 2015-11-02.
 */
public abstract class Pion {

    //False=noir et True=blanc
    boolean couleur;
    //Position du pion sur le plateau
    int x;
    int y;
    //Case caseActive;
    
    public Pion(boolean couleur, int x, int y){
    	this.couleur = couleur;
    	this.x = x;
    	this.y = y;
    }
    
}
