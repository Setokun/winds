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
		SIMPLE_BLOCK, FLOOR_BLOCK, PIRATE_CAISSE_1, PIRATE_CAISSE_2, PIRATE_CAISSE_3, PIRATE_CAISSE_4,
		ICE_BLOCK_1, ICE_BLOCK_2, ICE_BLOCK_3, ICE_BLOCK_4, ICE_BLOCK_5, ICE_BLOCK_6, ICE_BLOCK_7, ICE_BLOCK_8,
		
		// RONCES
		RONCES_ANGLE_HAUT_GAUCHE_1, RONCES_ANGLE_HAUT_GAUCHE_2, RONCES_ANGLE_HAUT_GAUCHE_3, RONCES_ANGLE_HAUT_GAUCHE_4, RONCES_ANGLE_HAUT_GAUCHE_5, RONCES_ANGLE_HAUT_GAUCHE_6,
		
		RONCES_HAUT_1, RONCES_HAUT_2, RONCES_HAUT_3, RONCES_HAUT_4, RONCES_HAUT_5, RONCES_HAUT_6, RONCES_HAUT_7, RONCES_HAUT_8,
		RONCES_HAUT_9, RONCES_HAUT_10, RONCES_HAUT_11, RONCES_HAUT_12, RONCES_HAUT_13, RONCES_HAUT_14, RONCES_HAUT_15, RONCES_HAUT_16,
		RONCES_HAUT_17, RONCES_HAUT_18, RONCES_HAUT_19, RONCES_HAUT_20,
		
		RONCES_COTE_1, RONCES_COTE_2, RONCES_COTE_3, RONCES_COTE_4, 
		RONCES_COTE_5, RONCES_COTE_6, RONCES_COTE_7, RONCES_COTE_8,
		RONCES_COTE_9, RONCES_COTE_10, RONCES_COTE_11, RONCES_COTE_12, 
		RONCES_COTE_13, RONCES_COTE_14, RONCES_COTE_15, RONCES_COTE_16,
		
		BRAMBLES_01, BRAMBLES_02, BRAMBLES_03, BRAMBLES_04, BRAMBLES_05, BRAMBLES_06, BRAMBLES_07, 
		BRAMBLES_08, BRAMBLES_09, BRAMBLES_10, BRAMBLES_11, BRAMBLES_12, BRAMBLES_13, BRAMBLES_14, 
		BRAMBLES_15, BRAMBLES_16, BRAMBLES_17, BRAMBLES_18, BRAMBLES_19, BRAMBLES_20, BRAMBLES_21;
	}
	
	Texture tex = Game.getInstance();
	private blockType type;
	private ArrayList<Rectangle> bounds;
	private ArrayList<CollisionBox> bounds3;
	
	public Block(float x, float y, blockType type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	/*public Block(float x, float y, blockType type, ObjectId id, ArrayList<int[]> collisions) {
		super(x, y, id);
		this.type = type;
		this.bounds = new ArrayList<Rectangle>();
		for (int i = 0; i < collisions.size(); i++) {
			int[] c = collisions.get(i);
			this.bounds.add(new Rectangle((int) (x + c[0]), (int) (y + c[1]), c[2], c[3]));
		}
		
	}*/
	
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
		/*g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 32);*/
		
		/*if(type == Block.blockType.SIMPLE_BLOCK)
			g.drawImage(tex.lvl_lockjaw13[63], (int)x, (int)y, null);
		if(type == Block.blockType.FLOOR_BLOCK)
			g.drawImage(tex.lvl_lockjaw13[62], (int)x, (int)y, null);
		if(type == Block.blockType.PIRATE_CAISSE_1) // caisse décorative part 1
			g.drawImage(tex.lvl_lockjaw11[20], (int)x, (int)y, null);
		if(type == Block.blockType.PIRATE_CAISSE_2) // caisse décorative part 2
			g.drawImage(tex.lvl_lockjaw11[21], (int)x, (int)y, null);
		if(type == Block.blockType.PIRATE_CAISSE_3) // caisse décorative part 3
			g.drawImage(tex.lvl_lockjaw11[28], (int)x, (int)y, null);
		if(type == Block.blockType.PIRATE_CAISSE_4) // caisse décorative part 4
			g.drawImage(tex.lvl_lockjaw11[29], (int)x, (int)y, null);
		
		if(type == Block.blockType.ICE_BLOCK_1) g.drawImage(tex.lvl_bib1[0], (int)x, (int)y, null);
		if(type == Block.blockType.ICE_BLOCK_2) g.drawImage(tex.lvl_bib1[1], (int)x, (int)y, null);
		if(type == Block.blockType.ICE_BLOCK_3) g.drawImage(tex.lvl_bib1[2], (int)x, (int)y, null);
		if(type == Block.blockType.ICE_BLOCK_4) g.drawImage(tex.lvl_bib1[3], (int)x, (int)y, null);
		if(type == Block.blockType.ICE_BLOCK_5) g.drawImage(tex.lvl_bib1[8], (int)x, (int)y, null);
		if(type == Block.blockType.ICE_BLOCK_6) g.drawImage(tex.lvl_bib1[9], (int)x, (int)y, null);
		if(type == Block.blockType.ICE_BLOCK_7) g.drawImage(tex.lvl_bib1[10], (int)x, (int)y, null);
		if(type == Block.blockType.ICE_BLOCK_8) g.drawImage(tex.lvl_bib1[11], (int)x, (int)y, null);*/
		
		
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
			for (int i = 0; i < getBounds3().size(); i++) {
				if(getBounds3().get(i).getId() == ObjectId.DangerousBlock)
					g2d.setColor(Color.red);
				if(getBounds3().get(i).getId() == ObjectId.Block)
					g2d.setColor(Color.green);
				g2d.draw(getBounds3().get(i).getBounds());
			}
			
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public ArrayList<Rectangle> getBounds2(){
		return this.bounds;
		/*ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();
		bounds.add(new Rectangle(0, 0, 128, 128));
		return bounds;*/
	}
	
	public ArrayList<CollisionBox> getBounds3(){
		return this.bounds3;
	}
	
}
