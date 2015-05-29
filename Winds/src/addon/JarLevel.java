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
	/*OK*/public JarLevel(){}
	/*OK*/public JarLevel(File jarFile){
		jar = jarFile;
		String jsonDecoded = encodeJson(getLevelContent(), false);
		lvl = new Gson().fromJson(jsonDecoded, Level.class);
	}
	//endregion
	
	//region Public methods 
	/*to finish*/public File save(){
		//String filename =  AddonManager.getThemeByID( lvl.getIdTheme() ).getName() + ".jar";
		String filename = "Pirate.jar";
		jar = new File(levelResourcePath, filename);
		
		if( needCreateJar() ){
			String jsonEncoded = encodeJson(lvl.toJson(), true);
			try {
				FileOutputStream fileOut = new FileOutputStream(jar);
				JarOutputStream jarOut = new JarOutputStream(fileOut);
				jarOut.putNextEntry(new ZipEntry(fileSourceName));
				jarOut.write( jsonEncoded.getBytes() );
				jarOut.closeEntry();
				jarOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return jar;
		}
		return null;
	}
	public boolean isValid(){
		return jar != null && lvl != null;
	}
	//endregion
	
	//region Private methods 
	/*OK*/private boolean needCreateJar(){
		if( !jar.exists() ){ return true; }
			
		int response = JOptionPane.showConfirmDialog(null, "Do you want to overwrite this level in resources folder ?",
				"Level already exists", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if( response == JOptionPane.NO_OPTION) { return false; }
		
		jar.delete();
		return true;
	}
	/*OK*/private String encodeJson(String text, boolean encoding){
		int offset = encoding ? -1 : 1;
		char[] cs = text.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<cs.length; i++) {
			int code = ((int) cs[i]) + offset;
			sb.append((char) code);
		}
		return sb.toString();
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
	public File getJarLevel(){
		return jar;
	}
	public Level getLevel(){
		return lvl;
	}
	//endregion
	
}
