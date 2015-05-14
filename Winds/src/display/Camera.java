package display;

import core.GameObject;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}

	private int largeur_niveau = 60*128; // TODO récupérer les largeurs de niveaux depuis le thème
	private int hauteur_niveau = 60*128;
	
	public void tick(GameObject player){
		if(player.getX() >= Game.WIDTH/2 && player.getX() <= largeur_niveau - Game.WIDTH/2)
			x = -player.getX() + Game.WIDTH/2;
		if(player.getY() >= Game.HEIGHT/2 && player.getY() <= hauteur_niveau - Game.HEIGHT/2)
			y = -player.getY() + Game.HEIGHT/2;
	}
	

	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public int getLargeur_niveau() {
		return largeur_niveau;
	}
	public void setLargeur_niveau(int largeur_niveau) {
		this.largeur_niveau = largeur_niveau;
	}
	public int getHauteur_niveau() {
		return hauteur_niveau;
	}
	public void setHauteur_niveau(int hauteur_niveau) {
		this.hauteur_niveau = hauteur_niveau;
	}
}
