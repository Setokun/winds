package addon;

import java.awt.Point;
import java.util.Map;

public abstract class ThemeBase {
	protected Map<Point, Integer[]> spriteCompatibility = initSpriteCompatibility();
	protected int[][] interactionsCompatibility = initInteractionsCompatibility();
	protected int[][][] collisionsList = initCollisionsList();	
	protected String[] interactionTips = initInteractionTips();
	
	
	abstract protected Map<Point, Integer[]> initSpriteCompatibility();
	abstract protected int[][] initInteractionsCompatibility();
	abstract protected int[][][] initCollisionsList();
	abstract protected String[] initInteractionTips();
	abstract public void run();
	
}
