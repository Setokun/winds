package addon;

import annotation.wCard;

abstract class JarItem {
	protected Class<?> mainClass;
	
	abstract boolean isValid();
	
	//region Annotation Getters 
	public String wCardToString(){
		return "wCard {creator: \""+ getCreator()
					+"\", date: \""+ getDate()
					+"\", name: \""+ getName()
					+"\", description: \""+ getDescription() +"\"}";
	}
	public String getCreator(){
		return mainClass.getDeclaredAnnotation(wCard.class).creator();
	}
	public String getDate(){
		return mainClass.getDeclaredAnnotation(wCard.class).date();
	}
	public String getName(){
		return mainClass.getDeclaredAnnotation(wCard.class).name();
	}
	public String getDescription(){
		return mainClass.getDeclaredAnnotation(wCard.class).description();
	}
	//endregion
	
	//region Getter & Setter 
	public Class<?> getMainClass() {
		return mainClass;
	}
	public void setMainClass(Class<?> mainClass) {
		this.mainClass = mainClass;
	}
	//endregion
}
