package addon;

import annotation.*;

@wCard(creator = "Player1", date = "2015/02/03", description = "Basic level", name = "Pirate 1")
@wLevel(idTheme = 1, type = "basic", idDB = 2, uploaded = false, mode = "standard")
public class Level {
	static private int[] matrix;
	static private int[] interactions;
	static private int timeMax;
}
