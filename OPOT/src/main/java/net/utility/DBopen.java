package net.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBopen {
	
	//마리아 DB 연결 메소드
	
	public Connection getConnection() {
		Connection con=null;

		try {
			
			String url     ="jdbc:mariadb://localhost:3306/shkim0922";
	        String user    ="shkim0922";
	        String password="seungstar7*";													
	        String driver  ="org.mariadb.jdbc.Driver"; 
	        Class.forName(driver);
			con =DriverManager.getConnection(url, user, password);		
						
		} catch (Exception e) {
			System.out.println("마리아DB연결실패: " + e);
		}//end
		
		return con;
		
	}//getConnection()
	

}//class end