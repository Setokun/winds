package addon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import display.Window;


public class JarLevel {
	private static final String fileSourceName = "level.src";
	
	private static String levelResourcePath;
	private File jar;
	private Level lvl;
	
	static {
		String currentPath = JarLevel.class.getResource("").getPath().replace("%20", " ");
		levelResourcePath = currentPath.replace("addon/", "resources/levels/");
	}
	
	//region Constructors 
	/*OK*/public JarLevel(Level lvl){
		this.lvl = lvl;
	}
	/*OK*/public JarLevel(File jarFile){
		this.jar = jarFile;
		String jsonDecoded = encodeJson(getLevelContent(), false);
		this.lvl = new Gson().fromJson(jsonDecoded, Level.class);
	}
	//endregion
	
	//region Public methods 
	/*OK*/public JarLevel save(){
		createFile();
		
		boolean writable = canWriteFile();
		String message = writable ? "Level saved" : "Unavailable writing access rights on the levels folder";
		String title   = writable ? "Saving level succeeded" : "Saving level failed";
		int image	   = writable ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE;
		
		if(writable){ writeFile(); }
		JOptionPane.showMessageDialog(Window.getFrame(), message, title, image);
		return writable ? this : null;
	}
	public boolean isValid(){
		return jar != null && jar.exists() && lvl != null;
	}
	public String toString(){
		return "JarLevel {jar: \""+ (jar==null ? "null" : jar.toURI()) +"\", lvl: \""+ lvl.toString() +"\"}";
	}
	//endregion
	
	//region Private methods 
	private void createFile(){
		if(jar != null && jar.exists())	 return;
		
		// first save - need to create the file
		String themeName = AddonManager.getJarThemeByID( lvl.getIdTheme() ).getName();
		int i = 0;
		String name;
		
		do {
			i++;
			name = themeName +"_"+ i +".jar";
			jar = new File(levelResourcePath, name);
		} while( jar.exists() );
	}
	private boolean canWriteFile(){
		return false;
	}
	private void writeFile(){
		String jsonEncoded = encodeJson(lvl.toJson(), true);
		try {
			FileOutputStream fileOut = new FileOutputStream(jar);
			JarOutputStream jarOut = new JarOutputStream(fileOut);
			jarOut.putNextEntry( new ZipEntry(fileSourceName) );
			jarOut.write( jsonEncoded.getBytes() );
			jarOut.closeEntry();
			jarOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*to finish*/private String encodeJson(String text, boolean encoding){
		/*int offset = encoding ? -1 : 1;
		char[] cs = text.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<cs.length; i++) {
			int code = ((int) cs[i]) + offset;
			sb.append((char) code);
		}*/
		return text; //sb.toString(); le temps du dev
	}
	/*OK*/private String getLevelContent(){
		StringBuilder sb = new StringBuilder();
		InputStream is = null;
		int bit;
		
		try {
			URL levelURL = new URL("jar:" + jar.toURI().toURL() + "!/"+ fileSourceName);
			is = levelURL.openConnection().getInputStream();
			while ((bit = is.read()) != -1) {
				sb.append( (char) bit );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	//endregion
	
	//region Getters 
	public File getFile(){
		return jar;
	}
	public Level getLevel(){
		return lvl;
	}
	//endregion
	
}
