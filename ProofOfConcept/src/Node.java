import java.util.List;

public class Node {
	int timeStamp;
	int NodeNum;
	List<String> MacAddr;
	List<String> NodeStrength;
	List<String> SSID;
	
	public Node(int Time, int Nodenum, List<String> Mac, List<String> Strength, List<String> ID)
	{
		timeStamp = Time;
		NodeNum = Nodenum;
		MacAddr = Mac;
		NodeStrength = Strength;
		SSID = ID;
	}
	

}
