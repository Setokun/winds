package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

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
		
		BRAMBLES_01, BRAMBLES_02, BRAMBLES_03, BRAMBLES_04, BRAMBLES_05, BRAMBLES_06, BRAMBLES_07, BRAMBLES_08, 
		BRAMBLES_09, BRAMBLES_10, BRAMBLES_11, BRAMBLES_12, BRAMBLES_13, BRAMBLES_14, BRAMBLES_15, BRAMBLES_16;
	}
	
	Texture tex = Game.getInstance();
	private blockType type;
	private ArrayList<Rectangle> bounds;
	
	public Block(float x, float y, blockType type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	public Block(float x, float y, blockType type, ObjectId id, ArrayList<int[]> collisions) {
		super(x, y, id);
		this.type = type;
		this.bounds = new ArrayList<Rectangle>();
		for (int i = 0; i < collisions.size(); i++) {
			int[] c = collisions.get(i);
			this.bounds.add(new Rectangle(c[0], c[1], c[2], c[3]));
		}
		
	}
	
	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		/*g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 32);*/
		
		if(type == Block.blockType.SIMPLE_BLOCK)
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
		if(type == Block.blockType.ICE_BLOCK_8) g.drawImage(tex.lvl_bib1[11], (int)x, (int)y, null);
		
		
		if(type == Block.blockType.RONCES_HAUT_1)  g.drawImage(tex.lvl_ronces[19], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_2)  g.drawImage(tex.lvl_ronces[43], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_3)  g.drawImage(tex.lvl_ronces[20], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_4)  g.drawImage(tex.lvl_ronces[44], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_5)  g.drawImage(tex.lvl_ronces[21], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_6)  g.drawImage(tex.lvl_ronces[45], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_7)  g.drawImage(tex.lvl_ronces[308], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_8)  g.drawImage(tex.lvl_ronces[332], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_9)  g.drawImage(tex.lvl_ronces[309], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_10) g.drawImage(tex.lvl_ronces[333], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_11) g.drawImage(tex.lvl_ronces[310], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_12) g.drawImage(tex.lvl_ronces[334], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_13) g.drawImage(tex.lvl_ronces[10], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_14) g.drawImage(tex.lvl_ronces[34], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_15) g.drawImage(tex.lvl_ronces[11], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_16) g.drawImage(tex.lvl_ronces[35], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_17) g.drawImage(tex.lvl_ronces[12], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_18) g.drawImage(tex.lvl_ronces[36], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_19) g.drawImage(tex.lvl_ronces[13], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_HAUT_20) g.drawImage(tex.lvl_ronces[37], (int)x, (int)y, null);
		
		if(type == Block.blockType.RONCES_COTE_1) g.drawImage(tex.lvl_ronces[210], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_2) g.drawImage(tex.lvl_ronces[211], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_3) g.drawImage(tex.lvl_ronces[234], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_4) g.drawImage(tex.lvl_ronces[235], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_5) g.drawImage(tex.lvl_ronces[258], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_6) g.drawImage(tex.lvl_ronces[259], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_7) g.drawImage(tex.lvl_ronces[282], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_8) g.drawImage(tex.lvl_ronces[283], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_9) g.drawImage(tex.lvl_ronces[306], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_10) g.drawImage(tex.lvl_ronces[307], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_11) g.drawImage(tex.lvl_ronces[330], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_12) g.drawImage(tex.lvl_ronces[331], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_13) g.drawImage(tex.lvl_ronces[354], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_14) g.drawImage(tex.lvl_ronces[355], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_15) g.drawImage(tex.lvl_ronces[378], (int)x, (int)y, null);
		if(type == Block.blockType.RONCES_COTE_16) g.drawImage(tex.lvl_ronces[379], (int)x, (int)y, null);
		
		if(type == Block.blockType.BRAMBLES_08) g.drawImage(tex.lvl_brambles[7], (int)x, (int)y, null);
		
		/*if(Window.debug){
			Graphics2D g2d = (Graphics2D) g;
			g.setColor(Color.red);
			for (int i = 0; i < getBounds2().size(); i++) {
				g2d.draw(getBounds2().get(i));
			}
			
		}*/
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public ArrayList<Rectangle> getBounds2(){
		return this.bounds;
	}
	
	
}
