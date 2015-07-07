package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import display.Game;
import display.Window;

public class Block extends GameObject{
	
	BufferedImage[] bi = Game.getInstance();
	private int numBlock;
	private ArrayList<CollisionBox> collisions;
	
	public Block(float x, float y, int numBlock, ArrayList<CollisionBox> collisions) {
		super(x, y, null);
		this.numBlock = numBlock;
		
		if(collisions != null){
			
			this.collisions = new ArrayList<CollisionBox>();
			for (int i = 0; i < collisions.size(); i++) {
				this.collisions.add(new CollisionBox((int) (x + collisions.get(i).getX()), 
												  (int) (y + collisions.get(i).getY()), 
												  (int) (collisions.get(i).getWidth()), 
												  (int) (collisions.get(i).getHeight()), 
												  collisions.get(i).getId()));
			}
		}
		
	}
	
	@Override
	public void tick(ArrayList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		
		if(this.numBlock != 0){
			g.drawImage(bi[numBlock], (int)x, (int)y, null);
		}
		
		
		
		if(Window.debug){
			if(this.getBounds() != null){
				Graphics2D g2d = (Graphics2D) g;
				g.setColor(Color.red);
				for (int i = 0; i < getBounds().size(); i++) {
					if(getBounds().get(i).getId() == ObjectId.DangerousBlock)
						g2d.setColor(Color.red);
					if(getBounds().get(i).getId() == ObjectId.Block)
						g2d.setColor(Color.green);
					g2d.draw(getBounds().get(i).getBounds());
				}
			}
		}
	}

	public ArrayList<CollisionBox> getBounds(){
		return this.collisions;
	}
	
}
