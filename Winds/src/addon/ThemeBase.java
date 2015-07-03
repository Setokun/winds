package addon;

import java.awt.Point;
import java.util.Map;

public abstract class ThemeBase {
	protected Map<Point, Integer[]> spriteCompatibility = initSpriteCompatibility();
	protected int[][] interactionsCompatibility = initInteractionsCompatibility();
	protected int[][][] collisionsList = initCollisionsList();
	protected String[] interactionTips = initInteractionTips();
	
	/**
	 * Initializes the compatibility between the sprites.
	 * @return Map<Point, Integer[]>
	 */
	abstract protected Map<Point, Integer[]> initSpriteCompatibility();
	/**
	 * Initializes the compatibility between the sprites and interactions.
	 * @return int[][]
	 */
	abstract protected int[][] initInteractionsCompatibility();
	/**
	 * Initializes the list of collisions.
	 * @return int[][][]
	 */
	abstract protected int[][][] initCollisionsList();
	/**
	 * Initializes the list of interactions' tooltip texts.
	 * @return String[]
	 */
	abstract protected String[] initInteractionTips();
	/**
	 * Runs the theme.
	 */
	abstract public void run();
	
}
