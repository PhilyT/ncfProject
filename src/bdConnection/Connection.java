package bdConnection;
import java.sql.*;

public class Connection {
	
	public void getPresenceEtudiant()
	{
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	 {
	  Class.forName("com.mysql.jdbc.Driver");
	  String url = "jdbc:mysql://localhost:3306/test";
	  Connection con = DriverManager.getConnection(url,"root","");
	  Statement stmt = con.createStatement();
	  
	  String requete = "";
	  ResultSet rs = stmt.executeQuery(requete);
	  while(rs.next())
	  {
	   String s = rs.getString("NOM_CAFE");
	   float n = rs.getFloat("PRIX");
	   System.out.println(s + " " + n);
	  }
	  
	  String updateString = "UPDATE CAFE" +
	    " SET VENTES = 75" +
	    " WHERE NOM_CAFE LIKE 'Colombian'";
	  stmt.executeUpdate(updateString);
	  
	  String requete2 = "SELECT NOM_CAFE, VENTES FROM CAFE" +
	    " WHERE NOM_CAFE LIKE 'Colombian'";
	  ResultSet rs2 = stmt.executeQuery(requete2);
	  while(rs2.next()){
	   String s = rs2.getString("NOM_CAFE");
	   int n = rs2.getInt("VENTES");
	   System.out.println(n + " livres de" + s + " vendu cette semaine.");}
	    


	        String updateString2 = "UPDATE CAFE" +
	        " SET TOTAL = TOTAL + 75 " +
	        "WHERE NOM_CAFE LIKE 'Colombian'";
	        stmt.executeUpdate(updateString2);
	        String query = "SELECT NOM_CAFE, TOTAL FROM CAFE" +
	        " WHERE NOM_CAFE LIKE 'Colombian'";
	        ResultSet rs3 = stmt.executeQuery(query);
	        while(rs3.next()){

	            String s = rs3.getString(1);
	            int n = rs3.getInt(2);
	            System.out.println(n+" livres de" + s + " vendu jusqu'à maintenant.");

	        }
	 }
}

