import java.util.ArrayList;
import java.util.List;

public class Packet {
	
	public int ID;
	public int SCityID;
	public int DCityID;
	@SuppressWarnings("rawtypes")
	public List path = new ArrayList();
	public int presCityID;
	public int[] pathDistances = new int[25];
	public int[] pathTimes = new int[25];
	public int totalDistance = 0;
	public int speed=35;
	public String status;
	public String[] pathDate = new String[25];
	public int weight;
	public int length;
	public int breadth;
	public int height;
	public int num_pack;
	public Packet(){
		
		pathTimes[0] = (int) (Math.random()*5)- ((int) (Math.random()*25));
		pathDate[0]= TestFedEx.date_stamp(pathTimes[0]);
		SCityID = (int) (Math.random()*25);
		DCityID = (int) (Math.random()*25);
		weight = (int) (Math.random()*1000);
		length = (int) (Math.random()*100);
		breadth = (int) (Math.random()*100);
		height = (int) (Math.random()*100);
		num_pack = (int) (Math.random()*3);
	
		while(DCityID==SCityID){
			DCityID = (int) (Math.random()*25);
		}
		path = TestFedEx.layout.getShortestPath(SCityID, DCityID);
		presCityID = SCityID;
		for(int i=0;i<path.size()-1;i++){
			pathDistances[i] = TestFedEx.layout.distance[(int)path.get(i)][(int)path.get(i+1)];
			pathTimes[i+1]=  pathTimes[i] + pathDistances[i]/speed;
			totalDistance += TestFedEx.layout.distance[(int)path.get(i)][(int)path.get(i+1)];
			pathDate[i+1] = TestFedEx.date_stamp(pathTimes[i+1]);
		}
	}

	int x=0;
	public void UpdatePacket() {
	
		if(pathTimes[path.size()-1]<(int)TestFedEx.time){
			status = "Packet Delivered";
			presCityID = (int) path.get((path.size()-1)); 
		}
		
		else {
			if(pathTimes[0]> (int) TestFedEx.time){
			status = ("Packet recived at: " + path.get(0));
			presCityID = (int) path.get(0);
			} else {
				status = "Packet in transit";	
				for (int c = 0;c<path.size();c++){
					if (pathTimes[c]> TestFedEx.time){
						break;
					}
				presCityID = (int)path.get(c);		
				}
			}
		}
		
		
		
	}
}