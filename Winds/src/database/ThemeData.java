package database;

public class ThemeData {
	
	private int idTheme;
	private String name;
	
	public ThemeData(){
		
	}

	public int getIdTheme() {
		return idTheme;
	}
	public String getName() {
		return name;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "idTheme:"+idTheme+",name:"+name;
	}
	
}
