package addon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import display.Window;


/**
 * Class which represents a level archive.
 */
public class JarLevel {
	private static final String fileSourceName = "level.src";
	private File jar;
	private Level lvl;

	
	//region Constructors 
	/**
	 * Create a new level archive with the specified level.
	 * @param lvl the level to insert in the archive
	 */
	/*OK*/public JarLevel(Level lvl){
		this.lvl = lvl;
	}
	/**
	 * Create a new level archive from the specified level archive.
	 * @param jar the archive which contains the data to pick up
	 */
	/*OK*/public JarLevel(File jarFile){
		this.jar = jarFile;
		String jsonDecoded = encodeJson(getLevelContent(), false);
		this.lvl = new Gson().fromJson(jsonDecoded, Level.class);
	}
	//endregion
	
	
	//region Public methods 
	/**
	 * Saves the current level archive and returns its success statement.
	 * @return boolean
	 */
	/*OK*/public boolean save(){
		createFile();
		return writeFile();
	}
	/**
	 * Checks if the current level archive is valid.
	 * @return boolean
	 */
	/*OK*/public boolean isValid(){
		return jar != null && jar.exists() && lvl != null;
	}
	/**
	 * Checks if the specified argument is equals to the current level.
	 * @return boolean
	 */
	/*OK*/public boolean equals(Object obj) {
		if( !(obj instanceof JarLevel) )  return false;
		
		JarLevel jl = (JarLevel) obj;
		return jar.getAbsolutePath().equals(jl.jar.getAbsolutePath());
	}
	/**
	 * Get the string representation of the current level archive.
	 * @return String
	 */
	/*OK*/public String toString(){
		return "JarLevel {jar: \""+ (jar==null ? "null" : jar.toURI()) +"\", lvl: \""+ lvl.toString() +"\"}";
	}
	//endregion
	
	
	//region Private methods 
	/**
	 * Creates a new file for this level archive if it doesn't exists.
	 */
	/*OK*/private void createFile(){
		if(jar != null && jar.exists())	 return;
		
		// first save - need to create the physical file
		String themeName = AddonManager.getJarThemeByID( lvl.getIdTheme() ).getName();
		int i = 0;
		String name;
		
		do {
			i++;
			name = themeName +"_"+ i +".jar";
			jar = new File(AddonManager.getLevelsPath(), name);
		} while( jar.exists() );
	}
	/**
	 * Writes the data in the level archive and returns its success statement.
	 * @return boolean
	 */
	/*OK*/private boolean writeFile(){
		String json = encodeJson(lvl.toJson(), true);
		String writable = canWriteFile(json);
		
		if( !writable.equals("OK") ){
			JOptionPane.showMessageDialog(Window.getFrame(),
				"Unable to save this level :\n"+ writable,
				"Saving level failed", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		
		try {
			FileOutputStream fileOut = new FileOutputStream(jar);
			JarOutputStream jarOut = new JarOutputStream(fileOut);
			jarOut.putNextEntry( new ZipEntry(fileSourceName) );
			jarOut.write( json.getBytes() );
			jarOut.closeEntry();
			jarOut.close();
			JOptionPane.showMessageDialog(Window.getFrame(), "Level saved",
				"Saved", JOptionPane.INFORMATION_MESSAGE);
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Window.getFrame(),
				"Unable to save this level :\n"+ e.getMessage(),
				"Saving level failed", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	/**
	 * Get the encoding/decoding string from the specified text.
	 * @param text the text to encode/decode
	 * @param encoding true to encode, false to decode
	 * @return String
	 */
	private String encodeJson(String text, boolean encoding){
		/*int offset = encoding ? -1 : 1;
		char[] cs = text.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<cs.length; i++) {
			int code = ((int) cs[i]) + offset;
			sb.append((char) code);
		}*/
		return text; //sb.toString(); le temps du dev
	}
	/**
	 * Checks if the current level archive is writable.
	 * @param json the json-formatted data to write in
	 * @return String
	 */
	/*OK*/private String canWriteFile(String json){
		File levelsFolder = new File(AddonManager.getLevelsPath());
		
		if( !levelsFolder.canWrite() )
			return "No writing access rights onto levels folder";
		if(json.length() >= (int) levelsFolder.getFreeSpace())
			return "Not enough free space into HDD";
		
		return "OK";
	}
	/**
	 * Get the level data of the current level archive.
	 * @return String
	 */
	/*OK*/private String getLevelContent(){
		StringBuilder sb = new StringBuilder();
		InputStream is = null;
		int bit;
		
		try {
			JarFile jf = new JarFile(jar);
			is = jf.getInputStream( jf.getEntry(fileSourceName) );
			while ((bit = is.read()) != -1)
				sb.append((char) bit);
			jf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	//endregion
	
	
	//region Getters 
	/**
	 * Get the current level archive file.
	 * @return File
	 */
	public File getFile(){
		return jar;
	}
	/**
	 * Get the level representation of the current level archive.
	 * @return Level
	 */
	public Level getLevel(){
		return lvl;
	}
	//endregion
	
}
