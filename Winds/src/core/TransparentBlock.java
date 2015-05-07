package core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import display.Game;

public class TransparentBlock extends GameObject{

	public static enum blockType{
		UPPER_SIMPLE_BLOCK,
		UPPER_LEFT_SINGLE_BLOCK,
		UPPER_RIGHT_SINGLE_BLOCK,
		LEFT_SIMPLE_BLOCK,
		LEFT_DOWN_SIMPLE_BLOCK,
		LEFT_UP_SIMPLE_BLOCK,
		LEFT_UP_CORNER_SIMPLE_BLOCK,
		UPPER_FLOOR_BLOCK,
		RONCES_HAUT_1, RONCES_HAUT_2, RONCES_HAUT_3, RONCES_HAUT_4, RONCES_HAUT_5;
	}
	
	Texture tex = Game.getInstance();
	private blockType type;
	
	public TransparentBlock(float x, float y, blockType type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		
		if(type == TransparentBlock.blockType.UPPER_SIMPLE_BLOCK) // dessus d'une caisse standard
			g.drawImage(tex.lvl_lockjaw13[55], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.LEFT_SIMPLE_BLOCK) // gauche d'un caisse standard
			g.drawImage(tex.lvl_lockjaw11[3], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.LEFT_DOWN_SIMPLE_BLOCK) // coin inférieur gauche d'une caisse standard
			g.drawImage(tex.lvl_lockjaw11[59], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.LEFT_UP_SIMPLE_BLOCK) // coin supérieur gauche qui fait la jonction entre un simple et un upper_simple
			g.drawImage(tex.lvl_lockjaw11[3], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.LEFT_UP_CORNER_SIMPLE_BLOCK) // coin supérieur gauche, jonction
			g.drawImage(tex.lvl_lockjaw13[1], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.UPPER_LEFT_SINGLE_BLOCK) // coin supérieur gauche, jonction
			g.drawImage(tex.lvl_lockjaw15[26], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.UPPER_RIGHT_SINGLE_BLOCK) // coin supérieur droit, jonction
			g.drawImage(tex.lvl_lockjaw15[27], (int)x, (int)y, null);
		
		if(type == TransparentBlock.blockType.UPPER_FLOOR_BLOCK) // grass block
			g.drawImage(tex.lvl_lockjaw13[53], (int)x, (int)y, null);
		
		
		if(type == TransparentBlock.blockType.RONCES_HAUT_1) g.drawImage(tex.lvl_ronces[67], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.RONCES_HAUT_2) g.drawImage(tex.lvl_ronces[68], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.RONCES_HAUT_3) g.drawImage(tex.lvl_ronces[69], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.RONCES_HAUT_4) g.drawImage(tex.lvl_ronces[59], (int)x, (int)y, null);
		if(type == TransparentBlock.blockType.RONCES_HAUT_5) g.drawImage(tex.lvl_ronces[60], (int)x, (int)y, null);

		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}


}
