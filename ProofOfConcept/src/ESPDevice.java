import java.util.List;

public abstract class ESPDevice {
	
	List<String> MacAddrs;
	List<String> NodeSignal;
	List<String> SSID;
	
	public ESPDevice( List<String> Mac, List<String> Strength, List<String> ID) {
		MacAddrs = Mac;
		NodeSignal = Strength;
		SSID = ID;
	}
	 
	@Override
	public String toString() {
		return "Mac Address: " + MacAddrs + "\n"
			  +"NodeSignal : " + NodeSignal + "\n"
		      +"      SSID : " + SSID + "\n";
	}

}