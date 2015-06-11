package addon;

import annotation.wTheme;

@wTheme(idDB = 1, name = "Pirate", creator = "admin", date = "2015-01-02", description = "Theme pirate")
public class Theme implements iTheme {
	private int[][][] spriteCompatibility;

	@Override
	public void run() {
		System.out.println("running");
	}
	
	
}
