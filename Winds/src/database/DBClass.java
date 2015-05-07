package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.jdbc.*;

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
    
    
}
