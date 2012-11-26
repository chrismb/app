package app;

import java.sql.*;

public class DbUtils {
	
	private Statement statement;
	private Connection connection;
	

	public DbUtils() throws SQLException, Exception {
	    Class.forName("org.h2.Driver");
	    this.connection = DriverManager.getConnection("jdbc:h2:~/test", "christophermb", "jeanjean");

	}
	
	
	
	public Statement getStatement() throws SQLException {
		
		return connection.createStatement();
	}
	
	
	
	
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}





}
