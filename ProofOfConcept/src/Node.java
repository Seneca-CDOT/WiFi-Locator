import java.util.List;

public class Node implements Comparable<Node> {
	List<ESPDevice> esp;
	public double score;
	public int id;
	
	public Node (List <ESPDevice> esp) {
		this.esp = esp;
	}

	@Override
	public String toString() {
		return esp.toString();
	}

	@Override
	public int compareTo(Node anotherDevice)  {
		if (!(anotherDevice instanceof Node))
			throw new ClassCastException("A Node object expected.");
	  
		
		return this.score > anotherDevice.score ? 1 : -1;    
	}
	
	@Override
	public boolean equals(Object c) {
	    if((c instanceof Node) == false) {
	        return false;
	    }

	    Node that = (Node)c;
	    return this.id == that.id;
	}
}
