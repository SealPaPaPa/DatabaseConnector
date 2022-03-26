import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseConnector {
	public static void main (String[] args) {
		if(args.length != 5){
			System.out.println("java -jar DatabaseConnector.jar [driver] [url] [user] [password] [sql]");
			return;
		}
		
		String driver = args[0];
		String url = args[1];
		String username = args[2];
		String password = args[3];
		String SQL = args[4];
		String filename = "output.txt";
	
		System.out.println(driver);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		System.out.println(SQL);
	
        BufferedWriter bw = null;
        FileWriter fw = null;
		
        try {
            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.out.println("Where is your JDBC Driver?");
				e.printStackTrace();
				return;
			}
			System.out.println("JDBC Driver Registered!");
			
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(url, username, password);
				if (connection != null) System.out.println("You made it, take control your database now!");
				else{
					System.out.println("Failed to make connection!");
					return;
				}
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;
			}
			try {
				System.out.println("SQL : " + SQL);
				Statement stmt=connection.createStatement();
				ResultSet rs=stmt.executeQuery(SQL);
				ResultSetMetaData rsmd = rs.getMetaData();
				int column_count = rsmd.getColumnCount();
				for(int n=1;n<=column_count;n++){
				 	System.out.print(rsmd.getColumnName(n)+"\t");
					bw.write(rsmd.getColumnName(n)+"\t");
				}
				System.out.print("\n");
				bw.write("\n");
				
				while(rs.next()) {
					for(int n=1;n<=column_count;n++){
						System.out.print(rs.getString(n)+"\t");
						bw.write(rs.getString(n)+"\t");
					}
					System.out.print("\n");
					bw.write("\n");
				}	
			} catch (Exception e){
				System.out.println(e);
			}
			
            System.out.println("Finish writing file.");
        }
        catch (IOException e) {
                e.printStackTrace();
        }
        finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return;
    }
}
