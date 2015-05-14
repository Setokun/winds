package core;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import display.Game;

public class InoffensiveBlock extends GameObject{

	public static enum blockType{
		SIMPLE_BLOCK, FLOOR_BLOCK,
		RONCES_HAUT_1, RONCES_HAUT_2, RONCES_HAUT_3, RONCES_HAUT_4, RONCES_HAUT_5;
	}
	
	Texture tex = Game.getInstance();
	private blockType type;
	
	public InoffensiveBlock(float x, float y, blockType type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	@Override
	public void tick(ArrayList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		/*g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 32, 32);*/
		
		if(type == InoffensiveBlock.blockType.SIMPLE_BLOCK) // dirt block
			g.drawImage(tex.lvl_lockjaw13[63], (int)x, (int)y, null);
		if(type == InoffensiveBlock.blockType.FLOOR_BLOCK) // grass block
			g.drawImage(tex.lvl_lockjaw13[62], (int)x, (int)y, null);
		
		if(type == InoffensiveBlock.blockType.RONCES_HAUT_1) g.drawImage(tex.lvl_ronces[67], (int)x, (int)y, null);
		if(type == InoffensiveBlock.blockType.RONCES_HAUT_2) g.drawImage(tex.lvl_ronces[68], (int)x, (int)y, null);
		if(type == InoffensiveBlock.blockType.RONCES_HAUT_3) g.drawImage(tex.lvl_ronces[69], (int)x, (int)y, null);
		if(type == InoffensiveBlock.blockType.RONCES_HAUT_4) g.drawImage(tex.lvl_ronces[59], (int)x, (int)y, null);
		if(type == InoffensiveBlock.blockType.RONCES_HAUT_5) g.drawImage(tex.lvl_ronces[60], (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	@Override
	public ArrayList<Rectangle> getBounds2() {
		// TODO Auto-generated method stub
		return null;
	}


}
