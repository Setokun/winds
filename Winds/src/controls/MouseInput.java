package controls;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import core.GameObject;
import core.ObjectId;
import core.Player;
import display.Game;
import display.Handler;

public class MouseInput extends MouseAdapter{
	
	Handler handler;
	
	public MouseInput(Handler handler){
		this.handler = handler;
	}
	
	

	public void mousePressed(MouseEvent e){
		int key = e.getModifiers();
		int mouseX = e.getX();
		int mouseY = e.getY();
	
		
		if(Game.getPause()){
			if(key == InputEvent.BUTTON1_MASK){
				//System.out.println(mouseX + " " + mouseY);
				if(mouseX >= 297 && mouseX <= 477 && mouseY >= 185 && mouseY <= 225){
					Game.setPause();
				}
				if(mouseX >= 217 && mouseX <= 547 && mouseY >= 265 && mouseY <= 305){
					Game.reafficherMenu();
				}
				if(mouseX >= 260 && mouseX <= 510 && mouseY >= 348 && mouseY <= 388){
					System.exit(0);
				}
			}
		}
		else{
			for(int i = 0; i<handler.objects.size(); i++){
				GameObject tempObject = handler.objects.get(i);
				int playerX = (int) (tempObject.getX()+32 + Game.cam.getX() );
				int playerY = (int) (tempObject.getY()+32 + Game.cam.getY() );
				
				
				if(tempObject.getId() == ObjectId.Player){
					
					if(key == InputEvent.BUTTON1_MASK)
					{				
						//System.out.println("x: " + Math.abs(mouseX - playerX) + ", y: " + Math.abs(mouseY - playerY));
						/*float testX = (Math.abs(mouseX - playerX) != 0)? Math.abs(mouseX - playerX) : Math.abs(mouseX - playerX) + 0.001f;
						float coefX = 1.5f - (1 / testX);
						if(coefX > 3.0f) coefX = 3.0f;*/
						
						Player.gravity = 0.03f;
						
						// calcul de la distance
						Point p1PLayer = new Point(playerX, playerY);
						Point p2Mouse = new Point(mouseX, mouseY);
						//System.out.println(distance(p1PLayer, p2Mouse));
						/////////////////////
						
						
						float coefX = (float) (Math.abs(mouseX - playerX) / (distance(p1PLayer, p2Mouse)/1.5)) ;
						//float coefX = 0.5f + (float) (5f / Math.sqrt(Math.abs(mouseX - playerX))) ;
						if(coefX > 1.0f) coefX = 1.0f;
						
						/*float testY = (Math.abs(mouseY - playerY) != 0)? Math.abs(mouseY - playerY) : Math.abs(mouseY - playerY) + 0.001f;
						float coefY = 1.5f - (1 / testY);
						if(coefY > 3.0f) coefY = 3.0f;*/
						
						float coefY = (float) (Math.abs(mouseY - playerY) / (distance(p1PLayer, p2Mouse)/1.5)) ;
						//float coefY = 0.5f + (float) (5f / Math.sqrt(Math.abs(mouseY - playerY)));
						if(coefY > 1.0f) coefY = 1.0f;
						
						if((playerX > mouseX) && (playerY > mouseY)){
							/*tempObject.setFacingRight(true);
							tempObject.setFacingDown(true);*/
							tempObject.setVelX(coefX);
							tempObject.setVelY(coefY);
						}
						if((playerX < mouseX) && (playerY > mouseY)){
							/*tempObject.setFacingRight(false);
							tempObject.setFacingDown(true);*/
							tempObject.setVelX(-coefX);
							tempObject.setVelY(coefY);
						}
						if((playerX > mouseX) && (playerY < mouseY)){
							/*tempObject.setFacingRight(true);
							tempObject.setFacingDown(false);*/
							tempObject.setVelX(coefX);
							tempObject.setVelY(-coefY);
						}
						if((playerX < mouseX) && (playerY < mouseY)){
							/*tempObject.setFacingRight(false);
							tempObject.setFacingDown(false);*/
							tempObject.setVelX(-coefX);
							tempObject.setVelY(-coefY);
						}

//						System.out.println("souris : " + mouseX + ","+ mouseY);
//						System.out.println("bulle : " + tempObject.getX() + "," + tempObject.getY());
//						System.out.println("cam : " + Game.cam.getX() + "," + Game.cam.getY());
//						System.out.println("player : " + playerX + "," + playerY);
//						System.out.println("");
					}
				}
				
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e){
		//int key = e.getModifiers();

	}
	
	double distance(Point p1, Point p2)
	{
	double dx,dy;
	dx=p2.x-p1.x;
	dy=p2.y-p1.y;
	return java.lang.Math.sqrt(dx*dx+dy*dy);
	}
	
}
