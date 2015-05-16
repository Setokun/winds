package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import display.Game;
import display.Window;

public class Block extends GameObject{

	public static enum blockType{
		BRAMBLES_01, BRAMBLES_02, BRAMBLES_03, BRAMBLES_04, BRAMBLES_05, BRAMBLES_06, BRAMBLES_07, 
		BRAMBLES_08, BRAMBLES_09, BRAMBLES_10, BRAMBLES_11, BRAMBLES_12, BRAMBLES_13, BRAMBLES_14, 
		BRAMBLES_15, BRAMBLES_16, BRAMBLES_17, BRAMBLES_18, BRAMBLES_19, BRAMBLES_20, BRAMBLES_21;
	}
	
	Texture tex = Game.getInstance();
	private blockType type;
	private ArrayList<Rectangle> bounds;
	private ArrayList<CollisionBox> bounds3;
	
	public Block(float x, float y, blockType type, ArrayList<CollisionBox> collisions) {
		super(x, y, null);
		this.type = type;
		bounds3 = new ArrayList<CollisionBox>();
		for (int i = 0; i < collisions.size(); i++) {
			this.bounds3.add(new CollisionBox((int) (x + collisions.get(i).getX()), 
											  (int) (y + collisions.get(i).getY()), 
											  (int) (collisions.get(i).getWidth()), 
											  (int) (collisions.get(i).getHeight()), 
											  collisions.get(i).getId()));
		}
		
	}
	
	@Override
	public void tick(ArrayList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
				
		if(type == Block.blockType.BRAMBLES_01) g.drawImage(tex.lvl_brambles[0], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_02) g.drawImage(tex.lvl_brambles[1], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_03) g.drawImage(tex.lvl_brambles[2], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_04) g.drawImage(tex.lvl_brambles[3], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_05) g.drawImage(tex.lvl_brambles[4], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_06) g.drawImage(tex.lvl_brambles[5], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_07) g.drawImage(tex.lvl_brambles[6], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_08) g.drawImage(tex.lvl_brambles[7], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_09) g.drawImage(tex.lvl_brambles[8], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_10) g.drawImage(tex.lvl_brambles[9], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_11) g.drawImage(tex.lvl_brambles[10], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_12) g.drawImage(tex.lvl_brambles[11], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_13) g.drawImage(tex.lvl_brambles[12], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_14) g.drawImage(tex.lvl_brambles[13], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_15) g.drawImage(tex.lvl_brambles[14], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_16) g.drawImage(tex.lvl_brambles[15], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_17) g.drawImage(tex.lvl_brambles[16], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_18) g.drawImage(tex.lvl_brambles[17], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_19) g.drawImage(tex.lvl_brambles[18], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_20) g.drawImage(tex.lvl_brambles[19], (int)x, (int)y, null);
		if(type == Block.blockType.BRAMBLES_21) g.drawImage(tex.lvl_brambles[20], (int)x, (int)y, null);
		
		if(Window.debug){
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


	public ArrayList<Rectangle> getBounds2(){
		return this.bounds;
	}
	
	public ArrayList<CollisionBox> getBounds(){
		return this.bounds3;
	}
	
}
