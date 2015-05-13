package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.jdbc.*;
import java.util.Random;

public class DBClass{    
    public static void executeQuery(String sql) throws ClassNotFoundException, SQLException{       
    	  
    	try {
    		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
          //System.out.println("Driver ok !!!");
          Connection connexion = null;
          try{
        	  connexion = DriverManager.getConnection("jdbc:hsqldb:file:winds", "sa",  "");
          }
          catch(Exception e){
        	  e.printStackTrace();
          }
          
          Statement statement = connexion.createStatement() ;
          statement.executeQuery(sql);
          
          statement.executeQuery("SHUTDOWN");
          statement.close();
    }
    
    public static ResultSet requestQuery(String sql) throws ClassNotFoundException, SQLException{       
  	  
    	try {
    		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
          Connection connexion = null;
          try{
        	  connexion = DriverManager.getConnection("jdbc:hsqldb:file:winds", "sa",  "");
          }
          catch(Exception e){
        	  e.printStackTrace();
          }
          
          Statement statement = connexion.createStatement() ;
          ResultSet result = statement.executeQuery(sql);
          statement.executeQuery("SHUTDOWN");
          statement.close();
          
          return result;
    }
    
    public static void createStructures(){
    	try {
			DBClass.executeQuery("CREATE TABLE scores (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, idPlayer INT, idLevel INT, time INT, nbClicks INT, nbItems INT, rank INT)");
			DBClass.executeQuery("CREATE TABLE levels (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, name VARCHAR, description VARCHAR, creationDate DATETIME, timeMax INT, levelType VARCHAR, levelStatus VARCHAR, levelMode VARCHAR, idCreator INT, idTheme INT)");
			DBClass.executeQuery("CREATE TABLE themes (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, name VARCHAR, description VARCHAR, creationDate DATETIME, idCreator INT)");
			DBClass.executeQuery("CREATE TABLE users (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, email VARCHAR, password VARCHAR, pseudo VARCHAR, registrationDate VARCHAR, userType VARCHAR, current BOOLEAN, music INT, effects INT, resolution INT)");
			DBClass.executeQuery("CREATE TABLE trophies (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) PRIMARY KEY, description VARCHAR)");
			DBClass.executeQuery("CREATE TABLE trophies_achieved(idPlayer INT, idTrophy INT, achieved VARCHAR, PRIMARY KEY ( idPlayer, idTrophy ))");
			DBClass.executeQuery("CREATE TABLE resolutions (id INT GENERATED BY DEFAULT AS IDENTITY(START WITH 0) PRIMARY KEY, width INT, height INT)");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void createTestData(){
		Random rand = new Random();
		try {
			for (int i = 1; i <= 4; i++) {
				executeQuery("INSERT INTO users (email, password, pseudo, registrationDate, userType, current, music, effects, resolution) VALUES ('perso"+i+"@live.fr','pwd"+i+"','pseudo"+i+"','2015-05-0"+i+" 12:05:27','player',false, 5, 5, 1);");
			}
			executeQuery("INSERT INTO users (email, password, pseudo, registrationDate, userType, current, music, effects, resolution) VALUES ('admin@live.fr','admin','admin','2015-04-30 00:05:00','administrator',false, 5, 5, 1);");
			for (int i = 1; i <= 20; i++) {
				DBClass.executeQuery("INSERT INTO scores (idPlayer, idLevel, time, nbClicks, nbItems, rank) "
											   + "VALUES ('"+(rand.nextInt(5)+1)+"','"+(rand.nextInt(20)+1)+"','"+ (rand.nextInt(100)+37) +"','"+ (rand.nextInt(500)+100) +"','"+ (rand.nextInt(50)+1) +"',0);");
			}
			for (int i = 1; i <= 20; i++) {
				DBClass.executeQuery("INSERT INTO levels (name, description, creationDate, timeMax, levelType, levelStatus, levelMode, idCreator, idTheme) VALUES ("
																+ "'level"+i+"',"
																+ "'description"+i+"',"
																+ "'2015-04-"+((i<10)?"0"+i:i)+" 00:05:00',"
																+ "'"+ (rand.nextInt(100)+37) +"',"
																+ "'"+ ((i%2==0)?"basic":"custom") +"',"
																+ "'active',"
																+ "'"+ ((i%4==0)?"standard":"boss") +"',"
																+ "'"+ (rand.nextInt(5)+1) +"',"
																+ "'"+ (rand.nextInt(3)+1) +"');");
			}
			
			DBClass.executeQuery("INSERT INTO themes (name, description, creationDate, idCreator) VALUES ('honey','honey','2015-04-30 00:05:00', 5);");
			DBClass.executeQuery("INSERT INTO themes (name, description, creationDate, idCreator) VALUES ('brambles','brambles','2015-04-30 00:05:00', 5);");
			DBClass.executeQuery("INSERT INTO themes (name, description, creationDate, idCreator) VALUES ('pirate','pirate','2015-04-30 00:05:00' ,5);");
						
			DBClass.executeQuery("INSERT INTO trophies (description) VALUES ('Achieve the first level');");
			DBClass.executeQuery("INSERT INTO trophies (description) VALUES ('Achieve 10 levels');");
			DBClass.executeQuery("INSERT INTO trophies (description) VALUES ('Achieve a level without being touched');");

			DBClass.executeQuery("INSERT INTO trophies_achieved (idPlayer, idTrophy, achieved) VALUES (1, 1, 'ok');");
			DBClass.executeQuery("INSERT INTO trophies_achieved (idPlayer, idTrophy, achieved) VALUES (5, 2, 'ok');");
			
			DBClass.executeQuery("INSERT INTO resolutions (width, height) VALUES (640, 480);");
			DBClass.executeQuery("INSERT INTO resolutions (width, height) VALUES (800, 600);");
			DBClass.executeQuery("INSERT INTO resolutions (width, height) VALUES (1024, 600);");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void deleteTestData(){
    	try {
			DBClass.executeQuery("DROP TABLE scores;");
			DBClass.executeQuery("DROP TABLE levels;");
			DBClass.executeQuery("DROP TABLE themes;");
			DBClass.executeQuery("DROP TABLE users;");
			DBClass.executeQuery("DROP TABLE trophies;");
			DBClass.executeQuery("DROP TABLE trophies_achieved;");
			DBClass.executeQuery("DROP TABLE resolutions;");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
    
}