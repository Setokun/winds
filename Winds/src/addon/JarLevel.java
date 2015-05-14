package addon;

import annotation.wCard;
import annotation.wLevel;

public class JarLevel extends JarItem {
	
	public boolean isValid(){
		wCard aCard = mainClass.getDeclaredAnnotation(wCard.class);
		wLevel aLevel = mainClass.getDeclaredAnnotation(wLevel.class);		
		
		return mainClass != null && aCard != null && aLevel != null;
	}
	
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
