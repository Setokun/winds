package addon;

import annotation.wCard;

abstract class JarItem {
	protected Class<?> mainClass;
	
	abstract boolean isValid();
	
	/*OK*/public boolean equals(Object o){
		if( !(o instanceof JarItem) ){ return false; }
		
		JarItem j = (JarItem) o;
		return mainClass.equals(j.mainClass) && getCreator().equals(j.getCreator())
			&& getDate().equals(j.getDate()) && getName().equals(j.getName())
			&& getDescription().equals(j.getDescription());
	}
	
	//region Annotation Getters 
	/*OK*/public String wCardToString(){
		return "wCard {creator: \""+ getCreator()
					+"\", date: \""+ getDate()
					+"\", name: \""+ getName()
					+"\", description: \""+ getDescription() +"\"}";
	}
	/*OK*/public String getCreator(){
		return mainClass.getDeclaredAnnotation(wCard.class).creator();
	}
	/*OK*/public String getDate(){
		return mainClass.getDeclaredAnnotation(wCard.class).date();
	}
	/*OK*/public String getName(){
		return mainClass.getDeclaredAnnotation(wCard.class).name();
	}
	/*OK*/public String getDescription(){
		return mainClass.getDeclaredAnnotation(wCard.class).description();
	}
	//endregion
	
	//region Getter & Setter 
	/*OK*/public Class<?> getMainClass() {
		return mainClass;
	}
	/*OK*/public void setMainClass(Class<?> mainClass) {
		this.mainClass = mainClass;
	}
	//endregion
}
