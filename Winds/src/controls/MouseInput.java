package controls;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import core.GameObject;
import core.ObjectId;
import core.Player;
import display.Game;
import display.Handler;


/**
 * Class used to catch the mouse click in game.
 */
public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	
	public MouseInput(Handler handler){
		this.handler = handler;
	}

	/**
	 * determines what action has to be done when clicking in game
	 */
	public void mousePressed(MouseEvent e){
		int key = e.getModifiers();
		int mouseX = e.getX();
		int mouseY = e.getY();
	
		
		if(Game.getPause()){
			if(key == InputEvent.BUTTON1_MASK){
				if(mouseX >= 122 && mouseX <= 302 && mouseY >= 169 && mouseY <= 209) Game.setPause();
				if(mouseX >= 47 && mouseX <= 377 && mouseY >= 235 && mouseY <= 275)  
					if(JOptionPane.showConfirmDialog(null, "Are you sure you want to go back to menu ?") == JOptionPane.YES_OPTION)
						Game.goBackToMenu();
				if(mouseX >= 148 && mouseX <= 398 && mouseY >= 307 && mouseY <= 347) 
					if(JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the game ?") == JOptionPane.YES_OPTION)
						System.exit(0);
			}
		}
		else{
			if(!Game.isFinished()){
				for(int i = 0; i<handler.objects.size(); i++){
					GameObject tempObject = handler.objects.get(i);

					if(tempObject.getId() == ObjectId.PLAYER){
						int playerX = (int) (tempObject.getX()+32 + Game.cam.getX() );
						int playerY = (int) (tempObject.getY()+32 + Game.cam.getY() );

						if(key == InputEvent.BUTTON1_MASK){
							Player.gravity = 0.03f;

							// distance calculation
							Point p1PLayer = new Point(playerX, playerY);
							Point p2Mouse = new Point(mouseX, mouseY);
							/////////////////////

							Game.score.setClicks(Game.score.getClicks()+1);

							float coefX = (float) (Math.abs(mouseX - playerX) / (distance(p1PLayer, p2Mouse)/1.5)) ;
							if(coefX > 1.0f) coefX = 1.0f;

							float coefY = (float) (Math.abs(mouseY - playerY) / (distance(p1PLayer, p2Mouse)/1.5)) ;
							if(coefY > 1.0f) coefY = 1.0f;

							if((playerX > mouseX) && (playerY > mouseY)){
								tempObject.setVelX(coefX);
								tempObject.setVelY(coefY);
							}
							if((playerX < mouseX) && (playerY > mouseY)){
								tempObject.setVelX(-coefX);
								tempObject.setVelY(coefY);
							}
							if((playerX > mouseX) && (playerY < mouseY)){
								tempObject.setVelX(coefX);
								tempObject.setVelY(-coefY);
							}
							if((playerX < mouseX) && (playerY < mouseY)){
								tempObject.setVelX(-coefX);
								tempObject.setVelY(-coefY);
							}
						}
					}
				}
			}
			else{
				if(Game.getDelayAfterFinished() > 3) Game.goBackToMenu();
			}
		}
		
	}
	/**
	 * determines the distance between two points
	 * @param p1 the first point
	 * @param p2 the second point
	 * @return double
	 */
	double distance(Point p1, Point p2){
		double dx,dy;
		dx=p2.x-p1.x;
		dy=p2.y-p1.y;
		return java.lang.Math.sqrt(dx*dx+dy*dy);
	}
	
}
