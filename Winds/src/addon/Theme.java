package addon;

import annotation.*;

@wCard(name = "Pirate", creator = "admin", date = "2015-01-02", description = "Theme pirate")
@wTheme(idDB = 1)
public class Theme implements iTheme {
	private int[][][] spriteCompatibility;

	@Override
	public void run() {
		System.out.println("running");
	}
	
	
}
