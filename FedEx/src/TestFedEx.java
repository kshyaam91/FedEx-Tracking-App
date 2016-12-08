import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestFedEx {
public static double time = 0;
	public static Layout layout = new Layout();
	public static Packet pacs[] = new Packet[100000];
	public static int i = 0;
	public static boolean updatingPackets = false;
	public static Connection connection = null;
	public static void main(String[] args) {
		
		
		ConnectDataBase();
		
		
		Query query = new Query();
		int i = 0;
		for(i=0; i<100000; i++){
			pacs[i] = new Packet();
			pacs[i].ID = (int) i; 
		
		}	
		query.start ();
		while(true){
			for(i=5500; i<100000; i++){
				pacs[i].UpdatePacket();
				
				
				try {
					Statement s = connection.createStatement();
					s.execute("update  packdb set sCity= '"+layout.cityName(pacs[i].SCityID)+"',dCity='"+layout.cityName(pacs[i].DCityID)+"',presCity='"+layout.cityName(pacs[i].presCityID)+"', startDate='"+pacs[i].pathDate[0]+   "', endDate='"+ pacs[i].pathDate[pacs[i].path.size()-1]+"', status='" +pacs[i].status+"'"+"where ID='"+i+"'");
					s.close();
				System.out.println("Successfully updated Packet no:	"+i);
				
				s.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
				updatingPackets = true;
			}
			updatingPackets = false;
			time++;
					
		}
	}
	private static void ConnectDataBase() {
		try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println("Where is your MySQL JDBC Driver?");
	        e.printStackTrace();
	        return;
	    }

	    System.out.println("MySQL JDBC Driver Registered!");
	    

	    try {
	        connection = DriverManager
	        .getConnection("jdbc:mysql://localhost:3306/packetdata","dk47", "dk47");

	    } catch (SQLException e) {
	        System.out.println("Connection Failed! Check output console");
	        e.printStackTrace();
	        return;
	    }

	    if (connection != null)	System.out.println("You made it, take control your database now!");
	    else	System.out.println("Failed to make connection!");

		
	}
	public static String date_stamp(int time){
		int time_form = time/5;
		int date = (6 + time_form)%30;
		int month = ((time_form)/30) + 1;
		int year = 2015;
		String date_stamp = (month +"/" +date + "/" + year);
		return date_stamp;		
	}
}
