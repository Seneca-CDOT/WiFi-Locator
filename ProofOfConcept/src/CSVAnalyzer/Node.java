package CSVAnalyzer;

import java.util.List;

/**
 * @author sammydamdam
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
	public String toString() {
		System.out.println(Nodenum);
		for (int i = 0;i<SSID.size();i++) {
			System.out.println(SSID.get(i) + MacAddrs.get(i) + Signal.get(i));
		}
		return "";
		
	
	}

}
