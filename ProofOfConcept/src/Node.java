import java.util.List;

/**
 * @author ethchil
 */
public class Node {
	int NodeNum;
	List<String> MacAddrs;
	List<String> NodeSignal;
	List<String> SSID;

	public Node(int Nodenum, List<String> Mac, List<String> Strength, List<String> ID) {
		NodeNum = Nodenum;
		MacAddrs = Mac;
		NodeSignal = Strength;
		SSID = ID;
	}
	
	@Override
	public String toString() {
		return "Node Number: " + NodeNum + "\n"
		      +"Mac Address: " + MacAddrs + "\n"
		      +"NodeSignal : " + NodeSignal + "\n"
		      +"      SSID : " + SSID + "\n";
	}
	

}
