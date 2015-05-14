package addon;

import annotation.wCard;
import annotation.wTheme;

@wCard(name = "", creator = "", date = "", description = "")
@wTheme(idDB = 0)
public class Theme implements iTheme {
	private int[][][] spriteCompatibility;

	@Override
	public void run() {
		System.out.println("running");
	}
	
	
}
