package addon;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import annotation.*;


public class manager {
	static private String themesPath = "themes";

	public static void main(String[] args) {
		ArrayList<File> themes = getThemesFile();
		
		String themeName = "addontest";
		File theme = null;
		for(File f : themes){
			if(f.getName().contains(themeName + ".jar")){
				theme = f;
				break;
			}
		}
		if(theme != null){
			try {
				loadJAR(theme);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	private static ArrayList<File> getThemesFile(){
		File folder = new File(themesPath);
		File[] items = folder.listFiles();
		ArrayList<File> files = new ArrayList<File>();
		 
		for (int i = 0; i < items.length; i++){
		   if (items[i].isFile()) {
			   files.add(items[i]);
		   }
		}	
		return files;
	}
	
	public static void loadJAR(File file) throws IOException  {
		JarFile jar = new JarFile(file);
		Enumeration<JarEntry> entries = jar.entries();
		
		while(entries.hasMoreElements()){
			JarEntry entry = entries.nextElement();
			if(entry.getName().endsWith(".class")){
				String className = entry.getName().replace(".class", "").replace('/', '.');
				try {
					Class<?> aa = ClassLoader.getSystemClassLoader().loadClass(className);
System.out.println(aa.getName());
					for(Annotation a : aa.getDeclaredAnnotations()){
						if(a instanceof wCard){
System.out.println("wCard");
System.out.println( ((wCard) a).description() );
						}
						if(a instanceof wInter){
System.out.println("wInter");
						}
						if(a instanceof wMob){
System.out.println("wMob");
						}
						if(a instanceof wLevel){
System.out.println("wLevel");
						}
System.out.println(a);
					}
System.out.println("--------------------");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(className);
			}
		}
				
		
		jar.close();
	}

}
