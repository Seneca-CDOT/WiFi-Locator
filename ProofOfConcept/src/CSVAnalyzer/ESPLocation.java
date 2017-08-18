package CSVAnalyzer;

import java.util.List;

/**
 * 
 * @author sammydamdam
 */
public class ESPLocation {
	long timestamp;
	String mac;
	List<String> SSID;
	List<String> MacAddrs;
	List<String> Signal;

	public ESPLocation(long time, String MACAddress, List<String> ssid, List<String> mac, List<String> signal) {
		timestamp = time;
		this.mac = MACAddress;
		this.SSID = ssid;
		this.MacAddrs = mac;
		this.Signal = signal;
	}
	public String toString() {
		System.out.println("Timestamp: " + timestamp);
		System.out.println(mac);
		for (int i = 0;i<SSID.size();i++) {
			System.out.println(SSID.get(i) + MacAddrs.get(i) + Signal.get(i));
		}
		return "";
		
	
	}

}
