import java.util.List;

public class Node {
	int NodeNum;
	List<String> MacAddr;
	List<String> NodeStrength;
	
	public Node(int Nodenum, List<String> Mac, List<String> Strength)
	{
		NodeNum = Nodenum;
		MacAddr = Mac;
		NodeStrength = Strength;
		
	}

}
