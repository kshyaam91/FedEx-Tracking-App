import java.util.Scanner;

public class Query extends Thread {
	public void run (){
		Scanner s = new Scanner(System.in);
		int input = 0;
		try{
		while(true){
			if(!TestFedEx.updatingPackets){
				System.out.println("Enter ID");
				input = s.nextInt();
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Tracking ID " + TestFedEx.pacs[input].ID + "\t\t\t\t\t" + "Today's Date:  " + TestFedEx.date_stamp((int)TestFedEx.time));
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("From           :  " + TestFedEx.layout.cityName(TestFedEx.pacs[input].SCityID) + "\t\t" + "Start Date : " + TestFedEx.pacs[input].pathDate[0]);
				System.out.println("Destination    :  " + TestFedEx.layout.cityName(TestFedEx.pacs[input].DCityID) + "\t\t" + "Estimated Date of Arrival : " + TestFedEx.pacs[input].pathDate[TestFedEx.pacs[input].path.size()-1]);
				System.out.print  ("Path           : ");
				for(int j =0; j<TestFedEx.pacs[input].path.size();j++){
					System.out.print("("+TestFedEx.layout.cityName((int)TestFedEx.pacs[input].path.get(j))+")");
					if((j<TestFedEx.pacs[input].path.size()-1)) System.out.print(" ---"+TestFedEx.pacs[input].pathDistances[j]+"--> ");
				}
				System.out.println("");
				System.out.println("Total Distance : " +TestFedEx.pacs[input].totalDistance );
				System.out.println("Present City   : " +TestFedEx.layout.cityName((int)TestFedEx.pacs[input].presCityID ));
				System.out.println("");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");	
				
				System.out.println("Status         : "+TestFedEx.pacs[input].status);
				for(int i=0;i<TestFedEx.pacs[input].path.size();i++){
					System.out.println("Received at "+ TestFedEx.layout.cityName((int)TestFedEx.pacs[input].path.get(i))+" on " +TestFedEx.date_stamp(TestFedEx.pacs[input].pathTimes[i])  );
					if((int)TestFedEx.pacs[input].path.get(i)==TestFedEx.pacs[input].presCityID) break;
				
				}
				System.out.println("");
				System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");	
				System.out.println("Shpiment Details");
				System.out.println("Weight of packet(in lbs)       :  " + TestFedEx.pacs[input].weight);
				System.out.println("Dimensions of packet(in inch.) :  " + TestFedEx.pacs[input].length +" X " +TestFedEx.pacs[input].breadth +" X " +TestFedEx.pacs[input].height);
				System.out.println("Number of deliverables         :  " + TestFedEx.pacs[input].num_pack);
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("\n\n\n\n\n\n");
			}
		}
		}catch(Exception e){
			System.out.println("Oops !! Incorrect Input, Try using numbers ");
			run ();
		}
		s.close();
	}

}
