package addon;

import java.lang.reflect.Field;

import annotation.wCard;
import annotation.wLevel;

public class JarLevel extends JarItem {
	
	/*OK*/public boolean isValid(){
		wCard aCard = mainClass.getDeclaredAnnotation(wCard.class);
		wLevel aLevel = mainClass.getDeclaredAnnotation(wLevel.class);		
		
		return mainClass != null && aCard != null && aLevel != null;
	}
	
	//region Getters 
	public int[][] getMatrix(){
		try {
			Field f = mainClass.getDeclaredField("matrix");
			System.out.println(mainClass.getDeclaredFields().length);
			f.setAccessible(true);
			return (int[][]) f.get(null);
		} catch (NoSuchFieldException e)	 { e.printStackTrace();
		} catch (SecurityException e)		 { e.printStackTrace();
		} catch (IllegalArgumentException e) { e.printStackTrace();
		} catch (IllegalAccessException e)	 { e.printStackTrace(); }
		return null;
	}
	public int[][] getInteractions(){
		try {
			Field f = mainClass.getDeclaredField("interactions");
			f.setAccessible(true);
			return (int[][]) f.get(null);
		} catch (NoSuchFieldException e)	 { e.printStackTrace();
		} catch (SecurityException e)		 { e.printStackTrace();
		} catch (IllegalArgumentException e) { e.printStackTrace();
		} catch (IllegalAccessException e)	 { e.printStackTrace(); }
		return null;
	}
	public int getTimeMax(){
		try {
			Field f = mainClass.getDeclaredField("timeMax");
			f.setAccessible(true);
			return (int) f.get(null);
		} catch (NoSuchFieldException e)	 { e.printStackTrace();
		} catch (SecurityException e)		 { e.printStackTrace();
		} catch (IllegalArgumentException e) { e.printStackTrace();
		} catch (IllegalAccessException e)	 { e.printStackTrace(); }
		return 0;
	}
	//endregion
	
	//region Annotation Getters 
	public String wLevelToString(){
		return "wLevel {idDB: \""+ getIdDB()
					+"\", type: \""+ getType()
					+"\", mode: \""+ getMode()
					+"\", idTheme: \""+ getIdTheme()
					+"\", uploaded: \""+ getUploaded() +"\"}";
	}
	public int getIdDB(){
		return mainClass.getDeclaredAnnotation(wLevel.class).idDB();
	}
	public String getType(){
		return mainClass.getDeclaredAnnotation(wLevel.class).type();
	}
	public String getMode(){
		return mainClass.getDeclaredAnnotation(wLevel.class).mode();
	}
	public int getIdTheme(){
		return mainClass.getDeclaredAnnotation(wLevel.class).idTheme();
	}
	public boolean getUploaded(){
		return mainClass.getDeclaredAnnotation(wLevel.class).uploaded();
	}
	//endregion

}
