package addon.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;


public class LevelCreator {
	private String currentPath, sources;
	private File javaFile, classFile, jarFile;
	
	private String creator, date, description, name, mode, type;
	private int timeMax, idDB, idTheme;
	private int[][] matrix, interactions;
	private boolean uploaded;
	
	
	public LevelCreator(){
		currentPath = getClass().getResource("").getPath().replace("%20", " ");
		matrix = new int[0][0];
		interactions = new int[0][0];
	}
	public File generateJarFile(){
		buildSourceString();
		createJavaFile();
		compileJavaFile();
		createJarFile();
		return jarFile;
	}
	
	private void buildSourceString(){
		StringBuilder sb = new StringBuilder();
		sb.append( writeHeader() );
		sb.append( writeAnnotations() );
		sb.append("public class Level {\n" );
		sb.append( writeMatrix() );
		sb.append( writeInteractions() );
		sb.append( "static int timeMax = "+ timeMax +";\n" );
		sb.append( "}" );
		sources = sb.toString();
	}
	private String writeHeader(){
		return "package addon;\n"
			 + "import annotation.wCard;\n"
			 + "import annotation.wLevel;\n";
	}
	private String writeAnnotations(){
		return "@wCard(creator=\""+ creator +"\", date=\""+ date
				+"\", description=\""+ description +"\", name=\""+ name +"\")\n"
			 + "@wLevel(idDB="+ idDB +", idTheme="+ idTheme +", mode=\""+ mode
			 	+"\", type=\""+ type +"\", uploaded="+ (uploaded?"true":"false") +")\n";
	}
	private String writeMatrix(){
		StringBuilder sb = new StringBuilder("static int[][] matrix = {");
		
		for (int i=0; i<matrix.length; i++) {
			if(i > 0){ sb.append(","); }
			sb.append("{");
			
			int[] cells = matrix[i];
			for (int j=0; j<cells.length; j++) {
				if(j > 0){ sb.append(","); }
				sb.append(cells[j]);
			}
			sb.append("}");
		}
		sb.append("};\n");
		return sb.toString();
	}
	private String writeInteractions(){
		StringBuilder sb = new StringBuilder("static int[][] interactions = {");
		
		for (int i=0; i<interactions.length; i++) {
			if(i > 0){ sb.append(","); }
			sb.append("{");
			
			int[] cells = interactions[i];
			for (int j=0; j<cells.length; j++) {
				if(j > 0){ sb.append(","); }
				sb.append(cells[j]);
			}
			sb.append("}");
		}
		sb.append("};\n");
		return sb.toString();
	}

	private void createJavaFile(){
		javaFile = new File(currentPath, "Level.java");
		javaFile.deleteOnExit();
		try (FileOutputStream fop = new FileOutputStream(javaFile)) {
			fop.write(sources.getBytes());
			fop.flush();
			fop.close();
		} catch (IOException e) { e.printStackTrace(); }
		
	}
	private void compileJavaFile(){
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		int result = compiler.run(null, null, null, javaFile.getAbsolutePath());
		if(result == 0){
			// compilation succeed
			classFile = new File(currentPath, "Level.class");
			classFile.deleteOnExit();
		}else {
			// compilation failed
		}
		
		//boolean javaFileRemoved = javaFile.delete();
		//if( !javaFileRemoved ){}
	}
	private void createJarFile(){
		if(classFile != null){
			
			StringBuilder sb = new StringBuilder();
			try {
				BufferedReader br = new BufferedReader(new FileReader(classFile));
				String line;
				while((line = br.readLine()) != null){
					sb.append(line);
				}
				br.close();
			} catch (FileNotFoundException e) { e.printStackTrace();
			} catch (IOException e) { e.printStackTrace();
			}
			
			jarFile = new File(currentPath, name+".jar");
			jarFile.deleteOnExit();
			try {
				FileOutputStream fop = new FileOutputStream(jarFile);
				JarOutputStream jarOut = new JarOutputStream(fop);
				jarOut.putNextEntry(new ZipEntry("winds/"));
				jarOut.putNextEntry(new ZipEntry("winds/Level.class"));
				jarOut.write( sb.toString().getBytes() );
				jarOut.closeEntry();
				jarOut.close();
				fop.close();
			} catch (FileNotFoundException e) { e.printStackTrace();
			} catch (IOException e) { e.printStackTrace();
			}
	
			boolean classFileRemoved = classFile.delete();
			if( !classFileRemoved ){}
		}
	}
	
	//region Setters 
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTimeMax(int timeMax) {
		this.timeMax = timeMax;
	}
	public void setIdDB(int idDB) {
		this.idDB = idDB;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public void setInteractions(int[][] interactions) {
		this.interactions = interactions;
	}
	public void setUploaded(boolean uploaded) {
		this.uploaded = uploaded;
	}
	//endregion
	
}
