/**
 * Created by maaj on 2015-11-02.
 */
public class Pousseur extends Pion {

	public Pousseur (boolean couleur, int x, int y){
        super(couleur, x, y);
    }

	public boolean getCouleur() {
		return couleur;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
